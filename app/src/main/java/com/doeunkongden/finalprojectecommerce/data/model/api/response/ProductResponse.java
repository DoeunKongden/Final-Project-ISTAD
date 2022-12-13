package com.doeunkongden.finalprojectecommerce.data.model.api.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductResponse implements Serializable {
    @SerializedName("data")
    List<ProductData> dataList;

    public ProductResponse(List<ProductData> dataList) {
        this.dataList = dataList;
    }

    public List<ProductData> getDataList() {
        return dataList;
    }

    public void setDataList(List<ProductData> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "dataList=" + dataList +
                '}';
    }
}
