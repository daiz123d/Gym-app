package com.uilover.workout.service;

import com.uilover.workout.entity.FavoriteWorkout;
import com.uilover.workout.repository.FavoriteWorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteWorkoutRepository favoriteRepository;
    
    public List<FavoriteWorkout> getAllFavorites() {
        return favoriteRepository.findAll();
    }
    
    public boolean isFavorite(String workoutTitle) {
        return favoriteRepository.existsByWorkoutTitle(workoutTitle);
    }
    
    public FavoriteWorkout addToFavorites(String workoutTitle) {
        if (!isFavorite(workoutTitle)) {
            FavoriteWorkout favorite = new FavoriteWorkout();
            favorite.setWorkoutTitle(workoutTitle);
            favorite.setAddedDate(System.currentTimeMillis());
            return favoriteRepository.save(favorite);
        }
        return favoriteRepository.findByWorkoutTitle(workoutTitle).orElse(null);
    }
    
    public void removeFromFavorites(String workoutTitle) {
        favoriteRepository.deleteByWorkoutTitle(workoutTitle);
    }
}

