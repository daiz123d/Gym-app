package com.uilover.project1932.Data.model;

public class ChatMessage {
    public static final int USER = 0;
    public static final int BOT = 1;

    public int type;
    public String message;

    public ChatMessage(int type, String message) {
        this.type = type;
        this.message = message;
    }
}