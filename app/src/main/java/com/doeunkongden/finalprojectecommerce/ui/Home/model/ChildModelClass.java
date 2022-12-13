package com.doeunkongden.finalprojectecommerce.ui.Home.model;

public class ChildModelClass {

    String product_title,product_price;
    int ImageUrl;

    public ChildModelClass(String product_title, String product_price , int imageUrl) {
        this.product_title = product_title;
        this.product_price = product_price;
        this.ImageUrl = imageUrl;
    }

    public int getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(int imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }
}
