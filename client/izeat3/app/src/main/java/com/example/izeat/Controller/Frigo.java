package com.example.izeat.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.izeat.Model.Product;
import com.example.izeat.R;
import com.example.izeat.View.ProductsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Frigo extends AppCompatActivity {

    //PRODUCTS RECOMMENDATIONS DATA
    private ArrayList<Product> productsInFridge;

    //For the recycler view
    private RecyclerView productsInFridgeRecyclerView;
    private RecyclerView.Adapter productsInFridgeAdapter;
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
        setContentView(R.layout.activity_frigo);

        //-------------------------------------------------------------------------------------
        //SETS THE RECYCLER VIEW

        productsInFridge = new ArrayList<Product>(0);
        for (int i = 0 ; i < 50 ; i++)
            productsInFridge.add(i,new Product("Product #" + i, ""));

        productsInFridgeRecyclerView = (RecyclerView) findViewById(R.id.products_recycler_view);

        productsLayoutManager = new GridLayoutManager(this, 3);
        productsInFridgeRecyclerView.setLayoutManager(productsLayoutManager);

        productsInFridgeAdapter = new ProductsAdapter(productsInFridge, Glide.with(this));
        productsInFridgeRecyclerView.setAdapter(productsInFridgeAdapter);

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


        this.btnProduct= (ImageView) findViewById(R.id.btnProduct);
        btnProduct.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductsRecomendationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //-------------------------------------------------------------------------------------
        // SETS THE FlOATING BTNS
        
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
    }
}
