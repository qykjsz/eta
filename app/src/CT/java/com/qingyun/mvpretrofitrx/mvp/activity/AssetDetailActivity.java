package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.view.View;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.senon.mvpretrofitrx.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AssetDetailActivity extends BaseActivity {
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
    public boolean haveHeader() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_asset_detail;
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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_change_password, R.id.btn_export_private_key, R.id.btn_export_key_store})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_change_password:
                startActivity(ChangeWalletPasswordActivity.class);
                break;
            case R.id.btn_export_private_key:
                startActivity(ExportPrivateKeyActivity.class);
                break;
            case R.id.btn_export_key_store:
                startActivity(ExportKeystoreActivity.class);

                break;
        }
    }
}
