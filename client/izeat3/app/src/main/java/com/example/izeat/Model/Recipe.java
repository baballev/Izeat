package com.example.izeat.Model;

public class Recipe {

    private String recipeName;
    private String recipeImageUrl;

    public Recipe(String recipeName) {
        this.recipeName = recipeName;
        recipeImageUrl = "";
    }

    public Recipe(String recipeName, String recipeImageUrl){
        this.recipeImageUrl = recipeImageUrl;
        this.recipeName = recipeName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getImageUrl() {
        return recipeImageUrl;
    }
}
