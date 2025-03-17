package com.example.afinal.ui.slideshow;

public class ChatMessage {
    public static final int TYPE_USER = 0;
    public static final int TYPE_BOT = 1;

    private String message;
    private int messageType;

    public ChatMessage(String message, int messageType) {
        this.message = message;
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public int getMessageType() {
        return messageType;
    }
}
