package fr.izeat.service.user;

import java.sql.ResultSet;
import java.util.ArrayList;

import fr.izeat.service.nutritionEngine.Recipe;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


	

public class User {
        private final String firstname;
        private final String lastname;
        private final int age;
	private final String gender;
        private final int height;
        private final int weight;
        private final boolean vegan;
        private final boolean vegetarian;
        private final boolean palmoil;
        private final String password_hash;
        private final String email;
        private UserInfo userInfo;
	private NutritionStatusInterface status;
        
	public User(String firstname,String lastname,int age,String gender,int height,int weight,boolean vegan,boolean vegetarian,boolean palmoil,String password_hash ,String email)   {
            this.firstname = firstname;
            this.lastname = lastname;
            this.age=age;
            this.gender=gender;
            this.height=height;
            this.weight=weight;
            this.vegan=vegan; 
            this.vegetarian=vegetarian;
            this.palmoil = palmoil; 
            this.password_hash = password_hash;   
            this.email=email;
            
	}

	public User(ResultSet rst) throws SQLException{
            rst.absolute(1); 
            this.firstname = rst.getString("firstName");
            this.lastname = rst.getString("lastName");
            this.age=rst.getInt("age");
            this.gender=rst.getString("gender");
            this.height=rst.getInt("height_cm");
            this.weight=rst.getInt("weight_g");
            this.vegan=rst.getInt("vegan") == 1; // MIAM
            this.vegetarian=rst.getInt("vegetarian") == 1; // MIAM
            this.palmoil = rst.getInt("palmoil") == 1; // MIAM
            this.password_hash = rst.getString("password");
            this.email= rst.getString("email");

        }

	//public User(String passWord, String name) {
		
	//	initUserInfo(passWord,name);
		
        //}
	
	public void initUserInfo(String password, String name) {
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
        public String getFirstName() {
		return this.firstname;
        }
        public String getLastName(){
            return this.lastname;
        }
        public int getAge(){
            return this.age;
        }
        public String getGender(){
            return this.gender;
        } 
        public boolean getVegan(){
            return this.vegan;
        }
        public boolean getVegetarian(){
            return this.vegetarian;
        }
        public boolean getPalmOil(){
            return this.palmoil;
        }
        public int getHeight(){
            return this.height;
        }
        public int getWeight(){
            return this.weight;
        }
        public String getPasswordHash() {
            return password_hash;
        }
        public String getEmail() {
            return email;
        }
        

        
        
}
