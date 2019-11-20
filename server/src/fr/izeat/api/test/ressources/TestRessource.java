package fr.izeat.api.test.ressources;

import javax.ws.rs.*;

@Path("/test")
public class TestRessource {
	
	@GET
	@Produces("text/plain")
	public String getMessage() {
		return "Hello World!";	
	}
	
}
