package com.uilover.workout.controller;

import com.uilover.workout.entity.Workout;
import com.uilover.workout.entity.Lession;
import com.uilover.workout.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/seed")
@CrossOrigin(origins = "*")
public class SeedDataController {
    @Autowired
    private WorkoutService workoutService;
    
    @PostMapping("/workouts")
    public ResponseEntity<Map<String, Object>> seedWorkouts() {
        try {
            List<Workout> sampleWorkouts = getSampleWorkouts();
            
            for (Workout workout : sampleWorkouts) {
                // Check if workout already exists
                if (workoutService.getWorkoutByTitle(workout.getTitle()).isEmpty()) {
                    workoutService.saveWorkout(workout);
                }
            }
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Đã thêm " + sampleWorkouts.size() + " bài tập vào database",
                "count", sampleWorkouts.size()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "error", e.getMessage()
            ));
        }
    }
    
    private List<Workout> getSampleWorkouts() {
        List<Workout> workouts = new ArrayList<>();
        
        // Bài tập 1: Chạy bộ
        List<Lession> runningLessons = new ArrayList<>();
        runningLessons.add(new Lession("Bài học 1", "03:46", "HBPMvFkpNgE", "pic_1_1"));
        runningLessons.add(new Lession("Bài học 2", "03:41", "K6I24WgiiPw", "pic_1_2"));
        runningLessons.add(new Lession("Bài học 3", "01:57", "Zc08v4YYOeA", "pic_1_3"));
        
        Workout running = new Workout();
        running.setTitle("Chạy bộ");
        running.setDescription("Bạn vừa thức dậy. Đây là một ngày mới. Tấm canvas trống rỗng. Bạn sẽ bắt đầu như thế nào? Hãy dành 21 phút để nuôi dưỡng một tâm trí bình yên và cơ thể khỏe mạnh");
        running.setPicPath("pic_1");
        running.setKcal(160);
        running.setDurationAll("9 phút");
        running.setLessions(runningLessons);
        workouts.add(running);
        
        // Bài tập 2: Kéo giãn
        List<Lession> stretchingLessons = new ArrayList<>();
        stretchingLessons.add(new Lession("Bài học 1", "20:23", "L3eImBAXT7I", "pic_3_1"));
        stretchingLessons.add(new Lession("Bài học 2", "18:27", "47ExgzO7FlU", "pic_3_2"));
        stretchingLessons.add(new Lession("Bài học 3", "32:25", "OmLx8tmaQ-4", "pic_3_3"));
        stretchingLessons.add(new Lession("Bài học 4", "07:52", "w86EalEoFRY", "pic_3_4"));
        
        Workout stretching = new Workout();
        stretching.setTitle("Kéo giãn");
        stretching.setDescription("Bạn vừa thức dậy. Đây là một ngày mới. Tấm canvas trống rỗng. Bạn sẽ bắt đầu như thế nào? Hãy dành 21 phút để nuôi dưỡng một tâm trí bình yên và cơ thể khỏe mạnh");
        stretching.setPicPath("pic_2");
        stretching.setKcal(230);
        stretching.setDurationAll("85 phút");
        stretching.setLessions(stretchingLessons);
        workouts.add(stretching);
        
        // Bài tập 3: Yoga
        List<Lession> yogaLessons = new ArrayList<>();
        yogaLessons.add(new Lession("Bài học 1", "23:00", "v7AYKMP6rOE", "pic_3_1"));
        yogaLessons.add(new Lession("Bài học 2", "27:00", "Eml2xnoLpYE", "pic_3_2"));
        yogaLessons.add(new Lession("Bài học 3", "25:00", "v7SN-d4qXx0", "pic_3_3"));
        yogaLessons.add(new Lession("Bài học 4", "21:00", "LqXZ628YNj4", "pic_3_4"));
        
        Workout yoga = new Workout();
        yoga.setTitle("Yoga");
        yoga.setDescription("Bạn vừa thức dậy. Đây là một ngày mới. Tấm canvas trống rỗng. Bạn sẽ bắt đầu như thế nào? Hãy dành 21 phút để nuôi dưỡng một tâm trí bình yên và cơ thể khỏe mạnh");
        yoga.setPicPath("pic_3");
        yoga.setKcal(180);
        yoga.setDurationAll("65 phút");
        yoga.setLessions(yogaLessons);
        workouts.add(yoga);
        
        return workouts;
    }
}

