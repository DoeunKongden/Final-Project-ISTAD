package com.doeunkongden.finalprojectecommerce.ui.ViewModel;

import android.provider.ContactsContract;
import android.widget.ImageButton;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.doeunkongden.finalprojectecommerce.data.model.api.PostProductAttributes;
import com.doeunkongden.finalprojectecommerce.data.model.api.ProductAttributes;
import com.doeunkongden.finalprojectecommerce.data.model.api.ThumbnailAttributes;
import com.doeunkongden.finalprojectecommerce.data.model.api.request.ProductRequest;
import com.doeunkongden.finalprojectecommerce.data.model.api.request.ProductRequestData;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductData;
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

    //posting new product method
    public MutableLiveData<ProductPostResponse> postProduct(String imageId,String productTitle,String productDescription){

        ProductRequest productRequest = new ProductRequest();
        ProductRequestData productRequestData = new ProductRequestData();

        productRequestData.setTitle(productTitle);
        productRequestData.setPrice("30");
        productRequestData.setCategory("Shoes");
        productRequestData.setQuantity("100");
        productRequestData.setDescription(productDescription);
        productRequestData.setRating("3.8");
        productRequestData.setThumbnail(imageId);

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
