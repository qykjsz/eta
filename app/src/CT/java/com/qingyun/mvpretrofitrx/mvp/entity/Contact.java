package com.qingyun.mvpretrofitrx.mvp.entity;

import java.io.Serializable;

public class Contact implements Serializable {
    private String id;
    private String name;
    private String remarks;
    private String wallettype;
    private String address;
    private String time;

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getWallettype() {
        return wallettype;
    }

    public void setWallettype(String wallettype) {
        this.wallettype = wallettype;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
