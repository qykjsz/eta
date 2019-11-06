package com.qingyun.mvpretrofitrx.mvp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;
public class PushService extends Service {

    public static int id;
    private static Channel channel1;
    private static Pusher pusher;
    private static String channelName;
    private static String FILED_BUY_LIST;
    private static String FILED_SELL_LIST;
    private static String FILED_PRICE;
    private static String FILED_MARKET = "ex_market_0";
    private static String HISTORY_ORDER;
    private static String HISTORY_PRICE;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    /**
     * 首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或 onBind() 之前）。
     * 如果服务已在运行，则不会调用此方法。该方法只被调用一次
     */
    @Override
    public void onCreate() {
        System.out.println("onCreate invoke");
        super.onCreate();
        PusherOptions options = new PusherOptions();
        options.setCluster("ap1");
        pusher = new Pusher("3118b71a32aa3557d6a5", options);
//        if (ApplicationUtil.isApkInDebug(ApplicationUtil.getContext()))
//        {
//            channelName = "testAndroidSubscribe";
//        }else {
        channelName = "androidSubscribe";

//        }
        channel1 = pusher.subscribe(channelName);
        FILED_BUY_LIST = "ex_list_buy_"+id;
        FILED_SELL_LIST = "ex_list_sell_"+id;
        FILED_PRICE= "ex_price_"+id;
        HISTORY_ORDER = "history_order_"+id;
        HISTORY_PRICE= "history_price_"+id;
        Log.e("push>>>>",FILED_BUY_LIST);
        channel1.bind(FILED_BUY_LIST, new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {


            }
        });

        pusher.connect();
    }



    public static void stopPush(){
        channel1.unbind(FILED_BUY_LIST, new SubscriptionEventListener() {
            @Override
            public void onEvent(String s, String s1, String s2) {

            }
        });

        pusher.disconnect();
        pusher.unsubscribe(channelName);
    }
    /**
     * 每次通过startService()方法启动Service时都会被回调。
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand invoke");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 服务销毁时的回调
     */
    @Override
    public void onDestroy() {
        System.out.println("onDestroy invoke");
        super.onDestroy();
    }


}
