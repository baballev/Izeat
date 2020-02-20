package com.example.izeat.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.izeat.Model.Recipe;
import com.example.izeat.R;
import com.example.izeat.View.RecipesAdapter;

import java.util.ArrayList;

public class RecipesListActivity extends AppCompatActivity {

    private RecyclerView recipesRecyclerView;
    private RecyclerView.Adapter recipesAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);

        recipesRecyclerView = (RecyclerView) findViewById(R.id.recipes_recycler_view);

        layoutManager = new GridLayoutManager(this, 3);
        recipesRecyclerView.setLayoutManager(layoutManager);

        recipesAdapter = new RecipesAdapter(recipeList);
        recipesRecyclerView.setAdapter(recipesAdapter);

    }
}
