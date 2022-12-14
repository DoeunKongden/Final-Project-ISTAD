package com.doeunkongden.finalprojectecommerce.ui.Search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doeunkongden.finalprojectecommerce.MainActivity;
import com.doeunkongden.finalprojectecommerce.R;
import com.doeunkongden.finalprojectecommerce.data.model.api.ProductAttributes;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductData;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductResponse;
import com.doeunkongden.finalprojectecommerce.ui.Search.adapters.ProductSearchAdapter;
import com.doeunkongden.finalprojectecommerce.ui.Search.adapters.SearchProductClickedListener;
import com.doeunkongden.finalprojectecommerce.ui.ViewModel.ProductViewModel;
import com.doeunkongden.finalprojectecommerce.ui.detail.ProductDetailActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchFragment extends Fragment implements SearchProductClickedListener {
    ProgressBar progressBarSearch;
    RecyclerView searchItem_Recycler;
    SearchView searchView;
    ProductSearchAdapter productSearchAdapter;
    ProductViewModel productViewModel;
    ImageView go_home_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        //Initializing the views in the fragment method
        initView(view);
        intiEven(view);

        //Initializing ViewModel Factory Provider
        ViewModelProvider.Factory factory = (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory();
        productViewModel = new ViewModelProvider(this, factory).get(ProductViewModel.class);
        productViewModel.init();

        //Get ProductResponseLive Data
        productViewModel.getProductResponseMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ProductResponse>() {
            @Override
            public void onChanged(ProductResponse productResponse) {
                Log.d("Product Response", "onChanged SearchFragment Data : " + productResponse.getDataList().toString());
                productSearchAdapter.setProductDatalist(productResponse.getDataList());
                setProgressBarSearchInvisible();
            }
        });

        //Search for Item
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                productViewModel.getAllProductByTitle(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.isEmpty()){
                    productViewModel.getAllProduct();
                }
                return false;
            }
        });
        return  view;
    }

    private void intiEven(View view) {
        go_home_button.setOnClickListener(view1 -> {
           Intent intent = new Intent(view.getContext(), MainActivity.class);
           startActivity(intent);
        });
    }

    private void setProgressBarVisible(){
        progressBarSearch.setVisibility(View.VISIBLE);
    }

    private void setProgressBarSearchInvisible(){
        progressBarSearch.setVisibility(View.INVISIBLE);
    }

    private void initView(View view) {
        searchView = view.findViewById(R.id.search_viewItem);
        progressBarSearch = view.findViewById(R.id.progressBar);
        searchItem_Recycler = view.findViewById(R.id.item_search_recycler);
        go_home_button = view.findViewById(R.id.go_home_button);
        productSearchAdapter = new ProductSearchAdapter(new ArrayList<>(),this);
        searchItem_Recycler.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        searchItem_Recycler.setAdapter(productSearchAdapter);
    }


    @Override
    public void onSearhProductClick(ProductAttributes productAttributes) {
        Intent intent =  new Intent(getContext(), ProductDetailActivity.class);
        intent.putExtra("productAttributes",productAttributes);
        startActivity(intent);
    }

    @Override
    public void onItemClick(ProductData productData) {
        Intent intent = new Intent(getContext(),ProductDetailActivity.class);
        intent.putExtra("productId" , productData);
        startActivity(intent);
    }
}