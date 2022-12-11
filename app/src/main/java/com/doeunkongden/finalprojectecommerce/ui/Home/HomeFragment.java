package com.doeunkongden.finalprojectecommerce.ui.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.doeunkongden.finalprojectecommerce.R;
import com.doeunkongden.finalprojectecommerce.ui.AddProduct.AddProductActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {
    FloatingActionButton addButton;
    RecyclerView parent_RecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view);

        addButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(view.getContext(), AddProductActivity.class);
            startActivity(intent);
        });

        return view;
    }

    private void initView(View view) {
        addButton = view.findViewById(R.id.floatingActionAddButton);
    }
}