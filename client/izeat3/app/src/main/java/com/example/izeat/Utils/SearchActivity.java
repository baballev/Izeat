package com.example.izeat.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.izeat.Controller.FridgeActivity;
import com.example.izeat.Model.Product;
import com.example.izeat.R;
import com.example.izeat.View.ProductsAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import static com.example.izeat.Utils.ProductInfo.productsFromJSON;

public class SearchActivity extends AppCompatActivity {

    private ArrayList<Product> products = new ArrayList<Product>(0);

    private RecyclerView productsRecyclerView;
    private RecyclerView.Adapter productsAdapter;
    private RecyclerView.LayoutManager productsLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        productsRecyclerView = (RecyclerView) findViewById(R.id.products_recycler_view);

        productsLayoutManager = new GridLayoutManager(this, 3);
        productsRecyclerView.setLayoutManager(productsLayoutManager);

        productsAdapter = new ProductsAdapter(products, Glide.with(this));
        productsRecyclerView.setAdapter(productsAdapter);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query, getApplicationContext());
        }

        configureOnClickProduct();

    }

    private void configureOnClickProduct() {
        ItemClickSupport.addTo(productsRecyclerView, R.layout.activity_products_recomendation)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        //Log.e("TAG", "Position : "+position);
                        Product productClicked = products.get(position);
                        Toast.makeText(getApplicationContext(), "You clicked on product : "+ productClicked.getProductName(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), FridgeActivity.class);
                        intent.putExtra("productSelectedBarcode", productClicked.getBarcode());
                        startActivity(intent);
                    }
                });
    }

    private void doMySearch(String query,final Context context) {
        String url = "https://izeat.r2.enst.fr/ws/Izeat/webresources/product/search/" + query;

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        /***************************
                         *   BEGINNING OF ACTIONS   *
                         ***************************/
                        // Example: Retrieve search results on OFF and print names.
                        try {
                            ArrayList<ProductInfo> productInfoList = productsFromJSON(response.toString());
                            for (ProductInfo productInfo : productInfoList) {
                                products.add(productInfo.summarize());

                            }
                            productsAdapter.notifyDataSetChanged();
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
