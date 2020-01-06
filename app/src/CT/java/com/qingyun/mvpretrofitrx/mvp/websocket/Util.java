package com.qingyun.mvpretrofitrx.mvp.websocket;

import android.content.Context;
import android.widget.Toast;

public class Util {
//    public static final String ws = "ws://echo.websocket.org";//websocket测试地址
//        public static final String ws = "ws://47.244.50.67:2569/refresh";
public static final String ws = "ws://47.56.69.153:9502";//websocket测试地址


    public static void showToast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }
}
