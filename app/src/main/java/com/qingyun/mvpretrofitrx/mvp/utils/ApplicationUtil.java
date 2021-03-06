package com.qingyun.mvpretrofitrx.mvp.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.develop.wallet.eth.Wallet;
import com.facebook.stetho.Stetho;

import com.github.jokar.multilanguages.library.LanguageLocalListener;
import com.github.jokar.multilanguages.library.MultiLanguage;
import com.google.gson.Gson;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseModel;
import com.qingyun.mvpretrofitrx.mvp.entity.AccountInfo;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessage;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.entity.PersonalInfo;
import com.qingyun.mvpretrofitrx.mvp.entity.VersionInfo;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;
import com.qingyun.mvpretrofitrx.mvp.service.SingleSocket;
import com.qingyun.mvpretrofitrx.mvp.service.WebSocketService;
import com.qingyun.mvpretrofitrx.mvp.weight.IntenalHeader;
import com.qingyun.mvpretrofitrx.mvp.weight.dialog.CommonData;
import com.qingyun.mvpretrofitrx.mvp.weight.dialog.CommonDialogService;
import com.qingyun.mvpretrofitrx.mvp.weight.dialog.ProgressDialogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.senon.mvpretrofitrx.R;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.smtt.sdk.QbSdk;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import io.reactivex.functions.Consumer;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.GroupUserInfo;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import skin.support.SkinCompatManager;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;

/**
 * Application类 初始化各种配置
 */
public class ApplicationUtil extends Application implements Application.ActivityLifecycleCallbacks{

    public static boolean isFirst = true;
    public static VersionInfo mVersionInfo;
    private static Context mContext;//全局上下文对象
    private static PersonalInfo user;
    public static List<BaseActivity> activityList;
    private static AccountInfo accountInfo;
    public static Context currentContext;
    public static Locale locale = Locale.getDefault();
    private static Wallet currentWallet;
    private static final String TAG = WebSocketService.class.getSimpleName();
    private static GroupMember chatPersonalInfo;
    private static ApplicationUtil applicationUtil;

    public static ApplicationUtil getInstance(){
        return applicationUtil;
    }
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {

                return new IntenalHeader(context,R.layout.refreash_header);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }



    public static void setCurrentWallet(Wallet wallet) {
        wallet.setPrivateKey(null);
        currentWallet = wallet;
        SpUtils.setObjectToShare(getContext(),wallet,"current_wallet");
    }



    public static Wallet getCurrentWallet() {
        if (currentWallet==null)        {
            currentWallet = (Wallet) SpUtils.getObjectFromShare(getContext(), "current_wallet");
        }
        return currentWallet;
    }


    public static GroupMember getChatPersonalInfo() {
        return chatPersonalInfo;
    }

