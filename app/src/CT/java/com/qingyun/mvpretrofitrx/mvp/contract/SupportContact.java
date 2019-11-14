package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;

import java.util.List;


public interface SupportContact {

    interface View extends BaseView {

        void getSupportListSuccess(List<Item> itemList);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getSupportList();
    }
}
