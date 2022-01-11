package com.example.kd;

public class PhoneModel {
    public PhoneModel(String name, String image, String brand) {
        this.name = name;
        this.image = image;
        this.brand = brand;
    }

    public PhoneModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String name;
    String image;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    String brand;
}
