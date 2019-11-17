package com.qingyun.mvpretrofitrx.mvp.entity;

/**
 * Date 2019/11/16.
 * Created by Sam
 * ClassExplain：
 */
public class DApp {
    public String name;//名称
    public String img;//图片
    public String url;//跳转连接
    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getUrl() {
        return url;
    }
}
