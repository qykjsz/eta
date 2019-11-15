package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.contract.CoinContact;
import com.qingyun.mvpretrofitrx.mvp.contract.NodeContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Node;
import com.qingyun.mvpretrofitrx.mvp.entity.NodeResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.qingyun.mvpretrofitrx.mvp.model.CoinModel;
import com.qingyun.mvpretrofitrx.mvp.model.NodeModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import java.util.List;

public class NodePresenter extends NodeContact.Presenter {
    private NodeModel model;
    private Context context;


    public NodePresenter(Context context) {
        this.model = new NodeModel();
        this.context = context;
    }


    @Override
    public void getNodeList() {
        model.getNodeList(context,getView().bindLifecycle(), new ObserverResponseListener<NodeResponse>() {

            @Override
            public void onNext(NodeResponse nodeResponsee) {
                if(getView() != null){
                    getView().getNodeListSuccess(nodeResponsee.getData());
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }
}
