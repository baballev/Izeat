package com.example.izeat.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.izeat.Model.Product;
import com.example.izeat.R;
import com.example.izeat.Utils.ItemClickSupport;
import com.example.izeat.View.ProductsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


import com.example.izeat.R;

public class ProductsRecomendationActivity extends AppCompatActivity {

    //PRODUCTS RECOMMENDATIONS DATA
    private ArrayList<Product> productsReco;

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

        productsReco = new ArrayList<Product>(0);
        for (int i = 0 ; i < 50 ; i++)
            productsReco.add(i,new Product("Product #" + i, ""));

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
                Intent intent = new Intent(getApplicationContext(), Frigo.class);
                startActivity(intent);
                finish();
            }
        });


        //---------------------------------------------------------------------------------------



        this.btnAdd=(FloatingActionButton) findViewById(R.id.btnadd);
        this.btnphoto=(FloatingActionButton)findViewById(R.id.btnphoto);
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
                        Log.e("TAG", "Position : "+position);
                        System.out.println("clicked on item " + position);
                    }
                });
    }

}
