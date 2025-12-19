package com.uilover.project1932.Database.Entity;

/**
 * Entity cho WorkoutHistory
 * Giữ lại để Adapters có thể sử dụng (không còn Room annotations)
 */
public class WorkoutHistory {
    public int id;
    public String workoutTitle;
    public long completionDate;
    public String duration;
    public int kcalBurned;
    public int completedLessons;
    public int totalLessons;

    public WorkoutHistory() {
    }

    public WorkoutHistory(String workoutTitle, long completionDate, String duration, int kcalBurned, int completedLessons, int totalLessons) {
        this.workoutTitle = workoutTitle;
        this.completionDate = completionDate;
        this.duration = duration;
        this.kcalBurned = kcalBurned;
        this.completedLessons = completedLessons;
        this.totalLessons = totalLessons;
    }
}
