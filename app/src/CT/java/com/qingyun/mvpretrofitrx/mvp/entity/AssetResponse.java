package com.qingyun.mvpretrofitrx.mvp.entity;

import java.io.Serializable;
import java.util.List;

public class AssetResponse implements Serializable {
    private String allnumber;
    private List<Asset> glod;
    private Proportion proportion;
    private String today;

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public Proportion getProportion() {
        return proportion;
    }

    public void setProportion(Proportion proportion) {
        this.proportion = proportion;
    }

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
