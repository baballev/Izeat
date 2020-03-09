package com.example.izeat.View;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.izeat.Model.Product;
import com.example.izeat.Model.Recipe;
import com.example.izeat.R;

public class ProductsViewHolder extends RecyclerView.ViewHolder {

    private TextView productName;
    private ImageView productImage;

    public ProductsViewHolder(View v) {
        super(v);
        productName = v.findViewById(R.id.product_name);
        productImage = v.findViewById(R.id.product_image);
       // ButterKnife.bind(this, v);
    }

    public void updateHolder(Product p, RequestManager glide){
        productName.setText(p.getProductName());
        String url = p.getProductUrl();

        if (url != "") {
            glide.load(url).into(productImage);
        }

        else {
            productImage.setImageResource(R.drawable.icone_aliment);
        }
    }
}
