
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




public class ConnexionBD {
  /*  public static void main(String args[]){
        User usr = new User("Ala","gabsi",21,"h",16,75,"no","no");
        addUser(usr);
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
    }*/
    public static Connection connecterDB(){
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
    public static void addUser(User user){
        try{
            String query="INSERT INTO appUser(firstname,lastname,age,gender,height,weight,preferences,allergies) VALUES ('"+user.getFirstName()+"','"+user.getLastName()+"',"+user.getAge()+",'"+user.getGender()+"',"+user.getHeight()+","+user.getWeight()+",'"+user.getVegan()+"','"+user.getVegetarian()+"')";
            Connection connection = connecterDB();
            Statement state=connection.createStatement();
            state.executeUpdate(query);
            System.out.println("User added");
            connection.close();
        }catch(SQLException e){
          System.out.println(e.getMessage());  
        }
    }
    public static User readUser(int id){
        try{
            Connection connection = connecterDB();
            Statement st = connection.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM appUser WHERE id =" + Integer.toString(id));
            //System.out.println(rst.getString("firstName"));
            User user = new User(rst);
            connection.close();
            return user;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        } 
        
    }
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
    // une première version pour se connecter (à améliorer)
    public static boolean connect(String firstname,String password){
        try{
            Connection connection=connecterDB();
            Statement st=connection.createStatement();
            ResultSet rst=st.executeQuery("SELECT id FROM appUser WHERE firstname="+firstname+" AND password="+password);
            if (rst ==null){
                System.out.println("Check firstname or password");
                return false;
            }
            connection.close();
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
    
}

