package com.qingyun.mvpretrofitrx.mvp.entity;

public class CoinTypeRate {

    private int id;
    private String name;
    private String rate;
    private String img;
    private String gasprice;
    private String gaslimit;
    private String decimal;
    private String address;


    public String getGasprice() {
        return gasprice;
    }

    public void setGasprice(String gasprice) {
        this.gasprice = gasprice;
    }

    public String getGaslimit() {
        return gaslimit;
    }

    public void setGaslimit(String gaslimit) {
        this.gaslimit = gaslimit;
    }

    public String getDecimal() {
        return decimal;
    }

    public void setDecimal(String decimal) {
        this.decimal = decimal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
