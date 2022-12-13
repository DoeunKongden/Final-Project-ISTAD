package com.doeunkongden.finalprojectecommerce.data.model.api.response;

import com.doeunkongden.finalprojectecommerce.data.model.api.ProductAttributes;
import com.doeunkongden.finalprojectecommerce.data.model.api.ProductPostAttributes;
import com.google.gson.annotations.SerializedName;

public class ProductPostData {

    @SerializedName("id")
    private int id;

    @SerializedName("attributes")
    private ProductAttributes productPostAttributes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductAttributes getProductPostAttributes() {
        return productPostAttributes;
    }

    public void setProductPostAttributes(ProductAttributes productPostAttributes) {
        this.productPostAttributes = productPostAttributes;
    }
}
