package com.doeunkongden.finalprojectecommerce.ui.detail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.doeunkongden.finalprojectecommerce.MainActivity;
import com.doeunkongden.finalprojectecommerce.R;
import com.doeunkongden.finalprojectecommerce.data.model.api.ProductAttributes;
import com.doeunkongden.finalprojectecommerce.data.model.api.ThumbnailAttributes;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductData;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductPostResponse;
import com.doeunkongden.finalprojectecommerce.ui.AddProduct.AddProductActivity;
import com.doeunkongden.finalprojectecommerce.ui.Cart.CartFragment;
import com.doeunkongden.finalprojectecommerce.ui.ViewModel.ProductViewModel;
import com.doeunkongden.finalprojectecommerce.utils.FilePath;

import java.io.File;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {
    ImageView go_back,product_detail_image,cart_button;
    EditText product_detail_title,product_detail_price,product_detail_descriptions;
    Button update_button;
    ProductViewModel productViewModel;
    ProgressBar progressBarDetail;
    int thumbnailId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ProductAttributes productAttributes = (ProductAttributes) getIntent().getSerializableExtra("productAttributes");
        int productId = (int) getIntent().getSerializableExtra("productId");

        //View Model Provider
        ViewModelProvider.Factory factory = (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory();
        productViewModel = new ViewModelProvider(this, factory).get(ProductViewModel.class);
        productViewModel.initRepo();

        initView();
        initEvent1(productAttributes, productId);

        setProgressBarInvisible();

        //using request Permission Method
        requestPermission();
    }

    private void initEvent1(ProductAttributes productAttributes , int productId){
        update_button.setOnClickListener(view -> {
            productViewModel.updateProduct(productId,product_detail_title.getText().toString(),product_detail_price.getText().toString(),product_detail_descriptions.getText().toString(),String.valueOf(thumbnailId))
                    .observe(this, new Observer<ProductPostResponse>() {
                        @Override
                        public void onChanged(ProductPostResponse productPostResponse) {
                            Toast.makeText(ProductDetailActivity.this,"Update Successfully ", Toast.LENGTH_SHORT);
                            Log.d("Update Res", "onChanged: " + productPostResponse);
                        }
                    });
        });

        product_detail_image.setOnClickListener(view -> {
            showFileChoser();
        });


        go_back.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        cart_button.setOnClickListener(view -> {
            Intent intent = new Intent(this, CartFragment.class);
            startActivity(intent);
        });


        //For Attributes from Item Click listener
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
        progressBarDetail = findViewById(R.id.progressBar_Detail);

    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 100);
        }
    }

    private void uploadFileToDataApi(File file) {
        productViewModel.uploadImage(file).observe(this, new Observer<List<ThumbnailAttributes>>() {
            @Override
            public void onChanged(List<ThumbnailAttributes> thumbnailAttributes) {
                String imageUrl = "https://cms.istad.co" + thumbnailAttributes.get(0).getUrl();
                thumbnailId = thumbnailAttributes.get(0).getId();
                Glide.with(ProductDetailActivity.this).load(imageUrl).into(product_detail_image);
                setProgressBarInvisible();
            }
        });
    }

    private void showFileChoser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Choose Image"), 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK && data != null) {
            setProgressBarVisible();
            Uri imageUri = data.getData();
            try {
                String selectedPath = FilePath.getPath(this, imageUri);
                File file = new File(selectedPath);
                uploadFileToDataApi(file);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Denied Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setProgressBarInvisible() {
        progressBarDetail.setVisibility(View.INVISIBLE);
    }

    private void setProgressBarVisible() {
        progressBarDetail.setVisibility(View.VISIBLE);
    }

}
