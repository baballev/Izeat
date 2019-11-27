package fr.izeat.service.ressources;

import fr.izeat.api.openfoodfacts.Tools;
import fr.izeat.service.product.Product;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.JSONObject;

@Path("/product/search/{search_terms}")  // TODO: Rework the search system, only search words are used atm
public class ProductSearchRessource {
        
    @GET
    @Produces("text/json")
    public String getSearchProducts(@PathParam("search_terms") String searchTerms){
        String s = Tools.getSearchQuery(searchTerms, 1, 20);
        ArrayList<Product> searchResult = Product.productsFromJSON(s);
        JSONObject jsonSearchResult = new JSONObject(searchResult);
        return jsonSearchResult.toString();
    }
}
