package fr.izeat.service.user;

import java.util.ArrayList;

import fr.izeat.service.nutritionEngine.Recipe;

public class User {
	
	private UserInfo userInfo;
	private NutritionStatusInterface status;
	
	public User(String passWord, String name) {
		
		initUserInfo(passWord,name);
		
	}
	
	public void initUserInfo(String passWord, String name) {
		/*
		 * TODO : initialized userInfo from dataBase 
		 */
	}
	
	public void saveUserInfo() {
		/* TODO : implement method that saves the changes made to
		 *  it's profile in the dataBase.
		 */
	}
	
	public ArrayList<Recipe> generateRecommandation(int howManyMeals){
		return null;
	}
}
