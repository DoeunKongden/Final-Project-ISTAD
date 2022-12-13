package com.doeunkongden.finalprojectecommerce.ui.update;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.doeunkongden.finalprojectecommerce.MainActivity;
import com.doeunkongden.finalprojectecommerce.R;

public class UpdateProductActivity extends AppCompatActivity {
    ImageView go_home;
    Button cancel_button,update_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        initView();
        initEvent();
    }

    private void initEvent() {
        go_home.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        cancel_button.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        update_button.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void initView() {
        go_home = findViewById(R.id.home_icon);
        cancel_button = findViewById(R.id.button_cancel);
        update_button = findViewById(R.id.update_button);
    }
}