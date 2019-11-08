package com.qingyun.mvpretrofitrx.mvp.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.CopyUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.DensityUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.FileUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ZXingUtils;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetMoneyActivity extends BaseActivity {
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.tv_address)
    TextView tvAddress;

    @Override
    protected String getTitleRightText() {
        return getResources().getString(R.string.log);
    }

    @Override
    protected String getTitleLeftText() {
        return null;
    }

    @Override
    protected String getTitleText() {
        return getResources().getString(R.string.get_money);
    }


    @Override
    public boolean haveHeader() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_get_money;
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
        Bitmap bitmap = ZXingUtils.createQRImage(ApplicationUtil.getCurrentWallet().getAddress(), DensityUtil.dip2px(getApplicationContext(), 180), DensityUtil.dip2px(getApplicationContext(), 180));
        ivCode.setImageBitmap(bitmap);
        tvAddress.setText(ApplicationUtil.getCurrentWallet().getAddress());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_save_code, R.id.btn_copy_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save_code:
                FileUtils.save(getContext(),ivCode);
                break;
            case R.id.btn_copy_address:
                CopyUtils.copy(getContext(),tvAddress.getText().toString());
                break;
        }
    }
}
