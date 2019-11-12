package com.qingyun.mvpretrofitrx.mvp.entity;

import java.io.Serializable;

public class Wallet implements Serializable {


    private String id;
    private String name;
    private String hyaddress;
    private String img;
//    1.我已经添加 2.我没有添加
    private String have;
    private String number;
    private String usdtnumber;


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

    public String getHyaddress() {
        return hyaddress;
    }

    public void setHyaddress(String hyaddress) {
        this.hyaddress = hyaddress;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHave() {
        return have;
    }

    public void setHave(String have) {
        this.have = have;
    }
}
