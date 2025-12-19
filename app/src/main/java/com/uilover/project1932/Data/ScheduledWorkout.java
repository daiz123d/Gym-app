package com.uilover.project1932.Data;

import java.io.Serializable;

public class ScheduledWorkout implements Serializable {
    private String workoutTitle;
    private String workoutType; // "workout" or "exercise"
    private String date; // Format: yyyy-MM-dd
    private int calories;
    private String duration;

    public ScheduledWorkout(String workoutTitle, String workoutType, String date, int calories, String duration) {
        this.workoutTitle = workoutTitle;
        this.workoutType = workoutType;
        this.date = date;
        this.calories = calories;
        this.duration = duration;
    }

    public String getWorkoutTitle() {
        return workoutTitle;
    }

    public void setWorkoutTitle(String workoutTitle) {
        this.workoutTitle = workoutTitle;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}

