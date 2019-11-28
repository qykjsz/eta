package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.contract.AboutUsContact;
import com.qingyun.mvpretrofitrx.mvp.contract.BusinessPayContact;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessPayLogResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinTypeRate;
import com.qingyun.mvpretrofitrx.mvp.entity.CurrencyRate;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.qingyun.mvpretrofitrx.mvp.model.AboutUsModel;
import com.qingyun.mvpretrofitrx.mvp.model.BusinessPayModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import java.util.List;

public class BusinessPayPresenter extends BusinessPayContact.Presenter {
    private BusinessPayModel model;
    private Context context;


    public BusinessPayPresenter(Context context) {
        this.model = new BusinessPayModel();
        this.context = context;
    }


    @Override
    public void getCurrencyRate() {
        model.getCurrencyRate(context,getView().bindLifecycle(), new ObserverResponseListener<List<CurrencyRate>>() {

            @Override
            public void onNext(List<CurrencyRate> currencyRateList) {
                if(getView() != null){
                    getView().getCurrencyRateSuccess(currencyRateList);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void getCoinTypeRate() {
        model.getCoinTypeRate(context,getView().bindLifecycle(), new ObserverResponseListener<List<CoinTypeRate>>() {

            @Override
            public void onNext(List<CoinTypeRate> coinTypeRateList) {
                if(getView() != null){
                    getView().getCoinTypeRateSuccess(coinTypeRateList);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void getBusinessPayLog(String address, int page) {
        model.getBusinessPayLog(context,address,page,getView().bindLifecycle(), new ObserverResponseListener<BusinessPayLogResponse>() {

            @Override
            public void onNext(BusinessPayLogResponse businessPayLogResponse) {
                if(getView() != null){
                    getView().getBusinessPayLogSuccess(businessPayLogResponse.getOrder());
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void addBusinessPayLog(String from, String to, String hash, String money, String moneyid, String fimoney, String fimoneyid) {
        model.addBusinessPayLog(context, from,  to,  hash,  money,  moneyid,  fimoney,  fimoneyid,getView().bindLifecycle(), new ObserverResponseListener<NormalResponse>() {

            @Override
            public void onNext(NormalResponse normalResponse) {
                if(getView() != null){
                    getView().addBusinessPayLogSuccess(normalResponse);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }


    @Override
    public void getNode() {
        model.getNode(context,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String  node) {
                if(getView() != null){
                    getView().getNodeSuccess(node);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

}
