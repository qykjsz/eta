package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessDetail;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;

import java.util.List;


public interface BusinessDetailContact {

    interface View extends BaseView {
        void getBusinessDetailSuccess(BusinessDetail businessDetail);
    }
    abstract class Presenter extends BasePresenter<View> {
        public abstract void getBusinessDetail(String address,String glod,String id);
    }

}
