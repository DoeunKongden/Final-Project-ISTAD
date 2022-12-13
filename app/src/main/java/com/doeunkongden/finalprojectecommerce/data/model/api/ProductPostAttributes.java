package com.doeunkongden.finalprojectecommerce.data.model.api;

import com.google.gson.annotations.SerializedName;

public class ProductPostAttributes {
    //Going through the json object of attributes
    @SerializedName("title")
    private String title;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("updatedAt")
    private String updatedAt;

    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("price")
    private String price;

    @SerializedName("rating")
    private String rating;

    @SerializedName("description")
    private String description;

    @SerializedName("quantity")
    private String quantity;

    public ProductPostAttributes(String title, String createdAt, String updatedAt, String publishedAt, String price, String rating, String description, String quantity) {
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.publishedAt = publishedAt;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    @Override
    public String toString() {
        return "ProductPostAttributes{" +
                "title='" + title + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", price='" + price + '\'' +
                ", rating='" + rating + '\'' +
                ", description='" + description + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
