package com.uilover.project1932.Data;

import java.io.Serializable;

public class MealItem implements Serializable {
    private String name;
    private int calories;
    private String description;
    private String imageName;
    private boolean isSelected;

    public MealItem(String name, int calories, String description, String imageName) {
        this.name = name;
        this.calories = calories;
        this.description = description;
        this.imageName = imageName;
        this.isSelected = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

