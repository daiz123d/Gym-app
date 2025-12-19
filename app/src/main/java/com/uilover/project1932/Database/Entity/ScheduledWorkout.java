package com.uilover.project1932.Database.Entity;

/**
 * Entity cho ScheduledWorkout
 * Giữ lại để Adapters có thể sử dụng (không còn Room annotations)
 */
public class ScheduledWorkout {
    public int id;
    public String workoutTitle;
    public long scheduledDate;
    public String scheduledTime;
    public boolean isCompleted;
    public long createdDate;

    public ScheduledWorkout() {
    }

    public ScheduledWorkout(String workoutTitle, long scheduledDate, String scheduledTime, boolean isCompleted, long createdDate) {
        this.workoutTitle = workoutTitle;
        this.scheduledDate = scheduledDate;
        this.scheduledTime = scheduledTime;
        this.isCompleted = isCompleted;
        this.createdDate = createdDate;
    }
}
