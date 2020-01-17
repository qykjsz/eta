package com.qingyun.mvpretrofitrx.mvp.entity;

import java.io.Serializable;

public class Group  implements Serializable {
    private String name;
    private String code;
    private String introduce;
    private String number;
//    是否需要验证 1需要 2不需要
    private int verification;
//    本人是否是群主 1是 2不是
    private int owner;
    private int qzid;
    private int id;
    private String photo;




    public int getQzid() {
        return qzid;
    }

    public void setQzid(int qzid) {
        this.qzid = qzid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getVerification() {
        return verification;
    }

    public void setVerification(int verification) {
        this.verification = verification;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
