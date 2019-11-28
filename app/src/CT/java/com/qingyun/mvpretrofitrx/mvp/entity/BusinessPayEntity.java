package com.qingyun.mvpretrofitrx.mvp.entity;

import java.io.Serializable;

public class BusinessPayEntity implements Serializable {

    private String address;
    private String amount;
    private String coinName;
    private String decimal;
    private String gaslimit;
    private String gasprice;
    private String img;
    private String price;
    private String priceName;
    private String token;

    public String getAdddress() {
        return address;
    }

    public void setAdddress(String adddress) {
        this.address = adddress;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getDecimal() {
        return decimal;
    }

    public void setDecimal(String decimal) {
        this.decimal = decimal;
    }

    public String getGaslimit() {
        return gaslimit;
    }

    public void setGaslimit(String gaslimit) {
        this.gaslimit = gaslimit;
    }

    public String getGasprice() {
        return gasprice;
    }

    public void setGasprice(String gasprice) {
        this.gasprice = gasprice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
