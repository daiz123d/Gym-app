package com.uilover.workout.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "favorite_workouts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteWorkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "workout_title", nullable = false, unique = true)
    private String workoutTitle;
    
    @Column(name = "added_date")
    private Long addedDate;
}

