package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.adapter.MainViewPagerAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.Asset;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLogResponse;
import com.qingyun.mvpretrofitrx.mvp.fragment.AssetWalletLogFragment;
import com.qingyun.mvpretrofitrx.mvp.utils.IndicatorUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.senon.mvpretrofitrx.R;

import net.lucode.hackware.magicindicator.MagicIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AssetWalletActivity extends BaseActivity {
    @BindView(R.id.magic_indicator3)
    MagicIndicator magicIndicator3;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    MainViewPagerAdapter mainViewPagerAdapter;
    @BindView(R.id.tv_asset)
    TextView tvAsset;
    @BindView(R.id.tv_income_today)
    TextView tvIncomeToday;
    private List<BaseFragment> fragments;
    private List<String> titles;
    private Asset asset;
    private int type;

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
        EventBus.getDefault().register(this);
        titles = new ArrayList<>();
        titles.add(getResources().getString(R.string.all));
        titles.add(getResources().getString(R.string.transfer_in));
        titles.add(getResources().getString(R.string.transfer_out));
        asset = (Asset) getIntent().getSerializableExtra(IntentUtils.ASSET);
        fragments = new ArrayList<>();
        fragments.add(new AssetWalletLogFragment(3, asset));
        fragments.add(new AssetWalletLogFragment(2, asset));
        fragments.add(new AssetWalletLogFragment(1, asset));

        mainViewPagerAdapter = new MainViewPagerAdapter(getContext(), fragments, getSupportFragmentManager());
        viewPager.setAdapter(mainViewPagerAdapter);
        IndicatorUtils.initMagicIndicator3(magicIndicator3, viewPager, titles, getActivity());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDetail(TransferLogResponse transferLogResponse) {
        tvAsset.setText(transferLogResponse.getUsdtnumber());
        tvIncomeToday.setText(transferLogResponse.getToday());

    }


    @OnClick({R.id.btn_back, R.id.btn_visiable, R.id.btn_get_money,R.id.btn_transfer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_transfer:
                startActivity(TransferActivity.class);
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_visiable:
                break;
            case R.id.btn_get_money:
                startActivity(GetMoneyActivity.class);
                break;
        }
    }
}
