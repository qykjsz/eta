package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.contract.AboutUsContact;
import com.qingyun.mvpretrofitrx.mvp.contract.TokenProfileContact;
import com.qingyun.mvpretrofitrx.mvp.entity.GoldInfo;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.qingyun.mvpretrofitrx.mvp.model.AboutUsModel;
import com.qingyun.mvpretrofitrx.mvp.model.TokenProfileModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import java.util.List;

public class TokenProflePresenter extends TokenProfileContact.Presenter {
    private TokenProfileModel model;
    private Context context;


    public TokenProflePresenter(Context context) {
        this.model = new TokenProfileModel();
        this.context = context;
    }




    @Override
    public void getGoldInfo(String glod) {
        model.getGoldInfo(context,glod,getView().bindLifecycle(), new ObserverResponseListener<GoldInfo>() {

            @Override
            public void onNext(GoldInfo goldInfo) {
                if(getView() != null){
                    getView().getGoldInfoSuccess(goldInfo);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }
}
