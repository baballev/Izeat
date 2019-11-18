package api.openfoodfacts;

import java.util.HashMap;
import java.util.Iterator;

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
	 * For example: "name": "Nutella",
	 */
	
	
	private final String name;		 			// Example: "Nutella"	
	private final String quantity;	 			// Example: "100 g"
	private final String imageUrl;	 			// Example: "https://static.openfoodfacts.org/images/products/301/762/040/2678/front_fr.77.400.jpg"
	private int novaScore;			 			// Example: 1
	private final char nutriScore;	 			// Example: 'c'
	private final String[] categories;  		// Example: {"Produits à tartiner", "Petits-déjeuners", "Produits à tartiner sucrés", "Pâtes à tartiner au chocolat", ...}
	private final boolean palmOil;				// Example: true
	private final boolean vegan;	    		// Example: false
	private final boolean vegetarian;   		// Example: true
	private HashMap<String, Float> nutriments;	// Example: {"
	
	public Product(StringBuilder sb) {  // sb corresponds to the .JSON file's content. Use Tools.getProductQuery to instanciate from a bar-code.
		String jsonContent = sb.toString();
		JSONObject jsonInfo = new JSONObject(jsonContent).getJSONObject("product");
		
		if (jsonInfo.getString("product_name_fr") != "") name = jsonInfo.getString("product_name_fr");
		else name = jsonInfo.getString("product_name");
		quantity = jsonInfo.getString("quantity");
		imageUrl = jsonInfo.getString("image_url");
		try {
			Object nova = jsonInfo.get("nova_group");  // There's a problem with nova-score because it is either an integer or a string on OpenFoodFacts.
			if (nova instanceof String) {
				novaScore = Integer.parseInt((String) nova);
			} else if(nova instanceof Integer) {
				Integer novaInteger = (Integer) nova;
				novaScore = novaInteger.intValue();
			}
		} catch(JSONException e) {
			novaScore = 0;
		}
		nutriScore = jsonInfo.getString("nutrition_grades").charAt(0);
		categories = jsonInfo.getString("categories").split(", ");
		JSONArray analysis = jsonInfo.getJSONArray("ingredients_analysis_tags");
		palmOil = (analysis.getString(0) == "en:palm-oil");
		vegan = (analysis.getString(1) == "en:vegan");
		vegetarian = (analysis.getString(2) == "en:vegetarian");
		JSONObject jNutriments = jsonInfo.getJSONObject("nutriments");
		nutriments = new HashMap<String, Float>();
		String[] nutrimentList = {"sodium", "fat", "fiber", "salt", "sugars", "proteins"}; // TODO: Complete with what's necessary
		for (String s : nutrimentList){
			nutriments.put(s, jNutriments.getFloat(s));
		}
	}
	
	public HashMap<String, Float> getNutriments() {
		return nutriments;
	}

	public String getQuantity() { // Returns the quantity (mass) as a String.
		return quantity;
	}

	public String getImageUrl() { // Returns an URL as a String to an image of the product. The image will be high definition. For a miniature, see TODO
		return imageUrl;
	}

	public int getNovaScore() { // Returns the Nova score of the product as an integer i where 0 < i < 6. Will return 0 if the Nova score is unknown.
		return novaScore;
	}

	public char getNutriScore() {  // Returns a character belonging to {a, b, c, d, e} according to the calculated nutri-score by OFF.
		return nutriScore;
	}

	public String[] getCategories() {  // Returns an array of String containing every category the product belongs to.
		return categories;
	}

	public String getName() { // Returns the french name of the product if available, and the english one otherwise.
		return name;
	}
	public boolean isPalmOil() {  // Returns true if the product is known to contains palm oil. If unknown, will return false by default.
		return palmOil;
	}

	public boolean isVegan() {	// Returns true if the product is vegan. If it is unknown, will return false by default.
		return vegan;
	}

	public boolean isVegetarian() {  // Returns true if the product is vegetarian. If it is unknown, will return false by default.
		return vegetarian;
	}
	
}
 