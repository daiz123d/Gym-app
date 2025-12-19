package com.uilover.project1932.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.uilover.project1932.Adapter.ExerciseListAdapter;
import com.uilover.project1932.Data.ExerciseData;
import com.uilover.project1932.Data.ExerciseItem;
import com.uilover.project1932.databinding.ActivityExerciseDetailBinding;

import java.util.ArrayList;

public class ExerciseDetailActivity extends AppCompatActivity {
    ActivityExerciseDetailBinding binding;
    private ExerciseListAdapter adapter;
    private ArrayList<ExerciseItem> allItems;
    private ArrayList<ExerciseItem> filteredItems;
    private String muscleGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExerciseDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        muscleGroup = getIntent().getStringExtra("muscleGroup");
        if (muscleGroup == null) {
            muscleGroup = "Cơ tay trước";
        }

        setupBackButton();
        loadExercises();
        setupSearch();
        updateTitle();
    }

    private void setupBackButton() {
        if (binding.backBtn != null) {
            binding.backBtn.setOnClickListener(v -> finish());
        }
    }

    private void updateTitle() {
        if (binding.titleText != null) {
            binding.titleText.setText(muscleGroup);
        }
    }

    private void loadExercises() {
        allItems = new ArrayList<>();
        
        switch (muscleGroup) {
            case "Cơ tay trước":
                allItems = ExerciseData.getBicepsExercises();
                break;
            case "Cơ tay sau":
                allItems = ExerciseData.getTricepsExercises();
                break;
            case "Ngực":
                allItems = ExerciseData.getChestExercises();
                break;
            case "Lưng":
                allItems = ExerciseData.getBackExercises();
                break;
            case "Chân":
                allItems = ExerciseData.getLegExercises();
                break;
            case "Cơ delta":
                allItems = ExerciseData.getShoulderExercises();
                break;
            case "Cơ bụng":
                allItems = ExerciseData.getAbsExercises();
                break;
            case "Cẳng tay":
                allItems = ExerciseData.getForearmExercises();
                break;
            case "Cardio":
                allItems = ExerciseData.getCardioExercises();
                break;
            case "Giãn cơ":
                allItems = ExerciseData.getStretchingExercises();
                break;
            case "Luyện tập chức năng":
                allItems = ExerciseData.getFunctionalExercises();
                break;
            case "Cơ mông":
                allItems = ExerciseData.getGlutesExercises();
                break;
            case "Cơ đùi trước":
                allItems = ExerciseData.getQuadricepsExercises();
                break;
            case "Cơ đùi sau":
                allItems = ExerciseData.getHamstringsExercises();
                break;
            case "Bắp chân":
                allItems = ExerciseData.getCalvesExercises();
                break;
            case "Cơ xô":
                allItems = ExerciseData.getLatsExercises();
                break;
            case "Cơ cầu vai":
                allItems = ExerciseData.getTrapsExercises();
                break;
            default:
                allItems = ExerciseData.getBicepsExercises();
                break;
        }

        filteredItems = new ArrayList<>(allItems);
        
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExerciseListAdapter(filteredItems, ExerciseTimerActivity.class);
        binding.recyclerView.setAdapter(adapter);
    }

    private void setupSearch() {
        if (binding.searchBar != null) {
            binding.searchBar.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    filterItems(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }
    }

    private void filterItems(String query) {
        filteredItems.clear();
        if (query.isEmpty()) {
            filteredItems.addAll(allItems);
        } else {
            String lowerQuery = query.toLowerCase();
            for (ExerciseItem item : allItems) {
                if (item.getTitle().toLowerCase().contains(lowerQuery) ||
                    item.getDescription().toLowerCase().contains(lowerQuery)) {
                    filteredItems.add(item);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}

