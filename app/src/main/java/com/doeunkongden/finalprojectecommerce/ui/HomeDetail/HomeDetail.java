package com.doeunkongden.finalprojectecommerce.ui.HomeDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doeunkongden.finalprojectecommerce.MainActivity;
import com.doeunkongden.finalprojectecommerce.R;
import com.doeunkongden.finalprojectecommerce.data.model.api.ProductAttributes;
import com.doeunkongden.finalprojectecommerce.ui.ViewModel.ProductViewModel;

public class HomeDetail extends AppCompatActivity {

    TextView productTitle, productPrice, productDescription;
    ImageView productImage, cartButton;
    Button addToCartButton;
    ProgressBar imageProgress;
    ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detail);

        ProductAttributes productAttributes = (ProductAttributes) getIntent().getSerializableExtra("homeProductAttributes");

        ViewModelProvider.Factory factory = (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory();
        productViewModel = new ViewModelProvider(this, factory).get(ProductViewModel.class);
        productViewModel.initRepo();

        initView();
        initEvent(productAttributes);
    }

    private void initView() {
        productTitle = findViewById(R.id.product_detail_title);
        productImage = findViewById(R.id.product_detail_image);
        productPrice = findViewById(R.id.product_detail_price);
        productDescription = findViewById(R.id.product_detail_description);
        addToCartButton = findViewById(R.id.add_to_cart_button);
        cartButton = findViewById(R.id.cart_button);
        imageProgress = findViewById(R.id.imageProgressBar);

        cartButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        addToCartButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void initEvent(ProductAttributes productAttributes) {
        if (productAttributes != null) {
            productTitle.setText(productAttributes.getTitle());
            productDescription.setText(productAttributes.getDescription());
            productPrice.setText(productAttributes.getPrice());

            if (productAttributes.getThumbnailResponse() != null) {
                if (productAttributes.getThumbnailResponse().getThumbnailData() != null) {
                    if (productAttributes.getThumbnailResponse().getThumbnailData().getThumbnailAttributes() != null) {
                        Glide.with(this).load("https://cms.istad.co" + productAttributes.getThumbnailResponse().getThumbnailData().getThumbnailAttributes().getUrl()).into(productImage);
                        setProgressBarInvisible();
                    }
                }
            }
        }
    }

    private void setProgressBarInvisible() {
        imageProgress.setVisibility(View.INVISIBLE);
    }

    private void setProgressBarVisible() {
        imageProgress.setVisibility(View.VISIBLE);
    }
}