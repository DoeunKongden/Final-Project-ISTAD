package com.doeunkongden.finalprojectecommerce.data.model.api.request;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class ProductRequestData {

    @SerializedName("title;")
    private String title;

    @SerializedName("rating")
    private String rating;

    @SerializedName("description")
    private String description;

    @SerializedName("quantity")
    private String quantity;

    @SerializedName("category")
    private String category;

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("price")
    private String price;

    public ProductRequestData(){

    }

    public ProductRequestData(String title, String rating, String description, String quantity, String category, String thumbnail, String price) {
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.quantity = quantity;
        this.category = category;
        this.thumbnail = thumbnail;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @NonNull
    @Override
    public String toString() {
        return "ProductRequestData{" +
                "title='" + title + '\'' +
                ", rating='" + rating + '\'' +
                ", description='" + description + '\'' +
                ", quantity='" + quantity + '\'' +
                ", category='" + category + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
