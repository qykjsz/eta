package com.qingyun.mvpretrofitrx.mvp.entity;

import java.util.List;

public class ChatMessageLogResponse {
    private List<ChatMessage> chat;

    public List<ChatMessage> getChat() {
        return chat;
    }

    public void setChat(List<ChatMessage> chat) {
        this.chat = chat;
    }
}
