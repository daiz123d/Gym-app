package com.uilover.project1932;

import android.app.Application;

/**
 * Application class - Không còn kết nối database/API
 */
public class WorkoutApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        android.util.Log.d("WorkoutApp", "App đã được khởi tạo - Sử dụng dữ liệu local");
    }
}
