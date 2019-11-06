package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.allens.lib_ios_dialog.IosDialog;
import com.qingyun.mvpretrofitrx.mvp.adapter.MainViewPagerAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.fragment.DistributionFragment;
import com.qingyun.mvpretrofitrx.mvp.fragment.MineFragment;
import com.qingyun.mvpretrofitrx.mvp.fragment.TwinFragment;
import com.qingyun.mvpretrofitrx.mvp.fragment.WalletFragment;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.weight.MyViewPager;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;

import static com.qingyun.mvpretrofitrx.mvp.utils.StatusBarUtil.setRootViewFitsSystemWindows;

public class MainActivity extends BaseActivity {


    @BindView(R.id.vp)
    MyViewPager vp;
    @BindView(R.id.tab)
    PageNavigationView tab;
    private NavigationController mNavigationController;
//    @BindView(R.id.navigation)
//    BottomNavigationView navigation;

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
        return R.layout.activity_main;
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
//        navigation.setItemIconTintList(null);
//        navigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        refreashUser();
        ViewGroup.LayoutParams lp = vp.getLayoutParams();
        setRootViewFitsSystemWindows(this, false);
        initNavigation();
        final List<BaseFragment> fragments = new ArrayList<>();
        final WalletFragment walletFragment = new WalletFragment();
        final TwinFragment twinFragment = new TwinFragment();
        DistributionFragment distributionFragment = new DistributionFragment();
        MineFragment mineFragment = new MineFragment();
        fragments.add(walletFragment);
        fragments.add(twinFragment);
        fragments.add(distributionFragment);
        fragments.add(mineFragment);

        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(MainActivity.this, fragments, getSupportFragmentManager());
        vp.setAdapter(mainViewPagerAdapter);
        vp.setOffscreenPageLimit(4);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                fragments.get(i).refreashData();

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        new IosDialog(MainActivity.this)
                .builder()
                .setCancelable(true)
                .setCancelOutside(true)
                .setTitle(getString(R.string.app_name))
                .setMsg(getResources().getString(R.string.sure_to_exit))
                .setDialogWidth(0.9f)
                .setPositiveButton(getResources().getString(R.string.confirm), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ApplicationUtil.exitApp();
                    }
                })
                .setNegativeButton(getResources().getString(R.string.cancel), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .show();
    }


    private void initNavigation() {
        mNavigationController = tab.material()
                .setDefaultColor(getResources().getColor(R.color.color_text_1))
                .addItem(R.mipmap.sy_foot_01,R.mipmap.sy_foot_01, getResources().getString(R.string.nav_wallet),getResources().getColor(R.color.color_text_2))
                .addItem(R.mipmap.sy_foot_2,R.mipmap.sy_foot_2, getResources().getString(R.string.nav_twin),getResources().getColor(R.color.color_text_2))
                .addItem(R.mipmap.sy_foot_3,R.mipmap.sy_foot_3, getResources().getString(R.string.nav_distribution),getResources().getColor(R.color.color_text_2))
                .addItem(R.mipmap.sy_foot_4, R.mipmap.sy_foot_4,getResources().getString(R.string.nav_mine),getResources().getColor(R.color.color_text_2))
                .build();
//        MyViewPagerAdapter pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), Math.max(5, mNavigationController.getItemCount()));
//        mViewPager.setAdapter(pagerAdapter);
        mNavigationController.setupWithViewPager(vp);

//        // 初始化消息数字为0
//        for (int i = 0; i < pagerAdapter.getCount(); i++) {
//            mMessageNumberList.add(0);
//        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
