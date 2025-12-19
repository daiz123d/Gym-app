package com.uilover.project1932.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Lịch tập cố định cho 3 loại người theo BMI
 * - Gầy (BMI < 18.5)
 * - Bình thường (18.5 - 24.9)
 * - Béo phì (≥ 25)
 */
public class BMIScheduleData implements Serializable {
    
    public static final String BMI_UNDER_WEIGHT = "Gầy";
    public static final String BMI_NORMAL = "Bình thường";
    public static final String BMI_OBESE = "Béo phì";
    
    // Lịch tập cho người Gầy (BMI < 18.5)
    // Sử dụng các Workout thực tế từ SampleData: Tăng cơ tay, Core bụng, Cardio, HIIT, Full Body, Yoga
    public static ArrayList<DailySchedule> getUnderWeightSchedule() {
        ArrayList<DailySchedule> schedule = new ArrayList<>();
        
        // Thứ 2: Tăng cơ tay + HIIT
        schedule.add(new DailySchedule("Thứ Hai", new String[]{
                "Tăng cơ tay (20 phút)",
                "HIIT 15 phút"
        }, 390, "35 phút", "Tăng cơ tay + Cardio cực"));
        
        // Thứ 3: Core bụng + Sức mạnh toàn thân
        schedule.add(new DailySchedule("Thứ Ba", new String[]{
                "Core bụng (18 phút)",
                "Sức mạnh toàn thân (30 phút)"
        }, 430, "48 phút", "Tăng sức mạnh tổng quát"));
        
        // Thứ 4: Yoga + Kéo giãn
        schedule.add(new DailySchedule("Thứ Tư", new String[]{
                "Yoga (23 phút)",
                "Kéo giãn (20 phút)"
        }, 410, "43 phút", "Yoga & Kéo giãn"));
        
        // Thứ 5: Cardio đốt mỡ + Tăng cơ tay
        schedule.add(new DailySchedule("Thứ Năm", new String[]{
                "Cardio đốt mỡ (25 phút)",
                "Tăng cơ tay (20 phút)"
        }, 420, "45 phút", "Cardio + Tay"));
        
        // Thứ 6: HIIT + Pilates
        schedule.add(new DailySchedule("Thứ Sáu", new String[]{
                "HIIT 15 phút",
                "Pilates nhẹ (22 phút)"
        }, 350, "37 phút", "Cardio mạnh + Flexibility"));
        
        // Thứ 7: Sức mạnh toàn thân
        schedule.add(new DailySchedule("Thứ Bảy", new String[]{
                "Sức mạnh toàn thân (30 phút)"
        }, 260, "30 phút", "Full body strength"));
        
        // Chủ Nhật: Nghỉ
        schedule.add(new DailySchedule("Chủ Nhật", new String[]{
                "Yoga (23 phút)",
                "Kéo giãn nhẹ"
        }, 410, "43 phút", "Nghỉ & Thư giãn"));
        
        return schedule;
    }
    
    // Lịch tập cho người Bình thường (18.5 - 24.9)
    // Sử dụng các Workout thực tế: Kéo giãn, Yoga, Core, Cardio, HIIT, Tăng cơ tay (cân bằng)
    public static ArrayList<DailySchedule> getNormalWeightSchedule() {
        ArrayList<DailySchedule> schedule = new ArrayList<>();
        
        // Thứ 2: Cardio đốt mỡ
        schedule.add(new DailySchedule("Thứ Hai", new String[]{
                "Cardio đốt mỡ (25 phút)",
                "Kéo giãn nhẹ"
        }, 230, "32 phút", "Cardio - Tăng nhịp tim"));
        
        // Thứ 3: Core bụng + Tăng cơ tay
        schedule.add(new DailySchedule("Thứ Ba", new String[]{
                "Core bụng (18 phút)",
                "Tăng cơ tay (20 phút)"
        }, 350, "38 phút", "Tăng sức mạnh cơ"));
        
        // Thứ 4: Yoga
        schedule.add(new DailySchedule("Thứ Tư", new String[]{
                "Yoga (23 phút)",
                "Yoga (27 phút)"
        }, 180, "50 phút", "Yoga - Cân bằng & Thư giãn"));
        
        // Thứ 5: HIIT + Pilates
        schedule.add(new DailySchedule("Thứ Năm", new String[]{
                "HIIT 15 phút",
                "Pilates nhẹ (22 phút)"
        }, 350, "37 phút", "Cardio mạnh + Flexibility"));
        
        // Thứ 6: Sức mạnh toàn thân
        schedule.add(new DailySchedule("Thứ Sáu", new String[]{
                "Sức mạnh toàn thân (30 phút)",
                "Kéo giãn (10 phút)"
        }, 260, "40 phút", "Full body strength"));
        
        // Thứ 7: Cardio + Yoga
        schedule.add(new DailySchedule("Thứ Bảy", new String[]{
                "Chạy bộ (9 phút)",
                "Yoga (23 phút)"
        }, 340, "32 phút", "Cardio nhẹ + Yoga"));
        
        // Chủ Nhật: Nghỉ
        schedule.add(new DailySchedule("Chủ Nhật", new String[]{
                "Kéo giãn nhẹ",
                "Thư giãn & Nghỉ"
        }, 100, "20 phút", "Nghỉ ngơi"));
        
        return schedule;
    }
    
