package com.uilover.project1932.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uilover.project1932.Data.ScheduledWorkout;
import com.uilover.project1932.databinding.ItemScheduledWorkoutBinding;

import java.util.ArrayList;

public class ScheduledWorkoutAdapter extends RecyclerView.Adapter<ScheduledWorkoutAdapter.ViewHolder> {
    private ArrayList<ScheduledWorkout> workouts;
    private OnWorkoutClickListener workoutClickListener;
    private OnDeleteClickListener deleteClickListener;

    public interface OnWorkoutClickListener {
        void onWorkoutClick(ScheduledWorkout workout);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(ScheduledWorkout workout);
    }

    public ScheduledWorkoutAdapter(ArrayList<ScheduledWorkout> workouts, 
                                   OnWorkoutClickListener workoutClickListener,
                                   OnDeleteClickListener deleteClickListener) {
        this.workouts = workouts != null ? workouts : new ArrayList<>();
        this.workoutClickListener = workoutClickListener;
        this.deleteClickListener = deleteClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemScheduledWorkoutBinding binding = ItemScheduledWorkoutBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduledWorkout workout = workouts.get(position);
        
        if (workout != null) {
            holder.binding.workoutTitle.setText(workout.getWorkoutTitle() != null ? workout.getWorkoutTitle() : "");
            holder.binding.workoutCalories.setText(workout.getCalories() + " kcal");
            holder.binding.workoutDuration.setText(workout.getDuration() != null ? workout.getDuration() : "");
        }

        // Start workout button
        if (holder.binding.startBtn != null) {
            holder.binding.startBtn.setOnClickListener(v -> {
                if (workoutClickListener != null) {
                    workoutClickListener.onWorkoutClick(workout);
                }
            });
        }

        // Delete button
        if (holder.binding.deleteBtn != null) {
            holder.binding.deleteBtn.setOnClickListener(v -> {
                if (deleteClickListener != null) {
                    deleteClickListener.onDeleteClick(workout);
                }
            });
        }

        // Click on card to start workout
        holder.itemView.setOnClickListener(v -> {
            if (workoutClickListener != null) {
                workoutClickListener.onWorkoutClick(workout);
            }
        });
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public void updateItems(ArrayList<ScheduledWorkout> newWorkouts) {
        this.workouts = newWorkouts != null ? newWorkouts : new ArrayList<>();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemScheduledWorkoutBinding binding;

        public ViewHolder(ItemScheduledWorkoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

