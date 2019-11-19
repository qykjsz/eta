package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.allens.lib_ios_dialog.IosDialog;
import com.develop.wallet.eth.Wallet;
import com.qingyun.mvpretrofitrx.mvp.adapter.MainViewPagerAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.fragment.AssetFragment;
import com.qingyun.mvpretrofitrx.mvp.fragment.ChatFragment;
import com.qingyun.mvpretrofitrx.mvp.fragment.ChooseBottomLevelFragment;
import com.qingyun.mvpretrofitrx.mvp.fragment.FindFragment;
import com.qingyun.mvpretrofitrx.mvp.fragment.InformationFragment;
import com.qingyun.mvpretrofitrx.mvp.fragment.MineFragment;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.weight.MyViewPager;
import com.senon.mvpretrofitrx.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private ArrayList<BaseFragment> fragments;
    private MainViewPagerAdapter mainViewPagerAdapter;

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
        EventBus.getDefault().register(this);

//        navigation.setItemIconTintList(null);
//        navigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        ViewGroup.LayoutParams lp = vp.getLayoutParams();
        setRootViewFitsSystemWindows(this, false);
        initNavigation();
        fragments = new ArrayList<>();
//        if (ApplicationUtil.getCurrentWallet()==null){
//            fragments.add(new ChooseBottomLevelFragment());

//        }else {
            fragments.add(new AssetFragment());
//        }
        fragments.add(new InformationFragment());
        fragments.add(new FindFragment());
        fragments.add(new ChatFragment());
        fragments.add(new MineFragment());


        mainViewPagerAdapter = new MainViewPagerAdapter(MainActivity.this, fragments, getSupportFragmentManager());
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



    public MyViewPager getVp() {
        return vp;
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

    @Override
    protected void onResume() {
        super.onResume();
        refreashUser();
    }


    @Override
    public void refreashUser() {

    }



    private void initNavigation() {
        mNavigationController = tab.material()
                .setDefaultColor(getResources().getColor(R.color.color_text_1))
                .addItem(R.mipmap.foot_1,R.mipmap.foot_01, getResources().getString(R.string.asset),getResources().getColor(R.color.main_blue))
                .addItem(R.mipmap.foot_2,R.mipmap.foot_02, getResources().getString(R.string.zixun),getResources().getColor(R.color.main_blue))
                .addItem(R.mipmap.foot_3,R.mipmap.foot_03, getResources().getString(R.string.find),getResources().getColor(R.color.main_blue))
                .addItem(R.mipmap.foot_4,R.mipmap.foot_04, getResources().getString(R.string.chat),getResources().getColor(R.color.main_blue))
                .addItem(R.mipmap.foot_5, R.mipmap.foot_05,getResources().getString(R.string.mine),getResources().getColor(R.color.main_blue))
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreashWallet(Wallet wallet) {
        if (wallet!=null){
            mainViewPagerAdapter.replaceFragment(0,new AssetFragment());
//            fragments.add(0,new AssetFragment());
//            fragments.remove(1);
//            mainViewPagerAdapter.notifyDataSetChanged(fragments);
//            vp.invalidate();
//            getSupportFragmentManager().beginTransaction().replace(0,new AssetFragment()).commit();
        }
    }
}
