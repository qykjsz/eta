package com.qingyun.mvpretrofitrx.mvp.activity.chat;

import android.os.Bundle;

import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.zbar.lib.CaptureActivity;

public class ScanActivity extends CaptureActivity {
    @Override
    protected boolean onScanSuccess(String result) {

        Bundle bundle = new Bundle();
        bundle.putString(IntentUtils.ADDRESS,result);
        startActivity(AddFriendsActivity.class,bundle);
        return false;
    }
}
