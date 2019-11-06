package com.qingyun.mvpretrofitrx.mvp.progress;

import com.qingyun.mvpretrofitrx.mvp.utils.ExceptionHandle.ResponeThrowable;

/**
 * 请求监听
 */

public interface ObserverResponseListener<T> {
    /**
     * 响应成功
     * @param t
     */
    void onNext(T t);

    /**
     * 响应失败
     * @param e
     */
    void onError(String e);
}
