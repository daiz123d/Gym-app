package com.uilover.project1932.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uilover.project1932.Adapter.DraggableWorkoutAdapter;
import com.uilover.project1932.Data.DraggableItem;
import com.uilover.project1932.Data.ExerciseData;
import com.uilover.project1932.Data.ExerciseItem;
import com.uilover.project1932.Data.LocalDataManager;
import com.uilover.project1932.Data.SampleData;
import com.uilover.project1932.Data.ScheduledWorkout;
import com.uilover.project1932.Domain.Workout;
import com.uilover.project1932.Helper.NavigationHelper;
import com.uilover.project1932.R;
import com.uilover.project1932.databinding.ActivityTrainingScheduleBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TrainingScheduleActivity extends AppCompatActivity {
    private ActivityTrainingScheduleBinding binding;
    private LocalDataManager dataManager;
    private DraggableWorkoutAdapter workoutAdapter;
    private String selectedDate;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private ArrayList<DraggableItem> allItems;
    private String currentCategory = "all";
    private String currentSearchQuery = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            binding = ActivityTrainingScheduleBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

            dataManager = LocalDataManager.getInstance(this);
            calendar = Calendar.getInstance();
            dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            selectedDate = dateFormat.format(calendar.getTime());

            setupUI();
            setupNavigation();
            loadAllItems();
            setupSearch();
            setupCategoryFilters();
            setupCalendar();
            loadScheduledWorkouts();
            
            // Ensure colors are set after all views are rendered
            binding.getRoot().post(() -> {
                if (binding.monthYearText != null) {
                    binding.monthYearText.setTextColor(ContextCompat.getColor(this, R.color.white));
                }
                if (binding.prevMonthBtn != null) {
                    binding.prevMonthBtn.setColorFilter(ContextCompat.getColor(this, R.color.white));
                }
                if (binding.nextMonthBtn != null) {
                    binding.nextMonthBtn.setColorFilter(ContextCompat.getColor(this, R.color.white));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi khởi tạo màn hình: " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void setupUI() {
        if (binding.backBtn != null) {
            binding.backBtn.setOnClickListener(v -> finish());
        }
        if (binding.menuBtn != null) {
            binding.menuBtn.setOnClickListener(v -> showMenu());
        }
        
        // Tab navigation
        if (binding.overviewTab != null) {
            binding.overviewTab.setOnClickListener(v -> switchTab(0));
        }
        if (binding.scheduleTab != null) {
            binding.scheduleTab.setOnClickListener(v -> switchTab(1));
        }
        if (binding.dietTab != null) {
            binding.dietTab.setOnClickListener(v -> switchTab(2));
        }
    }

    private void setupNavigation() {
        try {
            NavigationHelper.setupBottomNavigation(
                    binding.homeTab,
                    binding.favoriteTab,
                    binding.cartTab,
                    binding.profileTab,
                    this
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void switchTab(int position) {
        try {
            // Reset all tabs
            if (binding.overviewTab != null) {
                binding.overviewTab.setSelected(false);
            }
            if (binding.scheduleTab != null) {
                binding.scheduleTab.setSelected(false);
            }
            if (binding.dietTab != null) {
                binding.dietTab.setSelected(false);
            }
            
            // Select current tab
            switch (position) {
                case 0:
                    if (binding.overviewTab != null) {
                        binding.overviewTab.setSelected(true);
                    }
                    break;
                case 1:
                    if (binding.scheduleTab != null) {
                        binding.scheduleTab.setSelected(true);
                    }
                    break;
                case 2:
                    if (binding.dietTab != null) {
                        binding.dietTab.setSelected(true);
                    }
                    Intent intent = new Intent(this, MealPlanActivity.class);
                    startActivity(intent);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupCalendar() {
        try {
            // Set colors for navigation buttons and text
            if (binding.prevMonthBtn != null) {
                binding.prevMonthBtn.setColorFilter(ContextCompat.getColor(this, R.color.white));
            }
            if (binding.nextMonthBtn != null) {
                binding.nextMonthBtn.setColorFilter(ContextCompat.getColor(this, R.color.white));
            }
            if (binding.monthYearText != null) {
                binding.monthYearText.setTextColor(ContextCompat.getColor(this, R.color.white));
            }
            
            // Set current month
            updateMonthDisplay();
            
            // Previous month button
            if (binding.prevMonthBtn != null) {
                binding.prevMonthBtn.setOnClickListener(v -> {
                    try {
                        calendar.add(Calendar.MONTH, -1);
                        updateMonthDisplay();
                        loadScheduledWorkouts();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            
            // Next month button
            if (binding.nextMonthBtn != null) {
                binding.nextMonthBtn.setOnClickListener(v -> {
                    try {
                        calendar.add(Calendar.MONTH, 1);
                        updateMonthDisplay();
                        loadScheduledWorkouts();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            
            // Calendar view click listener
            if (binding.calendarView != null) {
                binding.calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
                    try {
                        calendar.set(year, month, dayOfMonth);
                        selectedDate = dateFormat.format(calendar.getTime());
                        showWorkoutsForDate(selectedDate);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            
            // Initialize with today's date
            selectedDate = dateFormat.format(Calendar.getInstance().getTime());
            showWorkoutsForDate(selectedDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateMonthDisplay() {
        try {
            String monthYear = new SimpleDateFormat("Tháng M yyyy", Locale.getDefault())
                    .format(calendar.getTime());
            if (binding.monthYearText != null) {
                binding.monthYearText.setText(monthYear);
                binding.monthYearText.setTextColor(ContextCompat.getColor(this, R.color.white));
            }
            
            // Update calendar view
            if (binding.calendarView != null) {
                binding.calendarView.setDate(calendar.getTimeInMillis());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllItems() {
        try {
            // Load all workouts
            ArrayList<Workout> workouts = SampleData.getAllWorkouts();
            
            // Load all exercises
            ArrayList<ExerciseItem> exercises = ExerciseData.getAllExercises();
            
            // Combine into DraggableItem list
            allItems = new ArrayList<>();
            
            // Add workouts first
            for (Workout workout : workouts) {
                allItems.add(new DraggableItem(workout));
            }
            
            // Add all exercises
            for (ExerciseItem exercise : exercises) {
                allItems.add(new DraggableItem(exercise));
            }
            
            // Initialize adapter with all items
            if (binding.workoutListRecyclerView != null) {
                binding.workoutListRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                workoutAdapter = new DraggableWorkoutAdapter(allItems, this::onItemSelected, this::onViewDetailClick);
                binding.workoutListRecyclerView.setAdapter(workoutAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupSearch() {
        if (binding.searchEditText != null) {
            binding.searchEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    currentSearchQuery = s.toString().toLowerCase().trim();
                    filterItems();
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }
    }

    private void setupCategoryFilters() {
        // All category
        if (binding.categoryAll != null) {
            binding.categoryAll.setOnClickListener(v -> selectCategory("all"));
        }
        
        // Workouts category
        if (binding.categoryWorkouts != null) {
            binding.categoryWorkouts.setOnClickListener(v -> selectCategory("workouts"));
        }
        
        // Chest category
        if (binding.categoryChest != null) {
            binding.categoryChest.setOnClickListener(v -> selectCategory("chest"));
        }
        
        // Back category
        if (binding.categoryBack != null) {
            binding.categoryBack.setOnClickListener(v -> selectCategory("back"));
        }
        
        // Legs category
        if (binding.categoryLegs != null) {
            binding.categoryLegs.setOnClickListener(v -> selectCategory("legs"));
        }
        
        // Arms category
        if (binding.categoryArms != null) {
            binding.categoryArms.setOnClickListener(v -> selectCategory("arms"));
        }
        
        // Abs category
        if (binding.categoryAbs != null) {
            binding.categoryAbs.setOnClickListener(v -> selectCategory("abs"));
        }
    }

    private void selectCategory(String category) {
        currentCategory = category;
        
        // Update UI
        updateCategoryButtons();
        
        // Filter items
        filterItems();
    }

    private void updateCategoryButtons() {
        // Reset all buttons
        if (binding.categoryAll != null) {
            binding.categoryAll.setBackgroundResource(currentCategory.equals("all") ? R.drawable.orange_bg : R.drawable.edittext_bg);
            binding.categoryAll.setTypeface(null, currentCategory.equals("all") ? android.graphics.Typeface.BOLD : android.graphics.Typeface.NORMAL);
        }
        if (binding.categoryWorkouts != null) {
            binding.categoryWorkouts.setBackgroundResource(currentCategory.equals("workouts") ? R.drawable.orange_bg : R.drawable.edittext_bg);
            binding.categoryWorkouts.setTypeface(null, currentCategory.equals("workouts") ? android.graphics.Typeface.BOLD : android.graphics.Typeface.NORMAL);
        }
        if (binding.categoryChest != null) {
            binding.categoryChest.setBackgroundResource(currentCategory.equals("chest") ? R.drawable.orange_bg : R.drawable.edittext_bg);
            binding.categoryChest.setTypeface(null, currentCategory.equals("chest") ? android.graphics.Typeface.BOLD : android.graphics.Typeface.NORMAL);
        }
        if (binding.categoryBack != null) {
            binding.categoryBack.setBackgroundResource(currentCategory.equals("back") ? R.drawable.orange_bg : R.drawable.edittext_bg);
            binding.categoryBack.setTypeface(null, currentCategory.equals("back") ? android.graphics.Typeface.BOLD : android.graphics.Typeface.NORMAL);
        }
        if (binding.categoryLegs != null) {
            binding.categoryLegs.setBackgroundResource(currentCategory.equals("legs") ? R.drawable.orange_bg : R.drawable.edittext_bg);
            binding.categoryLegs.setTypeface(null, currentCategory.equals("legs") ? android.graphics.Typeface.BOLD : android.graphics.Typeface.NORMAL);
        }
        if (binding.categoryArms != null) {
            binding.categoryArms.setBackgroundResource(currentCategory.equals("arms") ? R.drawable.orange_bg : R.drawable.edittext_bg);
            binding.categoryArms.setTypeface(null, currentCategory.equals("arms") ? android.graphics.Typeface.BOLD : android.graphics.Typeface.NORMAL);
        }
        if (binding.categoryAbs != null) {
            binding.categoryAbs.setBackgroundResource(currentCategory.equals("abs") ? R.drawable.orange_bg : R.drawable.edittext_bg);
            binding.categoryAbs.setTypeface(null, currentCategory.equals("abs") ? android.graphics.Typeface.BOLD : android.graphics.Typeface.NORMAL);
        }
    }

    private void filterItems() {
        if (allItems == null || workoutAdapter == null) return;
        
        ArrayList<DraggableItem> filtered = new ArrayList<>();
        
        for (DraggableItem item : allItems) {
            // Filter by category
            boolean matchesCategory = false;
            if (currentCategory.equals("all")) {
                matchesCategory = true;
            } else if (currentCategory.equals("workouts")) {
                matchesCategory = item.getType() == DraggableItem.TYPE_WORKOUT;
            } else if (currentCategory.equals("chest")) {
                matchesCategory = item.getType() == DraggableItem.TYPE_EXERCISE && 
                    (item.getTitle().toLowerCase().contains("ngực") || 
                     item.getTitle().toLowerCase().contains("chest"));
            } else if (currentCategory.equals("back")) {
                matchesCategory = item.getType() == DraggableItem.TYPE_EXERCISE && 
                    (item.getTitle().toLowerCase().contains("lưng") || 
                     item.getTitle().toLowerCase().contains("back"));
            } else if (currentCategory.equals("legs")) {
                matchesCategory = item.getType() == DraggableItem.TYPE_EXERCISE && 
                    (item.getTitle().toLowerCase().contains("chân") || 
                     item.getTitle().toLowerCase().contains("leg"));
            } else if (currentCategory.equals("arms")) {
                matchesCategory = item.getType() == DraggableItem.TYPE_EXERCISE && 
                    (item.getTitle().toLowerCase().contains("tay") || 
                     item.getTitle().toLowerCase().contains("arm") ||
                     item.getTitle().toLowerCase().contains("bắp"));
            } else if (currentCategory.equals("abs")) {
                matchesCategory = item.getType() == DraggableItem.TYPE_EXERCISE && 
                    (item.getTitle().toLowerCase().contains("bụng") || 
                     item.getTitle().toLowerCase().contains("abs"));
            }
            
            // Filter by search query
            boolean matchesSearch = currentSearchQuery.isEmpty() || 
                item.getTitle().toLowerCase().contains(currentSearchQuery);
            
            if (matchesCategory && matchesSearch) {
                filtered.add(item);
            }
        }
        
        workoutAdapter.updateItems(filtered);
    }

    private void onItemSelected(DraggableItem item) {
        // When user clicks on an item, add it to the selected date
        if (selectedDate == null || selectedDate.isEmpty()) {
            selectedDate = dateFormat.format(Calendar.getInstance().getTime());
        }
        
        if (item.getType() == DraggableItem.TYPE_WORKOUT) {
            addWorkoutToDate(item.getWorkout(), selectedDate);
        } else if (item.getType() == DraggableItem.TYPE_EXERCISE) {
            addExerciseToDate(item.getExercise(), selectedDate);
        }
    }

    private void onViewDetailClick(DraggableItem item) {
        try {
            if (item.getType() == DraggableItem.TYPE_WORKOUT) {
                // Navigate to WorkoutActivity
                Intent intent = new Intent(this, WorkoutActivity.class);
                intent.putExtra("object", item.getWorkout());
                startActivity(intent);
            } else if (item.getType() == DraggableItem.TYPE_EXERCISE) {
                // Navigate to ExerciseTimerActivity
                Intent intent = new Intent(this, ExerciseTimerActivity.class);
                intent.putExtra("exercise", item.getExercise());
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi mở chi tiết bài tập", Toast.LENGTH_SHORT).show();
        }
    }

    private void addWorkoutToDate(Workout workout, String date) {
        try {
            if (workout == null || date == null || date.isEmpty()) {
                Toast.makeText(this, "Dữ liệu không hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }
            
            // Check if already exists
            List<ScheduledWorkout> existing = dataManager.getScheduledWorkoutsForDate(date);
            if (existing != null) {
                for (ScheduledWorkout sw : existing) {
                    if (sw != null && sw.getWorkoutTitle() != null && 
                        sw.getWorkoutTitle().equals(workout.getTitle())) {
                        Toast.makeText(this, "Bài tập này đã có trong ngày " + formatDateDisplay(date), 
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
            
            ScheduledWorkout scheduled = new ScheduledWorkout(
                    workout.getTitle() != null ? workout.getTitle() : "",
                    "workout",
                    date,
                    workout.getKcal(),
                    workout.getDurationAll() != null ? workout.getDurationAll() : ""
            );
            
            dataManager.addScheduledWorkout(scheduled);
            loadScheduledWorkouts();
            Toast.makeText(this, "✓ Đã thêm " + workout.getTitle() + " vào " + formatDateDisplay(date), 
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi thêm bài tập", Toast.LENGTH_SHORT).show();
        }
    }
    
    public void addExerciseToDate(ExerciseItem exercise, String date) {
        try {
            if (exercise == null || date == null || date.isEmpty()) {
                Toast.makeText(this, "Dữ liệu không hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }
            
            // Check if already exists
            List<ScheduledWorkout> existing = dataManager.getScheduledWorkoutsForDate(date);
            if (existing != null) {
                for (ScheduledWorkout sw : existing) {
                    if (sw != null && sw.getWorkoutTitle() != null && 
                        sw.getWorkoutTitle().equals(exercise.getTitle())) {
                        Toast.makeText(this, "Bài tập này đã có trong ngày " + formatDateDisplay(date), 
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
            
            ScheduledWorkout scheduled = new ScheduledWorkout(
                    exercise.getTitle() != null ? exercise.getTitle() : "",
                    "exercise",
                    date,
                    50, // Estimated calories for individual exercise
                    exercise.getDefaultRestTime() + " giây"
            );
            
            dataManager.addScheduledWorkout(scheduled);
            loadScheduledWorkouts();
            Toast.makeText(this, "✓ Đã thêm " + exercise.getTitle() + " vào " + formatDateDisplay(date), 
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi thêm bài tập", Toast.LENGTH_SHORT).show();
        }
    }

    private void showWorkoutsForDate(String date) {
        try {
            if (date == null || date.isEmpty()) {
                return;
            }
            
            List<ScheduledWorkout> workouts = dataManager.getScheduledWorkoutsForDate(date);
            
            if (workouts == null || workouts.isEmpty()) {
                if (binding.selectedDateWorkouts != null) {
                    binding.selectedDateWorkouts.setVisibility(View.GONE);
                }
                if (binding.emptyDateText != null) {
                    binding.emptyDateText.setVisibility(View.VISIBLE);
                    binding.emptyDateText.setText("Chưa có bài tập cho ngày " + formatDateDisplay(date) + "\n\nNhấn vào bài tập bên dưới để thêm vào ngày này");
                }
            } else {
            if (binding.selectedDateWorkouts != null) {
                binding.selectedDateWorkouts.setVisibility(View.VISIBLE);
            }
            if (binding.emptyDateText != null) {
                binding.emptyDateText.setVisibility(View.GONE);
            }
            
            // Display workouts for selected date with link to detail
            StringBuilder workoutList = new StringBuilder();
            workoutList.append("Ngày ").append(formatDateDisplay(date)).append(":\n\n");
            for (int i = 0; i < workouts.size(); i++) {
                ScheduledWorkout sw = workouts.get(i);
                if (sw != null && sw.getWorkoutTitle() != null) {
                    workoutList.append((i + 1)).append(". ").append(sw.getWorkoutTitle())
                              .append(" (").append(sw.getCalories()).append(" kcal, ")
                              .append(sw.getDuration() != null ? sw.getDuration() : "").append(")\n");
                }
            }
            
            if (binding.selectedDateWorkouts != null) {
                binding.selectedDateWorkouts.setText(workoutList.toString());
                // Make it clickable to view detail
                binding.selectedDateWorkouts.setOnClickListener(v -> openDayDetail(date));
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (binding.emptyDateText != null) {
                binding.emptyDateText.setVisibility(View.VISIBLE);
                binding.emptyDateText.setText("Lỗi khi tải dữ liệu");
            }
        }
    }
    
    private void openDayDetail(String date) {
        try {
            Intent intent = new Intent(this, DayDetailActivity.class);
            intent.putExtra("date", date);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi mở chi tiết ngày", Toast.LENGTH_SHORT).show();
        }
    }

    private String formatDateDisplay(String date) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date d = inputFormat.parse(date);
            return outputFormat.format(d);
        } catch (Exception e) {
            return date;
        }
    }

    private void loadScheduledWorkouts() {
        // This will be used to show indicators on calendar
        // For now, we'll just refresh the display
        if (selectedDate != null) {
            showWorkoutsForDate(selectedDate);
        }
    }

    private void showMenu() {
        // Show menu options
        Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadScheduledWorkouts();
    }
}