    public static void setChatPersonalInfo(GroupMember chatPersonalInfo) {
        ApplicationUtil.chatPersonalInfo = chatPersonalInfo;

        final UserInfo userInfo = new UserInfo(chatPersonalInfo.getId()+"",chatPersonalInfo.getName(), Uri.parse(chatPersonalInfo.getPhoto()));
        RongIM.getInstance().setCurrentUserInfo(userInfo);
        RongIM.getInstance().setMessageAttachedUserInfo(true);
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {

            @Override
            public UserInfo getUserInfo(String userId) {

                return userInfo;//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
            }

        }, true);
    }



    public static void refreashPersonalInfo(GroupMember chatPersonalInfo) {
        ApplicationUtil.chatPersonalInfo = chatPersonalInfo;

        final UserInfo userInfo = new UserInfo(chatPersonalInfo.getId()+"",chatPersonalInfo.getName(), Uri.parse(chatPersonalInfo.getPhoto()));
        RongIM.getInstance().refreshUserInfoCache(userInfo);
        RongIM.getInstance().setCurrentUserInfo(userInfo);

    }



    public static Map<String,List<Wallet>> getWallet(){
       return (Map<String, List<Wallet>>) SpUtils.getObjectFromShare(getContext(),"wallet");
    }

    public static List<Wallet> getWalletBuyCoinType(String coinType){

        Map<String,List<Wallet>> walletMap =   getWallet();
        if (walletMap==null) return new ArrayList<>();
        return walletMap.get(coinType)==null?new ArrayList<Wallet>():walletMap.get(coinType);
    }




    public static void addWallet(Wallet wallet) {
        wallet.setPrivateKey(null);
        deleteWallet(wallet);
        Map<String,List<Wallet>> walletMap = null;
        List<Wallet> wallets = null;
        if (SpUtils.getObjectFromShare(getContext(),"wallet")==null){
            walletMap = new HashMap<>();
            wallets = new ArrayList<>();
            wallets.add(wallet);
            walletMap.put(wallet.getCoinType(),wallets);
        }else {
            walletMap = (Map<String, List<Wallet>>) SpUtils.getObjectFromShare(getContext(),"wallet");
            wallets =  walletMap.get(wallet.getCoinType());
            if (wallets==null){
                wallets = new ArrayList<>();
            }
            wallets.add(wallet);
        }
        walletMap.put(wallet.getCoinType(),wallets);
        SpUtils.setObjectToShare(getContext(),walletMap,"wallet");
    }

    public static void deleteWallet(Wallet wallet){
        if (wallet==null) return;
        Map<String, List<Wallet>> walletMap = (Map<String, List<Wallet>>) SpUtils.getObjectFromShare(getContext(), "wallet");
        if (walletMap==null||walletMap.size()<=0)return;
        List<Wallet> wallets = walletMap.get(wallet.getCoinType());
        for (int i = 0;i<wallets.size();i++){
            if (wallets.get(i).getAddress().equals(wallet.getAddress())){
                wallets.remove(i);
            }
        }
        walletMap.put(wallet.getCoinType(),wallets);
        SpUtils.setObjectToShare(getContext(),walletMap,"wallet");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initRongIm();



//        startSocket();
        SkinCompatManager.withoutActivity(this)
//                .addInflater(new SkinAppCompatViewInflater())           // 基础控件换肤初始化// 基础控件换肤初始化
                .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
                .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
//                .addInflater(new SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
                .setSkinStatusBarColorEnable(false)                     // 关闭状态栏换肤，默认打开[可选]
                .setSkinWindowBackgroundEnable(true)                   // 关闭windowBackground换肤，默认打开[可选]
                .loadSkin();

        initX5web();
        closeAndroidPDialog();
        MultiLanguage.init(new LanguageLocalListener() {
            @Override
            public Locale getSetLanguageLocale(Context context) {
                //返回自己本地保存选择的语言设置
                return LocalManageUtil.getSetLanguageLocale(context);
            }
        });
        MultiLanguage.setApplicationLanguage(this);
        initDialog();
        MultiDex.install(this);
        activityList = new ArrayList<>();
        mContext = getApplicationContext();
        applicationUtil = this;
        Stetho.initializeWithDefaults(this);
        //初始x3
        x.Ext.init(this);
        //极光统计
        StatsConfig.getInstance().initStats();
        /**极光统计开启crashlog日志上报**/
//        JAnalyticsInterface.initCrashHandler(mContext);
    }

    private void initRongIm() {

        RongIM.init(this,"y745wfm8yh1dv");

    }

    private void startSocket() {
        mSocket = SingleSocket.getInstance().getSocket();
        getSocketMsg();
    }



    private Socket mSocket;
    private void getSocketMsg(){
        mSocket.on("refresh", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    JSONObject data = (JSONObject) args[0];
                    Log.e("push>>>>>>",data.toString());
                    ChatMessage chatMessage = new Gson().fromJson(data.toString(), ChatMessage.class);
                    if (chatMessage.getState()==1){
                        if (chatMessage.getTowho().equals(ApplicationUtil.getCurrentWallet().getAddress()))
                            EventBus.getDefault().post(chatMessage);
                    }else if (chatMessage.getState()==2){
                        if (!chatMessage.getFromwho().equals(ApplicationUtil.getCurrentWallet().getAddress()))
                            EventBus.getDefault().post(chatMessage);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });



    }



    /**
     * 判断当前应用是否是debug状态
     */
    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }


    private void initX5web() {
        //非wifi情况下，主动下载x5内核
        QbSdk.setDownloadWithoutWifi(true);
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                Log.e("---------------------",arg0+"");
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
            }

            @Override
            public void onCoreInitFinished() {

            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);

    }


    //关闭Android P 反射调用弹窗
    private void closeAndroidPDialog(){
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static Context getContext() {
        return mContext;
    }


    private void initDialog(){
        this.registerActivityLifecycleCallbacks(this);//注册
        CommonData.applicationContext = this;
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.getDefaultDisplay().getMetrics(metric);
        CommonData.ScreenWidth = metric.widthPixels; // 屏幕宽度（像素）
        Intent dialogservice = new Intent(this, CommonDialogService.class);
        startService(dialogservice);
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (activity.getParent() != null) {
            CommonData.mNowContext = activity.getParent();
        } else
            CommonData.mNowContext = activity;
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (activity.getParent() != null) {
            CommonData.mNowContext = activity.getParent();
        } else
            CommonData.mNowContext = activity;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (activity.getParent() != null) {
            CommonData.mNowContext = activity.getParent();
        } else
            CommonData.mNowContext = activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ProgressDialogUtils.getInstances().cancel();

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }


    public static Context getCurrentContext(){
        return currentContext;
    }

    public static void setCurrentContext(Context currentContext){
        ApplicationUtil.currentContext =currentContext;
    }

    public static PersonalInfo getUser() {
        if (SpUtils.getObjectFromShare(getContext(),"user")!=null)
        {
            user = (PersonalInfo) SpUtils.getObjectFromShare(getContext(),"user");
        }

        return user;
    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        Context context = base;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
//             context = MyContextWrapper.wrap(base, locale);
//
//        }
//        super.attachBaseContext(context);
////        MultiLanguageUtil.getInstance().setConfiguration(getApplicationContext());
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
////        MultiLanguageUtil.getInstance().setConfiguration(getApplicationContext());
//    }

    @Override
    protected void attachBaseContext(Context base) {
        //第一次进入app时保存系统选择语言(为了选择随系统语言时使用，如果不保存，切换语言后就拿不到了）
        LocalManageUtil.saveSystemCurrentLanguage(base);
        super.attachBaseContext(MultiLanguage.setLocal(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //用户在系统设置页面切换语言时保存系统选择语言(为了选择随系统语言时使用，如果不保存，切换语言后就拿不到了）
        LocalManageUtil.saveSystemCurrentLanguage(getApplicationContext(), newConfig);
        MultiLanguage.onConfigurationChanged(getApplicationContext());
    }
    public static void setUser(PersonalInfo user) {
        ApplicationUtil.user = user;
        SpUtils.setObjectToShare(getContext(),user,"user");
    }
    public static void setAccountInfo(AccountInfo accountInfo) {
        ApplicationUtil.accountInfo = accountInfo;
    }

    public static AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public static void exitApp(){
        SpUtils.setObjectToShare(ApplicationUtil.getContext(),"","token");
        Api.UpdataToken();
        for (int j=0;j<activityList.size();j++){
            if (activityList.get(j)!=null){
                activityList.get(j).finish();
            }
        }
    }




}
