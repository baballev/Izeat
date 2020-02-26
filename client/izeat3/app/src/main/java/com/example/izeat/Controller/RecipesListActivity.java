package com.example.izeat.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.izeat.Model.Recipe;
import com.example.izeat.R;
import com.example.izeat.View.RecipesAdapter;

import java.util.ArrayList;

public class RecipesListActivity extends AppCompatActivity {
    //To configure the RecyclerView
    private RecyclerView recipesRecyclerView;
    private RecyclerView.Adapter recipesAdapter;
    private RecyclerView.LayoutManager layoutManager;

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

    }
}
