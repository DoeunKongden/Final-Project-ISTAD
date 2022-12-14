package com.doeunkongden.finalprojectecommerce.ui.AddProduct;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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

import com.bumptech.glide.Glide;
import com.doeunkongden.finalprojectecommerce.MainActivity;
import com.doeunkongden.finalprojectecommerce.R;
import com.doeunkongden.finalprojectecommerce.data.model.api.ThumbnailAttributes;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductPostResponse;
import com.doeunkongden.finalprojectecommerce.ui.ViewModel.ProductViewModel;
import com.doeunkongden.finalprojectecommerce.utils.FilePath;

import java.io.File;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {
    ImageView goHomeButton, thumbnailUpload;
    Button cancelButton,addButton;
    ProgressBar progressBar;
    EditText product_title, product_description;
    ProductViewModel productViewModel;
    int thumbnailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        //View Model Provider
        ViewModelProvider.Factory factory = (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory();
        productViewModel = new ViewModelProvider(this, factory).get(ProductViewModel.class);
        productViewModel.initRepo();

        initView();
        initEvent();
        //using request Permission Method
        requestPermission();



        //Home Icon button
        goHomeButton.setOnClickListener(view -> {
            Intent intent = new Intent(AddProductActivity.this, MainActivity.class);
            startActivity(intent);
        });

        //Cancel Button
        cancelButton.setOnClickListener(view -> {
            Intent intent = new Intent(AddProductActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }

    private void initEvent() {
        thumbnailUpload.setOnClickListener(view -> {
            showFileChoser();
        });
        addButton.setOnClickListener(view -> {
            productViewModel.postProduct(String.valueOf(thumbnailId),product_title.getText().toString(),product_description.getText().toString()).observe(this, new Observer<ProductPostResponse>() {
                @Override
                public void onChanged(ProductPostResponse productPostResponse) {
                    Toast.makeText(AddProductActivity.this, "Post SuccessFully", Toast.LENGTH_SHORT).show();
                    Log.d("PPR", "onChanged: " + productPostResponse);
                }
            });
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

    private void uploadFileToDataApi(File file) {
        productViewModel.uploadImage(file).observe(this, new Observer<List<ThumbnailAttributes>>() {
            @Override
            public void onChanged(List<ThumbnailAttributes> thumbnailAttributes) {
                String imageUrl = "https://cms.istad.co" + thumbnailAttributes.get(0).getUrl();
                thumbnailId = thumbnailAttributes.get(0).getId();
                Glide.with(AddProductActivity.this).load(imageUrl).into(thumbnailUpload);
                setProgressBarInvisible();
            }
        });
    }

    private void initView() {
        addButton = findViewById(R.id.update_button);
        goHomeButton = findViewById(R.id.home_icon);
        cancelButton = findViewById(R.id.button_cancel);
        thumbnailUpload = findViewById(R.id.iv_update_image);
        progressBar = findViewById(R.id.upload_progressBar);
        product_title = findViewById(R.id.et_update_ttile);
        product_description = findViewById(R.id.product_descriptions);
    }

    private void setProgressBarInvisible() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void setProgressBarVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 100);
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
}