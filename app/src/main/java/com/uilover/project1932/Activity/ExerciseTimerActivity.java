package com.uilover.project1932.Activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.uilover.project1932.Data.ExerciseItem;
import com.uilover.project1932.Data.LocalDataManager;
import com.uilover.project1932.Helper.ImageLoaderHelper;
import com.uilover.project1932.databinding.ActivityExerciseTimerBinding;

public class ExerciseTimerActivity extends AppCompatActivity {
    ActivityExerciseTimerBinding binding;
    private ExerciseItem exercise;
    private CountDownTimer timer;
    private long timeLeftInMillis;
    private boolean timerRunning;
    private long totalTime;
    private LocalDataManager dataManager;
    private long exerciseStartTime;
    private long totalExerciseTime; // Total time spent exercising in seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExerciseTimerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dataManager = LocalDataManager.getInstance(this);
        exercise = (ExerciseItem) getIntent().getSerializableExtra("exercise");
        if (exercise == null) {
            finish();
            return;
        }

        totalExerciseTime = 0;
        setupBackButton();
        loadExerciseDetails();
        setupTimer();
    }

    private void setupBackButton() {
        if (binding.backBtn != null) {
            binding.backBtn.setOnClickListener(v -> finish());
        }
    }

    private void loadExerciseDetails() {
        if (exercise != null) {
            binding.exerciseTitle.setText(exercise.getTitle());
            binding.exerciseDescription.setText(exercise.getDescription());
            binding.exerciseInstructions.setText(exercise.getInstructions());

            // Load image from URL or drawable
            ImageLoaderHelper.loadImage(this, exercise.getImageName(), binding.exerciseImage);
        }
    }

    private void setupTimer() {
        totalTime = exercise.getDefaultRestTime() * 1000L; // Convert to milliseconds
        timeLeftInMillis = totalTime;
        updateTimerDisplay();

        if (binding.startPauseBtn != null) {
            binding.startPauseBtn.setOnClickListener(v -> {
                if (timerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            });
        }

        if (binding.resetBtn != null) {
            binding.resetBtn.setOnClickListener(v -> resetTimer());
        }
    }

    private void startTimer() {
        exerciseStartTime = System.currentTimeMillis();
        
        timer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerDisplay();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                timeLeftInMillis = 0;
                updateTimerDisplay();
                binding.startPauseBtn.setText("Bắt đầu");
                
                // Calculate and save calories when timer finishes
                calculateAndSaveCalories();
            }
        }.start();

        timerRunning = true;
        binding.startPauseBtn.setText("Tạm dừng");
    }

    private void pauseTimer() {
        if (timer != null) {
            timer.cancel();
        }
        timerRunning = false;
        binding.startPauseBtn.setText("Tiếp tục");
        
        // Add time spent to total exercise time
        if (exerciseStartTime > 0) {
            long timeSpent = (System.currentTimeMillis() - exerciseStartTime) / 1000;
            totalExerciseTime += timeSpent;
            exerciseStartTime = 0;
        }
    }

    private void resetTimer() {
        if (timer != null) {
            timer.cancel();
        }
        timerRunning = false;
        timeLeftInMillis = totalTime;
        updateTimerDisplay();
        binding.startPauseBtn.setText("Bắt đầu");
    }

    private void updateTimerDisplay() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        binding.timerText.setText(timeLeftFormatted);
    }

    private void calculateAndSaveCalories() {
        // Add remaining time if timer was running
        if (exerciseStartTime > 0) {
            long timeSpent = (System.currentTimeMillis() - exerciseStartTime) / 1000;
            totalExerciseTime += timeSpent;
            exerciseStartTime = 0;
        }
        
        // Calculate calories based on exercise time
        // Formula: Average calories burned per minute for strength training = 5-8 kcal/min
        // We'll use 6 kcal/min as average for individual exercises
        double caloriesPerMinute = 6.0; // Average for strength training exercises
        long minutes = totalExerciseTime / 60;
        if (minutes < 1) minutes = 1; // Minimum 1 minute
        
        int caloriesBurned = (int) Math.round(caloriesPerMinute * minutes);
        
        // Save calories burned
        dataManager.addDailyCaloriesBurned(caloriesBurned);
        dataManager.addWorkoutHistory(caloriesBurned);
        
        // Show notification
        Toast.makeText(this, "Đã đốt " + caloriesBurned + " calories!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
        
        // Save calories if user exits while exercising
        if (timerRunning && exerciseStartTime > 0) {
            calculateAndSaveCalories();
        }
    }
}

