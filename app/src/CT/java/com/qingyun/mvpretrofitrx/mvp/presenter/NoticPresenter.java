package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.contract.CoinContact;
import com.qingyun.mvpretrofitrx.mvp.contract.NoticContact;
import com.qingyun.mvpretrofitrx.mvp.entity.NoticeDetail;
import com.qingyun.mvpretrofitrx.mvp.entity.PlatformNoticResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.qingyun.mvpretrofitrx.mvp.model.CoinModel;
import com.qingyun.mvpretrofitrx.mvp.model.NoticModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import java.util.List;

public class NoticPresenter extends NoticContact.Presenter {
    private NoticModel model;
    private Context context;


    public NoticPresenter(Context context) {
        this.model = new NoticModel();
        this.context = context;
    }



    @Override
    public void getNoticeList(int page, String contacts) {
        model.getNoticeList(context,page,contacts,getView().bindLifecycle(), new ObserverResponseListener<PlatformNoticResponse>() {

            @Override
            public void onNext(PlatformNoticResponse platformNoticResponse) {
                if(getView() != null){
                    getView().getNoticeListSuccess(platformNoticResponse);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void getNoticeDetail(int id,String contacts) {
        model.getNoticeDetail(context,id,contacts,getView().bindLifecycle(), new ObserverResponseListener<NoticeDetail>() {

            @Override
            public void onNext(NoticeDetail noticeDetail) {
                if(getView() != null){
                    getView().getNoticeDetailSuccess(noticeDetail);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }
}
