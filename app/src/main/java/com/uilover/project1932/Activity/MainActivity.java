package com.uilover.project1932.Activity;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.uilover.project1932.Adapter.WorkoutAdapter;
import com.uilover.project1932.Domain.Lession;
import com.uilover.project1932.Domain.Workout;
import com.uilover.project1932.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        binding.view1.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
        binding.view1.setAdapter(new WorkoutAdapter(getData()));
    }

    private ArrayList<Workout> getData() {
        ArrayList<Workout> list = new ArrayList<>();

        list.add(new Workout("Chạy bộ", "Bạn vừa thức dậy. Đây là một ngày mới. Tấm canvas trống rỗng. Bạn sẽ bắt đầu như thế nào? Hãy dành 21 phút để nuôi dưỡng một tâm trí bình yên và cơ thể khỏe mạnh", "pic_1", 160,"9 phút", getLession_1()));
        list.add(new Workout("Kéo giãn", "Bạn vừa thức dậy. Đây là một ngày mới. Tấm canvas trống rỗng. Bạn sẽ bắt đầu như thế nào? Hãy dành 21 phút để nuôi dưỡng một tâm trí bình yên và cơ thể khỏe mạnh", "pic_2", 230,"85 phút", getLession_2()));
        list.add(new Workout("Yoga", "Bạn vừa thức dậy. Đây là một ngày mới. Tấm canvas trống rỗng. Bạn sẽ bắt đầu như thế nào? Hãy dành 21 phút để nuôi dưỡng một tâm trí bình yên và cơ thể khỏe mạnh", "pic_3", 180,"65 phút", getLession_3()));

        return list;
    }
    private ArrayList<Lession> getLession_1() {
        ArrayList<Lession> list = new ArrayList<>();

        list.add(new Lession("Bài học 1", "03:46 ", "HBPMvFkpNgE","pic_1_1"));
        list.add(new Lession("Bài học 2", "03:41 ", "K6I24WgiiPw","pic_1_2"));
        list.add(new Lession("Bài học 3", "01:57 ", "Zc08v4YYOeA","pic_1_3"));


        return list;
    }
    private ArrayList<Lession> getLession_2() {
        ArrayList<Lession> list = new ArrayList<>();

        list.add(new Lession("Bài học 1", "20:23 ", "L3eImBAXT7I","pic_3_1"));
        list.add(new Lession("Bài học 2", "18:27 ", "47ExgzO7FlU","pic_3_2"));
        list.add(new Lession("Bài học 3", "32:25 ", "OmLx8tmaQ-4","pic_3_3"));
        list.add(new Lession("Bài học 4", "07:52 ", "w86EalEoFRY","pic_3_4"));

        return list;
    }
    private ArrayList<Lession> getLession_3() {
        ArrayList<Lession> list = new ArrayList<>();

        list.add(new Lession("Bài học 1", "23:00 ", "v7AYKMP6rOE","pic_3_1"));
        list.add(new Lession("Bài học 2", "27:00 ", "Eml2xnoLpYE","pic_3_2"));
        list.add(new Lession("Bài học 3", "25:00 ", "v7SN-d4qXx0","pic_3_3"));
        list.add(new Lession("Bài học 4", "21:00 ", "LqXZ628YNj4","pic_3_4"));

        return list;
    }
}