package com.qingyun.mvpretrofitrx.mvp.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Date 2020/1/18.
 * Created by Sam
 * ClassExplain：
 */
public class ChePackUtil {

    /**
     * 检查包是否存在
     *
     * @param packname
     * @return
     */
    public static boolean checkPackInfo(String packname, Context mContext) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = mContext.getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }
}
