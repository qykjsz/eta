package com.qingyun.mvpretrofitrx.mvp.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.senon.mvpretrofitrx.R;

public class CopyUtils {


    /**
     * 复制内容到剪切板
//     *
//     * @param copyStr
//     * @return
//     */
    public static boolean copy(Context context,String copyStr) {
        try {
            //获取剪贴板管理器
            ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", copyStr);
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
            ToastUtil.showShortToast(R.string.copy_success);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



}
