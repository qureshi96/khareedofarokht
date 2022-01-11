package com.example.kd;

public class FavoriteModel {
    String Image;
    String productName;
    String Brand;

    public FavoriteModel() {
    }

    public FavoriteModel(String image, String productName, String brand) {
        Image = image;
        this.productName = productName;
        Brand = brand;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

}
