package com.doeunkongden.finalprojectecommerce.data.model.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductPostResponse {

    @SerializedName("data")
    ProductData productData;

    public ProductPostResponse(ProductData productData) {
        this.productData = productData;
    }

    public ProductData getProductData() {
        return productData;
    }

    public void setProductData(ProductData productData) {
        this.productData = productData;
    }
}
