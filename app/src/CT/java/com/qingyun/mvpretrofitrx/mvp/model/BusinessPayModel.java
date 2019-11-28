package com.qingyun.mvpretrofitrx.mvp.model;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import io.reactivex.ObservableTransformer;


public class BusinessPayModel<T> extends BaseModel {

    public void getCurrencyRate(Context context, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getCurrencyRate(), observerListener,transformer);

    }

    public void getCoinTypeRate(Context context, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getCoinTypeRate(), observerListener,transformer);

    }


    public void getBusinessPayLog(Context context,String address,int page, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getBusinessPayLog(address,page), observerListener,transformer);

    }



    public void addBusinessPayLog(Context context,String from,String to,String hash,String money,String moneyid,String fimoney,String fimoneyid, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().addBusinessPayLog( from, to, hash, money, moneyid, fimoney, fimoneyid), observerListener,transformer);

    }

    public void getNode(Context context,ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getNode(), observerListener,transformer);

    }

}
