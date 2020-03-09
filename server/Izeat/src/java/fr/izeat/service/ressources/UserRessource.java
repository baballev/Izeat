package fr.izeat.service.ressources;

import javax.ws.rs.*;
import org.json.*;

import fr.izeat.service.user.User;
import fr.izeat.api.connexionBD.*;

@Path("/user/{userId}")
public class UserRessource {

	@GET
	@Produces("text/json")
	public String getUserDetails(@PathParam("userId") String id) {
		// TODO: 1) get info from the database
		// TODO: 2) Create a User object accordingly
		// TODO: 3) Make it a JSONObject
		// TODO: 4) Return the JSONObject.toString()
		int idd = Integer.getInteger(id);
		//User usr = new User(userFirstName,"d",19,"h",169,75,"no","no");
		
		User usr = ConnexionBD.readUser(idd);
		JSONObject jsonUser = new JSONObject(usr);
    
		return jsonUser.toString();

		// User usr = new User(userName); TODO
		// JSONObject jsonUser = new JSONObject(usr);
                    
		// return jsonUser.toString();
               

	}
	
}
