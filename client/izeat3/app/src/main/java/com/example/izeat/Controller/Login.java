package com.example.izeat.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.izeat.R;
import com.example.izeat.Utils.MyAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;


public class Login extends AppCompatActivity implements MyAsyncTask.Listeners {
    private Button suivant;
    private Button inscription;

    private EditText password;
    private EditText email;

    private boolean loginVerified = false;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.adresse_mail_login);
        password = findViewById(R.id.password_login);


        suivant = findViewById(R.id.btnsuivant);
        suivant.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startAsyncTask();
            }
        });

        /*String s = Product.getProductQuery("3017760589895");
        s.length();
        JSONObject jsonProduct = null;*/

        inscription = findViewById(R.id.btninscription);
        inscription.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void startAsyncTask() {
        new MyAsyncTask(this).execute();
    }


    public static void getLogInAction(String email, String password, final Context context){
        /*
         *  adaptation of the detUserDetailsAction to make it verify if the authentification infos
         *  are okay. See getUserDetailsAction for details on the method.
         */

        String url = "https://izeat.r2.enst.fr/ws/Izeat/webresources/user/login/details/" + email + "/" + password;
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    public void onResponse(final JSONObject response) {
                        /***************************
                         *   BEGINNING OF ACTIONS   *
                         ***************************/
                        try {
                            // ToDo: Implement the test of correct login.

                        }
                        catch (JSONException e) {
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

    @Override
    public void onPreExecute() {
        Toast.makeText(getApplicationContext(),"login", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doInBackground() {
        getLogInAction(email.getText().toString(),password.getText().toString(),getApplicationContext());

    }

    @Override
    public void onPostExecute(Long success) {

        if (loginVerified) {
            Intent recipeListIntent = new Intent(Login.this, RecipesListActivity.class);
            startActivity(recipeListIntent);
            Toast.makeText(getApplicationContext(),"Tu es maintenant enregistré !", Toast.LENGTH_LONG).show();
        }

        else
            Toast.makeText(getApplicationContext(), "wrond informations please retry", Toast.LENGTH_SHORT).show();

    }
}
