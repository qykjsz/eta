package com.qingyun.mvpretrofitrx.mvp.base;

import io.reactivex.ObservableTransformer;

public interface BaseView {
    <T> ObservableTransformer<T, T> bindLifecycle();
}
