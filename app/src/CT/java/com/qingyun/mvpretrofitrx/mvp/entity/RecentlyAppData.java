package com.qingyun.mvpretrofitrx.mvp.entity;

/**
 * Date 2019/11/19.
 * Created by Sam
 * ClassExplain：
 */
public class RecentlyAppData {
    public String id;//appid
    public String name;//APP名字
    public String img;//APP图片
    public String url;//app链接
    public String text;//app简介

    public String getId() {
        return id;
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

    public String getText() {
        return text;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setText(String text) {
        this.text = text;
    }
}
