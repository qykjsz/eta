package com.qingyun.mvpretrofitrx.mvp.entity;

import java.util.List;

public class PlatformNoticResponse {
    private int pages;
   private List<PlatformNotic> News;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<PlatformNotic> getNews() {
        return News;
    }

    public void setNews(List<PlatformNotic> news) {
        News = news;
    }
}
