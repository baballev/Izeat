import api.openfoodfacts.*;


public class Main {

	public static void main(String[] args) {

		String product = "0014100097006";
		StringBuilder sb = Tools.getProductQuery(product);
		System.out.println(sb.toString());
		
		
	}

}
