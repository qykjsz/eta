package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.view.View;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateWalletActivity extends BaseActivity {
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
        return R.layout.activity_cereat_wallet;
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


    @OnClick({R.id.btn_back, R.id.btn_import_by_private_key, R.id.btn_import_by_keystore, R.id.btn_import_by_comm, R.id.btn_create})
    public void onViewClicked(View view) {
        switch (view.getId()) {


            case R.id.btn_back:
                finish();

                break;

            case R.id.btn_import_by_private_key:
                startActivity(ImportByPrivatekeyActivity.class);

                break;
            case R.id.btn_import_by_keystore:
                startActivity(ImportByKeystoreActivity.class);

                break;
            case R.id.btn_import_by_comm:
                startActivity(ImportByCommemorationActivity.class);
                break;
            case R.id.btn_create:
                startActivity(CreateMyWalletActivity.class);
                break;
        }
    }

    @OnClick({R.id.btn_create_wallet, R.id.btn_visi_model})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_create_wallet:
                ToastUtil.showShortToast(R.string.not_open);
                break;
            case R.id.btn_visi_model:
                ToastUtil.showShortToast(R.string.not_open);

                break;
        }
    }
}
