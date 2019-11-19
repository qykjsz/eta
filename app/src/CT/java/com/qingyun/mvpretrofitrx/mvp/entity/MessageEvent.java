package com.qingyun.mvpretrofitrx.mvp.entity;

/**
 * Date 2019/11/18.
 * Created by Sam
 * ClassExplain：
 */
public class MessageEvent {
    private int id;
    private String name;

    public MessageEvent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
