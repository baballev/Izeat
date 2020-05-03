package fr.izeat.service.resources;

import javax.ws.rs.*;
import org.json.*;

import fr.izeat.service.user.User;
import fr.izeat.api.connexionBD.*;

@Path("/user/{userId}")
public class UserResource {
    /* This resource is used to retrieve a user info with its Id.
     * It's only used for test purposes and won't be implemented in the end.
     */   
    
	@GET
	@Produces("text/json")
	public String getUserDetails(@PathParam("userId") String id) {
		int idd = Integer.parseInt(id);
		
		User usr = ConnexionBD.readUser(idd);
		JSONObject jsonUser = new JSONObject(usr);
		return jsonUser.toString();
		  
	}
	
}
