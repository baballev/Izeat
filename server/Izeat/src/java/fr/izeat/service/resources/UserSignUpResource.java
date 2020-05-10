package fr.izeat.service.resources;

import fr.izeat.api.connexionBD.ConnexionBD;
import fr.izeat.service.user.User;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

/**
 * REST Web Service
 *
 * @author Ala
 */

@Path("/user/signup/{Firstname}/{Lastname}/{age}/{gender}/{height}/{weight}/{vegan}/{vegetarian}/{palmoil}/{password}/{email}") // HTTPS IS MANDATORY!
public class UserSignUpResource {
    // ToDo: Change request to PUT/POST instead of GET.
   @GET
   @Produces("application/json") 
   public String createUser(@PathParam("Firstname") String firstname,@PathParam("Lastname") String lastname,
   @PathParam("age") int age,@PathParam("gender") String gender,@PathParam("height") int height,@PathParam("weight") int weight,
   @PathParam("vegan") boolean vegan,@PathParam("vegetarian") boolean vegetarian,@PathParam("palmoil") boolean palmoil,@PathParam("password") String password,
   @PathParam("email") String email){
       // The password is hashed at the begining of the process so that every User object will always contain the hashes only.
       String password_hash = BCrypt.hashpw(password, BCrypt.gensalt(10));
       
       User usr=new User(firstname,lastname,age,gender,height,weight,vegan,vegetarian,palmoil,password_hash,email);
       JSONObject jsonObject = new JSONObject(new Integer(ConnexionBD.addUser(usr)));
       return jsonObject.toString();
   }
      
   }
    
    


