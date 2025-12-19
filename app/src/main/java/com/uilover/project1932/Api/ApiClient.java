package com.uilover.project1932.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://10.0.2.2:8080/api/"; // ✅ Đúng cho Android Emulator

    private static Retrofit retrofit;
    private static WorkoutApiService apiService;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static WorkoutApiService getApiService() {
        if (apiService == null) {
            apiService = getRetrofitInstance().create(WorkoutApiService.class);
        }
        return apiService;
    }

    // Method để thay đổi BASE_URL (nếu cần)
    public static void setBaseUrl(String newBaseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(newBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(WorkoutApiService.class);
    }
}

