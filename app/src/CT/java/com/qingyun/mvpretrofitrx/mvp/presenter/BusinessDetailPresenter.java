package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.contract.BusinessDetailContact;
import com.qingyun.mvpretrofitrx.mvp.contract.CoinContact;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessDetail;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.qingyun.mvpretrofitrx.mvp.model.BusinessDetailModel;
import com.qingyun.mvpretrofitrx.mvp.model.CoinModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import java.util.List;

public class BusinessDetailPresenter extends BusinessDetailContact.Presenter {
    private BusinessDetailModel model;
    private Context context;


    public BusinessDetailPresenter(Context context) {
        this.model = new BusinessDetailModel();
        this.context = context;
    }



    @Override
    public void getBusinessDetail(String address, String glod, String id) {
        model.getBusinessDetail(context,address,glod,id,getView().bindLifecycle(), new ObserverResponseListener<BusinessDetail>() {

            @Override
            public void onNext(BusinessDetail businessDetail) {
                if(getView() != null){
                    getView().getBusinessDetailSuccess(businessDetail);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }
}
