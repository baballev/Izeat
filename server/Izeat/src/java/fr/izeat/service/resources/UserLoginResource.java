package fr.izeat.service.resources;

import fr.izeat.api.connexionBD.ConnexionBD;
import fr.izeat.service.user.User;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.JSONObject;

@Path("/user/login/{email}/{password}") // HTTPS MANDATORY
public class UserLoginResource {
        @GET
	@Produces("text/json")
	public String getUserDetails(@PathParam("login") String id, @PathParam("password") String password) {
		// ToDo
		User usr = ConnexionBD.readUser(1);
		JSONObject jsonUser = new JSONObject(usr);
		return jsonUser.toString();
		  
	}
}
