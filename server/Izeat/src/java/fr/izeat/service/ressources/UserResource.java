package fr.izeat.service.ressources;

import javax.ws.rs.*;
import org.json.*;

import fr.izeat.service.user.User;
import fr.izeat.api.connexionBD.*;

@Path("/user/{userId}")
public class UserResource {
    /* This resource 
     *
    
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
