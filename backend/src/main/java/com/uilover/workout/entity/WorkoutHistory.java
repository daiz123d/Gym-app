package com.uilover.workout.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "workout_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "workout_title", nullable = false)
    private String workoutTitle;
    
    @Column(name = "completion_date")
    private Long completionDate;
    
    private String duration;
    
    @Column(name = "kcal_burned")
    private Integer kcalBurned;
    
    @Column(name = "completed_lessons")
    private Integer completedLessons;
    
    @Column(name = "total_lessons")
    private Integer totalLessons;
}

