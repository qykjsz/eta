package com.qingyun.mvpretrofitrx.mvp.entity;

import java.util.List;

public class BusinessSellOrBuyResponse {
    private List<BusinessSellOrBuy> data;
    private int total_people;
    private float total_s;

    public List<BusinessSellOrBuy> getData() {
        return data;
    }

    public void setData(List<BusinessSellOrBuy> data) {
        this.data = data;
    }

    public int getTotal_people() {
        return total_people;
    }

    public void setTotal_people(int total_people) {
        this.total_people = total_people;
    }

    public float getTotal_s() {
        return total_s;
    }

    public void setTotal_s(float total_s) {
        this.total_s = total_s;
    }
}
