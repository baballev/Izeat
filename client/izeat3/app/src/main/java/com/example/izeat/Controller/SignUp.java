package com.example.izeat.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.izeat.R;

public class SignUp extends AppCompatActivity {


    private TextView signup;
    private TextView sentance;
    private Button suivant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        suivant = findViewById(R.id.btn_suivant);
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recipeListIntent = new Intent(SignUp.this,RecipesListActivity.class);
                startActivity(recipeListIntent);
            }
        });


    }
}
