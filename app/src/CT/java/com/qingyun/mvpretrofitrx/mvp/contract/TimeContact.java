package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.Flash;
import com.qingyun.mvpretrofitrx.mvp.entity.Time;

public interface TimeContact {
     interface View extends BaseView {
        void getContactListFlash(Time contactList);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getContacFlashtList(String contacts);

    }
}
