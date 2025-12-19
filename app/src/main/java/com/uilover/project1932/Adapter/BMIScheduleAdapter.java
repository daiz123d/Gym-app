package com.uilover.project1932.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.uilover.project1932.Data.BMIScheduleData;
import com.uilover.project1932.R;
import com.uilover.project1932.databinding.ItemBmiScheduleBinding;

import java.util.ArrayList;

public class BMIScheduleAdapter extends RecyclerView.Adapter<BMIScheduleAdapter.ViewHolder> {
    
    private ArrayList<BMIScheduleData.DailySchedule> schedules;
    private Context context;
    private String bmiCategory;
    private int expandedPosition = -1;
    
    public BMIScheduleAdapter(ArrayList<BMIScheduleData.DailySchedule> schedules, String bmiCategory) {
        this.schedules = schedules;
        this.bmiCategory = bmiCategory;
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemBmiScheduleBinding binding = ItemBmiScheduleBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BMIScheduleData.DailySchedule schedule = schedules.get(position);
        
        holder.binding.dayNameTxt.setText(schedule.getDayName());
        holder.binding.descriptionTxt.setText(schedule.getDescription());
        holder.binding.caloriesTxt.setText(schedule.getCalories() + " kcal");
        holder.binding.durationTxt.setText(schedule.getDuration());
        
        // Thiết lập màu sắc theo BMI category
        setColorByCategory(holder, position);
        
        // Hiển thị bài tập
        StringBuilder exercisesText = new StringBuilder();
        String[] exercises = schedule.getExercises();
        for (int i = 0; i < exercises.length; i++) {
            exercisesText.append("• ").append(exercises[i]);
            if (i < exercises.length - 1) {
                exercisesText.append("\n");
            }
        }
        holder.binding.exercisesTxt.setText(exercisesText.toString());
        
        // Thiết lập expand/collapse
        boolean isExpanded = expandedPosition == position;
        holder.binding.exercisesTxt.setMaxLines(isExpanded ? Integer.MAX_VALUE : 2);
        
        holder.itemView.setOnClickListener(v -> {
            int previousExpanded = expandedPosition;
            expandedPosition = isExpanded ? -1 : position;
            notifyItemChanged(previousExpanded);
            notifyItemChanged(position);
        });
    }
    
    private void setColorByCategory(ViewHolder holder, int position) {
        int color;
        if (BMIScheduleData.BMI_UNDER_WEIGHT.equalsIgnoreCase(bmiCategory)) {
            // Gầy - màu xanh lá
            color = ContextCompat.getColor(context, android.R.color.holo_green_dark);
        } else if (BMIScheduleData.BMI_OBESE.equalsIgnoreCase(bmiCategory)) {
            // Béo phì - màu đỏ
            color = ContextCompat.getColor(context, android.R.color.holo_red_dark);
        } else {
            // Bình thường - màu xanh dương
            color = ContextCompat.getColor(context, android.R.color.holo_blue_dark);
        }
        
        holder.binding.dayNameTxt.setTextColor(color);
    }
    
    @Override
    public int getItemCount() {
        return schedules != null ? schedules.size() : 0;
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemBmiScheduleBinding binding;
        
        public ViewHolder(ItemBmiScheduleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
