package com.qingyun.mvpretrofitrx.mvp.model;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import io.reactivex.ObservableTransformer;


public class InvestModel<T> extends BaseModel {

    public static String gamePlatformBaseUrl;
//    http://www.amz23.com/?ct=new_recharge&ac=notice

    public void getCoinTypeRate(Context context, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getCoinTypeRate(), observerListener,transformer);

    }

    public void getNode(Context context,ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getNode(), observerListener,transformer);

    }

    public void checkAccount(Context context,String account ,ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        Api.UpdataBaseUrl(gamePlatformBaseUrl);
        subscribe(context, Api.getApiService().checkAccount(), observerListener,transformer);
        Api.UpdataBaseUrl(null);

    }

    public void addInvestInfo(Context context,String account,String num,String order_sn,String sign,ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        Api.UpdataBaseUrl(gamePlatformBaseUrl);
        subscribe(context, Api.getApiService().addInvestInfo(account,num,order_sn,sign), observerListener,transformer);
        Api.UpdataBaseUrl(null);
    }


    public void getSuprtPlatform(Context context, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getSuprtPlatform(), observerListener,transformer);

    }



    public void getInvestAmountList(Context context, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getInvestAmountList(), observerListener,transformer);

    }



    public void invest(Context context,String from,
                       String to,
                       String hash,
                       String moneystate,
                       String gameid,
                       String money,
                       String gamenumber,
                       String gameuser, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().invest(from,to,hash,moneystate,gameid,money,gamenumber,gameuser), observerListener,transformer);

    }



    public void getInvestLog(Context context, String address,int page,ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getInvestLog(address,page), observerListener,transformer);

    }

    public void checkInvestInfo(Context context,String gameid,String gamenumber,String moneystate,String money,ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().checkInvestInfo(gameid, gamenumber, moneystate, money), observerListener,transformer);

    }



    public void getCurrencyRate(Context context, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getCurrencyRate(), observerListener,transformer);

    }

}
