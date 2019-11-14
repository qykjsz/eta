package com.qingyun.mvpretrofitrx.mvp.activity;

import android.text.TextUtils;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.utils.LocalManageUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.SpUtils;
import com.senon.mvpretrofitrx.R;

public class SplashActivity extends BaseActivity {
    private String first;
    private int choose=1;

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
        return R.layout.activity_splash;
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

        first = (String) SpUtils.getObjectFromShare(SplashActivity.this,"first");
        if (TextUtils.isEmpty(first))
            SpUtils.setObjectToShare(SplashActivity.this,choose,"language");
        else {
            choose = (int) SpUtils.getObjectFromShare(SplashActivity.this,"language");
        }
        LocalManageUtil.saveLocal(getActivity(),choose);

        startActivity(MainActivity.class);
    }
}
