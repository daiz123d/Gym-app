package com.uilover.workout.service;

import com.uilover.workout.entity.Workout;
import com.uilover.workout.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {
    @Autowired
    private WorkoutRepository workoutRepository;
    
    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }
    
    public Optional<Workout> getWorkoutById(Integer id) {
        return workoutRepository.findById(id);
    }
    
    public Optional<Workout> getWorkoutByTitle(String title) {
        return workoutRepository.findByTitle(title);
    }
    
    public Workout saveWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }
    
    public void deleteWorkout(Integer id) {
        workoutRepository.deleteById(id);
    }
}

