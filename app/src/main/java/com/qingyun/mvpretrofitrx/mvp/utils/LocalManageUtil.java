package com.qingyun.mvpretrofitrx.mvp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.github.jokar.multilanguages.library.MultiLanguage;
import com.qingyun.mvpretrofitrx.mvp.activity.SplashActivity;
import com.senon.mvpretrofitrx.R;

import java.util.Locale;

public class LocalManageUtil {

    private static final String TAG = "LocalManageUtil";

    /**
     * 获取系统的locale
     *
     * @return Locale对象
     */
    public static Locale getSystemLocale(Context context) {
        return SPUtil.getInstance(context).getSystemCurrentLocal();
    }

//    public static String getSelectLanguage(Context context) {
//        switch (SPUtil.getInstance(context).getSelectLanguage()) {
//            case 0:
//                return context.getString(R.string.language_auto);
//            case 1:
//                return context.getString(R.string.language_cn);
//            case 2:
//                return context.getString(R.string.language_traditional);
//            case 3:
//            default:
//                return context.getString(R.string.language_en);
//        }
//    }

    /**
     * 获取选择的语言设置
     *
     * @param context
     * @return
     */
    public static Locale getSetLanguageLocale(Context context) {

        switch (SPUtil.getInstance(context).getSelectLanguage()) {
            case 0:
                return getSystemLocale(context);
            case 1:
                return Locale.CHINESE;
            case 2:
                return Locale.US;
            case 3:
            default:
                return Locale.US;
        }
    }


    public static void saveSystemCurrentLanguage(Context context) {
        SPUtil.getInstance(context).setSystemCurrentLocal(MultiLanguage.getSystemLocal(context));
    }

    /**
     * 保存系统语言
     * @param context
     * @param newConfig
     */
    public static void saveSystemCurrentLanguage(Context context, Configuration newConfig) {

        SPUtil.getInstance(context).setSystemCurrentLocal(MultiLanguage.getSystemLocal(newConfig));
    }

    public static void saveSelectLanguage(Context context, int select) {
        SPUtil.getInstance(context).saveLanguage(select);
        MultiLanguage.setApplicationLanguage(context);
    }

    public static void saveLocal(Activity activity,int choose){
        LocalManageUtil.saveSelectLanguage(activity, choose);
//        Resources resources = activity.getResources();
//        DisplayMetrics dm = resources.getDisplayMetrics();
//        Configuration config = resources.getConfiguration();
//        switch (choose){
//            case 1:
//                ApplicationUtil.locale =  config.locale = Locale.CHINESE;
//
//                break;
//            case 2:
//                ApplicationUtil.locale =  config.locale = Locale.US;
//                break;
//            case 3:
//                ApplicationUtil.locale =  config.locale = Locale.FRENCH;
//                break;
//            case 4:
//                ApplicationUtil.locale = config.locale = Locale.JAPAN;
//                break;
//            case 5:
//                ApplicationUtil.locale =  config.locale = Locale.KOREA;
//                break;
//        }
//        resources.updateConfiguration(config, dm);
        SpUtils.setObjectToShare(activity, choose, "language");

    }
}
