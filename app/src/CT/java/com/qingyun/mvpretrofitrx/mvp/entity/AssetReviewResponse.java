package com.qingyun.mvpretrofitrx.mvp.entity;

import java.util.List;

public class AssetReviewResponse {
    private String allnumber;
    private String today;
    private List<Assets> assets;

    public List<Assets> getAssets() {
        return assets;
    }

    public void setAssets(List<Assets> assets) {
        this.assets = assets;
    }

    public String getAllnumber() {
        return allnumber;
    }

    public void setAllnumber(String allnumber) {
        this.allnumber = allnumber;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }
}
