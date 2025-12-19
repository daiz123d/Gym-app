package com.uilover.project1932.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.uilover.project1932.Data.LocalDataManager;
import com.uilover.project1932.Domain.Lession;
import com.uilover.project1932.Helper.ImageLoaderHelper;
import com.uilover.project1932.Domain.Workout;
import com.uilover.project1932.databinding.ActivityWorkoutSessionBinding;

import java.util.ArrayList;
import java.util.Locale;

public class WorkoutSessionActivity extends AppCompatActivity {
    private ActivityWorkoutSessionBinding binding;
    private Workout workout;
    private ArrayList<Lession> exercises;
    private int currentExerciseIndex = 0;
    private CountDownTimer timer;
    private long timeRemaining = 0;
    private boolean isPaused = false;
    private long totalTimeElapsed = 0;
    private long sessionStartTime = 0;
    private TextToSpeech textToSpeech;
    private MediaPlayer mediaPlayer;
    private boolean isMusicEnabled = false;
    private LocalDataManager dataManager;
    
    // Session states
    private static final int STATE_PREPARE = 0;
    private static final int STATE_WORKING = 1;
    private static final int STATE_REST = 2;
    private static final int STATE_COMPLETED = 3;
    private int currentState = STATE_PREPARE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkoutSessionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, 
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        dataManager = LocalDataManager.getInstance(this);

