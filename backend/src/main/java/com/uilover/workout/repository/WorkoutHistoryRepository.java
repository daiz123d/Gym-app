package com.uilover.workout.repository;

import com.uilover.workout.entity.WorkoutHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutHistoryRepository extends JpaRepository<WorkoutHistory, Integer> {
    List<WorkoutHistory> findAllByOrderByCompletionDateDesc();
    
    @Query("SELECT COALESCE(SUM(h.kcalBurned), 0) FROM WorkoutHistory h")
    Integer getTotalKcalBurned();
    
    @Query("SELECT COUNT(h) FROM WorkoutHistory h")
    Long getTotalWorkoutCount();
}

