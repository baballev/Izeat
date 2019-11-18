package api.openfoodfacts;

import java.util.HashMap;

import org.json.*;

public class Product {
	
	/* General notes:
	 * The class was built to be easily convertible to a .JSON file:
	 * if product is instanced by this class,
	 * JSONObject obj = new JSONObject(product);
	 * String json = obj.toString();
	 * will give json the structure of a .JSON file, which means it is ready to be transferred.
	 * For every getter getAttribute() in the class, there will be 
	 * "attribute": getAttribute() 
	 * in json.
	 * For example: "name": "Nutella"
	 */
	
	
	private final String name;		 // Example: "Nutella"	
	private final String quantity;	 // Example: "100 g"
	private final String imageUrl;	 // Example: "https://static.openfoodfacts.org/images/products/301/762/040/2678/front_fr.77.400.jpg"
	private int novaScore;			 // Example: 1
	private final char nutriScore;	 // Example: 'c'
	
	public Product(StringBuilder sb) {  // sb corresponds to the json file.
		String str = sb.toString();
		JSONObject jsonInfo = new JSONObject(str).getJSONObject("product");
		
		if (jsonInfo.getString("product_name_fr") != "") name = jsonInfo.getString("product_name_fr");
		else name = jsonInfo.getString("product_name");
		quantity = jsonInfo.getString("quantity");
		imageUrl = jsonInfo.getString("image_url");
		try {novaScore = Integer.parseInt(jsonInfo.getString("nova_group"));}
		catch(JSONException e) {novaScore = 0;}
		nutriScore = jsonInfo.getString("nutrition_grades").charAt(0);
	}
	public Product(String code) {  // code represents the 12 digits bar-code.
		StringBuilder productInfo = Tools.getProductQuery(code);
		String str = productInfo.toString();
		JSONObject jsonInfo = new JSONObject(str).getJSONObject("product");
		if (jsonInfo.getString("product_name_fr") != "") name = jsonInfo.getString("product_name_fr");
		else name = jsonInfo.getString("product_name");
		quantity = jsonInfo.getString("quantity");
		imageUrl = jsonInfo.getString("image_url");
		try {novaScore = Integer.parseInt(jsonInfo.getString("nova_group"));}
		catch(JSONException e) {novaScore = 0;}
		nutriScore = jsonInfo.getString("nutrition_grades").charAt(0);
	}
	
	public String getName() { // Returns the french name of the product if available, and the english one otherwise.
		return name;
	}
	
	public HashMap<String, Float> getNutriment() {
		return null; // TODO
		
	}
	
}
 