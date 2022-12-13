package com.doeunkongden.finalprojectecommerce.data.remotes.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.doeunkongden.finalprojectecommerce.data.ServiceGenerator;
import com.doeunkongden.finalprojectecommerce.data.model.api.ThumbnailAttributes;
import com.doeunkongden.finalprojectecommerce.data.model.api.request.ProductRequest;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductPostResponse;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductResponse;
import com.doeunkongden.finalprojectecommerce.data.remotes.service.ProductService;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {

    private ProductService productService;

    private MutableLiveData<ProductResponse> productResponseMutableLiveData;

    public ProductRepository(){
        productResponseMutableLiveData = new MutableLiveData<>();
        productService = ServiceGenerator.createService(ProductService.class);
    }

    public ProductRepository(String service){
        productService = ServiceGenerator.createService(ProductService.class);
    }

    //Posting new Product
    public MutableLiveData<ProductPostResponse> postProduct(ProductRequest productRequest){
        MutableLiveData<ProductPostResponse> postProductResponse = new MutableLiveData<>();

        productService.postProduct(productRequest).enqueue(new Callback<ProductPostResponse>() {
            @Override
            public void onResponse(Call<ProductPostResponse> call, Response<ProductPostResponse> response) {
                Log.d("Post Response", "Post Product Response : " + response.body());
                postProductResponse.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ProductPostResponse> call, Throwable t) {
                Log.d("Post Fail", "Post Product Fail Response : " + t.getMessage());
                postProductResponse.postValue(null);
            }
        });


        return postProductResponse;
    }

    //upload image method
    public MutableLiveData<List<ThumbnailAttributes>> uploadImage(File file){
        MutableLiveData<List<ThumbnailAttributes>> thumbnailLiveData = new MutableLiveData<>();

        //Convert file to image url
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);

        //convert multipart body
        MultipartBody.Part body = MultipartBody.Part.createFormData("files",file.getAbsolutePath(),requestBody);

        productService.uploadImage(body).enqueue(new Callback<List<ThumbnailAttributes>>() {
            @Override
            public void onResponse(Call<List<ThumbnailAttributes>> call, Response<List<ThumbnailAttributes>> response) {
                thumbnailLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ThumbnailAttributes>> call, Throwable t) {
                thumbnailLiveData.postValue(null);
            }
        });

        return thumbnailLiveData;
    }

    public void getAllProduct(){
        productService.getAllProduct().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                productResponseMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                productResponseMutableLiveData.postValue(null);
            }
        });
    }

    public void getAllProductByTitle(String productTitle){
        productService.getAllProductByTitle(productTitle).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                productResponseMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                productResponseMutableLiveData.postValue(null);
            }
        });
    }

    public MutableLiveData<ProductResponse> getProductResponseMutableLiveData() {
        return productResponseMutableLiveData;
    }
}


