package com.qingyun.mvpretrofitrx.mvp.activity.chat;

import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatCode;
import com.qingyun.mvpretrofitrx.mvp.entity.Group;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;
import com.zbar.lib.CaptureActivity;

public class ScanActivity extends CaptureActivity {
    @Override
    protected boolean onScanSuccess(String result) {
        try {
            JSONObject object = JSONObject.parseObject(result);
            if (!TextUtils.isEmpty( object.get("chatCode")+"")){
                ChatCode chatCode = new Gson().fromJson(result, ChatCode.class);
                Bundle bundle = new Bundle();
                bundle.putString(IntentUtils.ADDRESS, chatCode.getChatCode());
                startActivity(AddFriendsActivity.class, bundle);
                finish();
                return true;

            }else {
                ToastUtil.showShortToast(R.string.check_code);
                return false;

            }
        }catch (Exception e){
            ToastUtil.showShortToast(R.string.check_code);
            return false;

        }


    }
}
