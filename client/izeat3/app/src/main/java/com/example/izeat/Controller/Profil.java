package com.example.izeat.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.izeat.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Profil extends AppCompatActivity {

    private ImageView btnFrigo;
    private ImageView btnProduct;
    private ImageView btnRecipes;
    private FloatingActionButton btnAdd;
    private FloatingActionButton btnsearch;
    private FloatingActionButton btnphoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        this.btnRecipes= (ImageView) findViewById(R.id.btnRecipes);
        btnRecipes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecipesListActivity.class);
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


        this.btnFrigo= (ImageView) findViewById(R.id.btnFrigo);
        btnFrigo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Frigo.class);
                startActivity(intent);
                finish();
            }
        });



        this.btnAdd=(FloatingActionButton) findViewById(R.id.btnadd2);
        this.btnphoto=(FloatingActionButton)findViewById(R.id.btnphoto2);
        this.btnsearch=(FloatingActionButton)findViewById(R.id.btnsearch2);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                btnAdd.setVisibility(View.GONE);
                btnsearch.setVisibility(View.VISIBLE);
                btnphoto.setVisibility(View.VISIBLE);

            }
        });
    }

}
