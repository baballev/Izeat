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
		User usr = new User(userFirstName,"d",19,"h",169,75,"no","no");
		JSONObject jsonUser = new JSONObject(usr);
    
		return jsonUser.toString();
	}
	
}
