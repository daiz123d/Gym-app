package com.uilover.project1932.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uilover.project1932.Database.Entity.ScheduledWorkout;
import com.uilover.project1932.databinding.ItemScheduledBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ScheduledAdapter extends RecyclerView.Adapter<ScheduledAdapter.Viewholder> {
    private final ArrayList<ScheduledWorkout> list;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onDeleteClick(int position, ScheduledWorkout scheduled);
    }

    public ScheduledAdapter(ArrayList<ScheduledWorkout> list) {
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemScheduledBinding binding = ItemScheduledBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        ScheduledWorkout scheduled = list.get(position);
        holder.binding.workoutTitleTxt.setText(scheduled.workoutTitle);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String dateStr = dateFormat.format(new Date(scheduled.scheduledDate));
        String timeStr = scheduled.scheduledTime != null ? scheduled.scheduledTime : timeFormat.format(new Date(scheduled.scheduledDate));
        holder.binding.dateTimeTxt.setText(dateStr + " - " + timeStr);
        
        holder.binding.deleteBtn.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(position, scheduled);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ItemScheduledBinding binding;

        public Viewholder(ItemScheduledBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

