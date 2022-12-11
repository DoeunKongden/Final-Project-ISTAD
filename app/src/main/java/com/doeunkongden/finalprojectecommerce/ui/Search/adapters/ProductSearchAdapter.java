package com.doeunkongden.finalprojectecommerce.ui.Search.adapters;

import android.content.Context;
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

import java.util.List;

public class ProductSearchAdapter extends RecyclerView.Adapter<ProductSearchAdapter.ProductViewHolder> {

    //List of All Product
    List<ProductData> productDatalist;
    Context context;

    public ProductSearchAdapter(List<ProductData> productDatalist) {
        this.productDatalist = productDatalist;
    }

    public void setProductDatalist(List<ProductData> productDatalist) {
        this.productDatalist = productDatalist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductSearchAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.searh_item_card,parent,false);
        context = parent.getContext();
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSearchAdapter.ProductViewHolder holder, int position) {
        ProductAttributes productAttributes = productDatalist.get(position).getProductAttributes();
        holder.product_price.setText(productAttributes.getPrice());
        holder.product_title.setText(productAttributes.getTitle());
        if(productAttributes.getThumbnailResponse() != null){
            if(productAttributes.getThumbnailResponse().getThumbnailData() != null){
                if (productAttributes.getThumbnailResponse().getThumbnailData().getThumbnailAttributes() != null){
                    Glide.with(context).load("https://cms.istad.co" + productAttributes.getThumbnailResponse().getThumbnailData().getThumbnailAttributes().getUrl()).into(holder.product_image);
                }
            }
        }
    }
    @Override
    public int getItemCount() {
        return productDatalist.size();
    }
    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView product_title,product_price;
        ImageView product_image;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            product_title = itemView.findViewById(R.id.product_title);
            product_price = itemView.findViewById(R.id.product_price);
            product_image = itemView.findViewById(R.id.product_search_image);
        }
    }
}
