package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.contract.WalletAssetContact;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.GasPrice;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLogResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.VersionInfo;
import com.qingyun.mvpretrofitrx.mvp.model.WalletAssetModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import java.util.List;

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

    @Override
    public void searchLogByHash(String address, String hash, String glod) {
        model.searchLogByHash(context,address,hash,glod,getView().bindLifecycle(), new ObserverResponseListener<TransferLog>() {

            @Override
            public void onNext(TransferLog  transferLog) {
                if(getView() != null){
                    getView().searchLogByHashSuccess(transferLog);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void getGasPrice() {
        model.getGasPrice(context,getView().bindLifecycle(), new ObserverResponseListener<List<GasPrice>>() {

            @Override
            public void onNext(List<GasPrice>  gasPriceList) {
                if(getView() != null){
                    getView().getGasPriceSuccess(gasPriceList);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void getVersion() {
        model.getVersion(context,getView().bindLifecycle(), new ObserverResponseListener<VersionInfo>() {

            @Override
            public void onNext(VersionInfo versionInfo) {
                if(getView() != null){
                    getView().getVersionSuccess(versionInfo);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void checkCanTransfer(String name) {
        model.checkCanTransfer(context,name,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().checkCanTransferSuccess();
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }
}
