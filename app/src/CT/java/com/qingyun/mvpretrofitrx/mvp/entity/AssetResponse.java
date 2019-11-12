package com.qingyun.mvpretrofitrx.mvp.entity;

import java.io.Serializable;
import java.util.List;

public class AssetResponse implements Serializable {
    private String allnumber;
    private List<Wallet> glod;
    private List<Proportion> proportion;
    private String today;
    private List<MyNews> news;

    public List<MyNews> getNews() {
        return news;
    }

    public void setNews(List<MyNews> news) {
        this.news = news;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }


    public List<Proportion> getProportion() {
        return proportion;
    }

    public void setProportion(List<Proportion> proportion) {
        this.proportion = proportion;
    }

    public String getAllnumber() {
        return allnumber;
    }

    public void setAllnumber(String allnumber) {
        this.allnumber = allnumber;
    }

    public List<Wallet> getGlod() {
        return glod;
    }

    public void setGlod(List<Wallet> glod) {
        this.glod = glod;
    }
}
