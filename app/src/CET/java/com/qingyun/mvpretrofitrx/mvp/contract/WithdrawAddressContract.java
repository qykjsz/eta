package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.WithdrawAddress;

import java.util.List;

public interface WithdrawAddressContract {

    interface View extends BaseView {
        void getWithdrawAddressListSuccess(List<WithdrawAddress> withdrawAddresses);
        void addWithdrawAddressSuccess(NormalResponse normalResponse);
        void deleteWithdrawAddressSuccess(NormalResponse normalResponse);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void addWithdrawAddress(String address,String remark);
        public abstract void deleteWithdrawAddress(int id);
        public abstract void getWithdrawAddressList(int page,int per_page);
    }
}
