package com.qingyun.mvpretrofitrx.mvp.entity;

import java.io.Serializable;

public class MyNews implements Serializable {

    private String id;
    private String name;

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
}
