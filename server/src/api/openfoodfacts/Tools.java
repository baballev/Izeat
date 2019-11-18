package api.openfoodfacts;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.io.IOException;
import java.lang.StringBuilder;

public class Tools implements ToolsInterface{

	public static StringBuilder getProductQuery(int code) {
		
		String url = "https://world.openfoodfacts.org/api/";
		
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "Java server IzEat"); // TODO: Check that
			
			StringBuilder content;
			
			try {	
				content = new StringBuilder();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
		
		return null;
	}
	
}
