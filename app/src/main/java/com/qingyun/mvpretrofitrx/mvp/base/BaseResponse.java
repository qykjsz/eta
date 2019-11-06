package com.qingyun.mvpretrofitrx.mvp.base;

import com.google.gson.annotations.SerializedName;

/**
 *
 * 返回的数据结构：
 * {
 "data": [],
 "errorCode": 0,
 "errorMsg": ""
 }
 */

public class BaseResponse<T> {

    //序列化名称  拿到我们想要的msg字段而不是errorMsg字段
    private String msg;
    private int code;
    private T data;

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

}
