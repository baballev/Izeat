import api.openfoodfacts.*;


public class Main {

	public static void main(String[] args) {

		String code = "3482970008397";
		Product product = new Product(code);
		System.out.println(product.getName());
		
	}

}
