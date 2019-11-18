package api.openfoodfacts;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.StringBuilder;


public class Tools implements ToolsInterface {

	public static StringBuilder getProductQuery(String code) {

		String url = "https://fr.openfoodfacts.org/api/v0/product/" + code + ".json";
		StringBuilder content = null;
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("GET");
			// connection.setRequestProperty("User-Agent", "Java server IzEat");
			try {
				BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
				content = new StringBuilder();
				String line;
				while ((line = input.readLine()) != null) {
					content.append(line);
					content.append(System.lineSeparator());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				connection.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return content;
	}

	
	public static String[] getIngredients(String code) {
		
		
		
		return null;
	}
	
	
	
}
