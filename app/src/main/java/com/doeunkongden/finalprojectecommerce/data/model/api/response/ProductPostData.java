package com.doeunkongden.finalprojectecommerce.data.model.api.response;

import com.doeunkongden.finalprojectecommerce.data.model.api.PostProductAttributes;
import com.google.gson.annotations.SerializedName;


public class ProductPostData {

    @SerializedName("id")
    private  int id;

    @SerializedName("attributes")
    private PostProductAttributes postProductAttributes;

    public ProductPostData(int id, PostProductAttributes postProductAttributes) {
        this.id = id;
        this.postProductAttributes = postProductAttributes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PostProductAttributes getPostProductAttributes() {
        return postProductAttributes;
    }

    public void setPostProductAttributes(PostProductAttributes postProductAttributes) {
        this.postProductAttributes = postProductAttributes;
    }

    @Override
    public String toString() {
        return "ProductPostData{" +
                "id=" + id +
                ", postProductAttributes=" + postProductAttributes +
                '}';
    }
}
