package com.qingyun.mvpretrofitrx.mvp.entity;

public class SystemNotice {
    private String type;
    private String status;


    public SystemNotice(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
