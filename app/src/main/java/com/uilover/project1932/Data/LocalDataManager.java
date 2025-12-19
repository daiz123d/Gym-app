package com.uilover.project1932.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.uilover.project1932.Domain.Workout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Quản lý dữ liệu local (favorites, history) không cần database
 */
public class LocalDataManager {
    private static final String PREFS_NAME = "WorkoutPrefs";
    private static final String KEY_FAVORITES = "favorites";
    private static final String KEY_HISTORY_COUNT = "history_count";
    private static final String KEY_TOTAL_KCAL = "total_kcal";
    private static final String KEY_TOTAL_WORKOUTS = "total_workouts";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_WAIST = "waist";
    private static final String KEY_ARM = "arm";
    private static final String KEY_THIGH = "thigh";
    private static final String KEY_ONE_RM = "one_rm";
    private static final String KEY_WORKOUT_HISTORY = "workout_history";
    private static final String KEY_WORKOUT_HISTORY_STR = "workout_history_str";
    private static final String KEY_SELECTED_MEALS = "selected_meals";
    private static final String KEY_DAILY_CALORIES = "daily_calories";
    private static final String KEY_DAILY_CALORIES_BURNED = "daily_calories_burned";
    private static final String KEY_DAILY_CALORIE_GOAL = "daily_calorie_goal";
    private static final String KEY_DAILY_DATE = "daily_date";
    private static final String KEY_SCHEDULED_WORKOUTS = "scheduled_workouts";
    private static final String KEY_BMI_CATEGORY = "bmi_category";
    
    private SharedPreferences prefs;
    private static LocalDataManager instance;
    
