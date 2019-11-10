package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qingyun.mvpretrofitrx.mvp.adapter.TransferLogAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.contract.WalletAssetContact;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLogResponse;
import com.qingyun.mvpretrofitrx.mvp.presenter.WalletAssetPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class TransferActivity extends BaseActivity<WalletAssetContact.View,WalletAssetContact.Presenter> implements  WalletAssetContact.View {
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
    public WalletAssetContact.Presenter createPresenter() {
        return new WalletAssetPresenter(this);
    }

    @Override
    public WalletAssetContact.View createView() {
        return this;
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
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected void refresh() {
        super.refresh();
//        getPresenter().getLog(ApplicationUtil.getCurrentWallet().getAddress(),ApplicationUtil.getCurrentWallet().getCoinType());

    }

    @Override
    protected void loadMore() {
        super.loadMore();

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

                startActivity(ScanActivity.class);
                break;
            case R.id.btn_transfer_contact:
                startActivity(ContactActivity.class);
                break;
        }
    }

    @Override
    public void getWalletInfoSuccess(AssetResponse assetResponse) {

    }

    @Override
    public void addWalletSuccess() {

    }

    @Override
    public void getLogSuccess(TransferLogResponse transferLogResponse) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
