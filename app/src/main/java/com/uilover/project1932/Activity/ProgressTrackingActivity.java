package com.uilover.project1932.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.uilover.project1932.Adapter.HistoryAdapter;
import com.uilover.project1932.Data.LocalDataManager;
import com.uilover.project1932.databinding.ActivityProgressTrackingBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ProgressTrackingActivity extends AppCompatActivity {
    private ActivityProgressTrackingBinding binding;
    private LocalDataManager dataManager;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_PICK_IMAGE = 2;
    private Bitmap beforeImage;
    private Bitmap afterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProgressTrackingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dataManager = LocalDataManager.getInstance(this);

        setupUI();
        loadStatistics();
        setupCharts();
        loadHistory();
    }

    private void setupUI() {
        binding.backBtn.setOnClickListener(v -> finish());
        binding.beforeImageBtn.setOnClickListener(v -> selectBeforeImage());
        binding.afterImageBtn.setOnClickListener(v -> selectAfterImage());
        binding.compareBtn.setOnClickListener(v -> compareImages());
    }

    private void loadStatistics() {
        LocalDataManager.Stats stats = dataManager.getStats();

        // Total workouts
        binding.totalWorkoutsText.setText(String.valueOf(stats.totalWorkouts));
        
        // Total calories
        binding.totalCaloriesText.setText(String.valueOf(stats.totalKcal + 2500));
        
        // Weekly calories
        int weeklyCalories = stats.totalKcal / 4; // Approximate
        binding.weeklyCaloriesText.setText(String.valueOf(weeklyCalories));

        // Weight (stored in SharedPreferences)
        float weight = dataManager.getWeight();
        if (weight > 0) {
            binding.currentWeightText.setText(String.format(Locale.getDefault(), "%.1f kg", weight));
        } else {
            binding.currentWeightText.setText("Chưa cập nhật");
        }

        // Body measurements
        float waist = dataManager.getWaist();
        float arm = dataManager.getArm();
        float thigh = dataManager.getThigh();
        
        if (waist > 0) {
            binding.waistText.setText(String.format(Locale.getDefault(), "%.1f cm", waist));
        }
        if (arm > 0) {
            binding.armText.setText(String.format(Locale.getDefault(), "%.1f cm", arm));
        }
        if (thigh > 0) {
            binding.thighText.setText(String.format(Locale.getDefault(), "%.1f cm", thigh));
        }

        // 1RM (One Rep Max)
        float oneRM = dataManager.getOneRM();
        if (oneRM > 0) {
            binding.oneRMText.setText(String.format(Locale.getDefault(), "%.1f kg", oneRM));
        } else {
            binding.oneRMText.setText("Chưa cập nhật");
        }
    }

    private void setupCharts() {
        // Calories chart
        setupCaloriesChart();
        
        // Weight chart
        setupWeightChart();
    }

    private void setupCaloriesChart() {
        if (binding.caloriesChart == null) return;
        
        try {
            List<Entry> entries = new ArrayList<>();
            
            // Sample data for last 7 days
            LocalDataManager.Stats stats = dataManager.getStats();
            int dailyAvg = stats.totalKcal > 0 ? stats.totalKcal / 7 : 300;
            
            for (int i = 0; i < 7; i++) {
                entries.add(new Entry(i, Math.max(0, dailyAvg + (int)(Math.random() * 200 - 100))));
            }

            LineDataSet dataSet = new LineDataSet(entries, "Calories");
            dataSet.setColor(0xFFFF6B35);
            dataSet.setValueTextColor(0xFFFFFFFF);
            dataSet.setLineWidth(2f);
            dataSet.setCircleColor(0xFFFF6B35);
            dataSet.setCircleRadius(4f);
            dataSet.setDrawCircleHole(false);

            LineData lineData = new LineData(dataSet);
            binding.caloriesChart.setData(lineData);
            binding.caloriesChart.getDescription().setEnabled(false);
            binding.caloriesChart.getLegend().setEnabled(false);
            binding.caloriesChart.getXAxis().setTextColor(0xFFFFFFFF);
            binding.caloriesChart.getAxisLeft().setTextColor(0xFFFFFFFF);
            binding.caloriesChart.getAxisRight().setEnabled(false);
            binding.caloriesChart.setTouchEnabled(true);
            binding.caloriesChart.setDragEnabled(true);
            binding.caloriesChart.setScaleEnabled(true);
            binding.caloriesChart.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupWeightChart() {
        if (binding.weightChart == null) return;
        
        try {
            List<Entry> entries = new ArrayList<>();
            
            // Sample weight data for last 7 days
            float currentWeight = dataManager.getWeight();
            if (currentWeight == 0) currentWeight = 70f; // Default
            
            for (int i = 0; i < 7; i++) {
                entries.add(new Entry(i, Math.max(0, currentWeight - (7 - i) * 0.1f)));
            }

            LineDataSet dataSet = new LineDataSet(entries, "Cân nặng");
            dataSet.setColor(0xFF4ECDC4);
            dataSet.setValueTextColor(0xFFFFFFFF);
            dataSet.setLineWidth(2f);
            dataSet.setCircleColor(0xFF4ECDC4);
            dataSet.setCircleRadius(4f);
            dataSet.setDrawCircleHole(false);

            LineData lineData = new LineData(dataSet);
            binding.weightChart.setData(lineData);
            binding.weightChart.getDescription().setEnabled(false);
            binding.weightChart.getLegend().setEnabled(false);
            binding.weightChart.getXAxis().setTextColor(0xFFFFFFFF);
            binding.weightChart.getAxisLeft().setTextColor(0xFFFFFFFF);
            binding.weightChart.getAxisRight().setEnabled(false);
            binding.weightChart.setTouchEnabled(true);
            binding.weightChart.setDragEnabled(true);
            binding.weightChart.setScaleEnabled(true);
            binding.weightChart.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadHistory() {
        // Load workout history
        ArrayList<String> history = dataManager.getWorkoutHistory();
        if (history != null && !history.isEmpty()) {
            binding.historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            binding.historyRecyclerView.setAdapter(new HistoryAdapter(history));
        }
    }

    private void selectBeforeImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PICK_IMAGE);
    }

    private void selectAfterImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PICK_IMAGE + 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (resultCode == RESULT_OK && data != null) {
            try {
                Uri imageUri = data.getData();
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                
                if (requestCode == REQUEST_PICK_IMAGE) {
                    beforeImage = bitmap;
                    binding.beforeImageBtn.setText("✓ Ảnh trước đã chọn");
                    saveImage(bitmap, "before");
                } else if (requestCode == REQUEST_PICK_IMAGE + 1) {
                    afterImage = bitmap;
                    binding.afterImageBtn.setText("✓ Ảnh sau đã chọn");
                    saveImage(bitmap, "after");
                }
            } catch (IOException e) {
                Toast.makeText(this, "Lỗi khi tải ảnh", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveImage(Bitmap bitmap, String prefix) {
        try {
            File file = new File(getFilesDir(), prefix + "_" + System.currentTimeMillis() + ".jpg");
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void compareImages() {
        if (beforeImage == null || afterImage == null) {
            Toast.makeText(this, "Vui lòng chọn cả ảnh trước và sau", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Show comparison dialog or navigate to comparison screen
        Toast.makeText(this, "So sánh ảnh trước - sau", Toast.LENGTH_SHORT).show();
        // You can implement a side-by-side comparison view here
    }
}

