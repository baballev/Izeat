package com.example.izeat.Model;

import android.content.Context;
import android.os.Build;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.izeat.Utils.ProductInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Product {
    /* This class is used to show the hint of the products in the main activities
     * it is built with a barcode and use a modified getProductAction method (see ExampleActivity)
     * to build a lighter product object than a ProductInfo product.
     *
     * Then the barcode is given to the ProductDetailActivity in order to build a complete
     * ProductInfo object to show to the user when the user click on a product.
     *
     */
    private String productName;
    private String imageUrl;
    private String barcode;

    public Product(String barcode, String imageUrl, String productName) {
        this.barcode = barcode;
        this.productName = productName;
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }


    public String getBarcode() {
        return barcode;
    }
}


