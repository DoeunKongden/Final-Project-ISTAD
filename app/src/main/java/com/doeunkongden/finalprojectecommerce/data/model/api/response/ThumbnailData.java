package com.doeunkongden.finalprojectecommerce.data.model.api.response;

import com.doeunkongden.finalprojectecommerce.data.model.api.ThumbnailAttributes;
import com.google.gson.annotations.SerializedName;

public class ThumbnailData {
    @SerializedName("id")
    private int id;

    @SerializedName("attributes")
    private ThumbnailAttributes thumbnailAttributes;

    public ThumbnailData(int id, ThumbnailAttributes thumbnailAttributes) {
        this.id = id;
        this.thumbnailAttributes = thumbnailAttributes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ThumbnailAttributes getThumbnailAttributes() {
        return thumbnailAttributes;
    }

    public void setThumbnailAttributes(ThumbnailAttributes thumbnailAttributes) {
        this.thumbnailAttributes = thumbnailAttributes;
    }
}
