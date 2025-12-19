/**
 * HƯỚNG DẪN CUSTOMIZATION - Lịch Tập Theo BMI
 * 
 * File: BMIScheduleData.java
 */

// ============================================
// THÊM LỊCH TẬP MỚI CHO LOẠI BMI KHÁC
// ============================================

/**
 * Ví dụ 1: Thêm ngày tập mới cho người Gầy
 */
public static ArrayList<DailySchedule> getUnderWeightScheduleCustom() {
    ArrayList<DailySchedule> schedule = new ArrayList<>();
    
    // Thêm ngày tập với tên, bài tập, calories, thời gian, mô tả
    schedule.add(new DailySchedule(
        "Thứ Hai",
        new String[]{
            "Tập Tay Trước (Dumbbell Curls) 3x8-10 reps",
            "Tập Tay Trước (Hammer Curls) 3x8-10 reps",
            "Tập Ngực (Bench Press) 4x6-8 reps",
            "Tập Ngực (Incline Dumbbell) 3x8-10 reps",
            "Tập Cardio (Chạy bộ) 15 phút"
        },
        850,        // Calories
        "1h 30 phút",
        "Tăng cơ tay & ngực, tăng cân"
    ));
    
    return schedule;
}

// ============================================
// THÊM LOẠI BMI MỚI (VD: Athlete / Vận động viên)
// ============================================

public static final String BMI_ATHLETE = "Vận động viên";

public static ArrayList<DailySchedule> getAthleteSchedule() {
    ArrayList<DailySchedule> schedule = new ArrayList<>();
    
    schedule.add(new DailySchedule("Thứ Hai", 
        new String[]{"Tập lực (Strength)","HIIT 20 phút"}, 
        1000, "1h 30 phút", "Tăng lực mạnh"));
    
    schedule.add(new DailySchedule("Thứ Ba", 
        new String[]{"Cardio vừa 45 phút","Core 20 phút"}, 
        700, "1h 5 phút", "Cardio"));
    
    // ... thêm các ngày khác
    
    return schedule;
}

public static ArrayList<DailySchedule> getScheduleByCategory(String category) {
    if (BMI_UNDER_WEIGHT.equalsIgnoreCase(category)) {
        return getUnderWeightSchedule();
    } else if (BMI_OBESE.equalsIgnoreCase(category)) {
        return getObeseSchedule();
    } else if (BMI_ATHLETE.equalsIgnoreCase(category)) {  // Thêm dòng này
        return getAthleteSchedule();
    } else {
        return getNormalWeightSchedule();
    }
}

// ============================================
// THAY ĐỔI CÁC BÀI TẬP CỤ THỂ
// ============================================

/**
 * Ví dụ 2: Thay đổi lịch Thứ Hai cho người Bình thường
 * để tập thêm nhiều bài tập chi tiết
 */
private static DailySchedule getNormalMondaySchedule() {
    return new DailySchedule(
        "Thứ Hai",
        new String[]{
            "Warm-up: Chạy bộ 5 phút",
            "Tập Ngực (Bench Press) 4x6 reps",
            "Tập Ngực (Flye Machine) 3x8-10 reps",
            "Tập Lưng (Lat Pulldown) 4x8 reps",
            "Tập Lưng (Row Machine) 3x8 reps",
            "Tập Cardio (Rowing Machine) 20 phút",
            "Cool-down: Stretching 10 phút"
        },
        750,           // Tăng calories (chi tiết hơn)
        "1h 45 phút",  // Tăng thời gian
        "Upper Body đầy đủ"
    );
}

// ============================================
// THAY ĐỔI MÀU SẮC CHO LOẠI MỚI
// ============================================

// File: BMIScheduleAdapter.java

private void setColorByCategory(ViewHolder holder, int position) {
    int color;
    if (BMIScheduleData.BMI_UNDER_WEIGHT.equalsIgnoreCase(bmiCategory)) {
        // Gầy - màu xanh lá
        color = ContextCompat.getColor(context, android.R.color.holo_green_dark);
    } else if (BMIScheduleData.BMI_ATHLETE.equalsIgnoreCase(bmiCategory)) {
        // Athlete - màu tím (loại mới)
        color = ContextCompat.getColor(context, android.R.color.holo_purple);
    } else if (BMIScheduleData.BMI_OBESE.equalsIgnoreCase(bmiCategory)) {
        // Béo phì - màu đỏ
        color = ContextCompat.getColor(context, android.R.color.holo_red_dark);
    } else {
        // Bình thường - màu xanh dương
        color = ContextCompat.getColor(context, android.R.color.holo_blue_dark);
    }
    
    holder.binding.dayNameTxt.setTextColor(color);
}

