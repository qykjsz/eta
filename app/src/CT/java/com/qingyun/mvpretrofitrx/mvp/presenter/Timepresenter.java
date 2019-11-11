package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.qingyun.mvpretrofitrx.mvp.contract.FlashContact;
import com.qingyun.mvpretrofitrx.mvp.contract.TimeContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Flash;
import com.qingyun.mvpretrofitrx.mvp.entity.Time;
import com.qingyun.mvpretrofitrx.mvp.model.FlashModel;
import com.qingyun.mvpretrofitrx.mvp.model.TimeModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;


public class Timepresenter extends TimeContact.Presenter {
    private Context context;
    TimeModel flashModel;

    public Timepresenter(Context context) {
        this.context = context;
        this.flashModel = new TimeModel();
    }

    @Override
    public void getContacFlashtList(String contacts) {

        flashModel.getFlashList(context, contacts, getView().bindLifecycle(), new ObserverResponseListener<Time>() {


            @Override
            public void onNext(Time news) {
                 if(getView() != null){
                    getView().getContactListFlash(news);
                }
            }

            @Override
            public void onError(String e) {
                Log.i("==========",e);

            }
        });
    }
}
