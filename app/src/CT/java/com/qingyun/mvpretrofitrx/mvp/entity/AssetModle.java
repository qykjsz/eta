package com.qingyun.mvpretrofitrx.mvp.entity;

public class AssetModle {
    private int resId;
    private int strId;

    public AssetModle(int resId, int strId) {
        this.resId = resId;
        this.strId = strId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getStrId() {
        return strId;
    }

    public void setStrId(int strId) {
        this.strId = strId;
    }
}
