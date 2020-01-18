package com.qingyun.mvpretrofitrx.mvp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.develop.wallet.eth.Wallet;
import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.net.HttpParamsUtils;
import com.qingyun.mvpretrofitrx.mvp.net.XCallBack;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.ZLog;
import com.senon.mvpretrofitrx.R;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Date 2020/1/17.
 * Created by Sam
 * ClassExplain：
 */
public class AdvertisementActivity extends BaseActivity {


    @Override
    protected String getTitleRightText() {
        return null;
    }

    @Override
    protected String getTitleLeftText() {
        return null;
    }

    @Override
    protected String getTitleText() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_advertisement;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    private int recLen = 3;//跳过倒计时提示3秒
    Timer timer = new Timer();
    private Handler handler;
    private Runnable runnable;
    private boolean state;



    TextView tv_skip;

    @Override
    public void init() {
        ImageView image = findViewById(R.id.iv_image);
        tv_skip = findViewById(R.id.tv_skip);
        String photo = getIntent().getStringExtra("photo");
        final String url = getIntent().getStringExtra("url");
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.circleCropTransform();
//        requestOptions.transforms(new RoundedCorners(0));
        Glide.with(AdvertisementActivity.this).load(photo).apply(new RequestOptions()).into(image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = true;
//                timer.purge();
//                task.cancel();
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                }
                startActivity();
                Intent intent = new Intent(AdvertisementActivity.this, WebActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("title", "");
                startActivity(intent);
            }
        });
//        task = new TimerTask() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() { // UI thread
//                    @Override
//                    public void run() {
//                        recLen--;
//                        if (recLen < 0) {
//                            timer.cancel();
//                            startActivity();
////                            tv_skip.setVisibility(View.GONE);//倒计时到0隐藏字体
//                        }
//                    }
//                });
//            }
//        };
        tv_skip.setText("跳过" /*+ recLen*/);
        /**
         * 正常情况下不点击跳过
         */
//        timer.schedule(task, 1000, 1000);//等待时间一秒，停顿时间一秒

        handler = new Handler();
        //防止时间刚好到时，点击了
            handler.postDelayed(runnable = new Runnable() {
                @Override
                public void run() {
                    startActivity();
                }
            }, 3000);//延迟3S后发送handler信息

        /**点击情况下跳转*/
        findViewById(R.id.rl_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                timer.purge();
//                task.cancel();
                handler.removeCallbacks(runnable);
                state = true;
                startActivity();

            }
        });

    }
    TimerTask task;
    private void startActivity() {
        final Wallet wallet = ApplicationUtil.getCurrentWallet();
        if (wallet == null) {
            Intent intent = new Intent(AdvertisementActivity.this, ChooseBottomLevelActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            finish();
        } else {
            Intent intent = new Intent(AdvertisementActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            finish();
        }
    }

}
