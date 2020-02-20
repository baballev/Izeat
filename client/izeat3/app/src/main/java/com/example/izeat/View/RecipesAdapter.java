package com.example.izeat.View;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.izeat.Model.Recipes;
import com.example.izeat.R;

import org.w3c.dom.Text;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesViewHolder> {

    private ArrayList<Recipes> recipesList;

    public RecipesAdapter(ArrayList<Recipes> recipesList){
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

        holder.recipeName.setHolder((CharSequence) recipesList.get(position));

    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

}
