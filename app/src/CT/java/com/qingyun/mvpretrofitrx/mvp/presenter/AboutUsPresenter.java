package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.contract.AboutUsContact;
import com.qingyun.mvpretrofitrx.mvp.contract.CoinContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.qingyun.mvpretrofitrx.mvp.model.AboutUsModel;
import com.qingyun.mvpretrofitrx.mvp.model.CoinModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import java.util.List;

public class AboutUsPresenter extends AboutUsContact.Presenter {
    private AboutUsModel model;
    private Context context;


    public AboutUsPresenter(Context context) {
        this.model = new AboutUsModel();
        this.context = context;
    }



    @Override
    public void getAboutUsList() {
        model.getAboutUsList(context,getView().bindLifecycle(), new ObserverResponseListener<List<Item>>() {

            @Override
            public void onNext(List<Item> itemList) {
                if(getView() != null){
                    getView().getAboutUsListSuccess(itemList);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }
}
