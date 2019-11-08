package com.qingyun.mvpretrofitrx.mvp.entity;

public class Asset {
    private String name;
    private String img;
    private String number;
    private String usdtnumber;

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
