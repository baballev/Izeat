package com.example.izeat.View;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.izeat.Model.Product;
import com.example.izeat.Model.Recipe;

public class ProductsViewHolder extends RecyclerView.ViewHolder {

    public TextView productName;
    public ProductsViewHolder(View v) {
        super(v);
       // ButterKnife.bind(this, v);
    }

    public void updateHolder(Product p){
        productName.setText(p.getProductName());
    }
}
