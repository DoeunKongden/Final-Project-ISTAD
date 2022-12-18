package com.doeunkongden.finalprojectecommerce.data.model.api.request;

import com.google.gson.annotations.SerializedName;

public class ProductUpdateRequest {

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
        return "ProductUpdateRequest{" +
                "productRequestData=" + productRequestData +
                '}';
    }
}
