
package fr.izeat.api.connexionBD;

import static fr.izeat.api.connexionBD.ConnexionBD.*;
import fr.izeat.service.user.User;
        
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import fr.izeat.service.nutritionEngine.Recipe;
import java.util.ArrayList;
import org.mindrot.jbcrypt.*;


public class ConnexionBD {
    // This class contains every method directly linked to the database on the
    // server.
    private static String code;
    
    public static void main(String args[]){
        User usr = new User("Ala","Gabsi",21,"H",170,72,false,false,false,"sfax","agabsi.work@gmail.com");
        //addUser(usr);
        try{
            Connection connection= connecterDB();
            Statement st;
            ResultSet rst;
            st=connection.createStatement();
            rst=st.executeQuery("SELECT * FROM appuser");
            while(rst.next()){
                System.out.print(rst.getInt("id")+"  ");
                System.out.print(rst.getString("firstname"));
                System.out.println();
            }                   
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /*******************Connection to DB***************************/
    
    private static Connection connecterDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver ok");
            String url="jdbc:mysql://127.0.0.1/izeat?autoReconnect=true&useSSL=false";
            String userName="vince";
            String password="tretre3.14";
                System.out.println("In process");
            Connection connection=DriverManager.getConnection(url,userName
                    ,password);
            System.out.println("Connection established");
            return connection;
        }catch(Exception e){
            System.out.println("NO connection");
            e.printStackTrace();
            return null;
        }
    }
    
    /********************Interroger la table appUser*********************/
    public static int addUser(User user){
        // ToDo: Check for collisions;
    /*  Method Usage:
     *  Parameters:
     *      User: a user object containing all info regarding the user within which
     *            his hashed password is stored.
     *  Return value:
     *      Returns 0 if everything went smoothly and the user has been added.
     *      Otherwise, returns -1 for a SQL related error, -2 if the user 
     *      alerady exists in the table (using email as the identifier),
     *      and return -3 if an invalid email was provided.
     *  Example:
     *      if (addUser(new User("Jean", "Dupont", 22, 'h', 170, 0, 1, 1, BCrypt.hashpw("pass_word", BCrypt.gensalt(10)), "jean.dupont@mail.com")) == 0){ return "Ok";}
     */
        try{
            int vegan = user.getVegan() ? 1 : 0; // Convert the booleans to int
            int vegetarian = user.getVegetarian() ? 1 : 0;
            int palmOil = user.getPalmOil() ? 1 : 0;
            
            String query = "INSERT INTO appUser(firstName,lastName,age,gender,height_cm,weight_g,vegan,vegetarian,palmoil,password,email) VALUES"
                    + " ('" + user.getFirstName() + "','" + user.getLastName() + "'," + Integer.toString(user.getAge()) + ",'" + user.getGender() + "'," + Integer.toString(user.getHeight()) + ","
                    + Integer.toString(user.getWeight()) + "," + Integer.toString(vegan) + "," + Integer.toString(vegetarian) + "," + Integer.toString(palmOil) + ",'" + user.getPasswordHash() + "','"
                    + user.getEmail() + "');";
            System.out.println("Trying to execute this mySQL query: \n" + query);
            String check_query = "SELECT * FROM appUser WHERE email='" + user.getEmail() + "';";
            Connection connection = connecterDB();
            Statement check_state = connection.createStatement();
            ResultSet rst = check_state.executeQuery(check_query);
            if (rst.next()){
                System.out.println("User with email: " + user.getEmail() + " already exists.");
                connection.close();
                return -2;
            } else if (!user.getEmail().contains("@")){
                System.out.println("Invalid email: " + user.getEmail());
                return -3;
            }
            Statement state=connection.createStatement();
            
            state.executeUpdate(query);
            System.out.println("User added");
            connection.close();
            return 0;
        }catch(SQLException e){
          System.err.println(e.getMessage());
          return -1;
        }
    }
    public static User readUser(int id) {
    /*  Method Usage:
     *  Parameters:
     *      id: An integer which corresponds to the user id in the database.
     *          The id is unique and incremented for each user added to the database.
     *  Return value:
     *      Returns a User object containing every info in the database of the 
     *      id'th user added into the database. If no user corresponds to the 
     *      given id, returns null.
     *  Example:
     *      readUser(3);
     *  Note:
     *      This method is used for test purposes only because it's not secure at
     *      all as you don't need a password to access the user data. It will be
     *      removed in the end.
     */
        try{
            Connection connection = connecterDB();
            Statement st = connection.createStatement();
            String query = "SELECT * FROM appUser WHERE id =" + Integer.toString(id) + ";";
            System.out.println("Trying to execute this mySQL query: \n" + query);
            ResultSet rst = st.executeQuery(query);
            if (!rst.next()){ // If the result set is empty, return null
                System.err.println("No user with the id '" + Integer.toString(id) + "' found in the database.");
                connection.close();
                return null;
            }
            User user = new User(rst);
            connection.close();
            return user;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        } 
        
    }
    
