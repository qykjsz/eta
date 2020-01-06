package com.qingyun.mvpretrofitrx.mvp.entity;

import java.util.List;

public class NewChat {
    private ApplyGroup addfriend;
    private ApplyGroup addgroup;
    private List<ChatMessage> chats;


    public ApplyGroup getAddfriend() {
        return addfriend;
    }

    public void setAddfriend(ApplyGroup addfriend) {
        this.addfriend = addfriend;
    }

    public ApplyGroup getAddgroup() {
        return addgroup;
    }

    public void setAddgroup(ApplyGroup addgroup) {
        this.addgroup = addgroup;
    }

    public List<ChatMessage> getChats() {
        return chats;
    }

    public void setChats(List<ChatMessage> chats) {
        this.chats = chats;
    }
}
