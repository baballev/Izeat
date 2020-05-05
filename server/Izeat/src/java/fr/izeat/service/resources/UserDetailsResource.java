
package fr.izeat.service.resources;

import fr.izeat.api.connexionBD.ConnexionBD;
import fr.izeat.service.user.User;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.JSONObject;

@Path("/user/login/details/{email}/{password}") // HTTPS MANDATORY
public class UserDetailsResource {
        @GET
	@Produces("application/json")
	public String getUserDetails(@PathParam("email") String email, @PathParam("password") String password) {
		User usr = ConnexionBD.readUser(email, password);
                JSONObject jsonUser = null;
                if (usr == null){
                    jsonUser = new JSONObject();
                } else{
                    jsonUser = new JSONObject(usr);
                }
		return jsonUser.toString();
	}
}
