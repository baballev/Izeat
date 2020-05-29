package com.example.izeat.Controller;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.izeat.R;
import com.example.izeat.Utils.ProductInfo;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductDetailActivity extends AppCompatActivity {

    ProductInfo productInfo;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        getProductAction(getIntent().getStringExtra("barcode"), getApplicationContext());

        Toolbar toolbar = findViewById(R.id.activity_product_detail_toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getProductAction(String barcode, Context context){
        /*
         *  Parameters:
         *      barcode: /!\ String /!\ The barcode of the desired product information.
         *      context: Give the current context of the activity (use getApplicationContext()).
         *  Info obtained inside the method:
         *      A ProductInfo object containing all the product info.
         *  Example:
         *      getProduct("3017760589895", getApplicationContext());
         *      -> Gives information about Pépito mini rollos.
         */

        String url = "https://izeat.r2.enst.fr/ws/Izeat/webresources/product/" + barcode;

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    public void onResponse(final JSONObject response) {
                        /***************************
                         *   BEGINNING OF ACTIONS   *
                         ***************************/
                        // Example: create a product object and do things with it
                        try {
                            productInfo = new ProductInfo(response);

                            ((TextView)findViewById(R.id.product_detail_product_name)).setText(productInfo.getName());
                            Glide.with(getApplicationContext()).load(productInfo.getImageUrl()).into((ImageView)findViewById(R.id.activity_product_detail_product_image));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        /***************************
                         *      END OF ACTIONS     *
                         ***************************/
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println(error.toString());
                    }
                });
        queue.add(jsonObjectRequest);
    }
}
