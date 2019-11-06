package com.qingyun.mvpretrofitrx.mvp.activity;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.senon.mvpretrofitrx.R;

public class ChangeWalletPasswordActivity extends BaseActivity {
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
}