        getWorkoutData();
        initializeTTS();
        setupUI();
        showPrepareScreen();
    }

    private void getWorkoutData() {
        workout = (Workout) getIntent().getSerializableExtra("workout");
        if (workout == null) {
            Toast.makeText(this, "Không tìm thấy bài tập", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        exercises = workout.getLessions();
        if (exercises == null || exercises.isEmpty()) {
            exercises = new ArrayList<>();
        }
    }

    private void initializeTTS() {
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(new Locale("vi", "VN"));
                if (result == TextToSpeech.LANG_MISSING_DATA || 
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Fallback to English if Vietnamese not available
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
    }

    private void setupUI() {
        binding.backBtn.setOnClickListener(v -> showExitDialog());
        binding.musicToggle.setOnClickListener(v -> toggleMusic());
        binding.nextBtn.setOnClickListener(v -> nextExercise());
        binding.pauseBtn.setOnClickListener(v -> togglePause());
        binding.startBtn.setOnClickListener(v -> startWorkout());
        
        // Find skip rest button if exists
        if (binding.skipRestBtn != null) {
            binding.skipRestBtn.setOnClickListener(v -> nextExercise());
        }
        
        // Find rating and finish buttons
        if (binding.ratingBtn != null) {
            binding.ratingBtn.setOnClickListener(v -> showRatingDialog());
        }
        
        if (binding.finishBtn != null) {
            binding.finishBtn.setOnClickListener(v -> finish());
        }
    }

    private void showPrepareScreen() {
        currentState = STATE_PREPARE;
        binding.prepareLayout.setVisibility(View.VISIBLE);
        binding.workoutLayout.setVisibility(View.GONE);
        binding.completedLayout.setVisibility(View.GONE);

        // Display workout info
        binding.prepareTitle.setText(workout.getTitle());
        binding.prepareDuration.setText("Tổng thời gian: " + workout.getDurationAll());
        binding.prepareKcal.setText("Calo: " + workout.getKcal() + " kcal");
        binding.prepareDifficulty.setText("Mức độ: Trung cấp");
        binding.prepareExerciseCount.setText(exercises.size() + " động tác");

        // Display exercise list
        StringBuilder exerciseList = new StringBuilder();
        for (int i = 0; i < exercises.size(); i++) {
            exerciseList.append((i + 1)).append(". ").append(exercises.get(i).getTitle());
            if (i < exercises.size() - 1) {
                exerciseList.append("\n");
            }
        }
        binding.prepareExerciseList.setText(exerciseList.toString());
    }

    private void startWorkout() {
        currentState = STATE_WORKING;
        binding.prepareLayout.setVisibility(View.GONE);
        binding.workoutLayout.setVisibility(View.VISIBLE);
        binding.completedLayout.setVisibility(View.GONE);

        sessionStartTime = System.currentTimeMillis();
        currentExerciseIndex = 0;
        startCurrentExercise();
    }

    private void startCurrentExercise() {
        if (currentExerciseIndex >= exercises.size()) {
            completeWorkout();
            return;
        }

        Lession currentExercise = exercises.get(currentExerciseIndex);
        
        // Update UI
        binding.exerciseTitle.setText(currentExercise.getTitle());
        binding.exerciseCounter.setText((currentExerciseIndex + 1) + " / " + exercises.size());
        
        // Load image from URL or drawable
        ImageLoaderHelper.loadImage(this, currentExercise.getPicPath(), binding.exerciseImage);

        // Parse duration (format: "30s" or "1min")
        String durationStr = currentExercise.getDuration();
        long durationSeconds = parseDuration(durationStr);
        timeRemaining = durationSeconds * 1000;

        startTimer(durationSeconds * 1000);
        speakExerciseName(currentExercise.getTitle());
    }

    private long parseDuration(String duration) {
        if (duration == null || duration.isEmpty()) {
            return 30; // Default 30 seconds
        }
        duration = duration.toLowerCase().trim();
        if (duration.endsWith("s")) {
            return Long.parseLong(duration.replace("s", "").trim());
        } else if (duration.endsWith("min")) {
            return Long.parseLong(duration.replace("min", "").trim()) * 60;
        } else if (duration.contains(":")) {
            String[] parts = duration.split(":");
            return Long.parseLong(parts[0]) * 60 + Long.parseLong(parts[1]);
        }
        return 30;
    }

    private long parseDurationToMinutes(String duration) {
        if (duration == null || duration.isEmpty()) {
            return 20; // Default 20 minutes
        }
        duration = duration.toLowerCase().trim();
        try {
            if (duration.contains("phút") || duration.contains("min")) {
                String numStr = duration.replaceAll("[^0-9]", "");
                if (!numStr.isEmpty()) {
                    return Long.parseLong(numStr);
                }
            } else if (duration.contains(":")) {
                String[] parts = duration.split(":");
                return Long.parseLong(parts[0]);
            } else {
                // Try to extract number
                String numStr = duration.replaceAll("[^0-9]", "");
                if (!numStr.isEmpty()) {
                    return Long.parseLong(numStr);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 20; // Default 20 minutes
    }

    private void startTimer(long duration) {
        if (timer != null) {
            timer.cancel();
        }

        timer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = millisUntilFinished;
                updateTimerDisplay(millisUntilFinished);
                
                // Voice prompt at 10 seconds
                if (millisUntilFinished <= 10000 && millisUntilFinished > 9000) {
                    speak("Còn 10 giây nữa, cố lên!");
                }
            }

            @Override
            public void onFinish() {
                timeRemaining = 0;
                onExerciseComplete();
            }
        };

        if (!isPaused) {
            timer.start();
        }
    }

    private void updateTimerDisplay(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        
        String timeText = String.format("%02d:%02d", minutes, seconds);
        if (currentState == STATE_REST && binding.restTimer != null) {
            binding.restTimer.setText(timeText);
        } else if (binding.timerText != null) {
            binding.timerText.setText(timeText);
        }
    }

    private void onExerciseComplete() {
        currentExerciseIndex++;
        if (currentExerciseIndex < exercises.size()) {
            // Show rest period
            showRestPeriod();
        } else {
            completeWorkout();
        }
    }

    private void showRestPeriod() {
        currentState = STATE_REST;
        binding.restLayout.setVisibility(View.VISIBLE);
        binding.workoutContent.setVisibility(View.GONE);
        
        // 30 second rest
        timeRemaining = 30000;
        startTimer(30000);
        speak("Nghỉ 30 giây trước khi tiếp tục");
    }

    private void nextExercise() {
        if (currentState == STATE_REST) {
            binding.restLayout.setVisibility(View.GONE);
            binding.workoutContent.setVisibility(View.VISIBLE);
        }
        
        if (timer != null) {
            timer.cancel();
        }
        
        currentState = STATE_WORKING;
        startCurrentExercise();
    }

    private void togglePause() {
        isPaused = !isPaused;
        
        if (isPaused) {
            if (timer != null) {
                timer.cancel();
            }
            binding.pauseBtn.setText("Tiếp tục");
            speak("Đã tạm dừng");
        } else {
            startTimer(timeRemaining);
            binding.pauseBtn.setText("Tạm dừng");
        }
    }

    private void completeWorkout() {
        currentState = STATE_COMPLETED;
        binding.prepareLayout.setVisibility(View.GONE);
        binding.workoutLayout.setVisibility(View.GONE);
        binding.completedLayout.setVisibility(View.VISIBLE);

        if (timer != null) {
            timer.cancel();
        }

        // Calculate stats
        long sessionDuration = (System.currentTimeMillis() - sessionStartTime) / 1000;
        
        // Calculate calories based on actual workout time
        // Formula: calories = (base calories per minute) * (actual minutes)
        // Base calories from workout is for estimated duration, we need to calculate per minute
        int baseCalories = workout.getKcal();
        long estimatedMinutes = parseDurationToMinutes(workout.getDurationAll());
        if (estimatedMinutes == 0) estimatedMinutes = 20; // Default 20 minutes
        
        double caloriesPerMinute = (double) baseCalories / estimatedMinutes;
        long actualMinutes = sessionDuration / 60;
        int caloriesBurned = (int) Math.round(caloriesPerMinute * actualMinutes);
        
        // Save to history
        dataManager.addWorkoutHistory(caloriesBurned);
        dataManager.addDailyCaloriesBurned(caloriesBurned);
        if (workout.getTitle() != null) {
            dataManager.addWorkoutHistoryItem(workout.getTitle());
        }
        
        // Update UI
        binding.completedKcal.setText(String.valueOf(caloriesBurned));
        binding.completedDuration.setText(formatDuration(sessionDuration));
        binding.completedExercises.setText(exercises.size() + " động tác");
        
        speak("Chúc mừng! Bạn đã hoàn thành bài tập");
    }

    private String formatDuration(long seconds) {
        long minutes = seconds / 60;
        long secs = seconds % 60;
        return minutes + " phút " + secs + " giây";
    }

    private void toggleMusic() {
        isMusicEnabled = !isMusicEnabled;
        binding.musicToggle.setAlpha(isMusicEnabled ? 1.0f : 0.5f);
        
        // Note: In a real app, you would play background music here
        // For now, we'll just toggle the visual state
        if (isMusicEnabled) {
            Toast.makeText(this, "Nhạc nền đã bật", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Nhạc nền đã tắt", Toast.LENGTH_SHORT).show();
        }
    }

    private void speak(String text) {
        if (textToSpeech != null) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    private void speakExerciseName(String exerciseName) {
        speak("Bắt đầu: " + exerciseName);
    }

    private void showExitDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Thoát bài tập?")
                .setMessage("Bạn có chắc muốn thoát? Tiến độ sẽ không được lưu.")
                .setPositiveButton("Thoát", (dialog, which) -> finish())
                .setNegativeButton("Tiếp tục", null)
                .show();
    }

    private void showRatingDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Đánh giá buổi tập")
                .setMessage("Buổi tập hôm nay như thế nào?")
                .setPositiveButton("Tuyệt vời", (dialog, which) -> {
                    Toast.makeText(this, "Cảm ơn bạn!", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .setNeutralButton("Tốt", (dialog, which) -> {
                    Toast.makeText(this, "Cảm ơn bạn!", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .setNegativeButton("Bình thường", (dialog, which) -> {
                    finish();
                })
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timer != null && !isPaused) {
            timer.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer != null && !isPaused && timeRemaining > 0) {
            startTimer(timeRemaining);
        }
    }
}

