package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.Quotation;
import com.qingyun.mvpretrofitrx.mvp.entity.Quotation1;
import com.qingyun.mvpretrofitrx.mvp.entity.Time;

import java.util.List;

public interface MakieContact {
     interface View extends BaseView {
        void getMakieListFlash(List<Quotation> contactList);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getContacMakieList(String contacts);

    }
}
