package com.doeunkongden.finalprojectecommerce.data.model.api.response;

import com.doeunkongden.finalprojectecommerce.data.model.api.ProductAttributes;
import com.google.gson.annotations.SerializedName;

public class ProductPostResponse {

    @SerializedName("data")
    ProductPostData productPostData;


    public ProductPostResponse(ProductPostData productPostData) {
        this.productPostData = productPostData;
    }

    public ProductPostData getProductPostData() {
        return productPostData;
    }

    public void setProductPostData(ProductPostData productPostData) {
        this.productPostData = productPostData;
    }

    @Override
    public String toString() {
        return "ProductPostResponse{" +
                "productPostData=" + productPostData +
                '}';
    }
}
