package fr.izeat.api.openfoodfacts;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.StringBuilder;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class Tools implements ToolsInterface {

	public static String getProductQuery(String code) {
                /*try{
                    InputStream content=new FileInputStream("off_product.txt");
                    InputStreamReader lecture=new InputStreamReader(content);
                    BufferedReader buff=new BufferedReader(lecture);
                    String line;
                    line=buff.readLine();
                    buff.close();
                    
                }catch(Exception e){
                    System.out.println(e.toString());
                }*/
                
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
                if (content != null){
                    return content.toString(); 
                }
                else return null;

	}
                

    public static String getSearchQuery(String query, int pageNumber, int pageSize) {
        
        //TODO: implement pageNumber and pageSize, tags, ...
           TrustManager[] httpsCertsManager = new TrustManager[]{ // Fix problems with https certs
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
                }
            };
        /*try{
            InputStream flux=new FileInputStream("off_search.txt");
            InputStreamReader lecture=new InputStreamReader(flux);
            BufferedReader buff=new BufferedReader(lecture);
            String line1;
            line1=buff.readLine();
            line2=buff.readline();
            buff.close();
            
        }catch(Exception e){
            System.out.println(e.toString());
        }*/
        String url = "https://fr.openfoodfacts.org/cgi/search.pl?search_terms=" + query + "&search_simple=1&action=process&json=1.json";
        StringBuilder content = null;
        try{
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, httpsCertsManager, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            
            HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.addRequestProperty("Accept", "text/json");
            connection.addRequestProperty("Accept-Language", "fr,fr-FR;q=0.8,en-US;q=0.5,en;q=0.3");
            connection.addRequestProperty("User-Agent", "IzEat"); // TODO: Pass parameters to customize userAgent field in the request's header          
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


