package fr.izeat.api.openfoodfacts;

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
		String url = "http://fr.openfoodfacts.org/api/v0/product/" + code + ".json"; // Problèmes si on met en https car les certificats ne sont pas valides, TODO: voir comment configurer les certifs ssl
		StringBuilder content = null;
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection(); 
			connection.setRequestMethod("GET"); // Could throw ProtocolException and SecurityException.
			// connection.setRequestProperty("User-Agent", "Java server IzEat");
			try {
				BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8")); // Could throw IOException
				content = new StringBuilder();
				String line;
				while ((line = input.readLine()) != null) {  // In fact there should be only 1 line for a .JSON file.
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
		return content;  // Returns a StringBuilder Object which can be interpreted using .toString() method which corresponds to the .JSON file content.
	}

    public static String getSearchQuery(String query, int pageNumber, int pageSize) {
        
        //TODO: implement pageNumber and pageSize, tags, ...
        
        String url = "https://world.openfoodfacts.org/cgi/search.pl?search_terms=" + query + "&search_simple=1&action=process&json=1";
        StringBuilder content = null;
        try{
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            try{
                BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                content = new StringBuilder();
                String line;
                while((line = input.readLine()) != null){
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            } catch(Exception e){
                e.printStackTrace();
            } finally{
                connection.disconnect();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return content.toString();
    }
}


