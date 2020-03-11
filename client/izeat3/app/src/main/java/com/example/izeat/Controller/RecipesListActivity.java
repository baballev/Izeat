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
import com.example.izeat.ImageRecognitionActivity;
import com.example.izeat.Model.Recipe;
import com.example.izeat.R;
import com.example.izeat.View.RecipesAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RecipesListActivity extends AppCompatActivity {
    //To configure the RecyclerView
    private RecyclerView recipesRecyclerView;
    private RecyclerView.Adapter recipesAdapter;
    private RecyclerView.LayoutManager layoutManager;

    //Navigation bar
    private ImageView btnProfil;
    private ImageView btnFrigo;
    private ImageView btnProduct;
    private FloatingActionButton btnAdd;
    private FloatingActionButton btnsearch;
    private FloatingActionButton btnphoto;

    //The list of recipes that is gonna be displayed
    private ArrayList<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);

        //-------------------------------------------------------------------------------------
        //SETS THE RECYCLER VIEW
        recipeList = new ArrayList<Recipe>(0);
        for (int i = 0; i < 1000; i++ ) recipeList.add(new Recipe("Recipe number " + i));

        recipesRecyclerView = (RecyclerView) findViewById(R.id.recipes_recycler_view);

        layoutManager = new GridLayoutManager(this, 3);
        recipesRecyclerView.setLayoutManager(layoutManager);

        recipesAdapter = new RecipesAdapter(recipeList, Glide.with(this));
        recipesRecyclerView.setAdapter(recipesAdapter);

        //-------------------------------------------------------------------------------------
        //SETS THE BOTTOM NAVIGATION BAR

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

        this.btnProduct= (ImageView) findViewById(R.id.btnProduct);
        btnProduct.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductsRecomendationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        this.btnAdd=(FloatingActionButton) findViewById(R.id.btnadd);
        this.btnphoto=(FloatingActionButton)findViewById(R.id.btnphoto);
        btnphoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent imageRecognitionIntent = new Intent(RecipesListActivity.this, ImageRecognitionActivity.class);
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
        //-------------------------------------------------------------------------------------
    }
}
