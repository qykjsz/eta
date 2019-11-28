package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessPayLog;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinTypeRate;
import com.qingyun.mvpretrofitrx.mvp.entity.CurrencyRate;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;

import java.util.List;


public interface BusinessPayContact {

    interface View extends BaseView {

        void getCurrencyRateSuccess(List<CurrencyRate> currencyRateList);
        void getCoinTypeRateSuccess(List<CoinTypeRate> coinTypeRateList);
        void getBusinessPayLogSuccess(List<BusinessPayLog> businessPayLogList);
        void addBusinessPayLogSuccess(NormalResponse normalResponse);
        void getNodeSuccess(String node);


    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getCurrencyRate();
        public abstract void getCoinTypeRate();
        public abstract void getBusinessPayLog(String address,int page);
        public abstract void getNode();
        public abstract void addBusinessPayLog(String from,String to,String hash,String money,String moneyid,String fimoney,String fimoneyid);

    }
}
