package com.qingyun.mvpretrofitrx.mvp.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

public class MultiLanguageUtil {
	private static final String TAG = "MultiLanguageUtil";
	private static volatile MultiLanguageUtil instance;
 
	private MultiLanguageUtil() {}
 
	public static MultiLanguageUtil getInstance() {
		if (instance == null) {
			synchronized (MultiLanguageUtil.class) {
				if (instance == null) {
					instance = new MultiLanguageUtil();
				}
			}
		}
		return instance;
	}
 
	/**
	 * 设置语言信息
	 *
	 * 说明：
	 * 该方法建议在attachBaseContext和onConfigurationChange中调用，attachBaseContext可以保证页面加载时修改语言信息，
	 * 而onConfigurationChange则是为了对应横竖屏切换时系统更新Resource的问题
	 *
	 * @param context application context
	 */
	public void setConfiguration(Context context) {
		if (context == null) {
			Log.e(TAG, "No context, MultiLanguageUtil will not work!");
			return;
		}
		/*
		 * 为防止传入非ApplicationContext，这里做一次强制转化，目的是避免onConfigurationChange可能导致的问题，
		 * 因为onConfigurationChange被触发时系统会更新ApplicationContext中的Resource，如果页面包含Runtime资源
		 * （运行时动态加载的资源）时，有可能语言显示不一致。
		 */
		Context appContext = context.getApplicationContext();
		Locale preferredLocale = getSysPreferredLocale();
		Log.d(TAG, "Set to preferred locale: " + preferredLocale);
		Configuration configuration = appContext.getResources().getConfiguration();
		if (Build.VERSION.SDK_INT >= 17) {
			configuration.setLocale(preferredLocale);
		} else {
			configuration.locale = preferredLocale;
		}
		// 更新context中的语言设置
		Resources resources = appContext.getResources();
		DisplayMetrics dm = resources.getDisplayMetrics();
		resources.updateConfiguration(configuration, dm);
	}
 
	/**
	 * 获取系统首选语言
	 *
	 * 注意：该方法获取的是用户实际设置的不经API调整的系统首选语言
	 *
	 * @return
	 */
	private Locale getSysPreferredLocale() {
		Locale locale;
		//7.0以下直接获取系统默认语言
		if (Build.VERSION.SDK_INT < 24) {
			// 等同于context.getResources().getConfiguration().locale;
			locale = Locale.getDefault();
		// 7.0以上获取系统首选语言
		} else {
			/*
			 * 以下两种方法等价，都是获取经API调整过的系统语言列表（可能与用户实际设置的不同）
			 * 1.context.getResources().getConfiguration().getLocales()
			 * 2.LocaleList.getAdjustedDefault()
			 */
			// 获取用户实际设置的语言列表
			locale = LocaleList.getDefault().get(0);
		}
		return locale;
	}
}
