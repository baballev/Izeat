package com.example.izeat.View;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RecipesViewHolder extends RecyclerView.ViewHolder {

    public TextView recipeName;

    public RecipesViewHolder(TextView v) {
        super(v);
        recipeName = v;
    }

}
