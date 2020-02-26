package com.example.izeat.View;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.izeat.Model.Recipe;
import com.example.izeat.R;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesViewHolder> {

    private ArrayList<Recipe> recipesList;

    public RecipesAdapter(ArrayList<Recipe> recipesList){
        this.recipesList = recipesList;
    }

    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_view, parent, false);
        RecipesViewHolder recipesViewHolder = new RecipesViewHolder(v);
        return recipesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder holder, int position) {

        holder.updateHolder(recipesList.get(position));

    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

}
