package com.qingyun.mvpretrofitrx.mvp.model;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import io.reactivex.ObservableTransformer;
import okhttp3.RequestBody;


public class AssetReviewModel<T> extends BaseModel {

    public void getAssetsReview(Context context, RequestBody address, ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){
        subscribe(context, Api.getApiService().getAssetsReview(address), observerListener,transformer);

    }
}
