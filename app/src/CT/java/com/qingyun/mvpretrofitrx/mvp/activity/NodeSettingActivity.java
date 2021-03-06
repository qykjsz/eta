package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qingyun.mvpretrofitrx.mvp.adapter.NodeAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.contract.NodeContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Node;
import com.qingyun.mvpretrofitrx.mvp.presenter.NodePresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.ObservableTransformer;

public class NodeSettingActivity extends BaseActivity<NodeContact.View,NodeContact.Presenter> implements NodeContact.View {
    @BindView(R.id.rcy)
    RecyclerView rcy;
    private NodeAdapter nodeAdapter;
    private List<Node> list;

    @Override
    protected String getTitleRightText() {
        return null;
    }

    @Override
    protected String getTitleLeftText() {
        return null;
    }

    @Override
    public boolean haveHeader() {
        return true;
    }

    @Override
    protected String getTitleText() {
        return getResources().getString(R.string.node_setting);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_node_setting;
    }

    @Override
    public NodeContact.Presenter createPresenter() {
        return new NodePresenter(this);
    }

    @Override
    public NodeContact.View createView() {
        return this;
    }


    @Override
    public void init() {
        list = new ArrayList<>();

        nodeAdapter = new NodeAdapter(getContext(), list);
        nodeAdapter.setDefaultSelectItem(0);
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.addItemDecoration(DividerHelper.getMyDivider(getContext()));
        rcy.setAdapter(nodeAdapter);
        refreashView(list,rcy);

        getPresenter().getNodeList();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void refresh() {
        super.refresh();
        getPresenter().getNodeList();

    }

    @Override
    public void getNodeListSuccess(List<Node> nodeList) {
        nodeAdapter.notifyDataSetChanged(nodeList);
        list = nodeList;
        refreashView(list,rcy);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
