package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinTypeRate;
import com.qingyun.mvpretrofitrx.mvp.entity.InvestLog;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Platform;

import java.util.List;


public interface InvestContact {

    interface View extends BaseView {

        void getNodeSuccess(String node);
        void getCoinTypeRateSuccess(List<CoinTypeRate> coinTypeRateList);
        void checkAccountSuccess(NormalResponse normalResponse);
        void addInvestInfoSuccess(NormalResponse normalResponse);

        void getSuprtPlatformSuccess(List<Platform> platformList);
        void getInvestAmountListSuccess(List<String> amounts);
        void investSuccess( );
        void getInvestLogSuccess(List<InvestLog> investLogList);
        void checkInvestInfoSuccess(String s);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getCoinTypeRate();
        public abstract void getNode();
        public abstract void checkAccount(String account);
        public abstract void addInvestInfo(String account,String num,String order_sn,String sign);
        public abstract void getSuprtPlatform();
        public abstract void getInvestAmountList();
        public abstract void invest(String from,
                                    String to,
                                    String hash,
                                    String moneystate,
                                    String gameid,
                                    String money,
                                    String gamenumber,
                                    String gameuser);

        public abstract void getInvestLog(String address,int page);
        public abstract void checkInvestInfo(String gameid,String gamenumber,String moneystate,String money);

    }
}
