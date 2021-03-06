package com.qingyun.mvpretrofitrx.mvp.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.allens.lib_ios_dialog.IosDialog;
import com.develop.wallet.eth.Wallet;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.qingyun.mvpretrofitrx.mvp.activity.chat.MyChatActivity;
import com.qingyun.mvpretrofitrx.mvp.adapter.MainViewPagerAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.entity.ImageSelectPath;
import com.qingyun.mvpretrofitrx.mvp.fragment.AssetFragment;
import com.qingyun.mvpretrofitrx.mvp.fragment.ChatFragment;
import com.qingyun.mvpretrofitrx.mvp.fragment.ChooseBottomLevelFragment;
import com.qingyun.mvpretrofitrx.mvp.fragment.FindFragment;
import com.qingyun.mvpretrofitrx.mvp.fragment.InformationFragment;
import com.qingyun.mvpretrofitrx.mvp.fragment.MineFragment;
import com.qingyun.mvpretrofitrx.mvp.fragment.RYunChatActivity;
import com.qingyun.mvpretrofitrx.mvp.fragment.ryunchat.RyunChatFragment;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.KeyboardChangeListener;
import com.qingyun.mvpretrofitrx.mvp.utils.KeyboardUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.qingyun.mvpretrofitrx.mvp.weight.MyViewPager;
import com.senon.mvpretrofitrx.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
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
    private BaseFragment chatFragment;
    public static int index;

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
    protected void setHeaderData() {
        super.setHeaderData();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED);
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

        setRootViewFitsSystemWindows(this, false);

//        RxPermissions rxPermissions=new RxPermissions(this);
//        rxPermissions.request(Manifest.permission.WRITE_SETTINGS).subscribe(new Consumer<Boolean>() {
//            @Override
//            public void accept(Boolean aBoolean) throws Exception {
//                if (aBoolean){


                    EventBus.getDefault().register(this);
//        navigation.setItemIconTintList(null);
//        navigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
                    ViewGroup.LayoutParams lp = vp.getLayoutParams();
                    initNavigation();
                    fragments = new ArrayList<>();
                    chatFragment = new RYunChatActivity();
//        if (ApplicationUtil.getCurrentWallet()==null){
//            fragments.add(new ChooseBottomLevelFragment());

//        }else {
                    fragments.add(new AssetFragment());
//        }
                    fragments.add(new InformationFragment());
                    fragments.add(new FindFragment());
                    fragments.add(new RyunChatFragment());
                    fragments.add(new MineFragment());


                    mainViewPagerAdapter = new MainViewPagerAdapter(MainActivity.this, fragments, getSupportFragmentManager());
                    vp.setAdapter(mainViewPagerAdapter);
                    vp.setOffscreenPageLimit(4);
                    index = 0;
                    vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                        @Override
                        public void onPageScrolled(int i, float v, int i1) {

                        }

                        @Override
                        public void onPageSelected(int i) {
                            index = i;
                            fragments.get(i).refreashData();
                        }

                        @Override
                        public void onPageScrollStateChanged(int i) {

                        }
                    });




//                }else{
//                    //只要有一个权限被拒绝，就会执行
//                    ToastUtil.showShortToast(R.string.permission_need_open);
//                }
//            }
//        });



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
        fragments.get(index).onMyResume();

//        if (index==3){
//            chatFragment.refreashData();
//        }
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

//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN&& KeyboardChangeListener.isSofeVisiable) {
//          KeyboardUtils.hideKeyboard(getContext());
//            KeyboardChangeListener.isSofeVisiable = false;
//            return true;
//        }
//        return super.onTouchEvent(ev);
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*结果回调*/
        if (requestCode == DialogUtils.CODE_CHOOSE_PICTURE_CAMEAR || requestCode == DialogUtils.CODE_CHOOSE_PICTURE_PHONE) {
            if (data != null) {
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                String picturePath = selectList.get(0).getCompressPath();
                EventBus.getDefault().post(new ImageSelectPath(picturePath));


            }
        }

    }



}
