package com.example.izeat.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.izeat.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ProductsRecomendationActivity extends AppCompatActivity {

    private ImageView btnRecipes;
    private ImageView btnProfil;
    private ImageView btnFrigo;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_recomendation);


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


        this.btnFrigo= (ImageView) findViewById(R.id.btnFrigo);
        btnFrigo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Frigo.class);
                startActivity(intent);
                finish();
            }
        });

        this.btnAdd=(FloatingActionButton) findViewById(R.id.btnphoto);
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
