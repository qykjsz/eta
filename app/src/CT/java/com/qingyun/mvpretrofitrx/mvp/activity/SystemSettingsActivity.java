package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.view.View;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.senon.mvpretrofitrx.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 系统设置
 */
public class SystemSettingsActivity extends BaseActivity {


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
        return getResources().getString(R.string.system_settings);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_system_settings;
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

    @OnClick({R.id.btn_language, R.id.btn_node, R.id.btn_unit, R.id.btn_up_down, R.id.btn_push})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_language:
                startActivity(ChooseLanguageActivity.class);
                break;
            case R.id.btn_node:
                startActivity(NodeSettingActivity.class);
                break;
            case R.id.btn_unit:
                break;
            case R.id.btn_up_down:
                break;
            case R.id.btn_push:
                break;
        }
    }
}
