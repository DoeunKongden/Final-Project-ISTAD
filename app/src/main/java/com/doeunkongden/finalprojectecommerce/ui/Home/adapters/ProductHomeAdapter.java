package com.doeunkongden.finalprojectecommerce.ui.Home.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doeunkongden.finalprojectecommerce.R;
import com.doeunkongden.finalprojectecommerce.data.model.api.ProductAttributes;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductData;
import com.doeunkongden.finalprojectecommerce.ui.Search.adapters.ProductSearchAdapter;

import java.util.List;

public class ProductHomeAdapter extends RecyclerView.Adapter<ProductHomeAdapter.ProductHomeViewHolder> {
    //List of All Product
    List<ProductData> productDatalist;
    Context context;

    public ProductHomeAdapter(List<ProductData> productDatalist) {
        this.productDatalist = productDatalist;
    }

    public void setProductDatalist(List<ProductData> productDatalist) {
        this.productDatalist = productDatalist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductHomeAdapter.ProductHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.child_recycler_item,parent,false);
        context = parent.getContext();
        return new ProductHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHomeAdapter.ProductHomeViewHolder holder, int position) {
        ProductAttributes productAttributes = productDatalist.get(position).getProductAttributes();
        holder.tv_title.setText(productAttributes.getTitle());
        holder.tv_price.setText(productAttributes.getPrice()+"$");
        if(productAttributes.getThumbnailResponse() != null){
            if(productAttributes.getThumbnailResponse().getThumbnailData() != null){
                if (productAttributes.getThumbnailResponse().getThumbnailData().getThumbnailAttributes() != null){
                    Glide.with(context).load("https://cms.istad.co" + productAttributes.getThumbnailResponse().getThumbnailData().getThumbnailAttributes().getUrl()).into(holder.iv_product_image);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return productDatalist.size();
    }

    public class ProductHomeViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title,tv_price;
        ImageView iv_product_image;
        public ProductHomeViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_price = itemView.findViewById(R.id.product_price);
            tv_title = itemView.findViewById(R.id.et_update_ttile);
            iv_product_image = itemView.findViewById(R.id.product_image);
        }
    }
}
