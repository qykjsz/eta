package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.contract.AboutUsContact;
import com.qingyun.mvpretrofitrx.mvp.contract.InvestContact;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessPayLogResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinTypeRate;
import com.qingyun.mvpretrofitrx.mvp.entity.InvestLogResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Platform;
import com.qingyun.mvpretrofitrx.mvp.model.AboutUsModel;
import com.qingyun.mvpretrofitrx.mvp.model.InvestModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import java.util.List;

public class InvestPresenter extends InvestContact.Presenter {
    private InvestModel model;
    private Context context;


    public InvestPresenter(Context context) {
        this.model = new InvestModel();
        this.context = context;
    }




    @Override
    public void getCoinTypeRate() {
        model.getCoinTypeRate(context,getView().bindLifecycle(), new ObserverResponseListener<List<CoinTypeRate>>() {

            @Override
            public void onNext(List<CoinTypeRate> coinTypeRateList) {
                if(getView() != null){
                    getView().getCoinTypeRateSuccess(coinTypeRateList);
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
    public void checkAccount(String account) {
        model.checkAccount(context,account,getView().bindLifecycle(), new ObserverResponseListener<NormalResponse>() {

            @Override
            public void onNext(NormalResponse  normalResponse) {
                if(getView() != null){
                    getView().checkAccountSuccess(normalResponse);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void addInvestInfo(String account, String num, String order_sn, String sign) {
        model.addInvestInfo(context,account,num,order_sn,sign,getView().bindLifecycle(), new ObserverResponseListener<NormalResponse>() {

            @Override
            public void onNext(NormalResponse  normalResponse) {
                if(getView() != null){
                    getView().addInvestInfoSuccess(normalResponse);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void getSuprtPlatform() {
        model.getSuprtPlatform(context,getView().bindLifecycle(), new ObserverResponseListener<List<Platform>>() {

            @Override
            public void onNext(List<Platform> platformList) {
                if(getView() != null){
                    getView().getSuprtPlatformSuccess(platformList);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void getInvestAmountList() {
        model.getInvestAmountList(context,getView().bindLifecycle(), new ObserverResponseListener<List<String>>() {

            @Override
            public void onNext(List<String> amounts) {
                if(getView() != null){
                    getView().getInvestAmountListSuccess(amounts);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void invest(String from, String to, String hash, String moneystate, String gameid, String money, String gamenumber, String gameuser) {
        model.invest(context,from,  to,  hash,  moneystate,  gameid,  money,  gamenumber,  gameuser,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().investSuccess();
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void getInvestLog(String address, int page) {
        model.getInvestLog(context,address,page,getView().bindLifecycle(), new ObserverResponseListener<InvestLogResponse>() {

            @Override
            public void onNext(InvestLogResponse investLogResponse) {
                if(getView() != null){
                    getView().getInvestLogSuccess(investLogResponse.getOrder());
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void checkInvestInfo(String gameid, String gamenumber, String moneystate, String money) {
        model.checkInvestInfo(context,gameid,  gamenumber,  moneystate,  money,getView().bindLifecycle(), new ObserverResponseListener<String>() {

            @Override
            public void onNext(String s) {
                if(getView() != null){
                    getView().checkInvestInfoSuccess(s);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

}
