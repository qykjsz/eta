package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.contract.SimpleContract;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;
import com.qingyun.mvpretrofitrx.mvp.model.SimpleModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

public class SimplePresenter extends SimpleContract.Presenter {
    private SimpleModel model;
    private Context context;


    public SimplePresenter(Context context) {
        this.model = new SimpleModel();
        this.context = context;
    }




    @Override
    public void getSimple() {
        model.getSimple(context, getView().bindLifecycle(), new ObserverResponseListener<NormalResponse>() {

            @Override
            public void onNext(NormalResponse normalResponse) {
                if(getView() != null){
                    getView().getSimpleSuccess();
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }
}
