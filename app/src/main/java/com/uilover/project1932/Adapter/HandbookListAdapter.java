package com.uilover.project1932.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uilover.project1932.Data.HandbookItem;
import com.uilover.project1932.Helper.ImageLoaderHelper;
import com.uilover.project1932.databinding.ItemHandbookListBinding;

import java.util.ArrayList;

public class HandbookListAdapter extends RecyclerView.Adapter<HandbookListAdapter.Viewholder> {
    private final ArrayList<HandbookItem> list;
    private Context context;
    private Class<?> detailActivityClass;

    public HandbookListAdapter(ArrayList<HandbookItem> list, Class<?> detailActivityClass) {
        this.list = list;
        this.detailActivityClass = detailActivityClass;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemHandbookListBinding binding = ItemHandbookListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        HandbookItem item = list.get(position);
        holder.binding.itemTitle.setText(item.getTitle());

        // Load image from URL or drawable
        ImageLoaderHelper.loadImage(context, item.getImageName(), holder.binding.itemImage);

        holder.binding.getRoot().setOnClickListener(v -> {
            if (detailActivityClass != null) {
                Intent intent = new Intent(context, detailActivityClass);
                intent.putExtra("item", item);
                // For ExerciseDetailActivity, pass muscle group name
                try {
                    Class<?> exerciseDetailClass = Class.forName("com.uilover.project1932.Activity.ExerciseDetailActivity");
                    if (detailActivityClass.equals(exerciseDetailClass)) {
                        intent.putExtra("muscleGroup", item.getTitle());
                    }
                } catch (ClassNotFoundException e) {
                    // Ignore
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ItemHandbookListBinding binding;

        public Viewholder(ItemHandbookListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

