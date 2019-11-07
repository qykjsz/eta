package com.qingyun.mvpretrofitrx.mvp.base;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;
import com.qingyun.mvpretrofitrx.mvp.progress.ProgressObserver;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * M层父类
 */

public class BaseModel<T> {
    /**
     * 封装线程管理和订阅的过程
     * flag  是否添加progressdialog
     */
    public void subscribe(Context context, final Observable observable, ObserverResponseListener<T> listener,
                          ObservableTransformer<T,T> transformer, boolean isDialog, boolean cancelable) {
        if (transformer==null) return;
        final Observer observer = new ProgressObserver<T>(context, listener, isDialog,cancelable);
        observable.compose(transformer)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void subscribe(Context context, final Observable observable, ObserverResponseListener<T> listener,
                          ObservableTransformer<T,T> transformer) {
        subscribe(context,observable,listener,transformer,true,true);
    }

}
