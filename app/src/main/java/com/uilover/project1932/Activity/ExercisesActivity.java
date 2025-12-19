package com.uilover.project1932.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.uilover.project1932.Adapter.WorkoutAdapter;
import com.uilover.project1932.Data.SampleData;
import com.uilover.project1932.Domain.Workout;
import com.uilover.project1932.Helper.NavigationHelper;
import com.uilover.project1932.databinding.ActivityExercisesBinding;

import java.util.ArrayList;
import java.text.Normalizer;

public class ExercisesActivity extends AppCompatActivity {
    ActivityExercisesBinding binding;
    private WorkoutAdapter adapter;
    private ArrayList<Workout> allItems;
    private ArrayList<Workout> filteredItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExercisesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setupBackButton();
        setupNavigation();
        loadExerciseItems();
        setupSearch();
    }

    private void setupBackButton() {
        if (binding.backBtn != null) {
            binding.backBtn.setOnClickListener(v -> finish());
        }
    }

    private void setupNavigation() {
        try {
            NavigationHelper.setupBottomNavigation(
                    binding.homeTab,
                    binding.favoriteTab,
                    binding.cartTab,
                    binding.profileTab,
                    this
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadExerciseItems() {
        allItems = SampleData.getAllWorkouts();
        filteredItems = new ArrayList<>(allItems);
        
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WorkoutAdapter(filteredItems);
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
            for (Workout item : allItems) {
                String title = normalize(item.getTitle());
                String desc = normalize(item.getDescription());
                String q = normalize(lowerQuery);
                if ((title.contains(q)) || (desc.contains(q))) {
                    filteredItems.add(item);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private String normalize(String input) {
        if (input == null) return "";
        String temp = Normalizer.normalize(input, Normalizer.Form.NFD);
        return temp.replaceAll("\\p{M}", "").toLowerCase();
    }
}

