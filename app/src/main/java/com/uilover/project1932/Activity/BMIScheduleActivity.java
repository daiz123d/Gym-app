package com.uilover.project1932.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.uilover.project1932.Adapter.BMIScheduleAdapter;
import com.uilover.project1932.Data.BMIScheduleData;
import com.uilover.project1932.Data.LocalDataManager;
import com.uilover.project1932.databinding.ActivityBmiScheduleBinding;

import java.util.ArrayList;

/**
 * Activity hiển thị lịch tập cố định theo 3 loại người dựa trên BMI
 * - Gầy (BMI < 18.5)
 * - Bình thường (18.5 - 24.9)
 * - Béo phì (≥ 25)
 */
public class BMIScheduleActivity extends AppCompatActivity {
    
    private ActivityBmiScheduleBinding binding;
    private LocalDataManager dataManager;
    private BMIScheduleAdapter adapter;
    private String currentBmiCategory;
    private ArrayList<BMIScheduleData.DailySchedule> currentSchedule;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBmiScheduleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        
        dataManager = LocalDataManager.getInstance(this);
        
        setupUI();
        loadBmiCategory();
        setupCategorySpinner();
    }
    
    private void setupUI() {
        // Back button
        if (binding.backBtn != null) {
            binding.backBtn.setOnClickListener(v -> finish());
        }
        
        // Setup RecyclerView
        binding.scheduleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        // Title
        if (binding.titleTxt != null) {
            binding.titleTxt.setText("Lịch Tập Theo BMI");
        }
    }
    
    private void loadBmiCategory() {
        // Lấy BMI category từ SharedPreferences
        currentBmiCategory = dataManager.getBmiCategory();
        
        if (currentBmiCategory == null || currentBmiCategory.isEmpty()) {
            currentBmiCategory = BMIScheduleData.BMI_NORMAL;
            dataManager.setBmiCategory(currentBmiCategory);
        }
        
        // Cập nhật UI
        updateScheduleDisplay();
    }
    
    private void setupCategorySpinner() {
        // Tạo adapter cho spinner
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                new String[]{
                        BMIScheduleData.BMI_UNDER_WEIGHT,
                        BMIScheduleData.BMI_NORMAL,
                        BMIScheduleData.BMI_OBESE
                }
        );
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        if (binding.categorySpinner != null) {
            binding.categorySpinner.setAdapter(categoryAdapter);
            
            // Thiết lập selection listener
            binding.categorySpinner.setOnItemSelectedListener(
                    new android.widget.AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                            String selectedCategory = (String) parent.getItemAtPosition(position);
                            if (!selectedCategory.equals(currentBmiCategory)) {
                                currentBmiCategory = selectedCategory;
                                dataManager.setBmiCategory(selectedCategory);
                                updateScheduleDisplay();
                                Toast.makeText(BMIScheduleActivity.this,
                                        "Lịch tập cho người: " + selectedCategory,
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                        
                        @Override
                        public void onNothingSelected(android.widget.AdapterView<?> parent) {}
                    }
            );
            
            // Thiết lập category hiện tại
            int position = 0;
            if (BMIScheduleData.BMI_UNDER_WEIGHT.equals(currentBmiCategory)) {
                position = 0;
            } else if (BMIScheduleData.BMI_OBESE.equals(currentBmiCategory)) {
                position = 2;
            } else {
                position = 1;
            }
            binding.categorySpinner.setSelection(position);
        }
    }
    
    private void updateScheduleDisplay() {
        // Lấy lịch tập theo category
        currentSchedule = BMIScheduleData.getScheduleByCategory(currentBmiCategory);
        
        // Cập nhật description
        updateCategoryDescription();
        
        // Setup adapter
        adapter = new BMIScheduleAdapter(currentSchedule, currentBmiCategory);
        binding.scheduleRecyclerView.setAdapter(adapter);
    }
    
    private void updateCategoryDescription() {
        String description = "";
        String tips = "";
        
        if (BMIScheduleData.BMI_UNDER_WEIGHT.equalsIgnoreCase(currentBmiCategory)) {
            description = "Bạn được phân loại: GẦY (BMI < 18.5)";
            tips = "💡 Mục tiêu: Tăng cơ và tăng cân\n" +
                    "• Tăng cường tập lực lượng\n" +
                    "• Ăn nhiều protein và carbs\n" +
                    "• Tăng calories hàng ngày";
        } else if (BMIScheduleData.BMI_OBESE.equalsIgnoreCase(currentBmiCategory)) {
            description = "Bạn được phân loại: BÉO PHÌ (BMI ≥ 25)";
            tips = "💡 Mục tiêu: Giảm cân và tăng sức khỏe\n" +
                    "• Tập cardio thường xuyên\n" +
                    "• Giảm lượng calories\n" +
                    "• Tập nhẹ nhàng, tăng dần";
        } else {
            description = "Bạn được phân loại: BÌNH THƯỜNG (18.5 - 24.9)";
            tips = "💡 Mục tiêu: Duy trì sức khỏe tốt\n" +
                    "• Kết hợp cardio và tập lực\n" +
                    "• Ăn cân bằng các chất\n" +
                    "• Tập 5-6 ngày mỗi tuần";
        }
        
        if (binding.categoryDescriptionTxt != null) {
            binding.categoryDescriptionTxt.setText(description);
        }
        
        if (binding.categoryTipsTxt != null) {
            binding.categoryTipsTxt.setText(tips);
        }
    }
    
    // Public method để gọi từ MealPlanActivity sau khi tính BMI
    public static void startWithBmiCategory(AppCompatActivity activity, String bmiCategory) {
        LocalDataManager dataManager = LocalDataManager.getInstance(activity);
        dataManager.setBmiCategory(bmiCategory);
        
        Intent intent = new Intent(activity, BMIScheduleActivity.class);
        activity.startActivity(intent);
    }
}
