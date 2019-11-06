package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qingyun.mvpretrofitrx.mvp.adapter.BottomLevelAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.BottomLevel;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseBottomLsvelActivity extends BaseActivity {
    @BindView(R.id.rcy)
    RecyclerView rcy;

    BottomLevelAdapter bottomLevelAdapterp;
    private List<BottomLevel> list;

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
        return R.layout.activity_choose_bottom_level;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
        list = new ArrayList<>();
        list.add(new BottomLevel());
        list.add(new BottomLevel());
        list.add(new BottomLevel());
        list.add(new BottomLevel());
        list.add(new BottomLevel());
        list.add(new BottomLevel());

        bottomLevelAdapterp = new BottomLevelAdapter(getContext(),list);

        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.setAdapter(bottomLevelAdapterp);
        refreashView(list,rcy);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
    }
}
