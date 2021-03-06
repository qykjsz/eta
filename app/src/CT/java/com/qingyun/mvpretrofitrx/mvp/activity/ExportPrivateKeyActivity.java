package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.develop.wallet.eth.Wallet;
import com.qingyun.mvpretrofitrx.mvp.adapter.MainViewPagerAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.fragment.ExportPriviteKeyKeyFragment;
import com.qingyun.mvpretrofitrx.mvp.fragment.ExportPriviteKeyQrcodeFragment;
import com.qingyun.mvpretrofitrx.mvp.utils.IndicatorUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.senon.mvpretrofitrx.R;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExportPrivateKeyActivity extends BaseActivity {
    @BindView(R.id.magic_indicator3)
    MagicIndicator magicIndicator3;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private List<BaseFragment> fragments;
    private List<String> titles;
    private Wallet wallet;

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
        return getResources().getString(R.string.export_privite_key);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_export_privite_key;
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
        wallet = (Wallet) getIntent().getSerializableExtra(IntentUtils.WALLET);
        titles.add(getResources().getString(R.string.private_key));
        titles.add(getResources().getString(R.string.qr_code));
        fragments = new ArrayList<>();
        ExportPriviteKeyKeyFragment exportPriviteKeyKeyFragment =  new ExportPriviteKeyKeyFragment();
        ExportPriviteKeyQrcodeFragment exportPriviteKeyQrcodeFragment =  new ExportPriviteKeyQrcodeFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(IntentUtils.WALLET,wallet);
        exportPriviteKeyKeyFragment.setArguments(bundle);
        exportPriviteKeyQrcodeFragment.setArguments(bundle);

        fragments.add(exportPriviteKeyKeyFragment);
        fragments.add(exportPriviteKeyQrcodeFragment);
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getContext(),fragments,getSupportFragmentManager());
        viewPager.setAdapter(mainViewPagerAdapter);
//        IndicatorUtils.initMagicIndicator3(magicIndicator3,viewPager,titles,getActivity());
        IndicatorUtils.initMagicIndicator3(viewPager,titles,getActivity(),magicIndicator3,0,0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
