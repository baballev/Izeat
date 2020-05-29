package com.example.izeat.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.izeat.ImageRecognitionActivity;
import com.example.izeat.Model.Product;
import com.example.izeat.R;
import com.example.izeat.Utils.ItemClickSupport;
import com.example.izeat.Utils.MyAsyncTask;
import com.example.izeat.View.ProductsAdapter;
import com.example.izeat.View.RecipesAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FridgeActivity extends AppCompatActivity {

    //mandatory to synchronize access to the data on the server
    private boolean semaphoreLike;

    //PRODUCTS RECOMMENDATIONS DATA
    private ArrayList<Product> productsInFridge = new ArrayList<Product>(0);

    //For the recycler view
    private RecyclerView productsInFridgeRecyclerView;
    private RecyclerView.Adapter productsRecoAdapter;
    private RecyclerView.LayoutManager productsLayoutManager;

    private ImageView btnProfil;
    private ImageView btnRecipes;
    private ImageView btnProduct;
    private FloatingActionButton btnAdd;
    private FloatingActionButton btnsearch;
    private FloatingActionButton btnphoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        //-------------------------------------------------------------------------------------
        //SETS THE RECYCLER VIEW

        fillProductList();

        productsInFridgeRecyclerView = (RecyclerView) findViewById(R.id.products_recycler_view);

        productsLayoutManager = new GridLayoutManager(this, 3);
        productsInFridgeRecyclerView.setLayoutManager(productsLayoutManager);

        productsRecoAdapter = new ProductsAdapter(productsInFridge, Glide.with(this));
        productsInFridgeRecyclerView.setAdapter(productsRecoAdapter);

        //-------------------------------------------------------------------------------------
        // SETS THE NAVIGATION BAR BUTTONS
        
        this.btnRecipes= (ImageView) findViewById(R.id.btnRecipes);
        btnRecipes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecipesListActivity.class);
                startActivity(intent);
            }
        });

        this.btnProfil= (ImageView) findViewById(R.id.btnProfil);
        btnProfil.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profil.class);
                startActivity(intent);
            }
        });


        this.btnProduct= (ImageView) findViewById(R.id.btnProduct);
        btnProduct.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductsRecomendationActivity.class);
                startActivity(intent);
            }
        });

        //-------------------------------------------------------------------------------------
        // SETS THE FlOATING BTNS
        
        this.btnAdd=(FloatingActionButton) findViewById(R.id.btnadd);
        this.btnphoto=(FloatingActionButton)findViewById(R.id.btnphoto);
        btnphoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent imageRecognitionIntent = new Intent(FridgeActivity.this, ImageRecognitionActivity.class);
                startActivity(imageRecognitionIntent);
            }
        });

        this.btnsearch=(FloatingActionButton)findViewById(R.id.btnsearch);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                btnAdd.setVisibility(View.GONE);
                btnsearch.setVisibility(View.VISIBLE);
                btnphoto.setVisibility(View.VISIBLE);

            }
        });
        
        configureOnClickProduct();
    }

    private void configureOnClickProduct() {
        ItemClickSupport.addTo(productsInFridgeRecyclerView, R.layout.activity_products_recomendation)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        //Log.e("TAG", "Position : "+position);
                        Product productClicked = productsInFridge.get(position);
                        Toast.makeText(getApplicationContext(), "You clicked on product : "+ productClicked.getProductName(), Toast.LENGTH_SHORT).show();
                        openProductDetail(productClicked);
                    }
                });
    }

    private void openProductDetail(Product productClicked) {
        Intent intent = new Intent(getApplicationContext(),ProductDetailActivity.class);
        intent.putExtra("barcode", productClicked.getBarcode());
        startActivity(intent);
    }

    private void fillProductList() {
        Context context = getApplicationContext();
        addProductAction("7613035989535",context);
        addProductAction("5410188031072", context);
        addProductAction("3504182920011", context);
        addProductAction("3017620422003", context);
        addProductAction("3449865232466", context);
        addProductAction("3073786865191", context);
        addProductAction("3564700486054", context);
    }

    private void addProductAction(final String barcode, final Context context){
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

                        try {
                            String name;
                            if (!response.isNull("name")) {
                                name = response.getString("name");
                            } else{
                                name = "Erreur_Nom";
                            }
                            Product product = new Product(barcode, response.getString("imageUrl"), name);
                            //Log.e("Info" , "This product has been fetched : " + product.toString());
                            productsInFridge.add( new Product(barcode, response.getString("imageUrl"), name));

                            productsRecoAdapter.notifyDataSetChanged();

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

