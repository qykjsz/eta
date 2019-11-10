package com.qingyun.mvpretrofitrx.mvp.entity;

import java.util.List;

public class TransferLogResponse {


    private String number;
    private String usdtnumber;
    private String today;
    private String pages;
    private List<TransferLog> order;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUsdtnumber() {
        return usdtnumber;
    }

    public void setUsdtnumber(String usdtnumber) {
        this.usdtnumber = usdtnumber;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public List<TransferLog> getOrder() {
        return order;
    }

    public void setOrder(List<TransferLog> order) {
        this.order = order;
    }
}
