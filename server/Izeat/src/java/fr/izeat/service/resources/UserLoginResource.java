package fr.izeat.service.resources;

import fr.izeat.api.connexionBD.ConnexionBD;
import fr.izeat.service.user.User;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.JSONObject;


public class UserLoginResource { // ToDo: CHECK FOR NULL return value
        @GET
	@Produces("text/json")
        @Path("/user/login/details/{email}/{password}") // HTTPS MANDATORY
	public String getUserDetails(@PathParam("login") String id, @PathParam("password") String password) {
		// ToDo
		User usr = ConnexionBD.readUser(1);
		JSONObject jsonUser = new JSONObject(usr);
		return jsonUser.toString();
	}
        @GET
	@Produces("text/json")
        @Path("/user/login/auth/{email}/{password}") // HTTPS MANDATORY
        public String getAuthResult(@PathParam("login") String id, @PathParam("password") String password){
            return "aaez";
        }
        
}
