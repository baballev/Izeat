
package fr.izeat.api.connexionBD;

import static fr.izeat.api.connexionBD.ConnexionBD.*;
import fr.izeat.service.user.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




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
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver ok");
            String url="jdbc:mysql://localhost:3306/izeat";
            String userName="root";
            String password="devala";
            System.out.println("In process");
            Connection connection=DriverManager.getConnection(url,userName
                    ,password);
            System.out.println("Connection established");
            return connection;
        }catch(Exception e){
            System.out.println("NO connection");
            return null;
        }
    }
    public static void addUser(User user){
        try{
            String query="INSERT INTO appuser(firstname,lastname,age,gender,height,weight,preferences,allergies) VALUES ('"+user.getFirstName()+"','"+user.getLastName()+"',"+user.getAge()+",'"+user.getGender()+"',"+user.getHeight()+","+user.getWeight()+",'"+user.getVegan()+"','"+user.getVegetarian()+"')";
            Connection connection = connecterDB();
            Statement state=connection.createStatement();
            state.executeUpdate(query);
            System.out.println("User added");


             
            
        
            
        }catch(SQLException e){
          System.out.println(e.getMessage());  
        }
        
    }
    public static User readUser(int id){
        try{
            Connection connection = connecterDB();
            Statement st;
            st=connection.createStatement();
            ResultSet rst = st.executeQuery("SELECT * FROM appuser WHERE id =" + Integer.toString(id));
            User user = new User(rst);


            return user;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}

