package com.uilover.project1932.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.uilover.project1932.Helper.NavigationHelper;
import com.uilover.project1932.databinding.ActivityHandbookBinding;

public class HandbookActivity extends AppCompatActivity {
    ActivityHandbookBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHandbookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setupNavigation();
        setupCardClickListeners();
    }

    private void setupNavigation() {
        NavigationHelper.setupBottomNavigation(
                binding.homeTab,
                binding.favoriteTab,
                binding.cartTab,
                binding.profileTab,
                this
        );
    }

    private void setupCardClickListeners() {
        // Card 1: Các bài tập (Exercises)
        if (binding.cardExercises != null) {
            binding.cardExercises.setOnClickListener(v -> {
                Intent intent = new Intent(HandbookActivity.this, ExercisesActivity.class);
                startActivity(intent);
            });
        }

        // Card 2: Dinh dưỡng thể thao (Sports nutrition)
        if (binding.cardNutrition != null) {
            binding.cardNutrition.setOnClickListener(v -> {
                Intent intent = new Intent(HandbookActivity.this, NutritionActivity.class);
                startActivity(intent);
            });
        }

        // Card 3: Danh sách các nguyên liệu và lượng calo
        if (binding.cardIngredients != null) {
            binding.cardIngredients.setOnClickListener(v -> {
                Intent intent = new Intent(HandbookActivity.this, IngredientsActivity.class);
                startActivity(intent);
            });
        }

        // Card 4: Dược lý học (Pharmacology)
        if (binding.cardPharmacology != null) {
            binding.cardPharmacology.setOnClickListener(v -> {
                Intent intent = new Intent(HandbookActivity.this, PharmacologyActivity.class);
                startActivity(intent);
            });
        }

        // Card 5: Bách khoa toàn thư (Encyclopedia)
        if (binding.cardEncyclopedia != null) {
            binding.cardEncyclopedia.setOnClickListener(v -> {
                Intent intent = new Intent(HandbookActivity.this, EncyclopediaActivity.class);
                startActivity(intent);
            });
        }
    }
}

