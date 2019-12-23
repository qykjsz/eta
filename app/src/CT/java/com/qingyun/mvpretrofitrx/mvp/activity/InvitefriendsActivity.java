package com.qingyun.mvpretrofitrx.mvp.activity;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.CopyUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.DensityUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.FileUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ImgUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.ZXingUtils;
import com.senon.mvpretrofitrx.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * 邀请好友
 */
public class InvitefriendsActivity extends BaseActivity {

    @BindView(R.id.iv_code)
    ImageView ivCode;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_invitefriends);
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
    public boolean haveHeader() {
        return true;
    }

    @Override
    protected String getTitleText() {
        return getResources().getString(R.string.invite_friends);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_invitefriends;
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
        bitmap = ZXingUtils.createQRImage(ApplicationUtil.mVersionInfo.getDownload(), DensityUtil.dip2px(getContext(), 115), DensityUtil.dip2px(getContext(), 115));
        Glide.with(getActivity()).load(bitmap).into(ivCode);
    }

    @OnClick({R.id.btn_copy_link, R.id.btn_copy_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_copy_link:
                CopyUtils.copy(getActivity(),ApplicationUtil.mVersionInfo.getDownload());
                break;
            case R.id.btn_copy_code:
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
        }
    }
}
