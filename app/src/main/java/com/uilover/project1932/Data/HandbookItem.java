package com.uilover.project1932.Data;

import java.io.Serializable;

public class HandbookItem implements Serializable {
    private String title;
    private String imageName;
    private String description;
    private String content;

    public HandbookItem(String title, String imageName, String description, String content) {
        this.title = title;
        this.imageName = imageName;
        this.description = description;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