// ============================================
// THÊM LOẠI EXERCISES KHÁC NHAU
// ============================================

/**
 * Ví dụ 3: Tập theo phương pháp Split
 */
public static ArrayList<DailySchedule> getPushPullLegsSchedule() {
    ArrayList<DailySchedule> schedule = new ArrayList<>();
    
    // Push Day
    schedule.add(new DailySchedule("Thứ Hai", 
        new String[]{
            "Bench Press 4x6",
            "Overhead Press 3x8",
            "Dips 3x8-10",
            "Lateral Raises 3x12"
        }, 650, "1h 15 phút", "Push Day"));
    
    // Pull Day
    schedule.add(new DailySchedule("Thứ Tư", 
        new String[]{
            "Deadlifts 4x5",
            "Barbell Rows 4x6",
            "Pull-ups 3x8",
            "Face Pulls 3x15"
        }, 700, "1h 15 phút", "Pull Day"));
    
    // Legs Day
    schedule.add(new DailySchedule("Thứ Sáu", 
        new String[]{
            "Squats 4x6",
            "Leg Press 3x8",
            "Leg Curls 3x10",
            "Calf Raises 3x15"
        }, 800, "1h 15 phút", "Legs Day"));
    
    return schedule;
}

// ============================================
// THÊM PHƯƠNG PHÁPTẬP CARDIO KHÁC
// ============================================

/**
 * Ví dụ 4: HIIT (High-Intensity Interval Training)
 */
public static ArrayList<DailySchedule> getHIITSchedule() {
    ArrayList<DailySchedule> schedule = new ArrayList<>();
    
    schedule.add(new DailySchedule("Thứ Hai", 
        new String[]{
            "Warm-up 5 phút",
            "30 giây sprint + 30 giây nghỉ (10 vòng)",
            "2 phút nghỉ",
            "30 giây burpees + 30 giây nghỉ (10 vòng)",
            "Cool-down 5 phút"
        }, 500, "40 phút", "HIIT Cardio"));
    
    return schedule;
}

// ============================================
// THÊM MỤC TIÊU TÙYCHỈNH
// ============================================

/**
 * Ví dụ 5: Lịch tập cho mục tiêu cụ thể
 */
public static ArrayList<DailySchedule> get6PackAbsSchedule() {
    ArrayList<DailySchedule> schedule = new ArrayList<>();
    
    schedule.add(new DailySchedule("Thứ Hai", 
        new String[]{
            "Cardio (Elliptical) 30 phút",
            "Tập Core:",
            "  - Crunches 3x20",
            "  - Leg Raises 3x15",
            "  - Plank 3x45 giây",
            "  - Bicycle Crunches 3x20"
        }, 400, "1h 15 phút", "6 Pack Abs"));
    
    return schedule;
}

// ============================================
// SỬ DỤNG CÁC LỊCH TẬP MỚI
// ============================================

// Trong BMIScheduleActivity.java
private void setupCategorySpinner() {
    ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
        this,
        android.R.layout.simple_spinner_item,
        new String[]{
            BMIScheduleData.BMI_UNDER_WEIGHT,
            BMIScheduleData.BMI_NORMAL,
            BMIScheduleData.BMI_OBESE,
            // Thêm loại mới
            BMIScheduleData.BMI_ATHLETE,
            "Push-Pull-Legs",
            "HIIT",
            "6 Pack Abs"
        }
    );
    // ... rest of code
}

// ============================================
// THÊM DESCRIPTION & TIPS CHO LOẠI MỚI
// ============================================

