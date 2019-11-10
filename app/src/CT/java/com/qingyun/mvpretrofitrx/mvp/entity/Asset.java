package com.qingyun.mvpretrofitrx.mvp.entity;

import java.io.Serializable;

public class Asset implements Serializable {
    private String name;
    private String img;
    private String number;
    private String usdtnumber;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUsdtnumber() {
        return usdtnumber;
    }

    public void setUsdtnumber(String usdtnumber) {
        this.usdtnumber = usdtnumber;
    }

}
