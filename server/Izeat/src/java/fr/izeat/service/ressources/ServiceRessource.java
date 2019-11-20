package fr.izeat.service.ressources;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

@Path("/service")
public class ServiceRessource {

    @Context
    private UriInfo context;
    
    // Creates a new instance of Service
    public ServiceRessource() {
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        return "<html lang=\\\"en\\\"><body><h1>Hello, World!!</body></h1></html>";
    }
}
