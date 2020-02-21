package com.example.izeat.View;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.izeat.Model.Recipe;

public class RecipesViewHolder extends RecyclerView.ViewHolder {

    public TextView recipeName;

    public RecipesViewHolder(TextView v) {
        super(v);
        recipeName = v;
    }

    public void updateHolder(Recipe r){
        recipeName.setText(r.getRecipeName());
    }
}
