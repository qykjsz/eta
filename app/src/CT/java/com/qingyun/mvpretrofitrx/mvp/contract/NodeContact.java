package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.qingyun.mvpretrofitrx.mvp.entity.Node;

import java.util.List;


public interface NodeContact {

    interface View extends BaseView {

        void getNodeListSuccess(List<Node> nodeList);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getNodeList();
    }
}
