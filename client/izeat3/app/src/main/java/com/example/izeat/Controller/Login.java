package com.example.izeat.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.izeat.R;


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
