package com.uilover.project1932.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uilover.project1932.Data.ExerciseItem;
import com.uilover.project1932.Helper.ImageLoaderHelper;
import com.uilover.project1932.databinding.ItemExerciseBinding;

import java.util.ArrayList;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.Viewholder> {
    private final ArrayList<ExerciseItem> list;
    private Context context;
    private Class<?> detailActivityClass;

    public ExerciseListAdapter(ArrayList<ExerciseItem> list, Class<?> detailActivityClass) {
        this.list = list;
        this.detailActivityClass = detailActivityClass;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemExerciseBinding binding = ItemExerciseBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        ExerciseItem item = list.get(position);
        holder.binding.exerciseTitle.setText(item.getTitle());

        // Load image from URL or drawable
        ImageLoaderHelper.loadImage(context, item.getImageName(), holder.binding.exerciseImage);

        holder.binding.getRoot().setOnClickListener(v -> {
            if (detailActivityClass != null) {
                Intent intent = new Intent(context, detailActivityClass);
                intent.putExtra("exercise", item);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ItemExerciseBinding binding;

        public Viewholder(ItemExerciseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

