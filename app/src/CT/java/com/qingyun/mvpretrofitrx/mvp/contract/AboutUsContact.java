package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;

import java.util.List;


public interface AboutUsContact {

    interface View extends BaseView {

        void getAboutUsListSuccess(List<Item> itemList);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getAboutUsList();
    }
}
