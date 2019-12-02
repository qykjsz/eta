package com.qingyun.mvpretrofitrx.mvp.activity;

import com.qingyun.mvpretrofitrx.mvp.entity.ImportScanResult;
import com.zbar.lib.CaptureActivity;

import org.greenrobot.eventbus.EventBus;

public class ImportScanActivity extends CaptureActivity {

    @Override
    protected boolean onScanSuccess(String result) {
        EventBus.getDefault().post(new ImportScanResult(result));
        finish();
        return true;
    }
}
