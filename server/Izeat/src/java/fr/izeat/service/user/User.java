package fr.izeat.service.user;

public class User {

	private final String firstname;
        private final String lastname;
        private final int age;
	private final String gender;
        private final int height;
        private final int weight;
        private final String preferences;
        private final String allergies;
        
	public User(String firstname,String lastname,int age,String gender,int height,int weight,String preferences,String allergies ) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.age=age;
            this.gender=gender;
            this.height=height;
            this.weight=weight;
            this.preferences=preferences;
            this.allergies=allergies;
            
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
        public String getPreferences(){
            return this.preferences;
        }
        public String getAllergies(){
            return this.allergies;
        }
        public int getHeight(){
            return this.height;
        }
        public int getWeight(){
            return this.weight;
        }
        
        
}
