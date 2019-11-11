package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.Contact;
import com.qingyun.mvpretrofitrx.mvp.entity.News;

import java.util.List;

public interface FlashContact {
     interface View extends BaseView {
        void getContactListFlash(News contactList);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getContacFlashtList(String contacts);

    }
}
