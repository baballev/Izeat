package com.example.izeat.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.izeat.Model.Product;
import com.example.izeat.Model.Recipe;
import com.example.izeat.R;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsViewHolder> {

    private ArrayList<Product> productslist;
    private RequestManager glide;

    public ProductsAdapter(ArrayList<Product> productsList, RequestManager glide){
        this.productslist = productsList;
        this.glide = glide;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view, parent, false);
        ProductsViewHolder productsViewHolder = new ProductsViewHolder(v);
        return productsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {

        holder.updateHolder(productslist.get(position),glide);

    }

    @Override
    public int getItemCount() {
        return productslist.size();
    }

}