    private LocalDataManager(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
    
    public static synchronized LocalDataManager getInstance(Context context) {
        if (instance == null) {
            instance = new LocalDataManager(context);
        }
        return instance;
    }
    
    // Favorites
    public void addFavorite(String workoutTitle) {
        Set<String> favorites = getFavorites();
        favorites.add(workoutTitle);
        saveFavorites(favorites);
    }
    
    public void removeFavorite(String workoutTitle) {
        Set<String> favorites = getFavorites();
        favorites.remove(workoutTitle);
        saveFavorites(favorites);
    }
    
    public boolean isFavorite(String workoutTitle) {
        return getFavorites().contains(workoutTitle);
    }
    
    public Set<String> getFavorites() {
        return prefs.getStringSet(KEY_FAVORITES, new HashSet<>());
    }
    
    private void saveFavorites(Set<String> favorites) {
        prefs.edit().putStringSet(KEY_FAVORITES, favorites).apply();
    }
    
    // History Stats
    public void addWorkoutHistory(int kcal) {
        int totalWorkouts = getTotalWorkouts() + 1;
        int totalKcal = getTotalKcal() + kcal;
        
        prefs.edit()
                .putInt(KEY_TOTAL_WORKOUTS, totalWorkouts)
                .putInt(KEY_TOTAL_KCAL, totalKcal)
                .apply();
    }
    
    public int getTotalWorkouts() {
        return prefs.getInt(KEY_TOTAL_WORKOUTS, 0);
    }
    
    public int getTotalKcal() {
        return prefs.getInt(KEY_TOTAL_KCAL, 0);
    }
    
    // Stats Response
    public static class Stats {
        public int totalKcal;
        public int totalWorkouts;
    }
    
    public Stats getStats() {
        Stats stats = new Stats();
        stats.totalKcal = getTotalKcal();
        stats.totalWorkouts = getTotalWorkouts();
        return stats;
    }
    
    // Weight tracking
    public void setWeight(float weight) {
        prefs.edit().putFloat(KEY_WEIGHT, weight).apply();
    }
    
    public float getWeight() {
        return prefs.getFloat(KEY_WEIGHT, 0);
    }
    
    // Body measurements
    public void setWaist(float waist) {
        prefs.edit().putFloat(KEY_WAIST, waist).apply();
    }
    
    public float getWaist() {
        return prefs.getFloat(KEY_WAIST, 0);
    }
    
    public void setArm(float arm) {
        prefs.edit().putFloat(KEY_ARM, arm).apply();
    }
    
    public float getArm() {
        return prefs.getFloat(KEY_ARM, 0);
    }
    
    public void setThigh(float thigh) {
        prefs.edit().putFloat(KEY_THIGH, thigh).apply();
    }
    
    public float getThigh() {
        return prefs.getFloat(KEY_THIGH, 0);
    }
    
    // 1RM (One Rep Max)
    public void setOneRM(float oneRM) {
        prefs.edit().putFloat(KEY_ONE_RM, oneRM).apply();
    }
    
    public float getOneRM() {
        return prefs.getFloat(KEY_ONE_RM, 0);
    }
    
    // Workout history list
    public void addWorkoutHistoryItem(String workoutTitle) {
        ArrayList<String> history = getWorkoutHistory();
        history.add(0, workoutTitle + " - " + System.currentTimeMillis());
        if (history.size() > 50) {
            history.remove(history.size() - 1);
        }
        saveWorkoutHistory(history);
    }
    
    public ArrayList<String> getWorkoutHistory() {
        String historyStr = prefs.getString(KEY_WORKOUT_HISTORY_STR, "");
        if (historyStr != null && !historyStr.isEmpty()) {
            String[] items = historyStr.split("\\n");
            ArrayList<String> result = new ArrayList<>();
            for (String item : items) {
                if (item != null && !item.trim().isEmpty()) {
                    result.add(item);
                }
            }
            return result;
        }
        // Fallback cho dữ liệu cũ dạng Set (mất thứ tự)
        Set<String> historySet = prefs.getStringSet(KEY_WORKOUT_HISTORY, new HashSet<>());
        return new ArrayList<>(historySet);
    }
    
    private void saveWorkoutHistory(ArrayList<String> history) {
        String joined = TextUtils.join("\n", history);
        prefs.edit()
                .putString(KEY_WORKOUT_HISTORY_STR, joined)
                // Lưu kèm set để tương thích cũ (dù mất thứ tự)
                .putStringSet(KEY_WORKOUT_HISTORY, new HashSet<>(history))
                .apply();
    }
    
    // Scheduled workouts
    public void addScheduledWorkout(ScheduledWorkout workout) {
        ArrayList<ScheduledWorkout> workouts = getAllScheduledWorkouts();
        workouts.add(workout);
        saveScheduledWorkouts(workouts);
    }
    
    public void removeScheduledWorkout(String date, String workoutTitle) {
        ArrayList<ScheduledWorkout> workouts = getAllScheduledWorkouts();
        workouts.removeIf(w -> w.getDate().equals(date) && w.getWorkoutTitle().equals(workoutTitle));
        saveScheduledWorkouts(workouts);
    }
    
    public ArrayList<ScheduledWorkout> getAllScheduledWorkouts() {
        try {
            String json = prefs.getString(KEY_SCHEDULED_WORKOUTS, "");
            return parseScheduledWorkouts(json);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public List<ScheduledWorkout> getScheduledWorkoutsForDate(String date) {
        ArrayList<ScheduledWorkout> all = getAllScheduledWorkouts();
        List<ScheduledWorkout> result = new ArrayList<>();
        for (ScheduledWorkout w : all) {
            if (w.getDate().equals(date)) {
                result.add(w);
            }
        }
        return result;
    }
    
    private void saveScheduledWorkouts(ArrayList<ScheduledWorkout> workouts) {
        try {
            // Simple JSON-like storage using SharedPreferences
            // Format: date|title|type|calories|duration;date|title|type|calories|duration;...
            StringBuilder sb = new StringBuilder();
            for (ScheduledWorkout w : workouts) {
                if (w == null) continue;
                if (sb.length() > 0) sb.append(";");
                String date = w.getDate() != null ? w.getDate() : "";
                String title = w.getWorkoutTitle() != null ? w.getWorkoutTitle() : "";
                String type = w.getWorkoutType() != null ? w.getWorkoutType() : "workout";
                String duration = w.getDuration() != null ? w.getDuration() : "";
                
                sb.append(date).append("|")
                  .append(title).append("|")
                  .append(type).append("|")
                  .append(w.getCalories()).append("|")
                  .append(duration);
            }
            prefs.edit().putString(KEY_SCHEDULED_WORKOUTS, sb.toString()).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private ArrayList<ScheduledWorkout> parseScheduledWorkouts(String data) {
        ArrayList<ScheduledWorkout> result = new ArrayList<>();
        try {
            if (data == null || data.isEmpty() || data.equals("[]") || data.trim().isEmpty()) {
                return result;
            }
            
            String[] items = data.split(";");
            for (String item : items) {
                if (item == null || item.trim().isEmpty()) continue;
                String[] parts = item.split("\\|");
                if (parts.length >= 5) {
                    try {
                        String title = parts[1] != null ? parts[1] : "";
                        String type = parts[2] != null ? parts[2] : "workout";
                        String date = parts[0] != null ? parts[0] : "";
                        int calories = 0;
                        try {
                            calories = Integer.parseInt(parts[3]);
                        } catch (NumberFormatException e) {
                            calories = 0;
                        }
                        String duration = parts.length > 4 && parts[4] != null ? parts[4] : "";
                        
                        ScheduledWorkout w = new ScheduledWorkout(title, type, date, calories, duration);
                        result.add(w);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    // Daily calories tracking
    public void setDailyCalories(int calories) {
        resetIfNewDay();
        String today = getTodayDateString();
        prefs.edit().putInt(KEY_DAILY_CALORIES + "_" + today, calories).apply();
        prefs.edit().putString(KEY_DAILY_DATE, today).apply();
    }
    
    public int getDailyCalories() {
        resetIfNewDay();
        String today = getTodayDateString();
        return prefs.getInt(KEY_DAILY_CALORIES + "_" + today, 0);
    }
    
    public void addDailyCaloriesBurned(int calories) {
        resetIfNewDay();
        String today = getTodayDateString();
        int current = prefs.getInt(KEY_DAILY_CALORIES_BURNED + "_" + today, 0);
        prefs.edit().putInt(KEY_DAILY_CALORIES_BURNED + "_" + today, current + calories).apply();
    }
    
    public int getDailyCaloriesBurned() {
        resetIfNewDay();
        String today = getTodayDateString();
        return prefs.getInt(KEY_DAILY_CALORIES_BURNED + "_" + today, 0);
    }
    
    public void setDailyCalorieGoal(int goal) {
        prefs.edit().putInt(KEY_DAILY_CALORIE_GOAL, goal).apply();
    }
    
    public int getDailyCalorieGoal() {
        return prefs.getInt(KEY_DAILY_CALORIE_GOAL, 2000); // Default 2000
    }

    private String getTodayDateString() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
        return sdf.format(new java.util.Date());
    }

    /**
     * Reset các số liệu theo ngày nếu đã sang ngày mới.
     */
    private void resetIfNewDay() {
        String today = getTodayDateString();
        String storedDate = prefs.getString(KEY_DAILY_DATE, "");
        if (storedDate == null || !storedDate.equals(today)) {
            // Reset calories ăn và calories đốt cho ngày mới
            prefs.edit()
                    .putInt(KEY_DAILY_CALORIES + "_" + today, 0)
                    .putInt(KEY_DAILY_CALORIES_BURNED + "_" + today, 0)
                    .putString(KEY_DAILY_DATE, today)
                    .apply();
        }
    }
    
    // Selected meals
    public void setSelectedMeals(ArrayList<String> meals) {
        prefs.edit().putStringSet(KEY_SELECTED_MEALS, new HashSet<>(meals)).apply();
    }
    
    public ArrayList<String> getSelectedMeals() {
        Set<String> mealsSet = prefs.getStringSet(KEY_SELECTED_MEALS, new HashSet<>());
        return new ArrayList<>(mealsSet);
    }
    
    // BMI Category
    public void setBmiCategory(String category) {
        prefs.edit().putString(KEY_BMI_CATEGORY, category).apply();
    }
    
    public String getBmiCategory() {
        return prefs.getString(KEY_BMI_CATEGORY, "Bình thường");
    }
}

