package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.activity.AboutUsActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.ContactActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.GeneralAssetsActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.InvitefriendsActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.SupportActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.SystemSettingsActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.ThewalletManagementActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.TradingRecordActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.iv_he)
    ImageView ivHe;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phe)
    TextView tvPhe;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.tv_invite)
    TextView tvInvite;
    @BindView(R.id.ll_yao_qing)
    LinearLayout llYaoQing;
    @BindView(R.id.tv_help)
    TextView tvHelp;
    @BindView(R.id.ll_help)
    LinearLayout llHelp;
    @BindView(R.id.tv_About_Us)
    TextView tvAboutUs;
    @BindView(R.id.ll_About_Us)
    LinearLayout llAboutUs;
    @BindView(R.id.tv_set)
    TextView tvSet;
    @BindView(R.id.ll_set)
    LinearLayout llSet;
    Unbinder unbinder;
    @BindView(R.id.ll_Asset_overview)
    LinearLayout llAssetOverview;
    @BindView(R.id.ll_Management_of_the_purse)
    LinearLayout llManagementOfThePurse;
    @BindView(R.id.ll_trading_record)
    LinearLayout llTradingRecord;
    @BindView(R.id.tv_log_out)
    TextView tvLogOut;

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

    @OnClick({R.id.ll_Asset_overview, R.id.ll_Management_of_the_purse, R.id.ll_trading_record, R.id.ll_top, R.id.ll_phone, R.id.ll_yao_qing, R.id.ll_help, R.id.ll_About_Us, R.id.ll_set, R.id.tv_log_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_Asset_overview:
                startActivity(GeneralAssetsActivity.class);
                break;
            case R.id.ll_Management_of_the_purse:
                startActivity(ThewalletManagementActivity.class);
                break;
            case R.id.ll_trading_record:
                startActivity(TradingRecordActivity.class);
                break;
            case R.id.ll_top:

                break;
            case R.id.ll_phone:

                startActivity(ContactActivity.class);
                break;
            case R.id.ll_yao_qing:
                startActivity(InvitefriendsActivity.class);
                break;
            case R.id.ll_help:
                startActivity(SupportActivity.class);
                break;
            case R.id.ll_About_Us:
                startActivity(AboutUsActivity.class);
                break;
            case R.id.ll_set:
                startActivity(SystemSettingsActivity.class);
                break;
            case R.id.tv_log_out:
                break;
            default:
        }
    }
}
