package fr.izeat.service.ressources;

import fr.izeat.api.openfoodfacts.Tools;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/product/search/{search_terms}")  // TODO: Rework the search system, only search words are used atm
public class ProductSearchRessource {
        
    @GET
    @Produces("text/json")
    public String getSearchProducts(@PathParam("search_terms") String searchTerms){
        StringBuilder sb = Tools.getSearchQuery(searchTerms, 0, 0);
        
        return null;
    }
}
