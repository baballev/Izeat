package com.example.izeat.Model;

import android.os.Build;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Product {
    private String productName;
    private String productUrl;

    public Product(String productName, String productUrl) {
        this.productName = productName;
        this.productUrl = productUrl;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductUrl() {
        return productUrl;
    }

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

        String url = "http://izeat.r2.enst.fr/ws/Izeat/webresources/product/" + code + ".json"; // Problèmes si on met en https car les certificats ne sont pas valides, TODO: voir comment configurer les certifs ssl
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
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        content.append(System.lineSeparator());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (content !=null){
            return content.toString(); }// Returns a StringBuilder Object which can be interpreted using .toString() method which corresponds to the .JSON file content.
        else return null;
    }

}
