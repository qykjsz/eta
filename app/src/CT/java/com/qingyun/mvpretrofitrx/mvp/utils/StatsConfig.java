package com.qingyun.mvpretrofitrx.mvp.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;


import com.facebook.stetho.common.LogUtil;

import java.util.Currency;
import java.util.Map;

import cn.jiguang.analytics.android.api.BrowseEvent;
import cn.jiguang.analytics.android.api.CalculateEvent;
import cn.jiguang.analytics.android.api.CountEvent;
import cn.jiguang.analytics.android.api.Event;
import cn.jiguang.analytics.android.api.JAnalyticsInterface;
import cn.jiguang.analytics.android.api.LoginEvent;
import cn.jiguang.analytics.android.api.PurchaseEvent;
import cn.jiguang.analytics.android.api.RegisterEvent;

/**
 * Date 2019/12/17.
 * Created by Sam
 * ClassExplain：
 */
public class StatsConfig {
    //App Key
    public static final String KEY_APP_KEY = "efd63d226e15e5392bf0b03b";

    private Context mContext;

    private StatsConfig() {
        mContext= ApplicationUtil.getInstance();
    }

    private static class Holder {
        private static StatsConfig instance = new StatsConfig();
    }

    public static StatsConfig getInstance() {
        return StatsConfig.Holder.instance;
    }

    /**初始化极光,在Application的oncreate()方法中调用**/
    public void initStats(){
        //极光统计
        JAnalyticsInterface.setDebugMode(true);
        JAnalyticsInterface.init(mContext);
    }

    /**开启crashlog日志上报**/
    public void openCrashLog(){
        JAnalyticsInterface.initCrashHandler(mContext);
    }

    /**关闭crashlog日志上报**/
    public void closeCrashLog(){
        JAnalyticsInterface.stopCrashHandler(mContext);
    }

    /**
     * 页面启动接口。在页面(activity和fragment)的相关生命周期内调用，和onPageEnd需要成对调用
     * @param context activity的上下文
     * @param pageName 页面名称
     */
    public void onPageStart(Context context,String pageName){
        JAnalyticsInterface.onPageStart(context,pageName);
    }

    /**
     * 页面结束接口。在页面(activity和fragment)的相关生命周期内调用，和onPageStart需要成对调用
     * @param context activity的上下文
     * @param pageName 页面名称
     */
    public void onPageEnd(Context context,String pageName){
        JAnalyticsInterface.onPageEnd(context,pageName);
    }

    /**
     * 注册事件
     * @param context
     * @param extra map的key不能为"register_method","register_success"
     */
    public void onRegisterEvent(Context context,Map<String,String>extra){
        RegisterEvent registerEvent = new RegisterEvent("testRegisterMethod", true);
        //添加自己的Extra 信息
        for (Map.Entry<String,String> entry : extra.entrySet()){
            registerEvent.addKeyValue(entry.getKey(),entry.getValue());
        }
        JAnalyticsInterface.onEvent(context, registerEvent);
    }

    /**
     * 注册事件
     *
     * @param context
     * @param registerMethod 注册方式
     * @param isSuccess      是否成功
     * @param extra          附加信息 map的key不能为"register_method","register_success"
     */
    public static void onRegisterEvent(Context context,
                                       String registerMethod,
                                       boolean isSuccess,
                                       Map<String, String> extra) {
        Event registerEvent = new RegisterEvent()
                .setRegisterMethod(registerMethod) //注册方式
                .setRegisterSuccess(isSuccess) //是否成功
                .addExtMap(extra);
        JAnalyticsInterface.onEvent(context, registerEvent);
    }

    /**
     * 登陆事件
     * @param context
     * @param extra map的key不能为"login_method","login_success"
     */
    public void onLoginEvent(Context context,Map<String,String>extra){
        LoginEvent loginEvent = new LoginEvent("testLoginMethod", true);
        //添加自己的Extra 信息
        for (Map.Entry<String,String> entry : extra.entrySet()){
            loginEvent.addKeyValue(entry.getKey(),entry.getValue());
        }
        JAnalyticsInterface.onEvent(context, loginEvent);
    }

    /**
     * 登录事件
     *
     * @param context
     * @param loginMethod 登录方式
     * @param isSuccess   是否成功
     * @param extra       附加信息  map的key不能为"login_method","login_success"
     */
    public static void onLoginEvent(Context context,
                                    String loginMethod,
                                    boolean isSuccess,
                                    Map<String, String> extra) {
        Event loginEvent = new LoginEvent()
                .setLoginMethod(loginMethod) //login方式
                .setLoginSuccess(isSuccess) //是否成功
                .addExtMap(extra);
        JAnalyticsInterface.onEvent(context, loginEvent);
    }

