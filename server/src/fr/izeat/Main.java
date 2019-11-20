package fr.izeat;
import org.json.JSONObject;

import fr.izeat.api.openfoodfacts.*;
import fr.izeat.api.test.ressources.Product;


public class Main {

	public static void main(String[] args) {	
		
		String code = "8076809529433"; 					// bar-code
		StringBuilder sb = Tools.getProductQuery(code); 
		if (sb != null) {
			Product product = new Product(sb);
			System.out.println(product.getCategories()[0]);
			JSONObject d = new JSONObject(product);			// Creating a JSONObject from a Product Object, ready to be transmitted in a .JSON file.
			System.out.println(d.toString());		
		}
	}

}
