package com.qingyun.mvpretrofitrx.mvp.model;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import io.reactivex.ObservableTransformer;


public class TradingRecordModel<T> extends BaseModel {


    public void getLog(Context context,String address,String glod,int type,int page, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getLog(address,glod,type,page), observerListener,transformer);

    }

}
