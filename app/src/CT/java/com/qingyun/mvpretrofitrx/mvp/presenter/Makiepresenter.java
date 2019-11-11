package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.qingyun.mvpretrofitrx.mvp.contract.FlashContact;
import com.qingyun.mvpretrofitrx.mvp.contract.MakieContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Flash;
import com.qingyun.mvpretrofitrx.mvp.entity.Quotation;
import com.qingyun.mvpretrofitrx.mvp.entity.Quotation1;
import com.qingyun.mvpretrofitrx.mvp.model.FlashModel;
import com.qingyun.mvpretrofitrx.mvp.model.MakieModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import java.util.List;


public class Makiepresenter extends MakieContact.Presenter {
    private Context context;
    MakieModel makieModel;

    public Makiepresenter(Context context) {
        this.context = context;
        this.makieModel = new MakieModel();
    }


    @Override
    public void getContacMakieList(String pase) {
        makieModel.getFlashList(context, pase, getView().bindLifecycle(), new ObserverResponseListener<List<Quotation>>() {
            @Override
            public void onNext(List<Quotation>quotations) {
                if (getView() != null) {
                    getView().getMakieListFlash(quotations);
                }
            }


            @Override
            public void onError(String e) {

            }
        });


    }
}