    // Lịch tập cho người Béo phì (BMI ≥ 25)
    // Sử dụng các Workout thực tế: Yoga, Kéo giãn, Pilates, Chạy bộ nhẹ (an toàn)
    public static ArrayList<DailySchedule> getObeseSchedule() {
        ArrayList<DailySchedule> schedule = new ArrayList<>();
        
        // Thứ 2: Yoga nhẹ
        schedule.add(new DailySchedule("Thứ Hai", new String[]{
                "Yoga (23 phút)",
                "Yoga (27 phút)"
        }, 180, "50 phút", "Yoga & Thư giãn"));
        
        // Thứ 3: Kéo giãn
        schedule.add(new DailySchedule("Thứ Ba", new String[]{
                "Kéo giãn (20 phút)",
                "Kéo giãn (18 phút)"
        }, 230, "38 phút", "Kéo giãn - Tăng độ mềm dẻo"));
        
        // Thứ 4: Pilates nhẹ
        schedule.add(new DailySchedule("Thứ Tư", new String[]{
                "Pilates nhẹ (22 phút)",
                "Kéo giãn nhẹ"
        }, 170, "30 phút", "Pilates & Linh hoạt"));
        
        // Thứ 5: Chạy bộ nhẹ (Cardio an toàn)
        schedule.add(new DailySchedule("Thứ Năm", new String[]{
                "Chạy bộ (9 phút)",
                "Kéo giãn (10 phút)"
        }, 170, "19 phút", "Cardio nhẹ"));
        
        // Thứ 6: Core bụng nhẹ + Yoga
        schedule.add(new DailySchedule("Thứ Sáu", new String[]{
                "Core bụng (18 phút) - nhẹ",
                "Yoga (23 phút)"
        }, 350, "41 phút", "Core + Yoga"));
        
        // Thứ 7: Yoga + Kéo giãn
        schedule.add(new DailySchedule("Thứ Bảy", new String[]{
                "Yoga (25 phút)",
                "Kéo giãn (20 phút)"
        }, 410, "45 phút", "Yoga toàn thân + Giãn"));
        
        // Chủ Nhật: Nghỉ hoàn toàn
        schedule.add(new DailySchedule("Chủ Nhật", new String[]{
                "Thư giãn & Hồi phục",
                "Nghỉ ngơi hoàn toàn"
        }, 50, "20 phút", "Nghỉ ngơi"));
        
        return schedule;
    }
    
    /**
     * Lấy lịch tập theo category BMI
     */
    public static ArrayList<DailySchedule> getScheduleByCategory(String category) {
        if (BMI_UNDER_WEIGHT.equalsIgnoreCase(category)) {
            return getUnderWeightSchedule();
        } else if (BMI_OBESE.equalsIgnoreCase(category)) {
            return getObeseSchedule();
        } else {
            return getNormalWeightSchedule();
        }
    }
    
    /**
     * Model cho từng ngày tập
     */
    public static class DailySchedule implements Serializable {
        private String dayName;
        private String[] exercises;
        private int calories;
        private String duration;
        private String description;
        
        public DailySchedule(String dayName, String[] exercises, int calories, String duration, String description) {
            this.dayName = dayName;
            this.exercises = exercises;
            this.calories = calories;
            this.duration = duration;
            this.description = description;
        }
        
        public String getDayName() {
            return dayName;
        }
        
        public void setDayName(String dayName) {
            this.dayName = dayName;
        }
        
        public String[] getExercises() {
            return exercises;
        }
        
        public void setExercises(String[] exercises) {
            this.exercises = exercises;
        }
        
        public int getCalories() {
            return calories;
        }
        
        public void setCalories(int calories) {
            this.calories = calories;
        }
        
        public String getDuration() {
            return duration;
        }
        
        public void setDuration(String duration) {
            this.duration = duration;
        }
        
        public String getDescription() {
            return description;
        }
        
        public void setDescription(String description) {
            this.description = description;
        }
    }
}
