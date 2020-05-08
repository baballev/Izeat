package com.example.izeat.Model;

import android.os.Build;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
