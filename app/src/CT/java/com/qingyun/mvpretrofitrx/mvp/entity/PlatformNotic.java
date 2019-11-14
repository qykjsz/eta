package com.qingyun.mvpretrofitrx.mvp.entity;

public class PlatformNotic {
    private String id;
    private String name;
    private String time;
//    0 没看过 1看过
    private String islook;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIslook() {
        return islook;
    }

    public void setIslook(String islook) {
        this.islook = islook;
    }
}
