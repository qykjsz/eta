package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.senon.mvpretrofitrx.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChooseBottomLevelActivity extends BaseActivity {

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
        return R.layout.fragment_choose_bottom_level;
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





    @OnClick({R.id.btn_back, R.id.btn_switch_current, R.id.btn_import_wallet, R.id.btn_create_wallet, R.id.btn_visi_model})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                break;
            case R.id.btn_switch_current:
                startActivity(ChooseBottomLsvelActivity.class);

                break;
            case R.id.btn_import_wallet:
                startActivity(ImportWalletActivity.class);
                break;
            case R.id.btn_create_wallet:
                startActivity(CreateMyWalletActivity.class);
                break;
            case R.id.btn_visi_model:
                break;
        }
    }
}
