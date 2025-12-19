package com.uilover.project1932.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uilover.project1932.Data.DraggableItem;
import com.uilover.project1932.Domain.Workout;
import com.uilover.project1932.Helper.ImageLoaderHelper;
import com.uilover.project1932.databinding.ItemDraggableWorkoutBinding;

import java.util.ArrayList;

public class DraggableWorkoutAdapter extends RecyclerView.Adapter<DraggableWorkoutAdapter.ViewHolder> {
    private ArrayList<DraggableItem> items;
    private OnItemClickListener listener;
    private OnViewDetailClickListener detailListener;

    public interface OnItemClickListener {
        void onItemClick(DraggableItem item);
    }

    public interface OnViewDetailClickListener {
        void onViewDetailClick(DraggableItem item);
    }

    public DraggableWorkoutAdapter(ArrayList<DraggableItem> items, OnItemClickListener listener, OnViewDetailClickListener detailListener) {
        this.items = items != null ? items : new ArrayList<>();
        this.listener = listener;
        this.detailListener = detailListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDraggableWorkoutBinding binding = ItemDraggableWorkoutBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DraggableItem item = items.get(position);
        
        holder.binding.workoutNameText.setText(item.getTitle());
        holder.binding.workoutCaloriesText.setText(item.getCalories() + " kcal");
        holder.binding.workoutDurationText.setText(item.getDuration());

        // Load image from URL or drawable
        ImageLoaderHelper.loadImage(holder.itemView.getContext(), item.getImageName(), holder.binding.workoutImage);

        // Click to add to schedule
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(item);
            }
        });

        // View detail button
        if (holder.binding.viewDetailBtn != null) {
            holder.binding.viewDetailBtn.setOnClickListener(v -> {
                if (detailListener != null) {
                    detailListener.onViewDetailClick(item);
                }
            });
        }

        // Enable long press for drag
        holder.itemView.setOnLongClickListener(v -> {
            // Start drag operation
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDragAndDrop(null, shadowBuilder, item, 0);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateItems(ArrayList<DraggableItem> newItems) {
        this.items = newItems != null ? newItems : new ArrayList<>();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemDraggableWorkoutBinding binding;

        public ViewHolder(ItemDraggableWorkoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

