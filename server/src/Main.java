import api.openfoodfacts.*;


public class Main {

	public static void main(String[] args) {
		
		String code = "3482970008397";
		StringBuilder sb = Tools.getProductQuery(code);
		Product product = new Product(sb);
		System.out.println(product.getName());
		
	}

}
