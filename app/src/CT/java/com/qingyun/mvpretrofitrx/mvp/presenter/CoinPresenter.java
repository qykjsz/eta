package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.contract.CoinContact;
import com.qingyun.mvpretrofitrx.mvp.contract.WalletAssetContact;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.qingyun.mvpretrofitrx.mvp.model.CoinModel;
import com.qingyun.mvpretrofitrx.mvp.model.WalletAssetModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import java.util.List;

public class CoinPresenter extends CoinContact.Presenter {
    private CoinModel model;
    private Context context;


    public CoinPresenter(Context context) {
        this.model = new CoinModel();
        this.context = context;
    }



    @Override
    public void getSupportCoinList(String address) {
        model.getSupportCoinList(context,address,getView().bindLifecycle(), new ObserverResponseListener<List<Wallet>>() {

            @Override
            public void onNext(List<Wallet> wallets) {
                if(getView() != null){
                    getView().getSupportCoinListSuccess(wallets);
                }
            }

            @Override
            public void onError(String e) {

            }
        });

    }

    @Override
    public void bindCoin(final String operationtype, String address, String glodid) {
        model.bindCoin(context,operationtype,address,glodid,getView().bindLifecycle(), new ObserverResponseListener<Object>() {

            @Override
            public void onNext(Object o) {
                if(getView() != null){
                    getView().bindCoinSuccess(operationtype);
                }
            }

            @Override
            public void onError(String e) {

            }
        });

    }
}
