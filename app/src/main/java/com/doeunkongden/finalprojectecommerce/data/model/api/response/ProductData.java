package com.doeunkongden.finalprojectecommerce.data.model.api.response;

import com.doeunkongden.finalprojectecommerce.data.model.api.ProductAttributes;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductData implements Serializable {
    //Going Through data
    @SerializedName("id")
    private int id;

    @SerializedName("attributes")
    private ProductAttributes productAttributes;

    public ProductData(int id, ProductAttributes productAttributes) {
        this.id = id;
        this.productAttributes = productAttributes;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductAttributes getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(ProductAttributes productAttributes) {
        this.productAttributes = productAttributes;
    }
}
