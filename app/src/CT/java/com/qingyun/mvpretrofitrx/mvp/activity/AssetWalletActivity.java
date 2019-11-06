package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.qingyun.mvpretrofitrx.mvp.adapter.MainViewPagerAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.fragment.AssetWalletLogFragment;
import com.qingyun.mvpretrofitrx.mvp.utils.IndicatorUtils;
import com.senon.mvpretrofitrx.R;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AssetWalletActivity extends BaseActivity {
    @BindView(R.id.magic_indicator3)
    MagicIndicator magicIndicator3;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    MainViewPagerAdapter mainViewPagerAdapter;
    private List<BaseFragment> fragments;
    private List<String> titles;

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
        return R.layout.activity_asset_wallet;
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
        titles = new ArrayList<>();
        titles.add(getResources().getString(R.string.all));
        titles.add(getResources().getString(R.string.transfer_in));
        titles.add(getResources().getString(R.string.transfer_out));

        fragments = new ArrayList<>();
        fragments.add(new AssetWalletLogFragment());
        fragments.add(new AssetWalletLogFragment());
        fragments.add(new AssetWalletLogFragment());
        mainViewPagerAdapter = new MainViewPagerAdapter(getContext(),fragments,getSupportFragmentManager());
        viewPager.setAdapter(mainViewPagerAdapter);
        IndicatorUtils.initMagicIndicator3(magicIndicator3,viewPager,titles,getActivity());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
