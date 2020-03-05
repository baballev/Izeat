package com.example.izeat.View;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.izeat.Model.Recipe;
import com.example.izeat.R;

public class RecipesViewHolder extends RecyclerView.ViewHolder {

    public TextView recipeName;
    public ImageButton recipeImage;

    public RecipesViewHolder(View v) {
        super(v);
        recipeName = v.findViewById(R.id.recipe_name);
        recipeImage = v.findViewById(R.id.recipe_image);
    }



    public void updateHolder(Recipe p, RequestManager glide){

        recipeName.setText(p.getRecipeName());
        glide.load(p.getImageUrl()).into(recipeImage);
    }
}
