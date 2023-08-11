package com.example.shoptrue.ItemsDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.shoptrue.MainActivity;
import com.example.shoptrue.Utilities.ImageListFragment;
import com.example.shoptrue.Utilities.SingleItemModule;
import com.example.shoptrue.databinding.ActivityItemDetailsBinding;
import com.example.shoptrue.pucharse.BuyNow;

public class ItemDetailsActivity extends AppCompatActivity {

    ActivityItemDetailsBinding binding;

    SingleItemModule stringImageUri;
    int imagePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemDetailsBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        // wenn intent nicht null ist, werden die URI & Position weitergegeben
        // Nummerische 0 - stellt sich, dass URI & Image immer einen Wert haben, auch wenn null
        // verhindert so Abstürze

        if (getIntent() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                stringImageUri = getIntent().getParcelableExtra(ImageListFragment.STRING_IMAGE_URI, SingleItemModule.class);
            }
            imagePosition = getIntent().getIntExtra(ImageListFragment.STRING_IMAGE_URI, 0);
        }

        // lädt URI in image1

        Glide.with(this).load(stringImageUri.getImage()).into(binding.image1);
        binding.productName.setText(stringImageUri.getName());
        binding.itemPrice.setText(stringImageUri.getPrice());
        binding.delivery.setText(stringImageUri.getDescription());
        binding.detailsTop.setText(stringImageUri.getName());
        binding.size.setText(stringImageUri.getDescription());
        binding.pattern.setText(stringImageUri.getDescription());

        // Wenn der User auf Buy Now" - Button klickt, navigiert es auf die Buy - Klasse

        binding.textActionBottom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemDetailsActivity.this, BuyNow.class);
                intent.putExtra(ImageListFragment.STRING_IMAGE_URI, stringImageUri);
                startActivity(intent);
            }
        });

        binding.textActionBottom1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemDetailsActivity.this, BuyNow.class);
                intent.putExtra(ImageListFragment.STRING_IMAGE_URI, stringImageUri);
                startActivity(intent);
            }
        });

    }
}