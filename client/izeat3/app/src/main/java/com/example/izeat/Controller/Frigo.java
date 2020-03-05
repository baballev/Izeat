package com.example.izeat.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.izeat.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Frigo extends AppCompatActivity {

    private ImageView btnProfil;
    private ImageView btnRecipes;
    private ImageView btnProduct;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frigo);


        this.btnRecipes= (ImageView) findViewById(R.id.btnRecipes);
        btnRecipes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecipesListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        this.btnProfil= (ImageView) findViewById(R.id.btnProfil);
        btnProfil.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profil.class);
                startActivity(intent);
                finish();
            }
        });


        this.btnProduct= (ImageView) findViewById(R.id.btnProduct);
        btnProduct.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductsRecomendationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        this.btnAdd=(FloatingActionButton) findViewById(R.id.btnadd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), add.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