    /**
     * 购买事件
     *
     * @param context
     */
//    public static void onPurchaseEvent(Context context,Map<String,String>extra) {
//        PurchaseEvent purchaseEvent = new PurchaseEvent("test_purchaseGoodsID", //商品ID
//                "篮球", //商品名称
//                300, //商品价格
//                true, //商品购买是否成功
//                Currency.CNY, //货币类型
//                "sport",//商品类型
//                1); //商品购买数量
//        //添加自己的Extra 信息
//        for (Map.Entry<String,String> entry : extra.entrySet()){
//            purchaseEvent.addKeyValue(entry.getKey(),entry.getValue());
//        }
//        JAnalyticsInterface.onEvent(context, purchaseEvent);
//    }

    /**
     * 购买事件
     *
     * @param context
     * @param purchaseGoodsid    物品id，开发者自己定义
     * @param purchaseGoodsname  购买物品名称(如lv ，香奈儿，劳力士表，安踏运动鞋 etc）
     * @param purchasePrice      购买价格
     * @param purchaseSuccess    购买是否成功
     * @param purchaseCurrency   购买货币类型(人民币(CNY)，美元(USD) ,具体参考Currency)
     * @param purchaseGoodsCount 购买数量
     * @param purchaseGoodstype  购买物品类型(如衣服，手表，鞋子，家用电器etc)
     */
//    public static void onPurchaseEvent(Context context,
//                                       String purchaseGoodsid,
//                                       String purchaseGoodsname,
//                                       double purchasePrice,
//                                       boolean purchaseSuccess,
//                                       Currency purchaseCurrency,
//                                       String purchaseGoodstype,
//                                       int purchaseGoodsCount,
//                                       Map<String, String> extra) {
//        Event purchaseEvent = new PurchaseEvent()
//                .setPurchaseGoodsid(purchaseGoodsid) //商品ID
//                .setPurchaseGoodsname(purchaseGoodsname) //商品名称
//                .setPurchasePrice(purchasePrice) //商品价格
//                .setPurchaseSuccess(purchaseSuccess) //购买是否成功
//                .setPurchaseCurrency(purchaseCurrency) //货币类型
//                .setPurchaseGoodstype(purchaseGoodstype) //商品类型
//                .setPurchaseGoodsCount(purchaseGoodsCount)
//                .addExtMap(extra);
//        JAnalyticsInterface.onEvent(context, purchaseEvent);
//    }

    /**
     * 浏览事件
     *
     * @param context
     * @param browseId       浏览内容id，开发者自己定义
     * @param browseName     浏览的内容的名称(如内容标题等)
     * @param browseType     浏览的内容类型(如是热点，还是nba，还是cba，还是汽车，财经 etc)
     * @param browseDuration 浏览的内容时长，单位秒
     * @param extra          附加信息
     */
    public static void onBrowseEvent(Context context,
                                     String browseId,
                                     String browseName,
                                     String browseType,
                                     float browseDuration,
                                     Map<String, String> extra) {
        Event browseEvent = new BrowseEvent()
                .setBrowseId(browseId) //设置浏览内容id
                .setBrowseName(browseName) //设置浏览的内容的名称
                .setBrowseType(browseType) //设置浏览的内容类型
                .setBrowseDuration(browseDuration) //设置浏览的内容时长
                .addExtMap(extra);
        JAnalyticsInterface.onEvent(context, browseEvent);
    }

    /**
     * 计算事件
     *
     * @param context
     * @param eventId    事件ID
     * @param eventValue 事件对应的值
     * @param extra      附加信息
     */
    public static void onCalculateEvent(Context context,
                                        String eventId,
                                        double eventValue,
                                        Map<String, String> extra) {
        Event calculateEvent = new CalculateEvent()
                .setEventId(eventId)
                .setEventValue(eventValue)
                .addExtMap(extra);
        JAnalyticsInterface.onEvent(context, calculateEvent);
    }

    /**
     * 计数事件
     *
     * @param context
     * @param eventId   事件ID
     * @param extra     附加信息
     */
    public static void onCountEvent(Context context, String eventId, Map<String, String> extra) {
        Event countEvent = new CountEvent()
                .setEventId(eventId)
                .addExtMap(extra);
        JAnalyticsInterface.onEvent(context, countEvent);
    }

    // 取得AppKey
    public static String getAppKey(Context context) {
        Bundle metaData = null;
        String appKey = null;
        try {
            ApplicationInfo ai=context.getPackageManager().getApplicationInfo(context.getPackageName(),PackageManager.GET_META_DATA);
            if(ai!=null){
                metaData = ai.metaData;
            }
            if(null != metaData){
                appKey = metaData.getString(KEY_APP_KEY);
                if ((null == appKey) || appKey.length() != 24) {
                    appKey = null;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e("post===极光错误===","===error======"+e.getMessage());
        }
        return appKey;
    }

}
