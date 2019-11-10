package com.qingyun.mvpretrofitrx.mvp.entity;

import java.io.Serializable;

public class Proportion implements Serializable {
    private String translate;
    private String ETH;
    private String USDT;
    private String HOPE;

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getETH() {
        return ETH;
    }

    public void setETH(String ETH) {
        this.ETH = ETH;
    }

    public String getUSDT() {
        return USDT;
    }

    public void setUSDT(String USDT) {
        this.USDT = USDT;
    }

    public String getHOPE() {
        return HOPE;
    }

    public void setHOPE(String HOPE) {
        this.HOPE = HOPE;
    }
}
