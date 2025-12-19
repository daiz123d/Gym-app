package com.uilover.workout.repository;

import com.uilover.workout.entity.FavoriteWorkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteWorkoutRepository extends JpaRepository<FavoriteWorkout, Integer> {
    Optional<FavoriteWorkout> findByWorkoutTitle(String workoutTitle);
    boolean existsByWorkoutTitle(String workoutTitle);
    void deleteByWorkoutTitle(String workoutTitle);
}

