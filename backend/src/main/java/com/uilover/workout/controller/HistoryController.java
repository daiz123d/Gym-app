package com.uilover.workout.controller;

import com.uilover.workout.entity.WorkoutHistory;
import com.uilover.workout.service.WorkoutHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "*")
public class HistoryController {
    @Autowired
    private WorkoutHistoryService historyService;
    
    @GetMapping
    public ResponseEntity<List<WorkoutHistory>> getAllHistory() {
        return ResponseEntity.ok(historyService.getAllHistory());
    }
    
    @PostMapping
    public ResponseEntity<WorkoutHistory> addToHistory(@RequestBody WorkoutHistory history) {
        return ResponseEntity.ok(historyService.saveHistory(history));
    }
    
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalKcal", historyService.getTotalKcalBurned());
        stats.put("totalWorkouts", historyService.getTotalWorkoutCount());
        return ResponseEntity.ok(stats);
    }
}

