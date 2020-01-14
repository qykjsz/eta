package com.qingyun.mvpretrofitrx.mvp.entity;

import java.io.Serializable;

public class GroupMember  implements Serializable {
    private String name;
//    1.群主 2成员
    private int shenfen;
    private int id;
    private int state;
    private String address;
    private String photo;
    private String pinyin;
    private int type=0;


    public GroupMember() {
    }

    public GroupMember(String pinyin, int type) {
        this.pinyin = pinyin;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShenfen() {
        return shenfen;
    }

    public void setShenfen(int shenfen) {
        this.shenfen = shenfen;
    }
}
