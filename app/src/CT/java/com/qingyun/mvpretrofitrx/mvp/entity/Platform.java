package com.qingyun.mvpretrofitrx.mvp.entity;

public class Platform  {
    private String name;
    private String  id;
    private String  address;
    private String proportion;
    private String is_user_url;

    public String getIs_user_url() {
        return is_user_url;
    }

    public void setIs_user_url(String is_user_url) {
        this.is_user_url = is_user_url;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Platform(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
