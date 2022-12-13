package com.doeunkongden.finalprojectecommerce.ui.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.doeunkongden.finalprojectecommerce.data.model.api.ProductPostAttributes;
import com.doeunkongden.finalprojectecommerce.data.model.api.ThumbnailAttributes;
import com.doeunkongden.finalprojectecommerce.data.model.api.request.ProductRequest;
import com.doeunkongden.finalprojectecommerce.data.model.api.request.ProductRequestData;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductPostData;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductPostResponse;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductResponse;
import com.doeunkongden.finalprojectecommerce.data.remotes.repository.ProductRepository;

import java.io.File;
import java.util.List;

public class ProductViewModel extends ViewModel {

    private ProductRepository productRepository;
    private MutableLiveData<ProductResponse> productResponseMutableLiveData;


    //Init() to initialize the productRepository and the ProductResponse live data
    public void init(){
        productRepository = new ProductRepository();
        productResponseMutableLiveData = productRepository.getProductResponseMutableLiveData();
        getAllProduct();
    }

    //Init repository
    public void initRepo(){
        productRepository = new ProductRepository("only init service");
    }

    //Posting New Product
    public MutableLiveData<ProductPostResponse> postProduct(String imageId, String product_title, String product_description){

        ProductRequest productRequest = new ProductRequest();
        ProductRequestData productRequestData = new ProductRequestData();

        productRequestData.setTitle(product_title);
        productRequestData.setRating("3.8");
        productRequestData.setDescription(product_description);
        productRequestData.setQuantity("20");
        productRequestData.setCategory("1");
        productRequestData.setThumbnail(imageId);
        productRequestData.setPrice("100");

        return productRepository.postProduct(productRequest);
    }

    //method upload image
    public MutableLiveData<List<ThumbnailAttributes>> uploadImage(File file){
        return productRepository.uploadImage(file);
    }

    //initializing the get all product method from the productRepository here as the --> Get all product method
    public void getAllProduct(){
        productRepository.getAllProduct();
    }

    //initializing the get all product by title method from the productRepository here as --> Get all product by title method
    public void getAllProductByTitle(String productTitle){
        productRepository.getAllProductByTitle(productTitle);
    }

    //Get All product Response live data
    public MutableLiveData<ProductResponse> getProductResponseMutableLiveData() {
        return productResponseMutableLiveData;
    }
}
