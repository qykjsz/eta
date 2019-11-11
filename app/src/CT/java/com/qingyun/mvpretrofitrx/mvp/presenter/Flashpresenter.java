package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.qingyun.mvpretrofitrx.mvp.contract.FlashContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Contact;
import com.qingyun.mvpretrofitrx.mvp.entity.News;
import com.qingyun.mvpretrofitrx.mvp.model.FlashModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import java.util.List;


public class Flashpresenter extends FlashContact.Presenter {
    private Context context;
    FlashModel flashModel;

    public Flashpresenter(Context context) {
        this.context = context;
        this.flashModel = new FlashModel();
    }

    @Override
    public void getContacFlashtList(String contacts) {

       flashModel.getContactList(context,contacts,getView().bindLifecycle(), new ObserverResponseListener<News>() {

            @Override
            public void onNext(News contactList) {
                if(getView() != null){
                    getView().getContactListFlash(contactList);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }
}
