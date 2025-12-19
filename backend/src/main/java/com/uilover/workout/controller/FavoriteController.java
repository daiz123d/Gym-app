package com.uilover.workout.controller;

import com.uilover.workout.entity.FavoriteWorkout;
import com.uilover.workout.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "*")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;
    
    @GetMapping
    public ResponseEntity<List<FavoriteWorkout>> getAllFavorites() {
        return ResponseEntity.ok(favoriteService.getAllFavorites());
    }
    
    @GetMapping("/check/{title}")
    public ResponseEntity<Map<String, Boolean>> isFavorite(@PathVariable String title) {
        return ResponseEntity.ok(Map.of("isFavorite", favoriteService.isFavorite(title)));
    }
    
    @PostMapping
    public ResponseEntity<FavoriteWorkout> addToFavorites(@RequestBody Map<String, String> request) {
        String workoutTitle = request.get("workoutTitle");
        return ResponseEntity.ok(favoriteService.addToFavorites(workoutTitle));
    }
    
    @DeleteMapping("/{title}")
    public ResponseEntity<Void> removeFromFavorites(@PathVariable String title) {
        favoriteService.removeFromFavorites(title);
        return ResponseEntity.noContent().build();
    }
}

