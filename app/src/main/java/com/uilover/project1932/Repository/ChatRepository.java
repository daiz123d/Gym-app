package com.uilover.project1932.Repository;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ChatRepository {

    private static final String API_KEY = "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"; // key OpenRouter
    private static final String URL = "https://openrouter.ai/api/v1/chat/completions";

    OkHttpClient client = new OkHttpClient();

    public interface Callback {
        void onResult(String text);
    }

    public void ask(String prompt, Callback callback) {
        try {
            JSONObject body = new JSONObject();
            body.put("model", "mistralai/mistral-nemo");

            JSONArray messages = new JSONArray();
            JSONObject msg = new JSONObject();
            msg.put("role", "user");
            msg.put("content", prompt);
            messages.put(msg);

            body.put("messages", messages);

            Request request = new Request.Builder()
                    .url(URL)
                    .addHeader("Authorization", "Bearer " + API_KEY)
                    .addHeader("Content-Type", "application/json")
                    .post(RequestBody.create(
                            body.toString(),
                            MediaType.parse("application/json")
                    ))
                    .build();

            client.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    callback.onResult("Lỗi mạng");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String res = response.body().string();
                    try {
                        JSONObject json = new JSONObject(res);
                        String text = json
                                .getJSONArray("choices")
                                .getJSONObject(0)
                                .getJSONObject("message")
                                .getString("content");
                        callback.onResult(text);
                    } catch (Exception e) {
                        callback.onResult("Parse lỗi");
                    }
                }
            });

        } catch (Exception e) {
            callback.onResult("Lỗi request");
        }
    }
}
