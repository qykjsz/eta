package com.qingyun.mvpretrofitrx.mvp.entity;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

public class BinnerData extends SimpleBannerInfo {
    String resid;
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BinnerData(String resid, String url) {
        this.resid = resid;
        this.url = url;
    }

    public String getResid() {
        return resid;
    }

    public void setResid(String resid) {
        this.resid = resid;
    }

    @Override
    public Object getXBannerUrl() {
        return resid;
    }
}
