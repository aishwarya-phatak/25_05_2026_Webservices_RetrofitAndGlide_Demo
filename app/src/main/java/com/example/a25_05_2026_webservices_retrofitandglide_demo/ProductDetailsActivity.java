package com.example.a25_05_2026_webservices_retrofitandglide_demo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ProductDetailsActivity extends AppCompatActivity {

    ImageView productImageView;
    TextView productIdTextView, productTitleTextView, productPriceTextView;
    Product extractedProduct;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.product_details_activity);

        productImageView = findViewById(R.id.productImageView);
        productIdTextView = findViewById(R.id.productIdTextView);
        productTitleTextView = findViewById(R.id.productTitleTextView);
        productPriceTextView = findViewById(R.id.productPriceTextView);

        extractAndBindData();
    }

    public void extractAndBindData() {
        Intent i = getIntent();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            extractedProduct = i.getSerializableExtra("product",
                    Product.class
            );
        }

        productIdTextView.setText(extractedProduct.getId() + "");
        productTitleTextView.setText(extractedProduct.getTitle());
        productPriceTextView.setText(extractedProduct.getPrice() + "");

        Glide.with(this)
                .load(extractedProduct.getThumbnail())
                .placeholder(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(productImageView);
    }
}