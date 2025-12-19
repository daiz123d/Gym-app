package com.uilover.workout.entity;

import com.uilover.workout.converter.LessionListConverter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Table(name = "workouts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, unique = true)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "pic_path")
    private String picPath;
    
    private Integer kcal;
    
    @Column(name = "duration_all")
    private String durationAll;
    
    @Column(columnDefinition = "JSON")
    @Convert(converter = LessionListConverter.class)
    private List<Lession> lessions;
}
