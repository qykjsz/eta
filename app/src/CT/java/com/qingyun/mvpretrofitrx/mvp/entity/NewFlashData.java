package com.qingyun.mvpretrofitrx.mvp.entity;

/**
 * Date 2019/11/14.
 * Created by Sam
 * ClassExplain：快讯
 */
public class NewFlashData {
    public String title;//题目
    public String content;//内容
    public long time;//时间
    public String source;//来源
    public boolean showDes;
    public String url;//二维码地址
    public boolean isShowDes() {
        return showDes;
    }

    public void setShowDes(boolean isShowDes) {
        showDes = isShowDes;
    }
}
