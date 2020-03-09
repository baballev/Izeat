package com.example.izeat.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.izeat.R;

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
/*
        try {
        String s = new ServerTools().execute("3017760589895").get();
        System.out.println(s);
        JSONObject jsonProduct;
        jsonProduct = new JSONObject(s);
        System.out.println(jsonProduct.getString("quantity"));
        } catch (Exception e) {
            e.printStackTrace();
        }

*/

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
