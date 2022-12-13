package com.doeunkongden.finalprojectecommerce.data.model.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ThumbnailAttributes implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String thumbnailName;

    @SerializedName("url")
    private  String url;

    public ThumbnailAttributes(int id, String thumbnailName, String url) {
        this.id = id;
        this.thumbnailName = thumbnailName;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThumbnailName() {
        return thumbnailName;
    }

    public void setThumbnailName(String thumbnailName) {
        this.thumbnailName = thumbnailName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
