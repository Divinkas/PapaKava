package com.example.divinkas.papacava.Data;

public class Product {
    public int id;
    public String name;
    public String description;
    public int price;
    public int count;
    public String first_marker;
    public String second_marker;
    public String brand_name;
    public String image_url;
    public int weight_type;

    public Product(int id, String name, String description, int price, int count, String first_marker,
                   String second_marker, String brand_name, String image_url, int weight_type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.count = count;
        this.first_marker = first_marker;
        this.second_marker = second_marker;
        this.brand_name = brand_name;
        this.image_url = image_url;
        this.weight_type = weight_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFirst_marker() {
        return first_marker;
    }

    public void setFirst_marker(String first_marker) {
        this.first_marker = first_marker;
    }

    public String getSecond_marker() {
        return second_marker;
    }

    public void setSecond_marker(String second_marker) {
        this.second_marker = second_marker;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getWeight_type() {
        return weight_type;
    }

    public void setWeight_type(int weight_type) {
        this.weight_type = weight_type;
    }
}
