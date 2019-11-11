package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.contract.FlashContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Flash;
import com.qingyun.mvpretrofitrx.mvp.model.FlashModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;


public class Flashpresenter extends FlashContact.Presenter {
    private Context context;
    FlashModel flashModel;

    public Flashpresenter(Context context) {
        this.context = context;
        this.flashModel = new FlashModel();
    }

    @Override
    public void getContacFlashtList(String contacts) {

        flashModel.getFlashList(context, contacts, getView().bindLifecycle(), new ObserverResponseListener<Flash>() {


            @Override
            public void onNext(Flash news) {

            }

            @Override
            public void onError(String e) {

            }
        });
    }
}
