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
		int idd = Integer.parseInt(id);
		//User usr = new User(userFirstName,"d",19,"h",169,75,"no","no");
		User usr = ConnexionBD.readUser(idd);
		JSONObject jsonUser = new JSONObject(usr);
		return jsonUser.toString();
		// User usr = new User(userName); TODO 
	}
	
}
