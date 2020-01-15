package fr.izeat.service.ressources;

import fr.izeat.api.openfoodfacts.Tools;
import fr.izeat.service.product.Product;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.JSONObject;

@Path("/product/{code}") // Code is a 12-13 digits number representing the bar-code of the queried procuct
public class ProductRessource { 
    
    @GET
    @Produces("text/json")
    public String getProduct(@PathParam("code") String code){
        try{
        String sb = Tools.getProductQuery(code); // Fetch data from OpenFoodFacts
        Product product = new Product(sb);          // Create a corresponding product object (Format data)0
        JSONObject jsonProduct = new JSONObject(product);
        return jsonProduct.toString();// Transfer .JSON file
        }catch(Exception e){
            e.printStackTrace();
        }
            
        return null;
    }
}    
