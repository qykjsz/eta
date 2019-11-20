package com.qingyun.mvpretrofitrx.mvp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;


import com.senon.mvpretrofitrx.R;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * Date 2019/1/18.
 * Created by Sam
 * ClassExplain：扫一扫
 */

public class ScanQrCodeActivity extends Activity implements QRCodeView.Delegate {

    private ZXingView mZBarView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr_code);
        mZBarView = findViewById(R.id.zbarview);
        mZBarView.setDelegate(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i("post---", "result:" + result);
        vibrate();
        if (result != null) {
            Intent intent = new Intent(ScanQrCodeActivity.this,WebActivity.class);
            intent.putExtra("url", result);
            intent.putExtra("title","");
            startActivity(intent);
        }
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
    }

    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;

    /**
     * 解析本地图片二维码。返回二维码图片里的内容 或 null
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mZBarView.showScanRect();
//        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY) {
//            final String picturePath = BGAPhotoPickerActivity.getSelectedPhotos(data).get(0);
//            mZBarView.decodeQRCode(picturePath);
//        }
    }

    /**
     * 震动方法
     */
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mZBarView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
//        mZBarView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别
        mZBarView.startSpotAndShowRect(); // 显示扫描框，并开始识别
    }

    @Override
    protected void onStop() {
        mZBarView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mZBarView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }
}
