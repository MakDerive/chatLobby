package com.example.chatlobby.model.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private String sender;
    private String content;
    private String timestamp;
    private MessageType type;

    public enum MessageType {
        CHAT, JOIN, LEAVE, SYSTEM
    }
}