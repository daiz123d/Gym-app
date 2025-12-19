package com.uilover.project1932.Activity;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.uilover.project1932.Data.HandbookItem;
import com.uilover.project1932.databinding.ActivityHandbookDetailBinding;

public class NutritionDetailActivity extends AppCompatActivity {
    ActivityHandbookDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHandbookDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setupBackButton();
        loadDetail();
    }

    private void setupBackButton() {
        if (binding.backBtn != null) {
            binding.backBtn.setOnClickListener(v -> finish());
        }
    }

    private void loadDetail() {
        HandbookItem item = (HandbookItem) getIntent().getSerializableExtra("item");
        if (item != null) {
            binding.detailTitle.setText(item.getTitle());
            binding.detailDescription.setText(item.getDescription());
            binding.detailContent.setText(item.getContent());

            int resId = getResources().getIdentifier(item.getImageName(), "drawable", getPackageName());
            if (resId != 0) {
                Glide.with(this)
                        .load(resId)
                        .into(binding.detailImage);
            }
        }
    }
}

