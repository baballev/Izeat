package fr.izeat.service.ressources;

import javax.ws.rs.*;
import org.json.*;

import fr.izeat.service.user.User;


@Path("/user/{userFirstName}")
public class UserRessource {

	@GET
	@Produces("text/json")
	public String getUserDetails(@PathParam("userFirstName") String userFirstName) {
		// TODO: 1) get info from the database
		// TODO: 2) Create a User object accordingly
		// TODO: 3) Make it a JSONObject
		// TODO: 4) Return the JSONObject.toString()
<<<<<<< HEAD
		User usr = new User(userFirstName,"d",19,"h",169,75,"no","no");
		JSONObject jsonUser = new JSONObject(usr);
    
		return jsonUser.toString();
=======
		// User usr = new User(userName); TODO
		// JSONObject jsonUser = new JSONObject(usr);
                    
		// return jsonUser.toString();
                return null;
>>>>>>> 6b7fbcb783c6fd45c183e83c73c4a703fac8b3bc
	}
	
}
