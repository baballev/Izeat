package com.example.izeat.View;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.izeat.Model.Recipe;
import com.example.izeat.R;

public class RecipesViewHolder extends RecyclerView.ViewHolder {

    public TextView recipeName;
    public ImageView recipeImage;

    public RecipesViewHolder(View v) {
        super(v);
        recipeName = v.findViewById(R.id.recipe_name);
        recipeImage = v.findViewById(R.id.recipe_image);
    }



    public void updateHolder(Recipe r, RequestManager glide){

        recipeName.setText(r.getRecipeName());
        String url = r.getImageUrl();
        if ( url != "") {
            glide.load(url).into(recipeImage);
        }
        else {
            recipeImage.setImageResource(R.drawable.recipes);
        }
    }
}
