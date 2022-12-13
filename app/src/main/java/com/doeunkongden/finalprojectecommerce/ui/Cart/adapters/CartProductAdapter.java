package com.doeunkongden.finalprojectecommerce.ui.Cart.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doeunkongden.finalprojectecommerce.R;
import com.doeunkongden.finalprojectecommerce.data.model.api.ProductAttributes;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductData;
import com.doeunkongden.finalprojectecommerce.ui.Cart.dialog.DeleteDialog;

import java.util.List;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.CartProductViewHolder> {

    List<ProductData> productData;
    Context context;

    public CartProductAdapter(List<ProductData> productData) {
        this.productData = productData;
    }

    public void setProductData(List<ProductData> productData) {
        this.productData = productData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartProductAdapter.CartProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cart_product_item,parent,false);
        context = parent.getContext();
        return new CartProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartProductAdapter.CartProductViewHolder holder, int position) {
        ProductAttributes productAttributes = productData.get(position).getProductAttributes();

        Log.d("onBindViewHolder", "onBindViewHolder: " + productAttributes);
        holder.product_cart_title.setText(productAttributes.getTitle());
        holder.product_cart_price.setText(productAttributes.getPrice()+" $");

        if(productAttributes.getThumbnailResponse() != null ){
            if (productAttributes.getThumbnailResponse().getThumbnailData() != null){
                if (productAttributes.getThumbnailResponse().getThumbnailData().getThumbnailAttributes() != null){
                    Glide.with(context).load("https://cms.istad.co" + productAttributes.getThumbnailResponse().getThumbnailData().getThumbnailAttributes().getUrl()).into(holder.product_cart_image);
                }
            }
        }

        holder.delete_cart_button.setOnClickListener(view -> {
            DeleteDialog deleteDialog = new DeleteDialog();
            FragmentManager fragmentManager = ((FragmentActivity)view.getContext()).getSupportFragmentManager();
            deleteDialog.show(fragmentManager,"Custom delete dialog");
        });
    }

    @Override
    public int getItemCount() {
        return productData.size();
    }

    public class CartProductViewHolder extends RecyclerView.ViewHolder {
        ImageView product_cart_image;
        TextView product_cart_title,product_cart_price;
        ImageButton delete_cart_button, increment,decrement;

        public CartProductViewHolder(@NonNull View itemView) {
            super(itemView);

            delete_cart_button = itemView.findViewById(R.id.delete_button_cart);
            product_cart_price = itemView.findViewById(R.id.product_cart_price);
            product_cart_image = itemView.findViewById(R.id.product_cart_image);
            product_cart_title = itemView.findViewById(R.id.product_cart_title);

        }
    }
}
