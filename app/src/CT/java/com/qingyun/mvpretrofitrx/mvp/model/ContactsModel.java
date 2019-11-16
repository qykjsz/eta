package com.qingyun.mvpretrofitrx.mvp.model;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import io.reactivex.ObservableTransformer;


public class ContactsModel<T> extends BaseModel {


    public void getWalletInfo(Context context,String walletAddress, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getWalletInfo(walletAddress), observerListener,transformer);

    }


    public void addContacts(Context context,String contacts,String name,String remarks,String wallettype,String address, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().addContacts(contacts, name, remarks, wallettype, address), observerListener,transformer);

    }

    public void getContactList(Context context,String contacts, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getContactList(contacts), observerListener,transformer);

    }
}
