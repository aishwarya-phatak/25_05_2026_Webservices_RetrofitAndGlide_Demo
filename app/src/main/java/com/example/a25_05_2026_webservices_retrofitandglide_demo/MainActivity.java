package com.example.a25_05_2026_webservices_retrofitandglide_demo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewForProducts;
    ArrayList<Product> productArrayList = new ArrayList<Product>();
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        recyclerViewForProducts = findViewById(R.id.recyclerViewForProducts);
        recyclerViewForProducts.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false)
        );
        productAdapter = new ProductAdapter(productArrayList);
        recyclerViewForProducts.setAdapter(productAdapter);

        ProductService productService = ProductService.getInstance();

        productService.getProducts().enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful() && response.body() != null){
//                    Product product = response.body().getProducts().get(0);

                    productArrayList.clear();
                    productArrayList.addAll(response.body().getProducts());
                    productAdapter.notifyDataSetChanged();          //imp
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                t.getMessage();
            }
        });
    }
}