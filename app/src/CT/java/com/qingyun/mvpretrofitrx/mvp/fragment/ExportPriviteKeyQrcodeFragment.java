package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DensityUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.ZXingUtils;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ExportPriviteKeyQrcodeFragment extends BaseFragment {
    @BindView(R.id.btn_eye)
    ImageView btnEye;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    Unbinder unbinder;
    private Bitmap bitmap;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_export_private_key_qrcode;
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
        bitmap = ZXingUtils.createQRImage(ApplicationUtil.getCurrentWallet().getPrivateKey(), DensityUtil.dip2px(getContext(), 180), DensityUtil.dip2px(getContext(), 180));


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

    @OnClick(R.id.btn_visiable)
    public void onViewClicked() {
        ivCode.setImageBitmap(bitmap);
    }
}
