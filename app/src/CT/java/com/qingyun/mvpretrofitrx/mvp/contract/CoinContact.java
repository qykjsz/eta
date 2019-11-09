package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;

import java.util.List;


public interface CoinContact {

    interface View extends BaseView {

        void getSupportCoinListSuccess(List<Wallet> coins);
        void bindCoinSuccess(String operationtype);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getSupportCoinList(String address);
//        操作 传1为绑定 2.取消绑定
        public abstract void bindCoin(String operationtype,String address,String glodid);
    }
}
