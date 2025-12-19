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
import com.uilover.project1932.databinding.ItemEncyclopediaBinding;

import java.util.ArrayList;

public class EncyclopediaAdapter extends RecyclerView.Adapter<EncyclopediaAdapter.Viewholder> {
    private final ArrayList<HandbookItem> list;
    private Context context;
    private Class<?> detailActivityClass;

    public EncyclopediaAdapter(ArrayList<HandbookItem> list, Class<?> detailActivityClass) {
        this.list = list;
        this.detailActivityClass = detailActivityClass;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemEncyclopediaBinding binding = ItemEncyclopediaBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        HandbookItem item = list.get(position);
        holder.binding.itemTitle.setText(item.getTitle());

        int resId = context.getResources().getIdentifier(item.getImageName(), "drawable", context.getPackageName());
        if (resId != 0) {
            Glide.with(context)
                    .load(resId)
                    .into(holder.binding.itemImage);
        }

        holder.binding.getRoot().setOnClickListener(v -> {
            if (detailActivityClass != null) {
                Intent intent = new Intent(context, detailActivityClass);
                intent.putExtra("item", item);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ItemEncyclopediaBinding binding;

        public Viewholder(ItemEncyclopediaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

