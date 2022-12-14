package com.doeunkongden.finalprojectecommerce.ui.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doeunkongden.finalprojectecommerce.R;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductResponse;
import com.doeunkongden.finalprojectecommerce.ui.AddProduct.AddProductActivity;
import com.doeunkongden.finalprojectecommerce.ui.Home.adapters.ProductHomeAdapter;
import com.doeunkongden.finalprojectecommerce.ui.ViewModel.ProductViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    FloatingActionButton addButton;
    RecyclerView parent_RecyclerView,rv_2;
    ProductViewModel productViewModel;
    ProductHomeAdapter productHomeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initEvent(view);

        ViewModelProvider.Factory factory = (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory();
        productViewModel = new ViewModelProvider(this,factory).get(ProductViewModel.class);
        productViewModel.init();

        productViewModel.getProductResponseMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ProductResponse>() {
            @Override
            public void onChanged(ProductResponse productResponse) {
                productHomeAdapter.setProductDatalist(productResponse.getDataList());
            }
        });

        return view;
    }

    private void initEvent(View view){
        addButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(view.getContext(), AddProductActivity.class);
            startActivity(intent);
        });
    }

    private void initView(View view) {
        addButton = view.findViewById(R.id.floatingActionAddButton);
        parent_RecyclerView = view.findViewById(R.id.rv_1);
        productHomeAdapter = new ProductHomeAdapter(new ArrayList<>());
        parent_RecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        parent_RecyclerView.setAdapter(productHomeAdapter);

        rv_2 = view.findViewById(R.id.rv_2);
        rv_2.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        rv_2.setAdapter(productHomeAdapter);

    }

}