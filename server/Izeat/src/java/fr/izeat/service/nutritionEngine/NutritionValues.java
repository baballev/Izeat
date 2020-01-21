package fr.izeat.service.nutritionEngine;

import java.util.HashMap;

public class NutritionValues {
	/*
	 * A user's nutrition status, a product from openFoodFact and a Recipe can be placed on a multi-dimensionnal graph
	 * , each dimension being a nutriment (quantity, consumption or need), or a constraint
	 * (vegan, palmOil...) 
	 * 
	 * This Class represent a point on this multi-dimensional graph.
	 */
	
	
	
	private HashMap<String, Float> nutriments = new HashMap<String, Float>(); // Example: {"sodium": 0.5f, "fat":12.3, ...}
	private Constraints constraints;
	
	public NutritionValues() {
	
	}

	public Constraints getConstraints() {
		return constraints;
	}


	public void setConstraints(Constraints constraints) {
		this.constraints = constraints;
	}
	
	public void addNutriment(String nutriment, Float quantity) {
		nutriments.put(nutriment,quantity);
	}
	
	
	
	
}
