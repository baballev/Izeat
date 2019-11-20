import org.json.JSONObject;

import api.openfoodfacts.*;


public class Main {

	public static void main(String[] args) {
		
		String code = "8076809529433"; 					// bar-code
		StringBuilder sb = Tools.getProductQuery(code); 
		Product product = new Product(sb);
		System.out.println(product.getCategories()[0]);
		JSONObject d = new JSONObject(product);			// Creating a JSONObject from a Product Object, ready to be transmitted in a .JSON file.
		System.out.println(d.toString());
		
	}

}
