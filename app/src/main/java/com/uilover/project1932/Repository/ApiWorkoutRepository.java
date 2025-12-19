package com.uilover.project1932.Repository;

import android.content.Context;
import android.util.Log;

import com.uilover.project1932.Api.ApiClient;
import com.uilover.project1932.Api.WorkoutApiService;
import com.uilover.project1932.Domain.Workout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository để kết nối với REST API backend
 * Thay thế cho Room Database khi dùng MySQL
 */
public class ApiWorkoutRepository {
    private static final String TAG = "ApiWorkoutRepository";
    private WorkoutApiService apiService;
    private Executor executor;

    public ApiWorkoutRepository(Context context) {
        this.apiService = ApiClient.getApiService();
        this.executor = Executors.newSingleThreadExecutor();
    }

    /**
     * Lấy tất cả bài tập từ API
     */
    public void getAllWorkouts(ApiCallback<List<Workout>> callback) {
        apiService.getAllWorkouts().enqueue(new Callback<List<com.uilover.project1932.Api.Response.WorkoutResponse>>() {
            @Override
            public void onResponse(Call<List<com.uilover.project1932.Api.Response.WorkoutResponse>> call,
                                   Response<List<com.uilover.project1932.Api.Response.WorkoutResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Workout> workouts = new ArrayList<>();
                    for (com.uilover.project1932.Api.Response.WorkoutResponse w : response.body()) {
                        workouts.add(w.toWorkout());
                    }
                    callback.onSuccess(workouts);
                } else {
                    callback.onError(new Exception("Failed to load workouts: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<com.uilover.project1932.Api.Response.WorkoutResponse>> call, Throwable t) {
                Log.e(TAG, "Network error loading workouts", t);
                String errorMsg = "Network error: ";
                if (t.getMessage() != null) {
                    errorMsg += t.getMessage();
                } else {
                    errorMsg += "Không thể kết nối server";
                }
                callback.onError(new Exception(errorMsg, t));
            }
        });
    }

    /**
     * Kiểm tra bài tập đã yêu thích chưa
     */
    public void isFavorite(String title, ApiCallback<Boolean> callback) {
        apiService.checkFavorite(title).enqueue(new Callback<WorkoutApiService.FavoriteCheckResponse>() {
            @Override
            public void onResponse(Call<WorkoutApiService.FavoriteCheckResponse> call,
                                   Response<WorkoutApiService.FavoriteCheckResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().isFavorite);
                } else {
                    callback.onError(new Exception("Failed to check favorite"));
                }
            }

            @Override
            public void onFailure(Call<WorkoutApiService.FavoriteCheckResponse> call, Throwable t) {
                Log.e(TAG, "Network error checking favorite", t);
                String errorMsg = "Network error: ";
                if (t.getMessage() != null) {
                    errorMsg += t.getMessage();
                } else {
                    errorMsg += "Không thể kết nối server";
                }
                callback.onError(new Exception(errorMsg, t));
            }
        });
    }

    /**
     * Thêm vào yêu thích
     */
    public void addToFavorites(String title, ApiCallback<Void> callback) {
        apiService.addToFavorites(new WorkoutApiService.FavoriteRequest(title))
                .enqueue(new Callback<com.uilover.project1932.Api.Response.FavoriteResponse>() {
                    @Override
                    public void onResponse(Call<com.uilover.project1932.Api.Response.FavoriteResponse> call,
                                           Response<com.uilover.project1932.Api.Response.FavoriteResponse> response) {
                        if (response.isSuccessful()) {
                            callback.onSuccess(null);
                        } else {
                            callback.onError(new Exception("Failed to add favorite"));
                        }
                    }

                    @Override
                    public void onFailure(Call<com.uilover.project1932.Api.Response.FavoriteResponse> call, Throwable t) {
                        Log.e(TAG, "Network error adding favorite", t);
                        String errorMsg = "Network error: ";
                        if (t.getMessage() != null) {
                            errorMsg += t.getMessage();
                        } else {
                            errorMsg += "Không thể kết nối server";
                        }
                        callback.onError(new Exception(errorMsg, t));
                    }
                });
    }

