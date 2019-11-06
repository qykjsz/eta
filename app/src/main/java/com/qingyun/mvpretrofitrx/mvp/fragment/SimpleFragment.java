package com.qingyun.mvpretrofitrx.mvp.fragment;

import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.contract.SimpleContract;
import com.qingyun.mvpretrofitrx.mvp.presenter.SimplePresenter;
import com.senon.mvpretrofitrx.R;
import com.trello.rxlifecycle2.android.FragmentEvent;

import io.reactivex.ObservableTransformer;

public class SimpleFragment extends BaseFragment<SimpleContract.View, SimpleContract.Presenter> implements SimpleContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.test;
    }

    @Override
    public SimpleContract.Presenter createPresenter() {
        return new SimplePresenter(getContext());

    }

    @Override
    public SimpleContract.View createView() {
        return this;
    }

    @Override
    public void init() {

    }

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
    public void getSimpleSuccess() {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }
}
