package com.qingyun.mvpretrofitrx.mvp.entity;

/**
 * Date 2019/11/18.
 * Created by Sam
 * ClassExplain：
 */
public class GameData {

    public String id;
    public String name;
    public String img;
    public String url;
    public String text;
    public String android;//android包名
    public String appdown;//下载链接
    public int types;//1.跳转Web页面 2.跳转app

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

    public String getAndroid() {
        return android;
    }

    public String getAppdown() {
        return appdown;
    }

    public int getTypes() {
        return types;
    }

    public void setAndroid(String android) {
        this.android = android;
    }

    public void setAppdown(String appdown) {
        this.appdown = appdown;
    }

    public void setTypes(int types) {
        this.types = types;
    }
}
