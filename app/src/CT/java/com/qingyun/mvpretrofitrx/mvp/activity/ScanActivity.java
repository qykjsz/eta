package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

import static com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils.IS_WITHDRAW;

public class ScanActivity extends BaseActivity implements QRCodeView.Delegate {

    @BindView(R.id.zbarview)
    ZXingView zbarview;
    private boolean isWithdraw;

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
        return R.layout.activity_scan;
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
        zbarview.setDelegate(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        isWithdraw = getIntent().getBooleanExtra(IS_WITHDRAW,false);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        vibrate();
        Log.e("----------",result);
        if (TextUtils.isEmpty(result)) return;

        Bundle bundle = new Bundle();
        bundle.putString(IntentUtils.TRANSFER_ADDRESS,result);
        startActivity(TransferImmediateActivity.class,bundle);
        zbarview.startSpot();
        finish();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(this, "错误", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        zbarview.startCamera();//打开相机
        zbarview.showScanRect();//显示扫描框
        zbarview.startSpot();//开始识别二维码
        //mQRCodeView.openFlashlight();//开灯
        //mQRCodeView.closeFlashlight();//关灯
    }

    @Override
    protected void onStop() {
        zbarview.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        zbarview.onDestroy();
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
}
