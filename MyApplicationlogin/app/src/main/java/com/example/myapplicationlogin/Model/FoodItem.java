package com.example.myapplicationlogin.Model;

public class FoodItem {
    private long id;
    private String name;
    private String price;
    private String description;
    private byte[] image;

    public FoodItem(long id, String name, String price, String description, byte[] image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getImage() {
        return image;
    }
}
