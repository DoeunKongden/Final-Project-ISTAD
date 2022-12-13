package com.doeunkongden.finalprojectecommerce.data.remotes.service;

import com.doeunkongden.finalprojectecommerce.data.model.api.ThumbnailAttributes;
import com.doeunkongden.finalprojectecommerce.data.model.api.request.ProductRequest;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductPostResponse;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ProductService {

    //calling method getAllProduct to use from ProductRepository Class
    @GET("e-commerce-products?populate=thumbnail")
    Call<ProductResponse> getAllProduct();

    //calling method to query for product
    @GET("e-commerce-products")
    Call<ProductResponse> getAllProductByTitle(@Query("filters[title][$containsi]") String productTitle);

    //Uploading Image
    @Multipart
    @POST("upload")
    Call<List<ThumbnailAttributes>> uploadImage(@Part MultipartBody.Part image);


    //Posting new product
    @POST("e-commerce-products")
    Call<ProductPostResponse> postProduct(@Body ProductRequest productRequest);
}
