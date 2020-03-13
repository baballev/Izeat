package fr.izeat.service.nutritionEngine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class Recipe {
    
	private String name;
	private HashSet<FoodItems> ingredients;
        
        //ce qui suit est pour creer un objet recipe
        public int mealID;
        public String items;
        public String baking_time;
        public String meal;
        public int lipids_g;
        public int carbohydrates_g;
        public int calories_Kcal;
        public String preparation;
        public String prep_time;
        
        
        public Recipe(int mealID,String items,String baking_time,String meal,int lipids_g,int carbohydrates_g,int calories_Kcal,String preparation,String prep_time){
            this.mealID=mealID;
            this.items=items;
            this.baking_time=baking_time;
            this.meal=meal;
            this.lipids_g=lipids_g;
            this.carbohydrates_g=carbohydrates_g;
            this.calories_Kcal=calories_Kcal;
            this.preparation=preparation;
            this.prep_time=prep_time;
        }
        public Recipe (ResultSet rst) throws SQLException{
            rst.absolute(1);
            this.mealID=rst.getInt("mealID");
            this.items=rst.getString("ingredients");
            this.baking_time=rst.getString("baking_time");
            this.meal=rst.getString("meal");
            this.lipids_g=rst.getInt("lipids_g");
            this.carbohydrates_g=rst.getInt("carbohydrates_g");
            this.calories_Kcal=rst.getInt("calories_Kcal");
            this.preparation=rst.getString("preparation");
            this.prep_time=rst.getString("prep_time");

    }
    public int getMealID() {
        return mealID;
    }

    public void setMealID(int mealID) {
        this.mealID = mealID;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getBaking_time() {
        return baking_time;
    }

    public void setBaking_time(String baking_time) {
        this.baking_time = baking_time;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public int getLipids_g() {
        return lipids_g;
    }

    public void setLipids_g(int lipids_g) {
        this.lipids_g = lipids_g;
    }

    public int getCarbohydrates_g() {
        return carbohydrates_g;
    }

    public void setCarbohydrates_g(int carbohydrates_g) {
        this.carbohydrates_g = carbohydrates_g;
    }

    public int getCalories_Kcal() {
        return calories_Kcal;
    }

    public void setCalories_Kcal(int calories_Kcal) {
        this.calories_Kcal = calories_Kcal;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getPrep_time() {
        return prep_time;
    }

    public void setPrep_time(String prep_time) {
        this.prep_time = prep_time;
    }
        

	public NutritionValues computeNutritionValues(){
		//TODO : implemente method
                return null;
	}
	
}
