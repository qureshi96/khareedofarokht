package com.example.kd;

public class ProductModel {
    String name;
    String price;
    String vendor;
    String vendorLink;
    String rating;

    public ProductModel(String name, String price, String vendor, String vendorLink, String rating, int price1) {
        this.name = name;
        this.price = price;
        this.vendor = vendor;
        this.vendorLink = vendorLink;
        this.rating = rating;
        this.price1 = price1;
    }

    public int getPrice1() {
        return price1;
    }

    public void setPrice1(int price1) {
        this.price1 = price1;
    }

    int price1;



    public ProductModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVendorLink() {
        return vendorLink;
    }

    public void setVendorLink(String vendorLink) {
        this.vendorLink = vendorLink;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
