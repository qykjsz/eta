package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.contract.TradingRecordContact;
import com.qingyun.mvpretrofitrx.mvp.contract.WalletAssetContact;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLogResponse;
import com.qingyun.mvpretrofitrx.mvp.model.TradingRecordModel;
import com.qingyun.mvpretrofitrx.mvp.model.WalletAssetModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

public class TradingRecordPresenter extends TradingRecordContact.Presenter {
    private TradingRecordModel model;
    private Context context;


    public TradingRecordPresenter(Context context) {
        this.model = new TradingRecordModel();
        this.context = context;
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
