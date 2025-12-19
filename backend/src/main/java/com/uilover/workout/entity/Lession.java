package com.uilover.workout.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lession {
    private String title;
    private String duration;
    private String link;
    private String picPath;
}

