package com.doeunkongden.finalprojectecommerce.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.doeunkongden.finalprojectecommerce.MainActivity;
import com.doeunkongden.finalprojectecommerce.R;
import com.doeunkongden.finalprojectecommerce.data.model.api.ProductAttributes;
import com.doeunkongden.finalprojectecommerce.ui.Cart.CartFragment;
import com.doeunkongden.finalprojectecommerce.ui.Search.SearchFragment;

public class ProductDetailActivity extends AppCompatActivity {
    ImageView go_back,product_detail_image,cart_button;
    TextView product_detail_title,product_detail_price,product_detail_descriptions;
    Button add_cart_button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ProductAttributes productAttributes = (ProductAttributes) getIntent().getSerializableExtra("productAttributes");
        initView();
        initEvent(productAttributes);
    }

    private void initEvent(ProductAttributes productAttributes){
        go_back.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        cart_button.setOnClickListener(view -> {
            Intent intent = new Intent(this, CartFragment.class);
            startActivity(intent);
        });

        product_detail_price.setText(productAttributes.getPrice());
        product_detail_title.setText(productAttributes.getTitle());
        product_detail_descriptions.setText(productAttributes.getDescription());

        if(productAttributes.getThumbnailResponse() != null){
            if(productAttributes.getThumbnailResponse().getThumbnailData() != null){
                if(productAttributes.getThumbnailResponse().getThumbnailData().getThumbnailAttributes() != null){
                    Glide.with(this).load("https://cms.istad.co" + productAttributes.getThumbnailResponse().getThumbnailData().getThumbnailAttributes().getUrl()).into(product_detail_image);
                }
            }
        }
    }

    private void initView(){
        go_back = findViewById(R.id.goBackButton);
        product_detail_image = findViewById(R.id.product_detail_image);
        cart_button = findViewById(R.id.cart_button);
        product_detail_descriptions = findViewById(R.id.product_detail_description);
        product_detail_title = findViewById(R.id.product_detail_title);
        product_detail_price = findViewById(R.id.product_detail_price);
    }
}
