package com.uilover.project1932.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uilover.project1932.Database.Entity.WorkoutHistory;
import com.uilover.project1932.databinding.ItemHistoryBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Viewholder> {
    private final ArrayList<String> list;

    public HistoryAdapter(ArrayList<String> list) {
        this.list = list != null ? list : new ArrayList<>();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHistoryBinding binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        String historyItem = list.get(position);
        if (historyItem != null && historyItem.contains(" - ")) {
            String[] parts = historyItem.split(" - ");
            if (parts.length >= 2) {
                holder.binding.workoutTitleTxt.setText(parts[0]);
                try {
                    long timestamp = Long.parseLong(parts[1]);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
                    holder.binding.dateTxt.setText(sdf.format(new Date(timestamp)));
                } catch (NumberFormatException e) {
                    holder.binding.dateTxt.setText(historyItem);
                }
            } else {
                holder.binding.workoutTitleTxt.setText(historyItem);
            }
        } else {
            holder.binding.workoutTitleTxt.setText(historyItem != null ? historyItem : "");
        }
        
        // Set default values for other fields
        holder.binding.kcalTxt.setText("-- Kcal");
        holder.binding.durationTxt.setText("--");
        holder.binding.progressTxt.setText("--");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ItemHistoryBinding binding;

        public Viewholder(ItemHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

