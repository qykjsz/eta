package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingyun.mvpretrofitrx.mvp.activity.ChooseBottomLsvelActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.CreateMyWalletActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.ImportWalletActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.senon.mvpretrofitrx.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChooseBottomLevelFragment extends BaseFragment {
    Unbinder unbinder;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
