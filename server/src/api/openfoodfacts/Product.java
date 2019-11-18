package api.openfoodfacts;

import java.util.HashMap;

import org.json.*;

public class Product {
				  
	private final JSONObject jsonInfo;
	
	public Product(StringBuilder sb) {  // sb corresponds to the json file.
		String str = sb.toString();
		this.jsonInfo = new JSONObject(str).getJSONObject("product");
		}
	
	public Product(String code) { // code represents the 12 digits bar-code.
		StringBuilder productInfo = Tools.getProductQuery(code);
		String str = productInfo.toString();
		this.jsonInfo = new JSONObject(str).getJSONObject("product");
				
	}
	
	public String getName() { // Returns the french name of the product
		return this.jsonInfo.getString("product_name_fr").toString();
	}
	
	public HashMap<String, Float> getNutriment() {
		return null; // TODO
		
	}
	
}
 