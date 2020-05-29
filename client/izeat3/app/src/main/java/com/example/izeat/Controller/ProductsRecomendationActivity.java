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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.izeat.ImageRecognitionActivity;
import com.example.izeat.Model.Product;
import com.example.izeat.R;
import com.example.izeat.Utils.ItemClickSupport;
import com.example.izeat.Utils.ProductInfo;
import com.example.izeat.View.ProductsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.izeat.Utils.ProductInfo.productsFromJSON;

public class ProductsRecomendationActivity extends AppCompatActivity {

    //PRODUCTS RECOMMENDATIONS DATA
    private ArrayList<Product> productsReco = new ArrayList<Product>(0);

    //For the recycler view
    private RecyclerView productsRecoRecyclerView;
    private RecyclerView.Adapter productsRecoAdapter;
    private RecyclerView.LayoutManager productsLayoutManager;

    //navigation Bar
    private ImageView btnRecipes;
    private ImageView btnProfil;
    private ImageView btnFrigo;
    private FloatingActionButton btnAdd;
    private FloatingActionButton btnsearch;
    private FloatingActionButton btnphoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_recomendation);

        //-------------------------------------------------------------------------------------
        //SETS THE RECYCLER VIEW

        fillProductList();

        productsRecoRecyclerView = (RecyclerView) findViewById(R.id.products_recycler_view);

        productsLayoutManager = new GridLayoutManager(this, 3);
        productsRecoRecyclerView.setLayoutManager(productsLayoutManager);

        productsRecoAdapter = new ProductsAdapter(productsReco, Glide.with(this));
        productsRecoRecyclerView.setAdapter(productsRecoAdapter);

        //-------------------------------------------------------------------------------------
        // SETS THE NAVIGATION BAR BUTTONS

        this.btnRecipes= (ImageView) findViewById(R.id.btnRecipes);
        btnRecipes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecipesListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        this.btnProfil= (ImageView) findViewById(R.id.btnProfil);
        btnProfil.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profil.class);
                startActivity(intent);
                finish();
            }
        });


        this.btnFrigo= (ImageView) findViewById(R.id.btnFrigo);
        btnFrigo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FridgeActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //---------------------------------------------------------------------------------------



        this.btnAdd=(FloatingActionButton) findViewById(R.id.btnadd);
        this.btnphoto=(FloatingActionButton)findViewById(R.id.btnphoto);
        btnphoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent imageRecognitionIntent = new Intent(ProductsRecomendationActivity.this, ImageRecognitionActivity.class);
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

        this.configureOnClickRecyclerView();
    }

    // 1 - Configure item click on RecyclerView
    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(productsRecoRecyclerView, R.layout.activity_products_recomendation)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        //Log.e("TAG", "Position : "+position);
                        Product productClicked = productsReco.get(position);
                        Toast.makeText(getApplicationContext(), "You clicked on product : "+ productClicked.getProductName(), Toast.LENGTH_SHORT).show();
                        //openProductDetail(productClicked);
                    }
                });
    }

    private void openProductDetail(Product productClicked) {

        Intent productDetailIntent = new Intent(ProductsRecomendationActivity.this, ProductDetailActivity.class);


    }

    private void fillProductList() {
        Context context = getApplicationContext();
        getSearchAction("Banane",context);
    }

    private void getSearchAction(String searchTerms, Context context){
        /*
         *  Parameters:
         *      searchTerms: A string which can contains spaces that describe the kind of product wanted.
         *      context: Give the current context of the activity (use getApplicationContext()).
         *  Info obtained inside the method:
         *      An array list of ProductInfo objects, each object representing a product found on OpenFoodFacts for the given search string.
         *  Example:
         *      getSearchAction("Jambon blanc", getApplicationContext());
         *      -> Retrieves at max 50 products on OpenFoodFacts which match with the name "Jambon blanc".
         */

        String url = "https://izeat.r2.enst.fr/ws/Izeat/webresources/product/search/" + searchTerms;

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        /***************************
                         *   BEGINNING OF ACTIONS   *
                         ***************************/
                        // Example: Retrieve search results on OFF and add them to the list of product
                        // to display. !! only the first page of result is retrieved. !!
                        try {
                            ArrayList<ProductInfo> productInfoList = productsFromJSON(response.toString());
                            for (ProductInfo product : productInfoList) {
                                productsReco.add(product.summarize());

                            }
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
        queue.add(jsonArrayRequest);
    }

}
