package com.qingyun.mvpretrofitrx.mvp.activity;


import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;

import com.google.gson.Gson;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessPayEntity;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;
import com.zbar.lib.CaptureActivity;

public class  ScanActivity extends CaptureActivity {


    @Override
    protected boolean onScanSuccess(String result) {


            try {
                BusinessPayEntity businessPayEntity= new Gson().fromJson(result, BusinessPayEntity.class);
                Bundle bundle = new Bundle();
                if (businessPayEntity.getPrice()==null)
                {
                    ToastUtil.showShortToast(R.string.scan_real_wallet_address);
                    return false;
                }
                bundle.putSerializable(IntentUtils.BUSINESS_PAY_ENTITY, businessPayEntity);
                startActivity(BuyerPayActivity.class, bundle);
                finish();
                return true;
            }catch (Exception e){
                Bundle bundle = new Bundle();
                if (result.length()==42&&result.startsWith("0x")){
                    bundle.putString(IntentUtils.TRANSFER_ADDRESS, result);
                    startActivity(TransferImmediateActivity.class, bundle);
                    finish();
                    return true;
                }else {
                    ToastUtil.showShortToast(R.string.scan_real_wallet_address);
                    return false;
                }

            }

    }

    /**
     * 震动方法
     */
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }


}
