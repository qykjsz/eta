package com.qingyun.mvpretrofitrx.mvp.activity;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.contract.SimpleContract;
import com.qingyun.mvpretrofitrx.mvp.presenter.SimplePresenter;
import com.senon.mvpretrofitrx.R;

import io.reactivex.ObservableTransformer;

public class SimpleActivity extends BaseActivity<SimpleContract.View,SimpleContract.Presenter> implements SimpleContract.View {
    @Override
    protected String getTitleRightText() {
        return null;
    }

    @Override
    protected String getTitleLeftText() {
        return null;
    }

    @Override
    protected String getTitleText() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.test;
    }

    @Override
    public SimpleContract.Presenter createPresenter() {
        return new SimplePresenter(this);
    }

    @Override
    public SimpleContract.View createView() {
        return this;
    }

    @Override
    public void init() {

    }

    @Override
    public void getSimpleSuccess() {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
