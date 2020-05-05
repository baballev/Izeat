package fr.izeat.service.resources;

import fr.izeat.api.connexionBD.ConnexionBD;    
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.JSONObject;
        
@Path("/user/login/auth/{email}/{password}") // HTTPS MANDATORY
public class UserLoginResource { // ToDo: CHECK FOR NULL return value
        @GET
	@Produces("application/json")
        public String getAuthResult(@PathParam("login") String email, @PathParam("password") String password){
            JSONObject jsonIsAuthSuccessful = new JSONObject(ConnexionBD.connect(email, password));
            return jsonIsAuthSuccessful.toString();
        }
        
}
