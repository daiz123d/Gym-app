package com.uilover.project1932.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uilover.project1932.Adapter.HandbookListAdapter;
import com.uilover.project1932.Adapter.WorkoutAdapter;
import com.uilover.project1932.Data.HandbookItem;
import com.uilover.project1932.Data.LocalDataManager;
import com.uilover.project1932.Data.SampleData;
import com.uilover.project1932.Domain.Workout;
import com.uilover.project1932.Helper.NavigationHelper;
import com.uilover.project1932.Helper.OpenAiHelper;
import com.uilover.project1932.R;
import com.uilover.project1932.databinding.ActivityMainBinding;
import com.uilover.project1932.Activity.HandbookActivity;
import com.uilover.project1932.Activity.ExerciseDetailActivity;
import com.uilover.project1932.Activity.TrainingActivity;

import java.util.ArrayList;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import java.text.Normalizer;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private LocalDataManager dataManager;
    private ArrayList<Workout> allWorkouts;
    private WorkoutAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dataManager = LocalDataManager.getInstance(this);
        
        loadWorkouts();
        loadStatistics();
        loadTrainingCategories();
        setupSearch();
        
        FloatingActionButton fabChat = findViewById(R.id.fab_chat);
        fabChat.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
            startActivity(intent);
        });
        
        // Setup handbook button in header
        if (binding.btnHandbook != null) {
            binding.btnHandbook.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, HandbookActivity.class);
                startActivity(intent);
            });
        }
        
        // Setup "Xem tất cả" button for training
        if (binding.textView12 != null) {
            binding.textView12.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ExercisesActivity.class);
                startActivity(intent);
            });
        }
        
        setupNavigation();
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
        
        // Update daily calories (eaten today)
        if (binding.textView71 != null) {
            int dailyCalories = dataManager.getDailyCalories();
            binding.textView71.setText(String.valueOf(dailyCalories));
        }
        
        // Update calories burned today
        if (binding.textView72 != null) {
            int caloriesBurned = dataManager.getDailyCaloriesBurned();
            // Format as "XXX kcal" instead of time
            binding.textView72.setText(caloriesBurned + " kcal");
        }
        
        // Update total workouts
        if (binding.textView7 != null) {
            // Format: Xw Ydays
            int weeks = stats.totalWorkouts / 7;
            int days = stats.totalWorkouts % 7;
            String workoutText = weeks > 0 ? weeks + "w " + days + "ngày" : days + " ngày";
            binding.textView7.setText(workoutText);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh statistics when returning to activity
        loadStatistics();
    }

    private void loadWorkouts() {
        allWorkouts = SampleData.getAllWorkouts();
        ArrayList<Workout> workoutList = allWorkouts;
        binding.view1.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        binding.view1.setAdapter(new WorkoutAdapter(workoutList));
    }

    private void setupSearch() {
        if (binding.editTextText == null || binding.searchResults == null) return;
        searchAdapter = new WorkoutAdapter(new ArrayList<>());
        binding.searchResults.setLayoutManager(new LinearLayoutManager(this));
        binding.searchResults.setAdapter(searchAdapter);

        binding.editTextText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterSearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private void filterSearch(String query) {
        if (binding.searchResults == null) return;
        if (query == null) query = "";
        String q = normalize(query.trim());
        if (q.isEmpty()) {
            binding.searchResults.setVisibility(View.GONE);
            return;
        }

        ArrayList<Workout> result = new ArrayList<>();
        if (allWorkouts == null) {
            allWorkouts = SampleData.getAllWorkouts();
        }
        for (Workout w : allWorkouts) {
            if (w != null && w.getTitle() != null) {
                String title = normalize(w.getTitle());
                if (title.contains(q)) {
                    result.add(w);
                }
            }
        }

        if (result.isEmpty()) {
            binding.searchResults.setVisibility(View.GONE);
        } else {
            searchAdapter = new WorkoutAdapter(result);
            binding.searchResults.setAdapter(searchAdapter);
            binding.searchResults.setVisibility(View.VISIBLE);
        }
    }

    private String normalize(String input) {
        if (input == null) return "";
        String temp = Normalizer.normalize(input, Normalizer.Form.NFD);
        return temp.replaceAll("\\p{M}", "").toLowerCase();
    }
    
    private void loadTrainingCategories() {
        if (binding.trainingRecyclerView == null) {
            return;
        }
        
        ArrayList<HandbookItem> muscleGroups = new ArrayList<>();
        
        // Hiển thị một số nhóm cơ phổ biến trên màn hình chính
        muscleGroups.add(new HandbookItem("Ngực", "pic_1", "Các bài tập ngực", ""));
        muscleGroups.add(new HandbookItem("Lưng", "pic_2", "Các bài tập lưng", ""));
        muscleGroups.add(new HandbookItem("Chân", "pic_3", "Các bài tập chân", ""));
        muscleGroups.add(new HandbookItem("Cơ tay trước", "pic_1_1", "Các bài tập bắp tay trước", ""));
        muscleGroups.add(new HandbookItem("Cơ tay sau", "pic_2_1", "Các bài tập bắp tay sau", ""));
        muscleGroups.add(new HandbookItem("Cơ bụng", "pic_3_1", "Các bài tập cơ bụng", ""));
        muscleGroups.add(new HandbookItem("Cơ delta", "pic_1_2", "Các bài tập vai", ""));
        muscleGroups.add(new HandbookItem("Cơ mông", "pic_2_2", "Các bài tập mông", ""));
        
        binding.trainingRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        HandbookListAdapter adapter = new HandbookListAdapter(muscleGroups, ExerciseDetailActivity.class);
        binding.trainingRecyclerView.setAdapter(adapter);
    }
}
