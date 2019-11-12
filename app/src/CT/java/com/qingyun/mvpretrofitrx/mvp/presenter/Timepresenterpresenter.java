package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.contract.TheArticleDetailsContact;
import com.qingyun.mvpretrofitrx.mvp.entity.TheArticleDetails;
import com.qingyun.mvpretrofitrx.mvp.model.TheArticleDetailsModel;
import com.qingyun.mvpretrofitrx.mvp.model.TimeModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

public class Timepresenterpresenter extends TheArticleDetailsContact.Presenter {
    private Context context;
    private TheArticleDetailsModel model;

    public Timepresenterpresenter(Context context) {
        this.context = context;
        this.model = new TheArticleDetailsModel();
    }

    @Override
    public void getTheArticleDetails(String id) {
        model.getFlashList(context, id, getView().bindLifecycle(), new ObserverResponseListener<TheArticleDetails>() {


            @Override
            public void onNext(TheArticleDetails theArticleDetails) {
                if (getView() != null) {
                    getView().getContactListFlash(theArticleDetails);
                }

            }

            @Override
            public void onError(String e) {

            }
        });

    }
}
