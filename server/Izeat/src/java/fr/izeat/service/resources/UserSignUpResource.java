package fr.izeat.service.resources;

import fr.izeat.api.connexionBD.ConnexionBD;
import fr.izeat.service.user.User;

import javax.ws.rs.Produces;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Ala
 */

@Path("/user/signup/{Firstname}/{Lastname}/{age}/{gender}/{height}/{weight}/{vegan}/{vegetarian}/{palmoil}/{password}/{email}/") // HTTPS IS MANDATORY!
public class UserSignUpResource {
// ToDo: Throw an exception when vegan but not vegetarian.
   @GET
   @Produces(MediaType.TEXT_HTML) 
   public String createUser(@PathParam("Firstname") String firstname,@PathParam("Lastname") String lastname,
   @PathParam("age") int age,@PathParam("gender") String gender,@PathParam("height") int height,@PathParam("weight") int weight,
   @PathParam("vegan") boolean vegan,@PathParam("vegetarian") boolean vegetarian,@PathParam("palmoil") boolean palmoil,@PathParam("password") String password,
   @PathParam("email") String email){
       User usr=new User(firstname,lastname,age,gender,height,weight,vegan,vegetarian,palmoil,password,email);
       ConnexionBD.addUser(usr);
       return "<html lang=\\\"en\\\"><body><h1> " + firstname + "   is signing up ! :)</body></h1></html>";
   }
   
  
   
       
   }
    
    


