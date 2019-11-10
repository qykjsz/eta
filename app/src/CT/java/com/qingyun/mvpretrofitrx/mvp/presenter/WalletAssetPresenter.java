package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.contract.WalletAssetContact;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLogResponse;
import com.qingyun.mvpretrofitrx.mvp.model.WalletAssetModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

public class WalletAssetPresenter extends WalletAssetContact.Presenter {
    private WalletAssetModel model;
    private Context context;


    public WalletAssetPresenter(Context context) {
        this.model = new WalletAssetModel();
        this.context = context;
    }

    @Override
    public void getWalletInfo(String walletAddress) {
        model.getWalletInfo(context,walletAddress,getView().bindLifecycle(), new ObserverResponseListener<AssetResponse>() {

            @Override
            public void onNext(AssetResponse assetResponse) {
                if(getView() != null){
                    getView().getWalletInfoSuccess(assetResponse);
                }
            }

            @Override
            public void onError(String e) {

            }
        });

    }

    @Override
    public void addWallet(String walletAddress) {
        model.addWallet(context,walletAddress,getView().bindLifecycle(), new ObserverResponseListener<Object>() {

            @Override
            public void onNext(Object o) {
                if(getView() != null){
                    getView().addWalletSuccess();
                }
            }

            @Override
            public void onError(String e) {

            }
        });

    }

    @Override
    public void getLog(String address, String glod, int type, int page) {
        model.getLog(context,address,  glod,  type,  page,getView().bindLifecycle(), new ObserverResponseListener<TransferLogResponse>() {

            @Override
            public void onNext(TransferLogResponse  transferLogResponse) {
                if(getView() != null){
                    getView().getLogSuccess(transferLogResponse);
                }
            }

            @Override
            public void onError(String e) {

            }
        });

    }
}
