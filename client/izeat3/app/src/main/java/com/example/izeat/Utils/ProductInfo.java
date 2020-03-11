package com.example.izeat.Utils;

import android.os.Build;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductInfo {
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
    private final boolean palmOil;			// Example: true
    private final boolean vegan;	    		// Example: false
    private final boolean vegetarian;   		// Example: true TODO: Implement the "maybe field"
    private final HashMap<String, Double> nutriments;          // Example: {"sodium": 0.5f, "fat":12.3, ...}

    public ProductInfo(String s) throws JSONException {  // s corresponds to the .JSON file's content. Use Tools.getProductQuery to instantiate from a bar-code.
        JSONObject jsonContent = new JSONObject(s);
        JSONObject jsonInfo;
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
        if (!jsonInfo.isNull("ingredients_analysis_tags")){
            JSONArray analysis = jsonInfo.getJSONArray("ingredients_analysis_tags");
            palmOil = (analysis.getString(0) == "en:palm-oil"); // TODO add not sure management
            vegan = (analysis.getString(1) == "en:vegan");
            vegetarian = (analysis.getString(2) == "en:vegetarian");
        } else{
            palmOil = false;
            vegan = false;
            vegetarian = false;
        }
        JSONObject jNutriments = jsonInfo.getJSONObject("nutriments");
        nutriments = new HashMap<>();
        String[] nutrimentList = {"sodium", "fat", "fiber", "salt", "sugars", "proteins"}; // TODO: Complete with what's necessary
        for (String st : nutrimentList) {
            if(!jNutriments.isNull(st)) nutriments.put(st, jNutriments.getDouble(st));
            else nutriments.put(st, 0.0);
        }
    }

    public ProductInfo(JSONObject jsonInfo) throws JSONException{ // Constructor used for JSON files coming from Izeat server.
        if (!jsonInfo.isNull("name")) {
            name = jsonInfo.getString("name");
        } else{
            name = "Erreur_Nom";
        }
        if (!jsonInfo.isNull("quantity")) quantity = jsonInfo.getString("quantity");
        else quantity = "NONE";
        imageUrl = jsonInfo.getString("imageUrl");
        if (!jsonInfo.isNull("novaScore")) {
            novaScore = jsonInfo.getInt("novaScore");
        } else novaScore = 0;
        if (!jsonInfo.isNull("nutriScore")) nutriScore = jsonInfo.getString("nutriScore").charAt(0);
        else nutriScore = 'n'; // Default value when nutriscore not found.
        JSONArray array = jsonInfo.getJSONArray("categories");
        categories = new String[array.length()];
            for(int k = 0; k < array.length(); k++){
                categories[k] = array.get(k).toString();
            }
        if (!jsonInfo.isNull("palmOil")) palmOil = jsonInfo.getBoolean("palmOil");
        else palmOil = false;
        if(!jsonInfo.isNull("vegan")) vegan = jsonInfo.getBoolean("vegan");
        else vegan = false;
        if (!jsonInfo.isNull("vegetarian")) vegetarian = jsonInfo.getBoolean("vegetarian");
        else vegetarian = false;
        nutriments = new HashMap<>();
        String[] nutrimentList = {"sodium", "fat", "fiber", "salt", "sugars", "proteins"}; // TODO: Complete with what's necessary
        if (!jsonInfo.isNull("nutriments")) {
            JSONObject jNutriments = jsonInfo.getJSONObject("nutriments");
            for (String st : nutrimentList) {
                if (!jNutriments.isNull(st)) nutriments.put(st, jNutriments.getDouble(st));
                else nutriments.put(st, 0.0);
            }
        } else{
            for(String st : nutrimentList){
                nutriments.put(st, 0.0);
            }
        }
    }


    public static ArrayList<ProductInfo>  productsFromJSON(String s) throws JSONException {
        JSONArray jsonProducts = new JSONArray(s);
        ArrayList<ProductInfo> products = new ArrayList<ProductInfo>();
        int n = jsonProducts.length();
        for(int k = 0; k < n; k++){
            JSONObject prod = (JSONObject) jsonProducts.get(k);
            products.add(new ProductInfo(prod));
        }
        return products;
    }

    public HashMap<String, Double> getNutriments() {
        return nutriments;
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
        return palmOil;
    }

    public boolean isVegan() {	// Returns true if the product is vegan. If it is unknown, will return false by default.
        return vegan;
    }

    public boolean isVegetarian() {  // Returns true if the product is vegetarian. If it is unknown, will return false by default.
        return vegetarian;
    }


}
