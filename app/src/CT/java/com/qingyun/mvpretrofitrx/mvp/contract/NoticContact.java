package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.NoticeDetail;
import com.qingyun.mvpretrofitrx.mvp.entity.PlatformNotic;
import com.qingyun.mvpretrofitrx.mvp.entity.PlatformNoticResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;

import java.util.List;


public interface NoticContact {

    interface View extends BaseView {

        void getNoticeListSuccess(PlatformNoticResponse platformNoticResponse);
        void getNoticeDetailSuccess(NoticeDetail noticeDetail);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getNoticeList(int page,String contacts);
        public abstract void getNoticeDetail(int id,String contacts );

    }
}
