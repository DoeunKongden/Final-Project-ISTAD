package com.doeunkongden.finalprojectecommerce.data.model.api.response;

import com.google.gson.annotations.SerializedName;

public class ThumbnailResponse {
    @SerializedName("data")
    private ThumbnailData thumbnailData;

    public ThumbnailResponse(ThumbnailData thumbnailData) {
        this.thumbnailData = thumbnailData;
    }

    public ThumbnailData getThumbnailData() {
        return thumbnailData;
    }

    public void setThumbnailData(ThumbnailData thumbnailData) {
        this.thumbnailData = thumbnailData;
    }

    @Override
    public String toString() {
        return "ThumbnailResponse{" +
                "thumbnailData=" + thumbnailData +
                '}';
    }
}
