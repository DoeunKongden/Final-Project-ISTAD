package com.doeunkongden.finalprojectecommerce.data.model.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductPostResponse {

    @SerializedName("data")
    List<ProductPostData> productPostData;

    public List<ProductPostData> getProductPostData() {
        return productPostData;
    }

    public void setProductPostData(List<ProductPostData> productPostData) {
        this.productPostData = productPostData;
    }

    @Override
    public String toString() {
        return "ProductPostResponse{" +
                "productPostData=" + productPostData +
                '}';
    }
}
