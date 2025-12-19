package com.uilover.project1932.Api;

import com.uilover.project1932.Api.Response.FavoriteResponse;
import com.uilover.project1932.Api.Response.WorkoutResponse;
import com.uilover.project1932.Api.Response.WorkoutHistoryResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WorkoutApiService {
    
    // Workouts
    @GET("workouts")
    Call<List<WorkoutResponse>> getAllWorkouts();
    
    @GET("workouts/{id}")
    Call<WorkoutResponse> getWorkoutById(@Path("id") int id);
    
    // Favorites
    @GET("favorites")
    Call<List<FavoriteResponse>> getAllFavorites();
    
    @GET("favorites/check/{title}")
    Call<FavoriteCheckResponse> checkFavorite(@Path("title") String title);
    
    @POST("favorites")
    Call<FavoriteResponse> addToFavorites(@Body FavoriteRequest request);
    
    @DELETE("favorites/{title}")
    Call<Void> removeFromFavorites(@Path("title") String title);
    
    // History
    @GET("history")
    Call<List<WorkoutHistoryResponse>> getAllHistory();
    
    @POST("history")
    Call<WorkoutHistoryResponse> addToHistory(@Body WorkoutHistoryResponse history);
    
    @GET("history/stats")
    Call<StatsResponse> getStats();
    
    // Inner classes for request/response
    class FavoriteRequest {
        public String workoutTitle;
        public FavoriteRequest(String workoutTitle) {
            this.workoutTitle = workoutTitle;
        }
    }
    
    class FavoriteCheckResponse {
        public boolean isFavorite;
    }
    
    class StatsResponse {
        public int totalKcal;
        public long totalWorkouts;
    }
}

