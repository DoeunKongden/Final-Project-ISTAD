package com.doeunkongden.finalprojectecommerce.ui.Search.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doeunkongden.finalprojectecommerce.R;
import com.doeunkongden.finalprojectecommerce.data.model.api.ProductAttributes;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductData;
import com.doeunkongden.finalprojectecommerce.ui.Search.dialog.DeleteDialog;
import com.doeunkongden.finalprojectecommerce.ui.update.UpdateProductActivity;

import java.util.List;

public class ProductSearchAdapter extends RecyclerView.Adapter<ProductSearchAdapter.ProductViewHolder> {

    //List of All Product
    List<ProductData> productDatalist;
    Context context;

    //declaring Search Product Click listener interface
    public SearchProductClickedListener searchProductClickedListener;

    public ProductSearchAdapter(List<ProductData> productDatalist, SearchProductClickedListener searchProductClickedListener) {
        this.productDatalist = productDatalist;
        this.searchProductClickedListener =searchProductClickedListener;
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
        holder.product_price.setText(productAttributes.getPrice() + "$");
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
    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView product_title,product_price;
        ImageView product_image,options_button;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            product_title = itemView.findViewById(R.id.et_update_ttile);
            product_price = itemView.findViewById(R.id.product_price);
            product_image = itemView.findViewById(R.id.product_search_image);
            options_button = itemView.findViewById(R.id.options_button);

            options_button.setOnClickListener(this);

            itemView.setOnClickListener(view -> {
                if(productDatalist.get(getAdapterPosition()).getProductAttributes() != null){
                    searchProductClickedListener.onSearhProductClick(productDatalist.get(getAdapterPosition()).getProductAttributes());
                }
            });
        }

        @Override
        public void onClick(View view) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(),view);
            popupMenu.inflate(R.menu.option_menu);
            Context context = view.getContext();
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.update_product:
                            Intent intent = new Intent(view.getContext(), UpdateProductActivity.class);
                            context.startActivity(intent);
                            break;
                        case R.id.delete_product:
                            DeleteDialog deleteDialog = new DeleteDialog();
                            FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                            deleteDialog.show(fragmentManager, "Custom Delete Dialog for product list");
                            break;
                    }
                    return false;
                }
            });

            popupMenu.show();
        }
    }
}
