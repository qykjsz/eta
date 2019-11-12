package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.TheArticleDetails;
import com.qingyun.mvpretrofitrx.mvp.entity.Time;

public interface TheArticleDetailsContact {
     interface View extends BaseView {
        void getContactListFlash(TheArticleDetails theArticleDetails);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getTheArticleDetails(String id);

    }
}
