package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qingyun.mvpretrofitrx.mvp.adapter.MainViewPagerAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.Asset;
import com.qingyun.mvpretrofitrx.mvp.fragment.AssetHistoryFragment;
import com.qingyun.mvpretrofitrx.mvp.utils.IndicatorUtils;
import com.senon.mvpretrofitrx.R;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AssetDetailActivity extends BaseActivity {
    @BindView(R.id.indicator)
    MagicIndicator indicator;
    @BindView(R.id.vp)
    ViewPager vp;

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
    public boolean haveHeader() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_asset_detail;
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

        List<BaseFragment> fragments = new ArrayList<>();
        titles = new ArrayList<>();
        titles.add(getResources().getString(R.string.all));
        titles.add(getResources().getString(R.string.transfer_out));
        titles.add(getResources().getString(R.string.transfer_in));
        titles.add(getResources().getString(R.string.failure));

        fragments.add(new AssetHistoryFragment());
        fragments.add(new AssetHistoryFragment());
        fragments.add(new AssetHistoryFragment());
        fragments.add(new AssetHistoryFragment());
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getContext(),fragments,getSupportFragmentManager());
        vp.setAdapter(mainViewPagerAdapter);

        IndicatorUtils.initMagicIndicator3(indicator, vp, titles,AssetDetailActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_make_collection, R.id.btn_transfer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_make_collection:
                break;
            case R.id.btn_transfer:
                startActivity(TransferActivity.class);
                break;
        }
    }
}
