package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.Flash;

public interface FlashContact {
     interface View extends BaseView {
        void getContactListFlash(Flash contactList);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getContacFlashtList(String contacts);

    }
}
