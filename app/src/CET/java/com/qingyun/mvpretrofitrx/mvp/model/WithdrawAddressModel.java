package com.qingyun.mvpretrofitrx.mvp.model;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import io.reactivex.ObservableTransformer;


public class WithdrawAddressModel<T> extends BaseModel {


    public void getWithdrawAddressList(Context context,int page,int per_page, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
//            subscribe(context, Api.getApiService().getWithdrawAddressList(page,per_page), observerListener,transformer);

    }
    public void addWithdrawAddress(Context context,String address,String remark, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
//        subscribe(context, Api.getApiService().addWithdrawAddress(address,remark), observerListener,transformer);

    }

    public void deleteWithdrawAddress(Context context,int id, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
//        subscribe(context, Api.getApiService().deleteWithdrawAddress(id), observerListener,transformer);

    }

}
