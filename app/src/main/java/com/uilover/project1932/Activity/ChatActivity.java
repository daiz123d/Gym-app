package com.uilover.project1932.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uilover.project1932.Adapter.ChatAdapter;
import com.uilover.project1932.Data.model.ChatMessage;
import com.uilover.project1932.R;
import com.uilover.project1932.Repository.ChatRepository;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    EditText edtPrompt;
    ImageButton btnSend;
    RecyclerView recyclerView;

    ChatAdapter chatAdapter;
    List<ChatMessage> messageList;

    ChatRepository chatRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        edtPrompt = findViewById(R.id.message_edit_text);
        btnSend = findViewById(R.id.send_btn);
        recyclerView = findViewById(R.id.chat_recycler_view);

        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(messageList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatAdapter);

        chatRepository = new ChatRepository();
        messageList.add(new ChatMessage(ChatMessage.USER, "Hello test"));
        messageList.add(new ChatMessage(ChatMessage.BOT, "Bot trả lời test"));
        chatAdapter.notifyDataSetChanged();
        btnSend.setOnClickListener(v -> {
            String prompt = edtPrompt.getText().toString().trim();
            if (prompt.isEmpty()) return;

            // 1️⃣ Add user message
            messageList.add(new ChatMessage(ChatMessage.USER, prompt));
            chatAdapter.notifyItemInserted(messageList.size() - 1);
            recyclerView.scrollToPosition(messageList.size() - 1);
            edtPrompt.setText("");

            // 2️⃣ Call AI
            chatRepository.ask(prompt, result -> {
                Log.d("AI", result);
                runOnUiThread(() -> {
                    messageList.add(new ChatMessage(ChatMessage.BOT, result));
                    chatAdapter.notifyItemInserted(messageList.size() - 1);
                });
            });

        });
    }
}
