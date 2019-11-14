package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qingyun.mvpretrofitrx.mvp.adapter.SupportItemAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.contract.SupportContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.qingyun.mvpretrofitrx.mvp.presenter.SupportPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.ObservableTransformer;

/**
 * 帮助中心
 */
public class SupportActivity extends BaseActivity<SupportContact.View, SupportContact.Presenter> implements SupportContact.View {


    @BindView(R.id.rcy)
    RecyclerView rcy;
    SupportItemAdapter supportItemAdapter;
    private List<Item> list;

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
        return getResources().getString(R.string.support_center);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_support;
    }

    @Override
    public SupportContact.Presenter createPresenter() {
        return new SupportPresenter(this);
    }

    @Override
    public SupportContact.View createView() {
        return this;
    }

    @Override
    public void init() {
        list = new ArrayList<>();
        supportItemAdapter = new SupportItemAdapter(getContext(),list);
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.addItemDecoration(DividerHelper.getMyDivider(getContext()));
        rcy.setAdapter(supportItemAdapter);
        getPresenter().getSupportList();
    }

    @Override
    public void getSupportListSuccess(List<Item> itemList) {
        list = itemList;
        supportItemAdapter.notifyDataSetChanged(list);
        refreashView(list,rcy);

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
