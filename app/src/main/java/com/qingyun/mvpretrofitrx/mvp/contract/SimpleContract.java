package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
public interface SimpleContract {

    interface View extends BaseView {
        void getSimpleSuccess();
    }

    abstract class Presenter extends BasePresenter<View> {

        public abstract void getSimple();
    }
}
