package fr.izeat.service.user;

import fr.izeat.service.nutritionEngine.NutritionValues;

public interface NutritionStatusInterface {
	
	/* The objects that implements this interface will have the 
	 * task to create a set of recommended recipies to the user,
	 * knowing informations on his health status, prefferencies 
	 * and feedback.
	 * 
	 */

	void setStatusFromDataBase(int userId);
	/*
	 * Using a user's ID, this method will generate his current status
	 * by asking the dataBase for all entries during the last week
	 */
	
	NutritionValues getUserNeeds();
	/*
	 * generate the raw amount of nutriments that a user needs to get to it's 
	 * optimal nutrition status, taking into account it's constraints.
	 */
	
	NutritionValues setUserNeeds();
	
	
	
	
}
