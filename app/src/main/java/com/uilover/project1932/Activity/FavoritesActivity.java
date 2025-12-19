package com.uilover.project1932.Activity;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.uilover.project1932.Adapter.WorkoutAdapter;
import com.uilover.project1932.Data.LocalDataManager;
import com.uilover.project1932.Data.SampleData;
import com.uilover.project1932.Domain.Workout;
import com.uilover.project1932.Helper.NavigationHelper;
import com.uilover.project1932.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Set;

public class FavoritesActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private LocalDataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dataManager = LocalDataManager.getInstance(this);
        loadFavorites();
        setupNavigation();
        
        // Update header text
        if (binding.textView11 != null) {
            binding.textView11.setText("Bài tập yêu thích");
        }
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

    private void loadFavorites() {
        Set<String> favoriteTitles = dataManager.getFavorites();
        ArrayList<Workout> allWorkouts = SampleData.getAllWorkouts();
        ArrayList<Workout> favoriteWorkouts = new ArrayList<>();
        
        for (Workout workout : allWorkouts) {
            if (favoriteTitles.contains(workout.getTitle())) {
                favoriteWorkouts.add(workout);
            }
        }
        
        if (favoriteWorkouts.isEmpty()) {
            // Show empty state message if needed
        } else {
            binding.view1.setLayoutManager(new LinearLayoutManager(FavoritesActivity.this, LinearLayoutManager.HORIZONTAL, false));
            binding.view1.setAdapter(new WorkoutAdapter(favoriteWorkouts));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFavorites();
    }
}
