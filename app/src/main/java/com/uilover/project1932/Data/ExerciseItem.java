package com.uilover.project1932.Data;

import java.io.Serializable;

public class ExerciseItem implements Serializable {
    private String title;
    private String imageName;
    private String description;
    private String instructions;
    private int defaultRestTime; // seconds

    public ExerciseItem(String title, String imageName, String description, String instructions, int defaultRestTime) {
        this.title = title;
        this.imageName = imageName;
        this.description = description;
        this.instructions = instructions;
        this.defaultRestTime = defaultRestTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getDefaultRestTime() {
        return defaultRestTime;
    }

    public void setDefaultRestTime(int defaultRestTime) {
        this.defaultRestTime = defaultRestTime;
    }
}

