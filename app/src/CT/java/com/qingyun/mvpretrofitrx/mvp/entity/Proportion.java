package com.qingyun.mvpretrofitrx.mvp.entity;

import java.io.Serializable;

public class Proportion implements Serializable {
    private String bili;
    private String name;


    public String getBili() {
        return bili;
    }

    public void setBili(String bili) {
        this.bili = bili;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
