package com.qingyun.mvpretrofitrx.mvp.model;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;
import com.qingyun.mvpretrofitrx.mvp.progress.ProgressObserver;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class WalletAssetModel<T> extends BaseModel {




    public void getWalletInfo(Context context,String walletAddress, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
            subscribe(context, Api.getApiService().getWalletInfo(walletAddress), observerListener,transformer,false,true);


    }
    public void addWallet(Context context,String walletAddress, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().addWallet(walletAddress), observerListener,transformer);

    }


    public void getLog(Context context,String address,String glod,int type,int page, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getLog(address,glod,type,page), observerListener,transformer);

    }
    public void getNode(Context context,ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getNode(), observerListener,transformer);

    }

    public void searchLogByHash(Context context,String address,String hash,String glod,ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().searchLogByHash(address,hash,glod), observerListener,transformer);

    }

      public void getGasPrice(Context context,ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getGasPrice(), observerListener,transformer);

    }



    public void getVersion(Context context,ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getVersion("android"), observerListener,transformer);

    }

    public void checkCanTransfer(Context context,String name
                                 ,ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().checkCanTransfer(name), observerListener,transformer);

    }

}
