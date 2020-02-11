package fr.izeat.service.nutritionEngine;


import java.util.HashSet;

public class Constraints {
	
	/*
	 * this class represent all the constraints that a product / recipies
	 * / user has : is it vegan, does it contains / agree to consume palm oil?...
	 */
	
	private HashSet<String> constraintsSet = new HashSet<String>();
	
	public void addConstraint(String constraint) {
		constraintsSet.add(constraint);
	}
	
	public boolean is(String constraint) {
		return constraintsSet.contains(constraint);
	}
    
    
}
