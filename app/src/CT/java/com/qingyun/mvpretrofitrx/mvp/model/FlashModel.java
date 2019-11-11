package com.qingyun.mvpretrofitrx.mvp.model;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseModel;
import com.qingyun.mvpretrofitrx.mvp.entity.Contact;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import java.util.List;

import io.reactivex.ObservableTransformer;

public class FlashModel<T> extends BaseModel {

    public void getFlashList(Context context, String page, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener) {
        subscribe(context, Api.getApiService().getflashmoel(page), observerListener, transformer);

    }


}
