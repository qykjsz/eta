package com.qingyun.mvpretrofitrx.mvp.activity;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.develop.wallet.eth.Wallet;
import com.google.gson.Gson;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessPayEntity;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DensityUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.FileUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.ZXingUtils;
import com.senon.mvpretrofitrx.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class BusinessGetMoneyActivity extends BaseActivity {
    @BindView(R.id.iv_code)
    ImageView ivCode;
    private Bitmap bitmap;

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
        return R.layout.activity_business_get_money;
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
        BusinessPayEntity businessPayEntity = new BusinessPayEntity();
        businessPayEntity.setAdddress(ApplicationUtil.getCurrentWallet().getAddress());
        bitmap = ZXingUtils.createQRImage(new Gson().toJson(businessPayEntity,BusinessPayEntity.class), DensityUtil.dip2px(getApplicationContext(), 180), DensityUtil.dip2px(getApplicationContext(), 180));
        Glide.with(getActivity()).load(bitmap).into(ivCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_back, R.id.btn_set_price, R.id.btn_save_img, R.id.btn_log})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_set_price:
                startActivity(SettingPriceActivity.class);
                break;
            case R.id.btn_save_img:
                RxPermissions rxPermissions=new RxPermissions(getActivity());
                rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean){
                            FileUtils.savePicture(getContext(),bitmap , System.currentTimeMillis() + ".png");
                            ToastUtil.showShortToast(R.string.save_success);
                        }else{
                            //只要有一个权限被拒绝，就会执行
                            ToastUtil.showShortToast(R.string.storage_permission_need_open);
                        }
                    }
                });
                break;
            case R.id.btn_log:
                startActivity(BusinessGetMoneyLogActivity.class);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreashCodeData(BusinessPayEntity businessPayEntity) {
        bitmap = ZXingUtils.createQRImage(new Gson().toJson(businessPayEntity,BusinessPayEntity.class), DensityUtil.dip2px(getApplicationContext(), 180), DensityUtil.dip2px(getApplicationContext(), 180));
        Glide.with(getActivity()).load(bitmap).into(ivCode);
    }

}
