import org.json.JSONObject;

import api.openfoodfacts.*;


public class Main {

	public static void main(String[] args) {
		
		String code = "8076809529433";
		StringBuilder sb = Tools.getProductQuery(code);
		Product product = new Product(sb);
		System.out.println(product.getCategories());
		JSONObject d = new JSONObject(product);
		System.out.println(d.toString());
		
	}

}
