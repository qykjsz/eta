package com.qingyun.mvpretrofitrx.mvp.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.pusher.java_websocket.drafts.Draft_17;
import com.qingyun.mvpretrofitrx.mvp.service.SingleSocket;
import com.qingyun.mvpretrofitrx.mvp.service.WebSocketService;
import com.qingyun.mvpretrofitrx.mvp.utils.JsOperation;
import com.qingyun.mvpretrofitrx.mvp.utils.ZLog;
import com.senon.mvpretrofitrx.R;

import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.enums.CloseHandshakeType;
import org.java_websocket.enums.HandshakeState;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.List;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;


/**
 * Date 2014/3/27.
 * Created by Sam
 * ClassExplain：
 */

public class WebActivity extends Activity {
    private static final String TAG = WebSocketService.class.getSimpleName();
    private WebView webView;
    private String linkUrl;//链接地址
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSocket = SingleSocket.getInstance().getSocket();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_my_webview);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        linkUrl = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        ((TextView)findViewById(R.id.tv_title)).setText(title);
        webView = findViewById(R.id.webview);
        initWebView();
        webView.loadUrl(linkUrl);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                //title 就是网页的title
                ((TextView)findViewById(R.id.tv_title)).setText(title);
            }
        };
        // 设置setWebChromeClient对象
        webView.setWebChromeClient(wvcc);
        findViewById(R.id.start_web).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(linkUrl);
                intent.setData(content_url);
                startActivity(intent);


            }
        });



        getSocketMsg();

    }
    private Socket mSocket;
    private void getSocketMsg(){
        mSocket.on("refresh", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    ZLog.showPost(TAG+data.getString("text"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



    }

    /**
     * 设置wenView参数
     */
    private void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setDatabaseEnabled(true);
        String dir = getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        webView.getSettings().setGeolocationDatabasePath(dir);
        webView.getSettings().setDomStorageEnabled(true);
        // 开启 Application Caches 功能
        webView.getSettings().setAppCacheEnabled(true);
        // webView.addJavascriptInterface(new JsOperation(WebActivity.this, webView), "client");//设置js调用的java类

//        webView.setWebChromeClient(new MyWebChromeClient());
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                // 如果url的协议 = 预先约定的 js 协议
                // 就解析往下解析参数
                if (uri.getScheme().equals("js")) {

                    if (uri.getAuthority().equals("webview")) {
                        String address = uri.getQueryParameter("address");
                        Intent intent = new Intent();
                        intent.setClass(WebActivity.this, TransferImmediateActivity.class);
                        intent.putExtra("transfer_address", address);
                        intent.putExtra("coin_name", "HOPE");
                        startActivity(intent);

                    }

                    return true;
                }
//                if (Build.VERSION.SDK_INT < 26) {
//
//                    view.loadUrl(url);
//                    return true;
//                }
//                view.loadUrl(url);
//
//                return false;
                return super.shouldOverrideUrlLoading(view, url);
            }


        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }
        });


    }

    @Override
    // 设置回退
    // 5、覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //按下返回键并且webview界面可以返回
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {

            webView.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
