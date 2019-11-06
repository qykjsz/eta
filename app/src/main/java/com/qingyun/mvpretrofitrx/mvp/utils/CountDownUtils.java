package com.qingyun.mvpretrofitrx.mvp.utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class CountDownUtils {

    private Disposable call;

    public Disposable getCall() {
        return call;
    }

    public CountDownUtils showCloseCountDown(int start, final int count, final CountDownListener countDownListener) {

         call = Observable.intervalRange(start, count, 0, 1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {

            @Override
            public void accept(Long aLong) throws Exception {
                countDownListener.next(count-aLong);
            }


        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                countDownListener.onComplete();
            }
        });
        return CountDownUtils.this;
    }




    public CountDownUtils showCountDown(int start, final int count, long delay , final CountDownListener countDownListener) {

        call = Observable.intervalRange(start, count, delay,1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {

            @Override
            public void accept(Long aLong) throws Exception {
                countDownListener.next(count-aLong-1);
            }


        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                countDownListener.onComplete();
            }
        });
        return CountDownUtils.this;
    }


    public interface  CountDownListener{
        void next(Long aLong);
        void onComplete();
    }

}
