package com.example.izeat.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.izeat.Model.Product;
import com.example.izeat.R;
import com.example.izeat.View.ProductsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ProductsRecomendationActivity extends AppCompatActivity {

    //PRODUCTS RECOMMENDATIONS DATA
    private ArrayList<Product> productsReco;

    //For the recycler view
    private RecyclerView productsRecoRecyclerView;
    private RecyclerView.Adapter productsRecoAdapter;
    private RecyclerView.LayoutManager productsLayoutManager;

    //navigation Bar
    private ImageView btnRecipes;
    private ImageView btnProfil;
    private ImageView btnFrigo;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_recomendation);

        //-------------------------------------------------------------------------------------
        //SETS THE RECYCLER VIEW

        productsReco = new ArrayList<Product>(0);
        for (int i = 0 ; i < 50 ; i++)
            productsReco.add(i,new Product("Product #" + i, ""));

        productsRecoRecyclerView = (RecyclerView) findViewById(R.id.products_recycler_view);

        productsLayoutManager = new GridLayoutManager(this, 3);
        productsRecoRecyclerView.setLayoutManager(productsLayoutManager);

        productsRecoAdapter = new ProductsAdapter(productsReco, Glide.with(this));
        productsRecoRecyclerView.setAdapter(productsRecoAdapter);

        //-------------------------------------------------------------------------------------
        // SETS THE NAVIGATION BAR BUTTONS

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

        this.btnAdd=(FloatingActionButton) findViewById(R.id.btnadd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), add.class);
                startActivity(intent);
                finish();

            }
        });
        //---------------------------------------------------------------------------------------
    }
}
