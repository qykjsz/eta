package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qingyun.mvpretrofitrx.mvp.adapter.TransferLogAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransferActivity extends BaseActivity {
    @BindView(R.id.rcy)
    RecyclerView rcy;

    TransferLogAdapter transferLogAdapter;
    private List<TransferLog> list;

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
        return R.layout.activity_transfer;
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
        list.add(new TransferLog());
        list.add(new TransferLog());
        list.add(new TransferLog());
        list.add(new TransferLog());

        transferLogAdapter = new TransferLogAdapter(getContext(), list);
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.addItemDecoration(DividerHelper.getMyDivider(getContext()));
        rcy.setAdapter(transferLogAdapter);
        refreashView(list, rcy);
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

    @OnClick({R.id.btn_transfer_immediate, R.id.btn_transfer_scan, R.id.btn_transfer_contact})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_transfer_immediate:
                startActivity(TransferImmediateActivity.class);
                break;
            case R.id.btn_transfer_scan:
                break;
            case R.id.btn_transfer_contact:
                startActivity(ContactActivity.class);
                break;
        }
    }
}
