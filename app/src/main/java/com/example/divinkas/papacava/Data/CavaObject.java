package com.example.divinkas.papacava.Data;

import java.util.List;

public class CavaObject {
    public boolean status;
    public List<Product> products;

    public CavaObject(boolean status, List<Product> products) {
        this.status = status;
        this.products = products;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
