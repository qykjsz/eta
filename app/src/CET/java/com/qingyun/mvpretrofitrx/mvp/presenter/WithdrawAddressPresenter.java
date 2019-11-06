package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.contract.WithdrawAddressContract;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.WithdrawAddress;
import com.qingyun.mvpretrofitrx.mvp.model.WithdrawAddressModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import java.util.List;

public class WithdrawAddressPresenter extends WithdrawAddressContract.Presenter {
    private WithdrawAddressModel model;
    private Context context;


    public WithdrawAddressPresenter(Context context) {
        this.model = new WithdrawAddressModel();
        this.context = context;
    }


    @Override
    public void addWithdrawAddress(String address, String remark) {
        model.addWithdrawAddress(context,address,remark,getView().bindLifecycle(), new ObserverResponseListener<WithdrawAddress>() {

            @Override
            public void onNext(WithdrawAddress withdrawAddress) {
                if(getView() != null){
                    getView().addWithdrawAddressSuccess(null);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void deleteWithdrawAddress(int id) {
        model.deleteWithdrawAddress(context,id,getView().bindLifecycle(), new ObserverResponseListener<NormalResponse>() {
            @Override
            public void onNext(NormalResponse normalResponse) {
                if(getView() != null){
                    getView().deleteWithdrawAddressSuccess(normalResponse);
                }
            }
            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void getWithdrawAddressList(int page, int per_page) {
        model.getWithdrawAddressList(context,page,per_page,getView().bindLifecycle(), new ObserverResponseListener<List<WithdrawAddress>>() {

            @Override
            public void onNext(List<WithdrawAddress> withdrawAddresses) {
                if(getView() != null){
                    getView().getWithdrawAddressListSuccess(withdrawAddresses);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }
}
