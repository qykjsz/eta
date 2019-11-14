package com.qingyun.mvpretrofitrx.mvp.model;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import io.reactivex.ObservableTransformer;


public class NoticModel<T> extends BaseModel {


    public void getNoticeList(Context context,int page,String contacts, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getNoticeList(page,contacts), observerListener,transformer);

    }

    public void getNoticeDetail(Context context,int id,String contacts, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getNoticeDetail(id,contacts), observerListener,transformer);

    }
}
