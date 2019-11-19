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
}
