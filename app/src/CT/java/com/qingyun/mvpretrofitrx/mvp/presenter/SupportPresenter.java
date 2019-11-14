package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.contract.AboutUsContact;
import com.qingyun.mvpretrofitrx.mvp.contract.SupportContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.qingyun.mvpretrofitrx.mvp.model.AboutUsModel;
import com.qingyun.mvpretrofitrx.mvp.model.SupportModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import java.util.List;

public class SupportPresenter extends SupportContact.Presenter {
    private SupportModel model;
    private Context context;


    public SupportPresenter(Context context) {
        this.model = new SupportModel();
        this.context = context;
    }

    @Override
    public void getSupportList() {
        model.getSupportList(context,getView().bindLifecycle(), new ObserverResponseListener<List<Item>>() {

            @Override
            public void onNext(List<Item> itemList) {
                if(getView() != null){
                    getView().getSupportListSuccess(itemList);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }
}
