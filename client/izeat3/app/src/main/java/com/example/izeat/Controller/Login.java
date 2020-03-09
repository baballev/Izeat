package com.example.izeat.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.izeat.Model.Product;
import com.example.izeat.R;

import org.json.JSONException;
import org.json.JSONObject;


public class Login extends AppCompatActivity {
    private Button suivant;
    private Button inscription;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        suivant = findViewById(R.id.btnsuivant);
        suivant.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecipesListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        /*String s = Product.getProductQuery("3017760589895");
        s.length();
        JSONObject jsonProduct = null;
        try {
            jsonProduct = new JSONObject(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(jsonProduct.getString("quantity"));
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

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

}