    /**
     * Xóa khỏi yêu thích
     */
    public void removeFromFavorites(String title, ApiCallback<Void> callback) {
        apiService.removeFromFavorites(title).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError(new Exception("Failed to remove favorite"));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "Network error removing favorite", t);
                String errorMsg = "Network error: ";
                if (t.getMessage() != null) {
                    errorMsg += t.getMessage();
                } else {
                    errorMsg += "Không thể kết nối server";
                }
                callback.onError(new Exception(errorMsg, t));
            }
        });
    }

    /**
     * Lấy lịch sử tập luyện
     */
    public void getAllHistory(ApiCallback<List<com.uilover.project1932.Api.Response.WorkoutHistoryResponse>> callback) {
        apiService.getAllHistory().enqueue(new Callback<List<com.uilover.project1932.Api.Response.WorkoutHistoryResponse>>() {
            @Override
            public void onResponse(Call<List<com.uilover.project1932.Api.Response.WorkoutHistoryResponse>> call,
                                   Response<List<com.uilover.project1932.Api.Response.WorkoutHistoryResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(new Exception("Failed to load history"));
                }
            }

            @Override
            public void onFailure(Call<List<com.uilover.project1932.Api.Response.WorkoutHistoryResponse>> call, Throwable t) {
                Log.e(TAG, "Network error loading history", t);
                String errorMsg = "Network error: ";
                if (t.getMessage() != null) {
                    errorMsg += t.getMessage();
                } else {
                    errorMsg += "Không thể kết nối server";
                }
                callback.onError(new Exception(errorMsg, t));
            }
        });
    }

    /**
     * Thêm vào lịch sử
     */
    public void addToHistory(com.uilover.project1932.Api.Response.WorkoutHistoryResponse history, ApiCallback<Void> callback) {
        apiService.addToHistory(history).enqueue(new Callback<com.uilover.project1932.Api.Response.WorkoutHistoryResponse>() {
            @Override
            public void onResponse(Call<com.uilover.project1932.Api.Response.WorkoutHistoryResponse> call,
                                   Response<com.uilover.project1932.Api.Response.WorkoutHistoryResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError(new Exception("Failed to add history"));
                }
            }

            @Override
            public void onFailure(Call<com.uilover.project1932.Api.Response.WorkoutHistoryResponse> call, Throwable t) {
                Log.e(TAG, "Network error adding history", t);
                String errorMsg = "Network error: ";
                if (t.getMessage() != null) {
                    errorMsg += t.getMessage();
                } else {
                    errorMsg += "Không thể kết nối server";
                }
                callback.onError(new Exception(errorMsg, t));
            }
        });
    }

    /**
     * Lấy tất cả yêu thích
     */
    public void getAllFavorites(ApiCallback<List<com.uilover.project1932.Api.Response.FavoriteResponse>> callback) {
        apiService.getAllFavorites().enqueue(new Callback<List<com.uilover.project1932.Api.Response.FavoriteResponse>>() {
            @Override
            public void onResponse(Call<List<com.uilover.project1932.Api.Response.FavoriteResponse>> call,
                                   Response<List<com.uilover.project1932.Api.Response.FavoriteResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    String errorMsg = "Failed to load favorites: HTTP " + response.code();
                    if (response.errorBody() != null) {
                        try {
                            errorMsg += " - " + response.errorBody().string();
                        } catch (Exception e) {
                            // Ignore
                        }
                    }
                    callback.onError(new Exception(errorMsg));
                }
            }

            @Override
            public void onFailure(Call<List<com.uilover.project1932.Api.Response.FavoriteResponse>> call, Throwable t) {
                Log.e(TAG, "Network error loading favorites", t);
                String errorMsg = "Network error: ";
                if (t.getMessage() != null) {
                    errorMsg += t.getMessage();
                } else {
                    errorMsg += "Không thể kết nối server";
                }
                callback.onError(new Exception(errorMsg, t));
            }
        });
    }

    /**
     * Lấy thống kê
     */
    public void getStats(ApiCallback<WorkoutApiService.StatsResponse> callback) {
        apiService.getStats().enqueue(new Callback<WorkoutApiService.StatsResponse>() {
            @Override
            public void onResponse(Call<WorkoutApiService.StatsResponse> call,
                                   Response<WorkoutApiService.StatsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    String errorMsg = "Failed to load stats: HTTP " + response.code();
                    if (response.errorBody() != null) {
                        try {
                            errorMsg += " - " + response.errorBody().string();
                        } catch (Exception e) {
                            // Ignore
                        }
                    }
                    callback.onError(new Exception(errorMsg));
                }
            }

            @Override
            public void onFailure(Call<WorkoutApiService.StatsResponse> call, Throwable t) {
                Log.e(TAG, "Network error loading stats", t);
                String errorMsg = "Network error: ";
                if (t.getMessage() != null) {
                    errorMsg += t.getMessage();
                } else {
                    errorMsg += "Không thể kết nối server";
                }
                callback.onError(new Exception(errorMsg, t));
            }
        });
    }

    /**
     * Callback interface cho API calls
     */
    public interface ApiCallback<T> {
        void onSuccess(T result);
        void onError(Throwable error);
    }
}

