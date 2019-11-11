package com.qingyun.mvpretrofitrx.mvp.model;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseModel;
import com.qingyun.mvpretrofitrx.mvp.entity.Contact;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import java.util.List;

import io.reactivex.ObservableTransformer;

public class FlashModel<T> extends BaseModel {
    public void getContactList(Context context, String contacts, ObservableTransformer transformer, ObserverResponseListener observerListener) {
        subscribe(context, Api.getApiService().getflashmoel(contacts), observerListener, transformer);

    }

}
