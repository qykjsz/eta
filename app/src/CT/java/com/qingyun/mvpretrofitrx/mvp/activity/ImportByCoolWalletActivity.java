package com.qingyun.mvpretrofitrx.mvp.activity;

import android.view.View;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.senon.mvpretrofitrx.R;

public class ImportByCoolWalletActivity  extends BaseActivity {
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
        return getResources().getString(R.string.cood_wallet);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_import_by_cool_wallet;
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
        setIvTitleRight(R.mipmap.icon_scan, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
