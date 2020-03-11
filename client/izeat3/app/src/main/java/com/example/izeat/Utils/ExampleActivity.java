package com.example.izeat.Utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.izeat.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.izeat.Utils.ProductInfo.productsFromJSON;

public class ExampleActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSearchAction("tagliatelle", getApplicationContext());
            }
        });

        this.getApplicationContext();

    }

    /*
     * Method Usage:
     *  Parameters:
     *      context: Give the current context of the activity (use getApplicationContext())
     *      barcode: /!\ String /!\ The barcode of the desired product information
     *  Return value:
     *      Returns a String containing all the json file related to the product infos
     *  Example:
     *      getProduct(getApplicationContext, "3017760589895");
     *      -> Gives information about Pépito mini rollos.
     */
    public static void getProductAction(String barcode, Context context){
        String url = "http://izeat.r2.enst.fr/ws/Izeat/webresources/product/" + barcode;

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    public void onResponse(final JSONObject response) {
                        /***************************
                         *   BEGINING OF ACTIONS   *
                         ***************************/
                        // Example: create a product object and do things with it
                        try {
                            ProductInfo produit = new ProductInfo(response);
                            System.out.println(produit.getName());
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

    public static void getSearchAction(String searchTerms, Context context){
        String url = "http://izeat.r2.enst.fr/ws/Izeat/webresources/product/search/" + searchTerms;

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        /***************************
                         *   BEGINING OF ACTIONS   *
                         ***************************/
                        // Example: Retrieve search results on OFF and print names.
                        try {
                            ArrayList<ProductInfo> products = productsFromJSON(response.toString());
                            for (ProductInfo product : products) {
                                System.out.println(product.getName());
                            }
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
        queue.add(jsonArrayRequest);
    }
}
