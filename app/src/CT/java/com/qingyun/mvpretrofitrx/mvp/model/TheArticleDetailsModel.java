package com.qingyun.mvpretrofitrx.mvp.model;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import io.reactivex.ObservableTransformer;

public class TheArticleDetailsModel<T> extends BaseModel {

    public void getFlashList(Context context, String id, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener) {
        subscribe(context, Api.getApiService().geTheArticleDetails(id), observerListener, transformer);

    }


}
