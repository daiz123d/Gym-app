package com.uilover.project1932.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uilover.project1932.Data.MealItem;
import com.uilover.project1932.Helper.ImageLoaderHelper;
import com.uilover.project1932.databinding.ItemMealBinding;

import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {
    private ArrayList<MealItem> meals;
    private OnMealSelectionListener listener;

    public interface OnMealSelectionListener {
        void onMealSelectionChanged();
    }

    public MealAdapter(ArrayList<MealItem> meals) {
        this.meals = meals != null ? meals : new ArrayList<>();
    }

    public void setOnMealSelectionListener(OnMealSelectionListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMealBinding binding = ItemMealBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealItem meal = meals.get(position);
        holder.binding.mealNameText.setText(meal.getName());
        holder.binding.mealCaloriesText.setText(meal.getCalories() + " kcal");
        holder.binding.mealDescriptionText.setText(meal.getDescription());
        holder.binding.mealCheckbox.setChecked(meal.isSelected());

        // Load image from URL or drawable
        ImageLoaderHelper.loadImage(holder.itemView.getContext(), meal.getImageName(), holder.binding.mealImage);

        // Set click listener for the entire item
        holder.itemView.setOnClickListener(v -> {
            meal.setSelected(!meal.isSelected());
            holder.binding.mealCheckbox.setChecked(meal.isSelected());
            if (listener != null) {
                listener.onMealSelectionChanged();
            }
        });

        // Set click listener for checkbox
        holder.binding.mealCheckbox.setOnClickListener(v -> {
            meal.setSelected(holder.binding.mealCheckbox.isChecked());
            if (listener != null) {
                listener.onMealSelectionChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public ArrayList<MealItem> getMeals() {
        return meals;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemMealBinding binding;

        public ViewHolder(ItemMealBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

