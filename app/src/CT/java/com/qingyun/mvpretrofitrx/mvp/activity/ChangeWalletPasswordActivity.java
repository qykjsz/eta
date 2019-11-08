package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.develop.wallet.eth.WalletManager;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeWalletPasswordActivity extends BaseActivity {
    @BindView(R.id.et_current_password)
    EditText etCurrentPassword;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.ben_confirm)
    TextView benConfirm;

    @Override
    protected String getTitleRightText() {
        return null;
    }


    @Override
    public boolean haveHeader() {
        return true;
    }

    @Override
    protected String getTitleLeftText() {
        return null;
    }

    @Override
    protected String getTitleText() {
        return getResources().getString(R.string.change_password);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_wallet_password;
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

    @OnClick(R.id.ben_confirm)
    public void onViewClicked() {
    }
}
