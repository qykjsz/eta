package com.qingyun.mvpretrofitrx.mvp.entity;

import com.github.huajianjiang.expandablerecyclerview.widget.Parent;

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable, Parent<Content> {
    private String name;
    private String iosurl;
    private String androidurl;
    private List<Content> content;

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIosurl() {
        return iosurl;
    }

    public void setIosurl(String iosurl) {
        this.iosurl = iosurl;
    }

    public String getAndroidurl() {
        return androidurl;
    }

    public void setAndroidurl(String androidurl) {
        this.androidurl = androidurl;
    }

    @Override
    public List<Content> getChildren() {
        return content;
    }

    @Override
    public boolean isInitiallyExpandable() {
        return false;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
