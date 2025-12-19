package com.uilover.project1932.Activity;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.uilover.project1932.Adapter.WorkoutAdapter;
import com.uilover.project1932.Data.SampleData;
import com.uilover.project1932.Domain.Workout;
import com.uilover.project1932.Helper.NavigationHelper;
import com.uilover.project1932.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private WorkoutAdapter workoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setupNavigation();
        loadAllWorkouts();
        
        // Update header text
        if (binding.textView11 != null) {
            binding.textView11.setText("Tất cả bài tập");
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

    private void loadAllWorkouts() {
        ArrayList<Workout> workouts = SampleData.getAllWorkouts();

        // Cho danh sách hiển thị dọc và full chiều cao trong ScrollView
        if (binding.view1 != null) {
            ViewGroup.LayoutParams params = binding.view1.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            binding.view1.setLayoutParams(params);

            binding.view1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            workoutAdapter = new WorkoutAdapter(workouts);
            binding.view1.setAdapter(workoutAdapter);
        }
    }
}

