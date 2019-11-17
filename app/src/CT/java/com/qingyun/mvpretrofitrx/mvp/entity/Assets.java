package com.qingyun.mvpretrofitrx.mvp.entity;

import java.util.List;

public class Assets {
    private String address;
    private List<Wallet> glods;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Wallet> getGlods() {
        return glods;
    }

    public void setGlods(List<Wallet> glods) {
        this.glods = glods;
    }
}
