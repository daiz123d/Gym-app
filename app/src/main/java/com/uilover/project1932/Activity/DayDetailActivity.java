package com.uilover.project1932.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.uilover.project1932.Adapter.ScheduledWorkoutAdapter;
import com.uilover.project1932.Data.ExerciseData;
import com.uilover.project1932.Data.ExerciseItem;
import com.uilover.project1932.Data.LocalDataManager;
import com.uilover.project1932.Data.SampleData;
import com.uilover.project1932.Data.ScheduledWorkout;
import com.uilover.project1932.Domain.Workout;
import com.uilover.project1932.databinding.ActivityDayDetailBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DayDetailActivity extends AppCompatActivity {
    private ActivityDayDetailBinding binding;
    private LocalDataManager dataManager;
    private ScheduledWorkoutAdapter adapter;
    private String selectedDate;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat displayFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDayDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dataManager = LocalDataManager.getInstance(this);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        displayFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // Get date from intent
        selectedDate = getIntent().getStringExtra("date");
        if (selectedDate == null || selectedDate.isEmpty()) {
            selectedDate = dateFormat.format(Calendar.getInstance().getTime());
        }

        setupUI();
        loadWorkouts();
    }

    private void setupUI() {
        if (binding.backBtn != null) {
            binding.backBtn.setOnClickListener(v -> finish());
        }

        // Update title with date
        if (binding.dateTitle != null) {
            try {
                Date date = dateFormat.parse(selectedDate);
                String displayDate = displayFormat.format(date);
                binding.dateTitle.setText("Ngày " + displayDate);
            } catch (Exception e) {
                binding.dateTitle.setText("Chi tiết ngày tập");
            }
        }
    }

    private void loadWorkouts() {
        try {
            List<ScheduledWorkout> workouts = dataManager.getScheduledWorkoutsForDate(selectedDate);
            
            if (workouts == null || workouts.isEmpty()) {
                if (binding.emptyText != null) {
                    binding.emptyText.setVisibility(View.VISIBLE);
                }
                if (binding.workoutRecyclerView != null) {
                    binding.workoutRecyclerView.setVisibility(View.GONE);
                }
                return;
            }

            if (binding.emptyText != null) {
                binding.emptyText.setVisibility(View.GONE);
            }
            if (binding.workoutRecyclerView != null) {
                binding.workoutRecyclerView.setVisibility(View.VISIBLE);
            }

            // Convert to ArrayList for adapter
            ArrayList<ScheduledWorkout> workoutList = new ArrayList<>(workouts);
            
            adapter = new ScheduledWorkoutAdapter(workoutList, this::onWorkoutClick, this::onDeleteClick);
            binding.workoutRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            binding.workoutRecyclerView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi tải danh sách bài tập", Toast.LENGTH_SHORT).show();
        }
    }

    private void onWorkoutClick(ScheduledWorkout scheduledWorkout) {
        try {
            if (scheduledWorkout == null) return;

            if ("workout".equals(scheduledWorkout.getWorkoutType())) {
                // Find and navigate directly to WorkoutSessionActivity to start workout
                Workout workout = findWorkoutByTitle(scheduledWorkout.getWorkoutTitle());
                if (workout != null) {
                    Intent intent = new Intent(this, WorkoutSessionActivity.class);
                    intent.putExtra("workout", workout);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Không tìm thấy bài tập", Toast.LENGTH_SHORT).show();
                }
            } else if ("exercise".equals(scheduledWorkout.getWorkoutType())) {
                // Find and navigate to ExerciseTimerActivity
                ExerciseItem exercise = findExerciseByTitle(scheduledWorkout.getWorkoutTitle());
                if (exercise != null) {
                    Intent intent = new Intent(this, ExerciseTimerActivity.class);
                    intent.putExtra("exercise", exercise);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Không tìm thấy bài tập", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi mở bài tập", Toast.LENGTH_SHORT).show();
        }
    }

    private void onDeleteClick(ScheduledWorkout scheduledWorkout) {
        try {
            if (scheduledWorkout == null) return;

            dataManager.removeScheduledWorkout(selectedDate, scheduledWorkout.getWorkoutTitle());
            loadWorkouts(); // Reload list
            Toast.makeText(this, "Đã xóa " + scheduledWorkout.getWorkoutTitle(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi xóa bài tập", Toast.LENGTH_SHORT).show();
        }
    }

    private Workout findWorkoutByTitle(String title) {
        ArrayList<Workout> workouts = SampleData.getAllWorkouts();
        for (Workout workout : workouts) {
            if (workout.getTitle() != null && workout.getTitle().equals(title)) {
                return workout;
            }
        }
        return null;
    }

    private ExerciseItem findExerciseByTitle(String title) {
        ArrayList<ExerciseItem> exercises = ExerciseData.getAllExercises();
        for (ExerciseItem exercise : exercises) {
            if (exercise.getTitle() != null && exercise.getTitle().equals(title)) {
                return exercise;
            }
        }
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload when returning to activity
        loadWorkouts();
    }
}

