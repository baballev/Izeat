package com.example.izeat.View;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.izeat.Model.Product;
import com.example.izeat.Model.Recipe;
import com.example.izeat.R;

public class ProductsViewHolder extends RecyclerView.ViewHolder {

    public TextView productName;
    public ProductsViewHolder(View v) {
        super(v);
        //v.findViewById(R.id.product_name);
    }

    public void updateHolder(Product p){
        productName.setText(p.getProductName());
    }
}
