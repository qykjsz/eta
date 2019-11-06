package com.qingyun.mvpretrofitrx.mvp.entity;

public class BusinessSellOrBuy {
    private int id;
    private int uid;
    private int is_over;
    private long created_at;
    private long updated_at;
    private String amount;
    private  String amount_done;
    private  String amount_lost;
    private String remark;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type
            ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getIs_over() {
        return is_over;
    }

    public void setIs_over(int is_over) {
        this.is_over = is_over;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount_done() {
        return amount_done;
    }

    public void setAmount_done(String amount_done) {
        this.amount_done = amount_done;
    }

    public String getAmount_lost() {
        return amount_lost;
    }

    public void setAmount_lost(String amount_lost) {
        this.amount_lost = amount_lost;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
