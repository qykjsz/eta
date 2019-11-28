package com.qingyun.mvpretrofitrx.mvp.entity;

import java.util.List;

public class BusinessPayLogResponse {
    private List<BusinessPayLog> order;

    public List<BusinessPayLog> getOrder() {
        return order;
    }

    public void setOrder(List<BusinessPayLog> order) {
        this.order = order;
    }
}
