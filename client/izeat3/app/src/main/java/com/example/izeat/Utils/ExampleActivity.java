package com.example.izeat.Utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.izeat.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.izeat.Utils.ProductInfo.productsFromJSON;

public class ExampleActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserSignUp("Test", "Client", 34, 'f', 178, 82, false, true, true, "mot_dE_PassE123", "test.client@supermail.fr", getApplicationContext());
            }
        });

        this.getApplicationContext();

    }

    public static void getUserSignUp(String firstName, String lastName, int age, char sex, int height, int weight, boolean isVegan, boolean isVegetarian, boolean isPalmOilOK, String password, String email, Context context){
        /*
         *  This method is used when a user wants to create an account on the database. Even though
         *  the HTTP request is a GET, it will add the user to the database when the method is used
         *  correctly.
         *
         *  Parameters:
         *      firstName: String representing the first name of the user. First letter should be uppercase.
         *      lastName: String representing the last name of the user. First letter should be uppercase.
         *      age: A positive integer giving the age of the user.
         *      sex: A character representing the gender of the user. 'f' -> female, 'h' -> male
         *      height: A positive integer giving the current height of the user in centimeters.
         *      weight: A positive integer giving the current weight of the user in kilograms.
         *      isVegan: A boolean: true -> User is vegan, false otherwise.
         *      isVegetarian: A boolean: true -> User is vegetarian, false otherwise.
         *      isPalmOilOK: A boolean: true -> User is ok with products containing palm oil, false otherwise.
         *      password: A string representing the password the user will use to retrieve its data.
         *      email: A string representing the email the user will use to login to retrieve its data.
         *      context: Give the current context of the activity (use getApplicationContext()).
         *  Info obtained inside the method:
         *      An integer which, according to its value, tells whether the sign up was successful are not.
         *  Example:
         *      getUserSignUp("Jean", "Dupont", 34, 'h', 178, 82, false, true, true, "mot_dE_PassE123", "jean.dupont@supermail.fr", getApplicationContext());
         *      -> Create an account for Jean Dupont.
         *  Notes:
         *      There is no verification made on the server at the moment apart from checking if the email
         *      is already in the database. It may be necessary to check things on client such as:
         *          - If isVegan = true and isVegetarian = false
         *          - firstName and lastName length < 20
         *          - Validity of each of the field used as parameters.
         */
        String vegan = isVegan ? "1" : "0"; // Convert the booleans to string.
        String vegetarian = isVegetarian ? "1" : "0";
        String palmOil = isPalmOilOK ? "1" : "0";
        // HTTPS is mandatory for requests containing user info.
        String url = "https://izeat.r2.enst.fr/ws/Izeat/webresources/user/signup/" + firstName + "/" + lastName + "/" + Integer.toString(age) + "/" + sex + "/" + Integer.toString(height) + "/" + Integer.toString(weight) + "/" + vegan + "/" + vegetarian + "/" + palmOil + "/" + password + "/" + email;

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    public void onResponse(final JSONObject response) {
                        /***************************
                         *   BEGINING OF ACTIONS   *
                         ***************************/
                        try {
                            int response_code = response.getInt("result");
                            switch (response_code){
                                case 0:
                                    System.out.println("User was successfully added to the database on the server.");
                                    break;
                                case -1:
                                    System.out.println("A (server-sided) SQL error occurred while signing up and user was not added to the database.");
                                    break;
                                case -2:
                                    System.out.println("A user with the provided email already exists. No new user was added to the database. Please consider signing in instead.");
                                    break;
                                case -3:
                                    System.out.println("An invalid email was provided while signing up and user was not added to the database.");
                                    break;
                                default:
                                    System.out.println("An unknown error occurred while signing up.");
                                    break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        /***************************
                         *      END OF ACTIONS     *
                         ***************************/
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println(error.toString());
                    }
                });
        queue.add(jsonObjectRequest);
    }

    public static void getProductAction(String barcode, Context context){
        /*
         *  Parameters:
         *      barcode: /!\ String /!\ The barcode of the desired product information.
         *      context: Give the current context of the activity (use getApplicationContext()).
         *  Info obtained inside the method:
         *      A ProductInfo object containing all the product info.
         *  Example:
         *      getProduct("3017760589895", getApplicationContext());
         *      -> Gives information about Pépito mini rollos.
         */

        String url = "https://izeat.r2.enst.fr/ws/Izeat/webresources/product/" + barcode;

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    public void onResponse(final JSONObject response) {
                        /***************************
                         *   BEGINING OF ACTIONS   *
                         ***************************/
                        // Example: create a product object and do things with it
                        try {
                            ProductInfo produit = new ProductInfo(response);
                            System.out.println(produit.getName());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        /***************************
                         *      END OF ACTIONS     *
                         ***************************/
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println(error.toString());
                    }
                });
        queue.add(jsonObjectRequest);
    }

    public static void getSearchAction(String searchTerms, Context context){
        /*
         *  Parameters:
         *      searchTerms: A string which can contains spaces that describe the kind of product wanted.
         *      context: Give the current context of the activity (use getApplicationContext()).
         *  Info obtained inside the method:
         *      An array list of ProductInfo objects, each object representing a product found on OpenFoodFacts for the given search string.
         *  Example:
         *      getSearchAction("Jambon blanc", getApplicationContext());
         *      -> Retrieves at max 50 products on OpenFoodFacts which match with the name "Jambon blanc".
         */

        String url = "https://izeat.r2.enst.fr/ws/Izeat/webresources/product/search/" + searchTerms;

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        /***************************
                         *   BEGINING OF ACTIONS   *
                         ***************************/
                        // Example: Retrieve search results on OFF and print names.
                        try {
                            ArrayList<ProductInfo> products = productsFromJSON(response.toString());
                            for (ProductInfo product : products) {
                                System.out.println(product.getName());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        /***************************
                         *      END OF ACTIONS     *
                         ***************************/
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.err.println(error.toString());
                    }
                });
        queue.add(jsonArrayRequest);
    }
}
