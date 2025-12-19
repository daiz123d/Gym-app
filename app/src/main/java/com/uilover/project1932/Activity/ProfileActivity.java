package com.uilover.project1932.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.uilover.project1932.Data.LocalDataManager;
import com.uilover.project1932.Helper.NavigationHelper;
import com.uilover.project1932.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    private LocalDataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dataManager = LocalDataManager.getInstance(this);
        
        setupNavigation();
        loadStatistics();
        setupClickListeners();
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

    private void loadStatistics() {
        LocalDataManager.Stats stats = dataManager.getStats();
        
        if (binding.totalKcalTxt != null) {
            binding.totalKcalTxt.setText(String.valueOf(stats.totalKcal));
        }
        if (binding.totalWorkoutsTxt != null) {
            binding.totalWorkoutsTxt.setText(String.valueOf(stats.totalWorkouts));
        }
        
        // Calculate streak
        int streak = calculateStreak(stats.totalWorkouts);
        if (binding.streakTxt != null) {
            binding.streakTxt.setText(String.valueOf(streak));
        }
        
        // Favorites count
        if (binding.favoriteCountTxt != null) {
            binding.favoriteCountTxt.setText(String.valueOf(dataManager.getFavorites().size()));
        }
    }

    private int calculateStreak(int totalWorkouts) {
        // Simple streak calculation: assume 1 workout per day
        return totalWorkouts;
    }

    private void setupClickListeners() {
        // Progress Tracking button
        if (binding.progressTrackingBtn != null) {
            binding.progressTrackingBtn.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, ProgressTrackingActivity.class);
                startActivity(intent);
            });
        }
        
        // Meal Plan button
        if (binding.mealPlanBtn != null) {
            binding.mealPlanBtn.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, MealPlanActivity.class);
                startActivity(intent);
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadStatistics();
    }
}
