package com.uilover.project1932.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.uilover.project1932.Data.LocalDataManager;
import com.uilover.project1932.Data.MealItem;
import com.uilover.project1932.Adapter.MealAdapter;
import com.uilover.project1932.databinding.ActivityMealPlanBinding;

import java.util.ArrayList;

public class MealPlanActivity extends AppCompatActivity {
    private ActivityMealPlanBinding binding;
    private LocalDataManager dataManager;
    private MealAdapter breakfastAdapter;
    private MealAdapter lunchAdapter;
    private MealAdapter dinnerAdapter;
    private MealAdapter snackAdapter;
    private ArrayList<MealItem> breakfastMeals;
    private ArrayList<MealItem> lunchMeals;
    private ArrayList<MealItem> dinnerMeals;
    private ArrayList<MealItem> snackMeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMealPlanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dataManager = LocalDataManager.getInstance(this);

        setupUI();
        loadMeals();
        updateDailyCalories();
    }

    private void setupUI() {
        binding.backBtn.setOnClickListener(v -> finish());
        binding.scanBarcodeBtn.setOnClickListener(v -> scanBarcode());
        binding.addMealBtn.setOnClickListener(v -> addCustomMeal());
        if (binding.bmiBtn != null) {
            binding.bmiBtn.setOnClickListener(v -> showBmiGoalDialog());
        }
        // Add BMI Schedule button
        if (binding.scheduleBtn != null) {
            binding.scheduleBtn.setOnClickListener(v -> openBmiSchedule());
        }
    }

    private void loadMeals() {
        // Load selected meals from storage
        ArrayList<String> selectedMeals = dataManager.getSelectedMeals();
        
        // Breakfast
        breakfastMeals = getBreakfastMeals();
        restoreMealSelection(breakfastMeals, selectedMeals);
        binding.breakfastRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        breakfastAdapter = new MealAdapter(breakfastMeals);
        breakfastAdapter.setOnMealSelectionListener(this::updateDailyCalories);
        binding.breakfastRecyclerView.setAdapter(breakfastAdapter);

        // Lunch
        lunchMeals = getLunchMeals();
        restoreMealSelection(lunchMeals, selectedMeals);
        binding.lunchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        lunchAdapter = new MealAdapter(lunchMeals);
        lunchAdapter.setOnMealSelectionListener(this::updateDailyCalories);
        binding.lunchRecyclerView.setAdapter(lunchAdapter);

        // Dinner
        dinnerMeals = getDinnerMeals();
        restoreMealSelection(dinnerMeals, selectedMeals);
        binding.dinnerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dinnerAdapter = new MealAdapter(dinnerMeals);
        dinnerAdapter.setOnMealSelectionListener(this::updateDailyCalories);
        binding.dinnerRecyclerView.setAdapter(dinnerAdapter);

        // Snacks
        snackMeals = getSnackMeals();
        restoreMealSelection(snackMeals, selectedMeals);
        binding.snackRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        snackAdapter = new MealAdapter(snackMeals);
        snackAdapter.setOnMealSelectionListener(this::updateDailyCalories);
        binding.snackRecyclerView.setAdapter(snackAdapter);
    }

    private void restoreMealSelection(ArrayList<MealItem> meals, ArrayList<String> selectedMeals) {
        if (selectedMeals == null) return;
        for (MealItem meal : meals) {
            if (selectedMeals.contains(meal.getName())) {
                meal.setSelected(true);
            }
        }
    }

    private ArrayList<MealItem> getBreakfastMeals() {
        ArrayList<MealItem> meals = new ArrayList<>();
        meals.add(new MealItem("Bánh mì trứng", 350, "Bánh mì, trứng, rau", "https://images.unsplash.com/photo-1604908177730-1f006a13cb05?auto=format&fit=crop&w=800&q=80"));
        meals.add(new MealItem("Cháo yến mạch", 280, "Yến mạch, sữa, mật ong", "https://images.unsplash.com/photo-1514996937319-344454492b37?auto=format&fit=crop&w=800&q=80"));
        meals.add(new MealItem("Sinh tố chuối", 200, "Chuối, sữa, hạt chia", "https://images.unsplash.com/photo-1505253758473-96b7015fcd40?auto=format&fit=crop&w=800&q=80"));
        return meals;
    }

    private ArrayList<MealItem> getLunchMeals() {
        ArrayList<MealItem> meals = new ArrayList<>();
        meals.add(new MealItem("Cơm gà nướng", 450, "Cơm, gà nướng, rau", "https://images.unsplash.com/photo-1504674900247-0877df9cc836?auto=format&fit=crop&w=800&q=80"));
        meals.add(new MealItem("Salad cá hồi", 380, "Cá hồi, rau xanh, dầu oliu", "https://images.unsplash.com/photo-1552332386-f8dd00dc2f85?auto=format&fit=crop&w=800&q=80"));
        meals.add(new MealItem("Bún bò", 420, "Bún, thịt bò, rau", "https://images.unsplash.com/photo-1517245386807-bb43f82c33c4?auto=format&fit=crop&w=800&q=80"));
        return meals;
    }

    private ArrayList<MealItem> getDinnerMeals() {
        ArrayList<MealItem> meals = new ArrayList<>();
        meals.add(new MealItem("Cá hồi áp chảo", 400, "Cá hồi, rau củ", "https://images.unsplash.com/photo-1612874742237-6526221588e3?auto=format&fit=crop&w=800&q=80"));
        meals.add(new MealItem("Thịt gà luộc", 350, "Thịt gà, rau luộc", "https://images.unsplash.com/photo-1604908177507-78c0b8c6625e?auto=format&fit=crop&w=800&q=80"));
        meals.add(new MealItem("Canh chua cá", 320, "Cá, cà chua, rau thơm", "https://images.unsplash.com/photo-1476718406336-bb5a9690ee2a?auto=format&fit=crop&w=800&q=80"));
        return meals;
    }

    private ArrayList<MealItem> getSnackMeals() {
        ArrayList<MealItem> meals = new ArrayList<>();
        meals.add(new MealItem("Hạt điều", 150, "Hạt điều rang", "https://images.unsplash.com/photo-1504384764586-bb4cdc1707b0?auto=format&fit=crop&w=800&q=80"));
        meals.add(new MealItem("Sữa chua", 120, "Sữa chua không đường", "https://images.unsplash.com/photo-1466637574441-749b8f19452f?auto=format&fit=crop&w=800&q=80"));
        meals.add(new MealItem("Trái cây", 100, "Táo, chuối, cam", "https://images.unsplash.com/photo-1490645935967-10de6ba17061?auto=format&fit=crop&w=800&q=80"));
        return meals;
    }

    private void updateDailyCalories() {
        int totalCalories = calculateTotalCalories();
        binding.dailyCaloriesText.setText(String.valueOf(totalCalories));
        
        // Save selected meals
        saveSelectedMeals();
        
        // Save daily calories
        dataManager.setDailyCalories(totalCalories);
        
        // Calculate remaining calories (assuming daily goal is 2000)
        int dailyGoal = dataManager.getDailyCalorieGoal();
        if (dailyGoal == 0) dailyGoal = 2000; // Default
        int remaining = dailyGoal - totalCalories;
        binding.remainingCaloriesText.setText(String.valueOf(remaining));
        
        if (remaining < 0) {
            binding.remainingCaloriesText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        } else {
            binding.remainingCaloriesText.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
        }
    }

    private void saveSelectedMeals() {
        ArrayList<String> selectedMeals = new ArrayList<>();
        if (breakfastAdapter != null) {
            for (MealItem meal : breakfastAdapter.getMeals()) {
                if (meal.isSelected()) {
                    selectedMeals.add(meal.getName());
                }
            }
        }
        if (lunchAdapter != null) {
            for (MealItem meal : lunchAdapter.getMeals()) {
                if (meal.isSelected()) {
                    selectedMeals.add(meal.getName());
                }
            }
        }
        if (dinnerAdapter != null) {
            for (MealItem meal : dinnerAdapter.getMeals()) {
                if (meal.isSelected()) {
                    selectedMeals.add(meal.getName());
                }
            }
        }
        if (snackAdapter != null) {
            for (MealItem meal : snackAdapter.getMeals()) {
                if (meal.isSelected()) {
                    selectedMeals.add(meal.getName());
                }
            }
        }
        dataManager.setSelectedMeals(selectedMeals);
    }

    private int calculateTotalCalories() {
        int total = 0;
        try {
            // Only count selected meals
            if (breakfastAdapter != null && breakfastAdapter.getMeals() != null) {
                for (MealItem meal : breakfastAdapter.getMeals()) {
                    if (meal.isSelected()) {
                        total += meal.getCalories();
                    }
                }
            }
            if (lunchAdapter != null && lunchAdapter.getMeals() != null) {
                for (MealItem meal : lunchAdapter.getMeals()) {
                    if (meal.isSelected()) {
                        total += meal.getCalories();
                    }
                }
            }
            if (dinnerAdapter != null && dinnerAdapter.getMeals() != null) {
                for (MealItem meal : dinnerAdapter.getMeals()) {
                    if (meal.isSelected()) {
                        total += meal.getCalories();
                    }
                }
            }
            if (snackAdapter != null && snackAdapter.getMeals() != null) {
                for (MealItem meal : snackAdapter.getMeals()) {
                    if (meal.isSelected()) {
                        total += meal.getCalories();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    private void showBmiGoalDialog() {
        try {
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            int pad = (int) (16 * getResources().getDisplayMetrics().density);
            layout.setPadding(pad, pad, pad, pad);

            EditText weightInput = new EditText(this);
            weightInput.setHint("Cân nặng (kg)");
            weightInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
            layout.addView(weightInput);

            EditText heightInput = new EditText(this);
            heightInput.setHint("Chiều cao (cm)");
            heightInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
            layout.addView(heightInput);

            RadioGroup group = new RadioGroup(this);
            group.setOrientation(RadioGroup.VERTICAL);
            RadioButton under = new RadioButton(this);
            under.setText("Gầy (BMI < 18.5)");
            RadioButton normal = new RadioButton(this);
            normal.setText("Bình thường (18.5 - 24.9)");
            RadioButton obese = new RadioButton(this);
            obese.setText("Béo phì (≥ 25)");
            group.addView(under);
            group.addView(normal);
            group.addView(obese);
            group.check(normal.getId()); // default
            layout.addView(group);

            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Chọn giới hạn calories theo BMI")
                    .setView(layout)
                    .setPositiveButton("Áp dụng", (dialog, which) -> {
                        double bmi = -1;
                        String wStr = weightInput.getText().toString().trim();
                        String hStr = heightInput.getText().toString().trim();
                        if (!TextUtils.isEmpty(wStr) && !TextUtils.isEmpty(hStr)) {
                            try {
                                double kg = Double.parseDouble(wStr);
                                double cm = Double.parseDouble(hStr);
                                if (kg > 0 && cm > 0) {
                                    bmi = kg / Math.pow(cm / 100.0, 2);
                                }
                            } catch (NumberFormatException ignored) {}
                        }

                        String category;
                        if (bmi > 0) {
                            if (bmi < 18.5) {
                                category = "Gầy";
                            } else if (bmi < 25) {
                                category = "Bình thường";
                            } else {
                                category = "Béo phì";
                            }
                        } else {
                            int checkedId = group.getCheckedRadioButtonId();
                            if (checkedId == under.getId()) {
                                category = "Gầy";
                            } else if (checkedId == obese.getId()) {
                                category = "Béo phì";
                            } else {
                                category = "Bình thường";
                            }
                        }

                        int goal = recommendCalories(category);
                        dataManager.setDailyCalorieGoal(goal);
                        updateDailyCalories();
                        String bmiInfo = bmi > 0 ? String.format(java.util.Locale.getDefault(), " (BMI %.1f)", bmi) : "";
                        Toast.makeText(this, "Đặt giới hạn " + goal + " kcal - " + category + bmiInfo, Toast.LENGTH_LONG).show();
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Không thiết lập được BMI: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private int recommendCalories(String category) {
        if ("Gầy".equalsIgnoreCase(category)) return 2400;
        if ("Béo phì".equalsIgnoreCase(category)) return 1600;
        return 2000; // Bình thường
    }

    private void scanBarcode() {
        // In a real app, you would integrate with a barcode scanner library
        // For now, show a toast
        Toast.makeText(this, "Tính năng quét mã vạch sẽ được bổ sung", Toast.LENGTH_SHORT).show();
    }

    private void addCustomMeal() {
        try {
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            int pad = (int) (16 * getResources().getDisplayMetrics().density);
            layout.setPadding(pad, pad, pad, pad);

            EditText nameInput = new EditText(this);
            nameInput.setHint("Tên món (VD: Ức gà áp chảo)");
            layout.addView(nameInput);

            EditText caloriesInput = new EditText(this);
            caloriesInput.setHint("Calories (kcal)");
            caloriesInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
            layout.addView(caloriesInput);

            EditText descInput = new EditText(this);
            descInput.setHint("Mô tả ngắn (tùy chọn)");
            layout.addView(descInput);

            Spinner categorySpinner = new Spinner(this);
            String[] categories = new String[]{"Bữa sáng", "Bữa trưa", "Bữa tối", "Đồ ăn nhẹ"};
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
            categorySpinner.setAdapter(spinnerAdapter);
            layout.addView(categorySpinner);

            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Thêm món ăn")
                    .setView(layout)
                    .setPositiveButton("Thêm", (dialog, which) -> {
                        String name = nameInput.getText().toString().trim();
                        String calStr = caloriesInput.getText().toString().trim();
                        String desc = descInput.getText().toString().trim();
                        if (name.isEmpty() || calStr.isEmpty()) {
                            Toast.makeText(this, "Vui lòng nhập tên và calories", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        int caloriesVal = 0;
                        try {
                            caloriesVal = Integer.parseInt(calStr);
                        } catch (NumberFormatException e) {
                            Toast.makeText(this, "Calories phải là số", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        MealItem newMeal = new MealItem(
                                name,
                                caloriesVal,
                                desc.isEmpty() ? "Món ăn tùy chỉnh" : desc,
                                ""  // dùng placeholder mặc định
                        );
                        newMeal.setSelected(true);

                        String category = categories[categorySpinner.getSelectedItemPosition()];
                        switch (category) {
                            case "Bữa sáng":
                                breakfastMeals.add(0, newMeal);
                                breakfastAdapter.notifyItemInserted(0);
                                binding.breakfastRecyclerView.scrollToPosition(0);
                                break;
                            case "Bữa trưa":
                                lunchMeals.add(0, newMeal);
                                lunchAdapter.notifyItemInserted(0);
                                binding.lunchRecyclerView.scrollToPosition(0);
                                break;
                            case "Bữa tối":
                                dinnerMeals.add(0, newMeal);
                                dinnerAdapter.notifyItemInserted(0);
                                binding.dinnerRecyclerView.scrollToPosition(0);
                                break;
                            default:
                                snackMeals.add(0, newMeal);
                                snackAdapter.notifyItemInserted(0);
                                binding.snackRecyclerView.scrollToPosition(0);
                                break;
                        }

                        updateDailyCalories();
                        Toast.makeText(this, "Đã thêm món: " + name, Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Không thêm được món ăn: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    
    /**
     * Mở giao diện lịch tập theo BMI
     */
    private void openBmiSchedule() {
        Intent intent = new Intent(this, BMIScheduleActivity.class);
        startActivity(intent);
    }
}

