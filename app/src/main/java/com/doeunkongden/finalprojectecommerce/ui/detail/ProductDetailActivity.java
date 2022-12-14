package com.doeunkongden.finalprojectecommerce.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.doeunkongden.finalprojectecommerce.MainActivity;
import com.doeunkongden.finalprojectecommerce.R;
import com.doeunkongden.finalprojectecommerce.data.model.api.ProductAttributes;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductData;
import com.doeunkongden.finalprojectecommerce.ui.Cart.CartFragment;
import com.doeunkongden.finalprojectecommerce.ui.ViewModel.ProductViewModel;

public class ProductDetailActivity extends AppCompatActivity {
    ImageView go_back,product_detail_image,cart_button;
    EditText product_detail_title,product_detail_price,product_detail_descriptions;
    Button update_button;
    ProductViewModel productViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ProductAttributes productAttributes = (ProductAttributes) getIntent().getSerializableExtra("productAttributes");
        ProductData productDataId = getIntent().getParcelableExtra("productId");

        //View Model Provider
        ViewModelProvider.Factory factory = (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory();
        productViewModel = new ViewModelProvider(this, factory).get(ProductViewModel.class);
        productViewModel.initRepo();

        initView();
        initEvent(productAttributes);
    }

    private void initEvent(ProductAttributes productAttributes){

        update_button.setOnClickListener(view -> {

        });


        go_back.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        cart_button.setOnClickListener(view -> {
            Intent intent = new Intent(this, CartFragment.class);
            startActivity(intent);
        });

        product_detail_title.setText(productAttributes.getTitle());
        product_detail_price.setText(productAttributes.getPrice());
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
        cart_button = findViewById(R.id.delete_button);
        product_detail_descriptions = findViewById(R.id.product_detail_description);
        product_detail_title = findViewById(R.id.product_detail_title);
        product_detail_price = findViewById(R.id.product_detail_price);
        update_button = findViewById(R.id.update_button);
    }
}
