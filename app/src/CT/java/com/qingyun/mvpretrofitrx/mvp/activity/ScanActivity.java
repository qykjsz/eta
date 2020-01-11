package com.qingyun.mvpretrofitrx.mvp.activity;


import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qingyun.mvpretrofitrx.mvp.activity.chat.AddFriendsActivity;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessPayEntity;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatCode;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;
import com.zbar.lib.CaptureActivity;

public class  ScanActivity extends CaptureActivity {


    @Override
    protected boolean onScanSuccess(String result) {

        try {
            JSONObject object = JSONObject.parseObject(result);

            if (object.containsKey("chatCode")){
                Log.e("---------------->","1");
                ChatCode chatCode = new Gson().fromJson(result, ChatCode.class);
                Bundle bundle = new Bundle();
                bundle.putString(IntentUtils.ADDRESS, chatCode.getChatCode());
                startActivity(AddFriendsActivity.class, bundle);
                finish();
                return true;

            }else if (object.containsKey("version")){
                Log.e("---------------->","2");

                ToastUtil.showShortToast(R.string.scan_real_wallet_address);
                return false;

            }else if (object.containsKey("address")){
                Log.e("---------------->","3");

                BusinessPayEntity businessPayEntity= new Gson().fromJson(result, BusinessPayEntity.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable(IntentUtils.BUSINESS_PAY_ENTITY, businessPayEntity);
                startActivity(BuyerPayActivity.class, bundle);
                finish();
                return true;
            }else {
                ToastUtil.showShortToast(R.string.check_code);
                return false;
            }
        }catch (Exception e){
            Bundle bundle = new Bundle();
            if (result.length()==42&&result.startsWith("0x")){
                bundle.putString(IntentUtils.TRANSFER_ADDRESS, result);
                startActivity(TransferImmediateActivity.class, bundle);
                finish();
                return true;
            }else {
                ToastUtil.showShortToast(R.string.check_code);
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
