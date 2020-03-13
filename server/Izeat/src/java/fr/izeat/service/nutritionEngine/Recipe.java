package fr.izeat.service.nutritionEngine;

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
        

	public NutritionValues computeNutritionValues(){
		//TODO : implemente method
                return null;
	}
	
}