private void updateCategoryDescription() {
    String description = "";
    String tips = "";
    
    if (BMIScheduleData.BMI_UNDER_WEIGHT.equalsIgnoreCase(currentBmiCategory)) {
        description = "Bạn được phân loại: GẦY (BMI < 18.5)";
        tips = "💡 Mục tiêu: Tăng cơ và tăng cân\n" +
                "• Tăng cường tập lực lượng\n" +
                "• Ăn nhiều protein và carbs\n" +
                "• Tăng calories hàng ngày";
    } else if (BMIScheduleData.BMI_ATHLETE.equalsIgnoreCase(currentBmiCategory)) {
        description = "Bạn được phân loại: VẬN ĐỘNG VIÊN";
        tips = "💡 Mục tiêu: Tăng hiệu suất thể thao\n" +
                "• Tập lực và cardio kết hợp\n" +
                "• Protein cao, carbs hợp lý\n" +
                "• Tập 6 ngày + 1 ngày nghỉ";
    }
    // ... thêm else if cho các loại khác
}

// ============================================
// THÊM STATISTICS & TRACKING
// ============================================

/**
 * Lưu workouts đã hoàn thành
 */
public void trackCompletedWorkout(String dayName, int caloriesBurned) {
    LocalDataManager dataManager = LocalDataManager.getInstance(context);
    
    // Lưu calories
    dataManager.addDailyCaloriesBurned(caloriesBurned);
    
    // Lưu workout history
    dataManager.addWorkoutHistoryItem(dayName + " - " + System.currentTimeMillis());
}

/**
 * Hiển thị tiến độ
 */
public void displayProgress() {
    LocalDataManager dataManager = LocalDataManager.getInstance(context);
    
    int totalWorkouts = dataManager.getTotalWorkouts();
    int totalKcal = dataManager.getTotalKcal();
    int todayKcal = dataManager.getDailyCaloriesBurned();
    
    // Cập nhật UI
    binding.totalWorkoutsTxt.setText("Total Workouts: " + totalWorkouts);
    binding.totalCaloriesTxt.setText("Total Kcal: " + totalKcal);
    binding.todayCaloriesTxt.setText("Today Kcal: " + todayKcal);
}

// ============================================
// EXPORT / IMPORT LỊCH TẬP
// ============================================

/**
 * Export lịch tập thành JSON
 */
public String exportScheduleToJson() {
    ArrayList<DailySchedule> schedule = currentSchedule;
    StringBuilder json = new StringBuilder("[");
    
    for (int i = 0; i < schedule.size(); i++) {
        DailySchedule day = schedule.get(i);
        json.append("{\"day\":\"").append(day.getDayName()).append("\",");
        json.append("\"exercises\":[");
        
        String[] exercises = day.getExercises();
        for (int j = 0; j < exercises.length; j++) {
            json.append("\"").append(exercises[j]).append("\"");
            if (j < exercises.length - 1) json.append(",");
        }
        
        json.append("],\"calories\":").append(day.getCalories())
            .append(",\"duration\":\"").append(day.getDuration())
            .append("\"}");
        
        if (i < schedule.size() - 1) json.append(",");
    }
    
    json.append("]");
    return json.toString();
}

/**
 * Share schedule
 */
public void shareSchedule() {
    String scheduleText = "Lịch Tập Của Tôi (" + currentBmiCategory + ")\n\n";
    
    for (DailySchedule day : currentSchedule) {
        scheduleText += day.getDayName() + ": ";
        for (String exercise : day.getExercises()) {
            scheduleText += "\n  • " + exercise;
        }
        scheduleText += "\n  Calories: " + day.getCalories() + "kcal\n\n";
    }
    
    Intent shareIntent = new Intent(Intent.ACTION_SEND);
    shareIntent.setType("text/plain");
    shareIntent.putExtra(Intent.EXTRA_TEXT, scheduleText);
    startActivity(Intent.createChooser(shareIntent, "Share Schedule"));
}

// ============================================
// KẾT LUẬN
// ============================================

/**
 * Để customize lịch tập:
 * 
 * 1. Thêm lịch mới: getXxxSchedule() method
 * 2. Thêm loại: BMI_XXX constant
 * 3. Cập nhật getScheduleByCategory()
 * 4. Thêm spinner option
 * 5. Cập nhật updateCategoryDescription()
 * 6. Thay đổi màu trong setColorByCategory()
 * 
 * Thế là xong! 💪
 */
