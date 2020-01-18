package com.qingyun.mvpretrofitrx.mvp.entity;

/**
 * Date 2019/11/16.
 * Created by Sam
 * ClassExplain：
 */
public class DApp {
    public String id;
    public String name;//名称
    public String img;//图片
    public String url;//跳转连接
    public String android;//android包名
    public String appdown;//下载链接
    public int types;//1.跳转Web页面 2.跳转app
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
