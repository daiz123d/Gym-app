package com.uilover.project1932.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uilover.project1932.Activity.WorkoutActivity;
import com.uilover.project1932.Domain.Workout;
import com.uilover.project1932.Helper.ImageLoaderHelper;
import com.uilover.project1932.WorkoutApp;
import com.uilover.project1932.databinding.ViewholderWorktoutBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.Viewholder> {
    private final ArrayList<Workout> list;
    private Context context;

    public WorkoutAdapter(ArrayList<Workout> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public WorkoutAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderWorktoutBinding binding = ViewholderWorktoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutAdapter.Viewholder holder, int position) {

        holder.binding.titleTxt.setText(list.get(position).getTitle());
        
        // Load image from URL or drawable
        ImageLoaderHelper.loadImage(context, list.get(position).getPicPath(), holder.binding.pic);

        holder.binding.excerciseTxt.setText(list.get(position).getLessions().size() + " Bài tập");
        holder.binding.kcalTxt.setText(list.get(position).getKcal() + " Kcal");
        holder.binding.durationTxt.setText(list.get(position).getDurationAll());

        holder.binding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(context, WorkoutActivity.class);
            intent.putExtra("object", list.get(position));
            context.startActivity(intent);
        });
        
        // Long press to schedule workout - Feature not implemented in API yet
        // holder.binding.getRoot().setOnLongClickListener(v -> {
        //     showScheduleDialog(list.get(position));
        //     return true;
        // });
    }
    
    private void showScheduleDialog(Workout workout) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Lập lịch tập luyện");
        builder.setMessage("Bạn muốn lập lịch cho bài tập: " + workout.getTitle() + "?");
        
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 20, 50, 20);
        
        EditText dateInput = new EditText(context);
        dateInput.setHint("Ngày (dd/MM/yyyy)");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        dateInput.setText(dateFormat.format(new Date()));
        
        EditText timeInput = new EditText(context);
        timeInput.setHint("Giờ (HH:mm)");
        timeInput.setText("08:00");
        
        layout.addView(dateInput);
        layout.addView(timeInput);
        builder.setView(layout);
        
        builder.setPositiveButton("Lập lịch", (dialog, which) -> {
            try {
                String dateStr = dateInput.getText().toString();
                String timeStr = timeInput.getText().toString();
                
                SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                Date date = parser.parse(dateStr);
                long scheduledDate = date != null ? date.getTime() : System.currentTimeMillis();
                
                // TODO: Implement schedule workout API endpoint
                // WorkoutApp.getApiRepository().scheduleWorkout(...);
                Toast.makeText(context, "Chức năng lập lịch chưa được hỗ trợ", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(context, "Lỗi định dạng ngày/giờ", Toast.LENGTH_SHORT).show();
            }
        });
        
        builder.setNegativeButton("Hủy", null);
        builder.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ViewholderWorktoutBinding binding;

        public Viewholder(ViewholderWorktoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
