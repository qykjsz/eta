package com.qingyun.mvpretrofitrx.mvp.entity;

public class Quotation {


    /**
     * name : ETH
     * img : https://et2.etac.io/glods/ETH.png
     * shangmoney : 185.43
     * xiamoney : 185.43
     * zd : -0.76
     */

    public String name;//名称
    public String allname;//全称
    public String circulation;//发行量
    public String shizhi;//市值
    public String vol;//24小时成交量

    public String img;
    public String shangmoney;
    public String xiamoney;
    public String zd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getShangmoney() {
        return shangmoney;
    }

    public void setShangmoney(String shangmoney) {
        this.shangmoney = shangmoney;
    }

    public String getXiamoney() {
        return xiamoney;
    }

    public void setXiamoney(String xiamoney) {
        this.xiamoney = xiamoney;
    }

    public String getZd() {
        return zd;
    }

    public void setZd(String zd) {
        this.zd = zd;
    }
}
