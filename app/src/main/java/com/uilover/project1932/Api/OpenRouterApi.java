package com.uilover.project1932.Api;

import com.uilover.project1932.Data.model.ChatRequest;
import com.uilover.project1932.Data.model.ChatResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OpenRouterApi {

    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer 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"
    })
    @POST("chat/completions")
    Call<ChatResponse> chat(@Body ChatRequest request);
}
