package com.qingyun.mvpretrofitrx.mvp.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;

import com.qingyun.mvpretrofitrx.mvp.activity.ScanActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.senon.mvpretrofitrx.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class ScanUtil {


    public static void start(final Activity activity){
        RxPermissions rxPermissions=new RxPermissions(activity);
        rxPermissions.request(Manifest.permission.CAMERA).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean){
                    Intent intent = new Intent(activity,ScanActivity.class);
                    activity.startActivity(intent);

                }else{
                    //只要有一个权限被拒绝，就会执行
                    ToastUtil.showShortToast(R.string.camera_permission_required);
                }
            }
        });
    }
}
