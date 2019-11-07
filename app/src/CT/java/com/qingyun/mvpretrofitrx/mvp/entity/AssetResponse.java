package com.qingyun.mvpretrofitrx.mvp.entity;

import java.util.List;

public class AssetResponse {
    private String allnumber;
    private List<Asset> glod;

    public String getAllnumber() {
        return allnumber;
    }

    public void setAllnumber(String allnumber) {
        this.allnumber = allnumber;
    }

    public List<Asset> getGlod() {
        return glod;
    }

    public void setGlod(List<Asset> glod) {
        this.glod = glod;
    }
}
