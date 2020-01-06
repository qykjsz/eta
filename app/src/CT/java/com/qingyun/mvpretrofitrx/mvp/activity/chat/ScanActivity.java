package com.qingyun.mvpretrofitrx.mvp.activity.chat;

import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qingyun.mvpretrofitrx.mvp.entity.Group;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;
import com.zbar.lib.CaptureActivity;

public class ScanActivity extends CaptureActivity {
    @Override
    protected boolean onScanSuccess(String result) {

        JSONObject object = JSONObject.parseObject(result);
        if (!TextUtils.isEmpty((String) object.get("owner"))){
            Group group = new Gson().fromJson(result, Group.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(IntentUtils.GROUP, group);
            startActivity(AddGroupAddActivity.class, bundle);
            return true;

        }else if (!TextUtils.isEmpty((String) object.get("name"))&&!TextUtils.isEmpty((String) object.get("address"))){
            GroupMember groupMember = new Gson().fromJson(result, GroupMember.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(IntentUtils.GROUP_MEMBER, groupMember);
            startActivity(AddFriendsAddActivity.class, bundle);
            return true;
        }else {
            ToastUtil.showShortToast(R.string.check_code);
            return false;

        }

    }
}