    public static User readUser(String email, String password){
    /*  Method Usage:
     *  Parameters:
     *      email: The email of the user that you need to retrieve data from. 
     *             The email acts as a unique identifier of a user: There can't 
     *             be 2 users with the same email in the database.
     *      password: Non-hashed password of the given user.
     *  Return value:
     *      Returns a User object containing every info in the database. If no
     *      user corresponds to the given email, returns null.
     *  Example:
     *      readUser("jean.dupont@gmail.com", "azert123");
     */
        try{
            Connection connection = connecterDB();
            String query = "SELECT * FROM appUser WHERE email = '" + email + "';";
            Statement st = connection.createStatement();
            System.out.println("Trying to execute this mySQL query: \n" + query);
            ResultSet rst = st.executeQuery(query);
            if (!rst.next()){ // If the result set is empty, return null
                System.err.println("No user with the email '" + email + "' found in the database.");
                connection.close();
                return null;
            } else if (!BCrypt.checkpw(password, rst.getString("password"))){
                System.err.println("Invalid password for the email '" + email +"'.");
                connection.close();
                return null;
            } else{
                User user = new User(rst);
                connection.close();
                return user;   
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    public static boolean connect(String email,String password){
    /*  Method Usage:
            This method allows to check if input auth info are ok.
     *  Parameters:
     *      email: The email of the user that you need to check auth. 
     *      password: Non-hashed password of the given user.
     *  Return value:
     *      Returns true if password match the password hash in the database.
            Returns false if the password is incorrect or the user with the given email was not found.
     *  Example:
     *      connect("jean.dupont@gmail.com", "azert123");
     *  Note:
     *      Could be vulnerable to bruteforce attacks but BCrypt is pretty slow 
     *      to compute hashes so if a bruteforce occurs it might just make the whole server lags.
     */
        try{
            Connection connection=connecterDB();
            Statement st=connection.createStatement();
            ResultSet rst=st.executeQuery("SELECT id FROM appUser WHERE email="+email+";");
            if (!rst.next()){
                System.err.println("No user with the email '" + email + "' found in the database.");
                return false;
            } else if (!BCrypt.checkpw(password, rst.getString("password"))){
                connection.close();
                return false;
            } else{
                connection.close();
                return true;   
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static void infoUpdate_firstname(int id,String firstname){
        try{
            String query="UPDATE appUser "
                    + "SET firstName=  "+firstname+"WHERE id="+Integer.toString(id);
            Connection connection=connecterDB();
            Statement st=connection.createStatement();
            st.executeUpdate(query);
            connection.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }    
    }
    public static void infoUpdate_lastname(int id,String lastname){
        try{
            String query="UPDATE appUser "
                    + "SET firstName=  "+lastname+"WHERE id="+Integer.toString(id);
            Connection connection=connecterDB();
            Statement st=connection.createStatement();
            st.executeUpdate(query);
            connection.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }    
    }
    public static void infoUpdate_age(int id,int age){
        try{
            String query="UPDATE appUser "
                    + "SET age=  "+Integer.toString(age)+"WHERE id="+Integer.toString(id);
            Connection connection=connecterDB();
            Statement st=connection.createStatement();
            st.executeUpdate(query);
            connection.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }    
    }
    public static void infoUpdate_height(int id,int height){
        try{
            String query="UPDATE appUser "
                    + "SET height_cm=  "+Integer.toString(height)+"WHERE id="+Integer.toString(id);
            Connection connection=connecterDB();
            Statement st=connection.createStatement();
            st.executeUpdate(query);
            connection.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }    
    }
    public static void infoUpdate_weight(int id,int weight){
        try{
            String query="UPDATE appUser "
                    + "SET weight_g=  "+Integer.toString(weight)+"WHERE id="+Integer.toString(id);
            Connection connection=connecterDB();
            Statement st=connection.createStatement();
            st.executeUpdate(query);
            connection.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }    
    }
    
    /******************Interroger la table recipes*************************/
    
     public static Recipe readRecipe(int id){
        // le parametre id  peut etre modifier par un autre selon les besoins de recommandations
        try{
            Connection connection=connecterDB();
            Statement st = connection.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM recipes WHERE mealID="+Integer.toString(id));
            Recipe recipe=new Recipe(rst);
            connection.close();
            return(recipe);
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        } 
        
    }
     // une méthode qui retourne une liste de recette avec la valeur calorique fournie
    public static ArrayList<Recipe> readRecipe_calories(int cal){
        try{
            Connection connection=connecterDB();
            Statement st=connection.createStatement();
            ResultSet rst=st.executeQuery("SELECT mealID FROM recipes WHERE calories_Kcal= " +Integer.toString(cal));
            ArrayList<Recipe> recipes=new ArrayList<Recipe>();
            while(rst.next()){
                recipes.add(readRecipe(rst.getInt("mealID")));// c'est une liste de recette
            }
            connection.close();
            return recipes;
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        } 
    }
    /*************************Interroger la table consumption*************************/
    // une méthode pour ajouter une consommation d'un utilisateur à une date donnée
    public static void addConsumtion(int id,int cal,int mag,int calc,int sod,int lip,int pro){
        try{
            Connection connection=connecterDB();
            Statement st=connection.createStatement();
            st.executeUpdate("INSERT INTO consumption(date,proteins,lipids,sodium,calcium,magnesium,calories,userID) "
                    + "VALUES (NOW(),"+Integer.toString(pro)+Integer.toString(lip)+Integer.toString(sod)+Integer.toString(calc)
                    +Integer.toString(mag)+Integer.toString(cal)+Integer.toString(id)+")");
            connection.close();
        
        }catch(SQLException e){
            System.out.println(e.getMessage());
            
        } 
        
    }
    /*********************Interroger la table fridge*************************/
    
    public static void addToFridge(int userID ,int itemID ,int qty){
        try{
            Connection connection=connecterDB();
            Statement st=connection.createStatement();
            st.executeUpdate("INSERT INTO fridge(date,itemID,qtyPercent,userID) VALUES (NOW(),"
                    +Integer.toString(itemID)+Integer.toString(qty)+Integer.toString(userID)+")");
            connection.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void fridgeUpdate(int userID, int itemID){
         try{
            String query="DELETE FROM fridge WHERE userID="+Integer.toString(userID)+" AND itemID="+Integer.toString(itemID);
            Connection connection=connecterDB();
            Statement st=connection.createStatement();
            st.executeUpdate(query);
            connection.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void consume(int userID, int itemID,int qty){
         try{
            String query="UPDATE fridge "
                    + "SET qtyPercent=  "+Integer.toString(qty)+"WHERE itemID="+Integer.toString(itemID)
                    +" AND userID="+Integer.toString(userID);
            Connection connection=connecterDB();
            Statement st=connection.createStatement();
            st.executeUpdate(query);
            connection.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    
  
  }
    


