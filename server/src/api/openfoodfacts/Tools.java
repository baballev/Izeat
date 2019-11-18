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

	public static StringBuilder getProductQuery(String code) {  // Do not spam for no reason the function or we will be blocked by OFF.

		String url = "https://world.openfoodfacts.org/api/v0/product/" + code + ".json";
		StringBuilder content = null;
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("GET");
			// connection.setRequestProperty("User-Agent", "Java server IzEat");
			try {
				BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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

}
