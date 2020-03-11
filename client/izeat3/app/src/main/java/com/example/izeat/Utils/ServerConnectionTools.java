package com.example.izeat.Utils;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ServerConnectionTools {

    public static String getJSONObjectServer(Context context, String url){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    public void onResponse(final JSONObject response) {
                        System.out.println("Request received: " + response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println(error.toString());
                    }
                }) {
        };
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
        return jsonObjectRequest.toString();
    }
    /*
     * Method Usage:
     *  Parameters:
     *      context: Give the current context of the activity (use getApplicationContext())
     *      barcode: /!\ String /!\ The barcode of the desired product information
     *  Return value:
     *      Returns a String containing all the json file related to the product infos
     *  Example:
     *      getProduct(getApplicationContext, "3017760589895");
     *      -> Gives information about Pépito mini rollos.
     */
    public static String getProduct(Context context, String barcode){
        String url = "http://izeat.r2.enst.fr/ws/Izeat/webresources/product/" + barcode;
        return getJSONObjectServer(context, url);
    }

    public static String getSearchResults(Context context, String searchTerms){
        String url = "http://izeat.r2.enst.fr/ws/Izeat/webresources/product/search/" + searchTerms;
        return getJSONObjectServer(context, url); // TODO MANAGE JSON ARRAY
    }
}
