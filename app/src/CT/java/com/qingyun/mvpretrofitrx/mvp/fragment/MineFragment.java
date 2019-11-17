package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.develop.wallet.eth.WalletManager;
import com.qingyun.mvpretrofitrx.mvp.activity.AboutUsActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.AssetReviewActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.BusinessLogActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.ContactActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.InvitefriendsActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.SupportActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.SystemSettingsActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.WalletManagerActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.senon.mvpretrofitrx.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的
 */
public class MineFragment extends BaseFragment {

    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
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

    @OnClick({R.id.btn_back, R.id.btn_asset_review, R.id.btn_wallet_manager, R.id.btn_business_log, R.id.btn_contact, R.id.btn_friends, R.id.btn_help_center, R.id.btn_about_us, R.id.btn_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_asset_review:
                startActivity(AssetReviewActivity.class);
                break;
            case R.id.btn_wallet_manager:
                startActivity(WalletManagerActivity.class);
                break;
            case R.id.btn_business_log:
                startActivity(BusinessLogActivity.class);
                break;
            case R.id.btn_contact:
                startActivity(ContactActivity.class);
                break;
            case R.id.btn_friends:
                startActivity(InvitefriendsActivity.class);
                break;
            case R.id.btn_help_center:
                startActivity(SupportActivity.class);
                break;
            case R.id.btn_about_us:
                startActivity(AboutUsActivity.class);
                break;
            case R.id.btn_setting:
                startActivity(SystemSettingsActivity.class);
                break;
        }
    }
}
