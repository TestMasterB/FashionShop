package com.example.shoptrue.pucharse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shoptrue.Utilities.ImageListFragment;
import com.example.shoptrue.Utilities.SingleItemModule;
import com.example.shoptrue.database.CartTable;
import com.example.shoptrue.database.DatabaseInstance;
import com.example.shoptrue.databinding.ActivityBuyNowBinding;

import java.util.ArrayList;
import java.util.List;

public class BuyNow extends AppCompatActivity {

    ActivityBuyNowBinding binding;
    List<CartTable> stringImageUri;
    int imagePosition;
    RecyclerView recyclerView;
    BuyNowAdapter adapter;


    static DatabaseInstance instance;

    // Über die DatenbaseInstance wird auf die Bildinformationen zugegriffen und in stringImageUri gespeichert
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBuyNowBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        instance = DatabaseInstance.getInstance(this);
        stringImageUri = instance.databaseDao().getCardDetails();

        recyclerView = binding.cartRecyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new BuyNowAdapter(this, stringImageUri);
        recyclerView.setAdapter(adapter);

        // Ab hier wird dem User angezeigt, dass einer einen Kauf getätigt hat
        binding.cardPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BuyNow.this, "Payment is Done", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

        binding.cashOnDeliveryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BuyNow.this, "Payment is Done", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

    }
}

