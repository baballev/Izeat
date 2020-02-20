package com.example.izeat.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.izeat.R;
import com.example.izeat.View.RecipesAdapter;

public class RecipesListActivity extends AppCompatActivity {

    private RecyclerView recipesRecyclerView;
    private RecyclerView.Adapter recipesAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);

        recipesRecyclerView = (RecyclerView) findViewById(R.id.recipes_recycler_view);

        layoutManager = new GridLayoutManager(this);
        recipesRecyclerView.setLayoutManager(layoutManager);

        recipesAdapter = new RecipesAdapter();

    }
}
