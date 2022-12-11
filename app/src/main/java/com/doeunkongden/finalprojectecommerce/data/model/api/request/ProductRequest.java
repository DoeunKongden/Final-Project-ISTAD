package com.doeunkongden.finalprojectecommerce.data.model.api.request;

import com.google.gson.annotations.SerializedName;

public class ProductRequest {

    @SerializedName("data")
    private ProductRequestData productRequestData;

    public ProductRequestData getProductRequestData() {
        return productRequestData;
    }

    public void setProductRequestData(ProductRequestData productRequestData) {
        this.productRequestData = productRequestData;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "productRequestData=" + productRequestData +
                '}';
    }
}
