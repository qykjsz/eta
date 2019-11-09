package com.qingyun.mvpretrofitrx.mvp.model;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import io.reactivex.ObservableTransformer;


public class CoinModel<T> extends BaseModel {




    public void getSupportCoinList(Context context,String address, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
            subscribe(context, Api.getApiService().getSupportCoinList(address), observerListener,transformer);

    }

    public void bindCoin(Context context,String operationtype,String address,String glodid, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().bindCoin(operationtype,address,glodid), observerListener,transformer);

    }
}
