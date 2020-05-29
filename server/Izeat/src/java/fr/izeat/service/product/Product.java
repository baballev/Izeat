package fr.izeat.service.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.*;

import fr.izeat.service.nutritionEngine.Constraints;
import fr.izeat.service.nutritionEngine.NutritionValues;

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
	 * 
	 * For org.json documentation, see: https://stleary.github.io/JSON-java/
     */
    private final String name;                          // Example: "Nutella"	
    private final String quantity;	 		// Example: "100 g"
    private final String imageUrl;	 		// Example: "https://static.openfoodfacts.org/images/products/301/762/040/2678/front_fr.77.400.jpg"
    private int novaScore;			 	// Example: 1
    private final char nutriScore;	 		// Example: 'c'
    private final String[] categories;  		// Example: {"Produits � tartiner", "Petits-d�jeuners", "Produits � tartiner sucr�s", "P�tes � tartiner au chocolat", ...}
    private final NutritionValues nutritionValues;  		// Example: true TODO: Implement the "maybe field"
    private final String barCode;

    public Product(String s) {  // s corresponds to the .JSON file's content. Use Tools.getProductQuery to instanciate from a bar-code.0
    	nutritionValues = new NutritionValues();
    	JSONObject jsonContent = new JSONObject(s);
        JSONObject jsonInfo;
        if (!jsonContent.isNull("code")){
            this.barCode = jsonContent.getString("code");
        } else {
            this.barCode = "00000000000000";
        }
        if (jsonContent.isNull("product")){
            jsonInfo = jsonContent;
        } else jsonInfo = jsonContent.getJSONObject("product");
        
        if (!jsonInfo.isNull("product_name_fr")) {
            name = jsonInfo.getString("product_name_fr");
        } else {
            name = jsonInfo.getString("product_name");
        }
        if (!jsonInfo.isNull("quantity")) quantity = jsonInfo.getString("quantity");
        else quantity = "NONE";
        imageUrl = jsonInfo.getString("image_url");
        if (!jsonInfo.isNull("nova_group")) {
            Object nova = jsonInfo.get("nova_group");  // There's a problem with nova-score because it is either an integer or a string on OpenFoodFacts.
            if (nova instanceof String) {
                novaScore = Integer.parseInt((String) nova);
            } else if (nova instanceof Integer) {
                Integer novaInteger = (Integer) nova;
                novaScore = novaInteger.intValue();
            }
        } else novaScore = 0;
        if (!jsonInfo.isNull("nutrition_grades")) nutriScore = jsonInfo.getString("nutrition_grades").charAt(0);
        else nutriScore = 'n'; // Default value when nutriscore not found.
        categories = jsonInfo.getString("categories").split(", ");
        
        Constraints constraints = new Constraints();
        if (!jsonInfo.isNull("ingredients_analysis_tags")){
            JSONArray analysis = jsonInfo.getJSONArray("ingredients_analysis_tags");
            
            boolean palmOil = (analysis.getString(0) == "en:palm-oil"); // TODO add not sure management
            
            if(palmOil) {
            	constraints.addConstraint("palmOil");
            }
            boolean vegan = (analysis.getString(1) == "en:vegan");  
            
            if(vegan) {
            	constraints.addConstraint("vegan");
            }
            
            boolean vegetarian = (analysis.getString(2) == "en:vegetarian");
            
            if(vegetarian) {
            	constraints.addConstraint("vegetarian");
            }

        } 
        nutritionValues.setConstraints(constraints);
        
        
        JSONObject jNutriments = jsonInfo.getJSONObject("nutriments");
        
        String[] nutrimentList = {"sodium", "fat", "fiber", "salt", "sugars", "proteins"}; // TODO: Complete with what's necessary
        for (String st : nutrimentList) {
               if(!jNutriments.isNull(st)) nutritionValues.addNutriment(st, jNutriments.getFloat(st));
               else nutritionValues.addNutriment(st, 0f);
        }
        
    }
    
    public static ArrayList<Product>  productsFromJSON(String s) {
        JSONObject jsonObj = new JSONObject(s);
        JSONArray jsonProducts = jsonObj.getJSONArray("products");
        ArrayList<Product> products = new ArrayList<Product>();
        for(Object product : jsonProducts){
            JSONObject prod = (JSONObject) product;
            if (!prod.isNull("completeness")){ 
              if(prod.getFloat("completeness") > 0.45){ // Sort products with very few information TODO: modify the cap if needed.
                  products.add(new Product(prod.toString())); 
              }   
            }
        }
        return products;
    }

    public NutritionValues getNutritionValues() {
        return nutritionValues;
    }

    public String getQuantity() { // Returns the quantity (mass) as a String. If not found/not applicable (for example: fruits), returns "NONE".
        return quantity;
    }

    public String getImageUrl() { // Returns an URL as a String to an image of the product. The image will be high definition. For a miniature, see TODO
        return imageUrl;
    }

    public int getNovaScore() { // Returns the Nova score of the product as an integer i where 0 < i < 6. Will return 0 if the Nova score is unknown.
        return novaScore;
    }

    public char getNutriScore() {  // Returns a character belonging to {a, b, c, d, e} according to the calculated nutri-score by OFF. If not found; return 'n'.
        return nutriScore;
    }

    public String[] getCategories() {  // Returns an array of String containing every category the product belongs to.
        return categories;
    }

    public String getName() { // Returns the french name of the product if available, and the english one otherwise.
        return name;
    }

    public boolean isPalmOil() {  // Returns true if the product is known to contains palm oil. If unknown, will return false by default.
        return nutritionValues.getConstraints().is("palOil");
    }

    public boolean isVegan() {	// Returns true if the product is vegan. If it is unknown, will return false by default.
    	return nutritionValues.getConstraints().is("vegan");
    }

    public boolean isVegetarian() {  // Returns true if the product is vegetarian. If it is unknown, will return false by default.
    	return nutritionValues.getConstraints().is("vegetarian");
    }
    
    public String getBarCode(){
        return this.barCode;
    }

}
