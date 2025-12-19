package com.uilover.project1932.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.uilover.project1932.Adapter.LessionsAdapter;
import com.uilover.project1932.Data.LocalDataManager;
import com.uilover.project1932.Domain.Workout;
import com.uilover.project1932.databinding.ActivityWorkoutBinding;

public class WorkoutActivity extends AppCompatActivity {
    ActivityWorkoutBinding binding;
    private Workout workout;
    private LocalDataManager dataManager;
    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dataManager = LocalDataManager.getInstance(this);

        getObject();
        checkFavoriteStatus();
        setVariable();

        // Add click listener for favorite button if exists
        if (binding.imageView8 != null) {
            binding.imageView8.setOnClickListener(v -> toggleFavorite());
        }

        // Add click listener for Start Workout button
        if (binding.button != null) {
            binding.button.setOnClickListener(v -> startWorkout());
        }
    }

    private void getObject() {
        workout = (Workout) getIntent().getSerializableExtra("object");
        if (workout == null) {
            finish();
            return;
        }
    }

    private void checkFavoriteStatus() {
        if (workout == null) return;
        isFavorite = dataManager.isFavorite(workout.getTitle());
        updateFavoriteIcon();
    }

    private void toggleFavorite() {
        if (workout == null) return;
        
        if (isFavorite) {
            dataManager.removeFavorite(workout.getTitle());
            isFavorite = false;
            updateFavoriteIcon();
            Toast.makeText(WorkoutActivity.this, "Đã xóa khỏi yêu thích", Toast.LENGTH_SHORT).show();
        } else {
            dataManager.addFavorite(workout.getTitle());
            isFavorite = true;
            updateFavoriteIcon();
            Toast.makeText(WorkoutActivity.this, "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateFavoriteIcon() {
        // You can change the icon based on favorite status
        // For now, we'll just update the visual state
        if (binding.imageView8 != null) {
            binding.imageView8.setAlpha(isFavorite ? 1.0f : 0.5f);
        }
    }

    private void startWorkout() {
        if (workout == null) return;
        
        // Navigate to workout session screen
        Intent intent = new Intent(WorkoutActivity.this, WorkoutSessionActivity.class);
        intent.putExtra("workout", workout);
        startActivity(intent);
    }

    private void setVariable() {
        int resId = getResources().getIdentifier(workout.getPicPath(), "drawable", getPackageName());
        Glide.with(this)
                .load(resId)
                .into(binding.pic);

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.titleTxt.setText(workout.getTitle());
        binding.excerciseTxt.setText(workout.getLessions().size() + " Bài tập");
        binding.kcalTxt.setText(workout.getKcal() + " Kcal");
        binding.durationTxt.setText(workout.getDurationAll());
        binding.descriptionTxt.setText(workout.getDescription());

        binding.view3.setLayoutManager(new LinearLayoutManager(WorkoutActivity.this, LinearLayoutManager.VERTICAL, false));
        binding.view3.setAdapter(new LessionsAdapter(workout.getLessions(), workout));
    }
}
