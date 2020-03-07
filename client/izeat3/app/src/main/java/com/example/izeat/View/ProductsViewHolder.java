package com.example.izeat.View;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.izeat.Model.Product;
import com.example.izeat.Model.Recipe;
import com.example.izeat.R;

public class ProductsViewHolder extends RecyclerView.ViewHolder {

    private TextView productName;
    private ImageButton productImage;

    public ProductsViewHolder(View v) {
        super(v);
        productName = v.findViewById(R.id.product_name);
        productImage = v.findViewById(R.id.product_image);
    }

    public void updateHolder(Product p, RequestManager glide){
        productName.setText(p.getProductName());
        String url = p.getProductUrl();
        System.out.println("ViewHolder -> I'm gonna update the image");
        System.out.println("The url is : " + url);

        if (url != "") {
            System.out.println("I'm in the if with an url");
            glide.load(url).into(productImage);
        }
        else {
            System.out.println("I'm in the if with an empty url");
            productImage.setImageResource(R.drawable.icone_recette);
        }
        System.out.println("IMAGE UPDATED");
    }
}
