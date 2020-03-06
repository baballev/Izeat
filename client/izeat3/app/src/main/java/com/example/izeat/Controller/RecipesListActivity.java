package com.example.izeat.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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

    private ImageView btnProfil;
    private ImageView btnFrigo;
    private ImageView btnProduct;
    private FloatingActionButton btnAdd;

    //The list of recipes that is gonna be displayed
    private ArrayList<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);

        recipeList = new ArrayList<Recipe>(0);
        for (int i = 0; i < 1000; i++ ) recipeList.add(new Recipe("Recipe number " + i));

        recipesRecyclerView = (RecyclerView) findViewById(R.id.recipes_recycler_view);

        layoutManager = new GridLayoutManager(this, 3);
        recipesRecyclerView.setLayoutManager(layoutManager);

        recipesAdapter = new RecipesAdapter(recipeList, Glide.with(this));
        recipesRecyclerView.setAdapter(recipesAdapter);

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

        this.btnProduct= (ImageView) findViewById(R.id.btnProduct);
        btnProduct.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductsRecomendationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        this.btnAdd=(FloatingActionButton) findViewById(R.id.btnphoto);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), add.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
