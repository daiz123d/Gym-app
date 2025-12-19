package com.uilover.workout.service;

import com.uilover.workout.entity.WorkoutHistory;
import com.uilover.workout.repository.WorkoutHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutHistoryService {
    @Autowired
    private WorkoutHistoryRepository historyRepository;
    
    public List<WorkoutHistory> getAllHistory() {
        return historyRepository.findAllByOrderByCompletionDateDesc();
    }
    
    public WorkoutHistory saveHistory(WorkoutHistory history) {
        return historyRepository.save(history);
    }
    
    public Integer getTotalKcalBurned() {
        return historyRepository.getTotalKcalBurned();
    }
    
    public Long getTotalWorkoutCount() {
        return historyRepository.getTotalWorkoutCount();
    }
}

