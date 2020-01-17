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
    private String type;
    private boolean isCheck = false;
    private int type1;
    private String remarks;
//    1+,2-
    private int actionType = 0;

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public GroupMember() {
    }

    public GroupMember(int actionType) {
        this.actionType = actionType;
    }

    public GroupMember(String pinyin, int type) {
        this.pinyin = pinyin;
        this.type1 = type;
    }

    public int getType() {
        return type1;
    }

    public void setType(int type) {
        this.type1 = type;
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
