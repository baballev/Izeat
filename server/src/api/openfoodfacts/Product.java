package api.openfoodfacts;

public class Product {

	public Product(StringBuilder sb) {  // sb corresponds to the json file.
		
	}
	
	public Product(String code) { // code represents the 12 digits bar-code.
		StringBuilder productInfo = Tools.getProductQuery(code);
	}
	
	
}
