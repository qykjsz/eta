package com.qingyun.mvpretrofitrx.mvp.utils;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.qingyun.mvpretrofitrx.mvp.weight.dialog.ProgressDialogUtils;
import com.senon.mvpretrofitrx.R;

import per.goweii.anylayer.AnyLayer;
import per.goweii.anylayer.LayerManager;

public class LoadingUtils {
    private static   AnyLayer anyLayer;
    public static void showLoading(Context context){
//        if (anyLayer!=null&&anyLayer.isShow())
////        {
////            return;
////        }
////        anyLayer =  AnyLayer.with(ApplicationUtil.getCurrentContext())
////                .contentView(R.layout.dialog_loading)
////                .backgroundColorInt(context.getResources().getColor(R.color.bg_dialog))
////                .gravity(Gravity.CENTER);
////        anyLayer.show();
        ProgressDialogUtils progressDialogUtils = ProgressDialogUtils.getInstances();
        progressDialogUtils.showDialog();
    }
    public static void loadingDismiss(){
        ProgressDialogUtils progressDialogUtils = ProgressDialogUtils.getInstances();
        progressDialogUtils.cancel();

//        if (anyLayer!=null&&anyLayer.isShow())
//        {
//            anyLayer.dismiss();
//        }
    }
}
