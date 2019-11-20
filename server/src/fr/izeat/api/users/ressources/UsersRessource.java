package fr.izeat.api.users.ressources;

import javax.ws.rs.*;
import org.json.*;

import fr.izeat.api.users.User;


@Path("/users/{username}")
public class UsersRessource {

	@GET
	@Produces("text/json")
	public String getUserDetails(@PathParam("username") String userName) {
		// TODO: 1) get info from the database
		// TODO: 2) Create a User object accordingly
		// TODO: 3) Make it a JSONObject
		// TODO: 4) Return the JSONObject.toString()
		User usr = new User(userName);
		JSONObject jsonUser = new JSONObject(usr);
		
		return jsonUser.toString();
	}
	
}
