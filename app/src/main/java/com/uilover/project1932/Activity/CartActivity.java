package com.uilover.project1932.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.uilover.project1932.Adapter.HistoryAdapter;
import com.uilover.project1932.Adapter.ScheduledWorkoutAdapter;
import com.uilover.project1932.Data.LocalDataManager;
import com.uilover.project1932.Data.SampleData;
import com.uilover.project1932.Data.ScheduledWorkout;
import com.uilover.project1932.Domain.Workout;
import com.uilover.project1932.Helper.NavigationHelper;
import com.uilover.project1932.databinding.ActivityCartBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    private HistoryAdapter historyAdapter;
    private ScheduledWorkoutAdapter scheduledAdapter;
    private LocalDataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        
        dataManager = LocalDataManager.getInstance(this);
        setupNavigation();
        loadData();
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

    private void loadData() {
        // Get history from LocalDataManager
        ArrayList<String> historyList = dataManager.getWorkoutHistory();
        sortHistory(historyList);
        
        if (binding.totalWorkoutsTxt != null) {
            binding.totalWorkoutsTxt.setText("Tổng số: " + historyList.size() + " bài tập");
        }
        
        if (historyList.isEmpty()) {
            if (binding.emptyHistoryTxt != null) {
                binding.emptyHistoryTxt.setVisibility(android.view.View.VISIBLE);
            }
            if (binding.historyRecyclerView != null) {
                binding.historyRecyclerView.setVisibility(android.view.View.GONE);
            }
        } else {
            if (binding.emptyHistoryTxt != null) {
                binding.emptyHistoryTxt.setVisibility(android.view.View.GONE);
            }
            if (binding.historyRecyclerView != null) {
                binding.historyRecyclerView.setVisibility(android.view.View.VISIBLE);
                binding.historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                historyAdapter = new HistoryAdapter(historyList);
                binding.historyRecyclerView.setAdapter(historyAdapter);
            }
        }

        // Scheduled workouts
        ArrayList<ScheduledWorkout> scheduled = new ArrayList<>(dataManager.getAllScheduledWorkouts());
        if (scheduled == null || scheduled.isEmpty()) {
            if (binding.emptyScheduledTxt != null) {
                binding.emptyScheduledTxt.setVisibility(android.view.View.VISIBLE);
            }
            if (binding.scheduledRecyclerView != null) {
                binding.scheduledRecyclerView.setVisibility(android.view.View.GONE);
            }
        } else {
            if (binding.emptyScheduledTxt != null) {
                binding.emptyScheduledTxt.setVisibility(android.view.View.GONE);
            }
            if (binding.scheduledRecyclerView != null) {
                binding.scheduledRecyclerView.setVisibility(android.view.View.VISIBLE);
                binding.scheduledRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                scheduledAdapter = new ScheduledWorkoutAdapter(
                        scheduled,
                        this::onScheduledClick,
                        this::onScheduledDelete
                );
                binding.scheduledRecyclerView.setAdapter(scheduledAdapter);
            }
        }
    }

    private void sortHistory(ArrayList<String> historyList) {
        if (historyList == null) return;
        Collections.sort(historyList, (a, b) -> {
            long ta = extractTimestamp(a);
            long tb = extractTimestamp(b);
            return Long.compare(tb, ta); // desc
        });
    }

    private long extractTimestamp(String item) {
        if (item == null || !item.contains(" - ")) return 0;
        String[] parts = item.split(" - ");
        if (parts.length >= 2) {
            try {
                return Long.parseLong(parts[1]);
            } catch (NumberFormatException ignored) { }
        }
        return 0;
    }

    private void onScheduledClick(ScheduledWorkout scheduledWorkout) {
        if (scheduledWorkout == null || scheduledWorkout.getWorkoutTitle() == null) return;
        Workout target = findWorkoutByTitle(scheduledWorkout.getWorkoutTitle());
        if (target != null) {
            Intent intent = new Intent(this, WorkoutActivity.class);
            intent.putExtra("object", target);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Không tìm thấy bài tập \"" + scheduledWorkout.getWorkoutTitle() + "\"", Toast.LENGTH_SHORT).show();
        }
    }

    private Workout findWorkoutByTitle(String title) {
        if (title == null) return null;
        ArrayList<Workout> all = SampleData.getAllWorkouts();
        for (Workout w : all) {
            if (w != null && title.equals(w.getTitle())) {
                return w;
            }
        }
        return null;
    }

    private void onScheduledDelete(ScheduledWorkout scheduledWorkout) {
        if (scheduledWorkout == null) return;
        dataManager.removeScheduledWorkout(
                scheduledWorkout.getDate() != null ? scheduledWorkout.getDate() : "",
                scheduledWorkout.getWorkoutTitle() != null ? scheduledWorkout.getWorkoutTitle() : ""
        );
        loadData();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        // Refresh data when returning to activity
        loadData();
    }
}
