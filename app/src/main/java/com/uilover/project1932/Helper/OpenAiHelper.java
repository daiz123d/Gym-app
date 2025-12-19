package com.uilover.project1932.Helper;

import com.uilover.project1932.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OpenAiHelper {

    public interface ApiResponseListener {
        void onSuccess(String response);
        void onFailure(String error);
    }

    public static void callOpenAiApi(String prompt, ApiResponseListener listener) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        
        // Using JSONObject to build the request body safely
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model", "gpt-3.5-turbo");
            JSONArray messages = new JSONArray();
            JSONObject userMessage = new JSONObject();
            userMessage.put("role", "user");
            userMessage.put("content", prompt);
            messages.put(userMessage);
            jsonBody.put("messages", messages);
            jsonBody.put("temperature", 0.7);
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onFailure("Failed to create JSON request body.");
            return;
        }

        RequestBody body = RequestBody.create(jsonBody.toString(), mediaType);

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .post(body)
                .addHeader("Authorization", "Bearer " + BuildConfig.OPENAI_API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                listener.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseBody = response.body().string();
                if (response.isSuccessful()) {
                    try {
                        // Parse the response to get the actual message content
                        JSONObject jsonObject = new JSONObject(responseBody);
                        JSONArray choices = jsonObject.getJSONArray("choices");
                        if (choices.length() > 0) {
                            JSONObject firstChoice = choices.getJSONObject(0);
                            JSONObject message = firstChoice.getJSONObject("message");
                            String content = message.getString("content");
                            listener.onSuccess(content.trim());
                        } else {
                            listener.onFailure("No response choices found.");
                        }
                    } catch (JSONException e) {
                         e.printStackTrace();
                         listener.onFailure("Failed to parse JSON response.");
                    }
                } else {
                    listener.onFailure("API Call failed with code: " + response.code() + " - " + responseBody);
                }
            }
        });
    }
}
