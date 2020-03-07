package com.example.izeat.Model;

public class Product {
    private String productName;
    private String productUrl;

    public Product(String productName, String productUrl) {
        this.productName = productName;
        this.productUrl = productUrl;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductUrl() {
        return productUrl;
    }

}
