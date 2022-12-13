package com.doeunkongden.finalprojectecommerce.ui.Cart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doeunkongden.finalprojectecommerce.MainActivity;
import com.doeunkongden.finalprojectecommerce.R;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductResponse;
import com.doeunkongden.finalprojectecommerce.ui.Cart.adapters.CartProductAdapter;
import com.doeunkongden.finalprojectecommerce.ui.Home.HomeFragment;
import com.doeunkongden.finalprojectecommerce.ui.ViewModel.ProductViewModel;

import java.util.ArrayList;


public class CartFragment extends Fragment {
    ImageView goBack_button;
    RecyclerView cart_item_rv;
    ProgressBar progressBar;
    ProductViewModel productViewModel;
    CartProductAdapter cartProductAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        ViewModelProvider.Factory factory = (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory();
        productViewModel = new ViewModelProvider(this,factory).get(ProductViewModel.class);
        productViewModel.init();

        initView(view);
        initEvent(view);
        productViewModel.getProductResponseMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ProductResponse>() {
            @Override
            public void onChanged(ProductResponse productResponse) {
                Log.d("Product Cart response", "onChanged: " + productResponse.getDataList());
                cartProductAdapter.setProductData(productResponse.getDataList());
                setProgressBarInvisible();
            }
        });
        return view;
    }

    private void initEvent(View view){
        goBack_button.setOnClickListener(view1 -> {
            Log.d("go back cart ", "On Click button back");
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        });
    }

    private void setProgressBarVisible(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void setProgressBarInvisible(){
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void initView(View view) {
        goBack_button = view.findViewById(R.id.go_back_buttonCart);
        cart_item_rv = view.findViewById(R.id.recyclerView_cart_item);
        progressBar = view.findViewById(R.id.progressBarCartItem);

        cartProductAdapter = new CartProductAdapter(new ArrayList<>());
        cart_item_rv.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        cart_item_rv.setAdapter(cartProductAdapter);
    }
}