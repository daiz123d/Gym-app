package com.uilover.project1932.Api.Response;

import com.uilover.project1932.Domain.Lession;
import java.util.List;

public class WorkoutResponse {
    public Integer id;
    public String title;
    public String description;
    public String picPath;
    public Integer kcal;
    public String durationAll;
    public List<Lession> lessions;
    
    // Convert to Domain Workout
    public com.uilover.project1932.Domain.Workout toWorkout() {
        return new com.uilover.project1932.Domain.Workout(
                title,
                description,
                picPath,
                kcal,
                durationAll,
                lessions != null ? new java.util.ArrayList<>(lessions) : new java.util.ArrayList<>()
        );
    }
}

