package com.uilover.project1932.Data;

import com.uilover.project1932.Domain.Workout;

import java.io.Serializable;

/**
 * Wrapper class để xử lý cả Workout và ExerciseItem trong draggable list
 */
public class DraggableItem implements Serializable {
    public static final int TYPE_WORKOUT = 0;
    public static final int TYPE_EXERCISE = 1;
    
    private int type;
    private Workout workout;
    private ExerciseItem exercise;
    
    public DraggableItem(Workout workout) {
        this.type = TYPE_WORKOUT;
        this.workout = workout;
        this.exercise = null;
    }
    
    public DraggableItem(ExerciseItem exercise) {
        this.type = TYPE_EXERCISE;
        this.workout = null;
        this.exercise = exercise;
    }
    
    public int getType() {
        return type;
    }
    
    public Workout getWorkout() {
        return workout;
    }
    
    public ExerciseItem getExercise() {
        return exercise;
    }
    
    public String getTitle() {
        if (type == TYPE_WORKOUT && workout != null) {
            return workout.getTitle();
        } else if (type == TYPE_EXERCISE && exercise != null) {
            return exercise.getTitle();
        }
        return "";
    }
    
    public String getImageName() {
        if (type == TYPE_WORKOUT && workout != null) {
            return workout.getPicPath();
        } else if (type == TYPE_EXERCISE && exercise != null) {
            return exercise.getImageName();
        }
        return "";
    }
    
    public int getCalories() {
        if (type == TYPE_WORKOUT && workout != null) {
            return workout.getKcal();
        } else if (type == TYPE_EXERCISE && exercise != null) {
            // Estimate calories for individual exercise (50 kcal per exercise)
            return 50;
        }
        return 0;
    }
    
    public String getDuration() {
        if (type == TYPE_WORKOUT && workout != null) {
            return workout.getDurationAll();
        } else if (type == TYPE_EXERCISE && exercise != null) {
            // Convert rest time to duration display
            int minutes = exercise.getDefaultRestTime() / 60;
            int seconds = exercise.getDefaultRestTime() % 60;
            if (minutes > 0) {
                return minutes + " phút" + (seconds > 0 ? " " + seconds + " giây" : "");
            } else {
                return exercise.getDefaultRestTime() + " giây";
            }
        }
        return "";
    }
}

