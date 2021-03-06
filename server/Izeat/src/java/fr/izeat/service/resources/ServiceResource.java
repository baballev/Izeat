 package fr.izeat.service.resources;

import fr.izeat.api.openfoodfacts.Tools;
import fr.izeat.service.product.Product;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

@Path("/service")
public class ServiceResource {

    @Context
    private UriInfo context;
    
    // Creates a new instance of Service
    public ServiceResource() {
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        return "<html lang=\\\"en\\\"><body><h1>Bienvenue sur le serveur Izeat !</body></h1></html>";
    }
}
    