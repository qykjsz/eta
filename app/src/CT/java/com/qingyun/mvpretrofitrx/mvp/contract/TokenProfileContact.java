package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.GoldInfo;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;

import java.util.List;


public interface TokenProfileContact {

    interface View extends BaseView {

        void getGoldInfoSuccess(GoldInfo goldInfo);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getGoldInfo(String glod);

    }
}
