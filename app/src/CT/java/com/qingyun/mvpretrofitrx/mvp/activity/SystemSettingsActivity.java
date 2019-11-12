package com.qingyun.mvpretrofitrx.mvp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Notification.EXTRA_CHANNEL_ID;

/**
 * 系统设置
 */
public class SystemSettingsActivity extends BaseActivity {

    private static final String EXTRA_APP_PACKAGE = "";
    @BindView(R.id.Rl_language_choice)
    RelativeLayout RlLanguageChoice;
    @BindView(R.id.Rl_Node_set)
    RelativeLayout RlNodeSet;
    @BindView(R.id.Rl_unit)
    RelativeLayout RlUnit;
    @BindView(R.id.Rl_push_notification)
    RelativeLayout RlPushNotification;
    @BindView(R.id.sw_1)
    Switch sw1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_system_settings);
    }

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
        return getResources().getString(R.string.system_settings);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_system_settings;
    }

    @Override
    public boolean haveHeader() {
        return true;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {

    }

    @OnClick({R.id.Rl_language_choice, R.id.Rl_Node_set, R.id.Rl_unit, R.id.Rl_push_notification, R.id.sw_1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Rl_language_choice:
                break;
            case R.id.Rl_Node_set:
                break;
            case R.id.Rl_unit:
                break;
            case R.id.Rl_push_notification:

                push_notification();
                break;
            case R.id.sw_1:
                break;

            default:
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkNotifySetting();
    }

    private void push_notification() {

        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", SystemSettingsActivity.this.getPackageName());
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {  //5.0
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", SystemSettingsActivity.this.getPackageName());
            intent.putExtra("app_uid", SystemSettingsActivity.this.getApplicationInfo().uid);
            startActivity(intent);
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {  //4.4
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse("package:" + SystemSettingsActivity.this.getPackageName()));
        } else if (Build.VERSION.SDK_INT >= 15) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", SystemSettingsActivity.this.getPackageName(), null));
        }
        startActivity(intent);
    }

    /**
     * 作者：CnPeng
     * 时间：2018/7/12 上午9:02
     * 功用：检查是否已经开启了通知权限
     * 说明：
     */
    private void checkNotifySetting() {
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        // areNotificationsEnabled方法的有效性官方只最低支持到API 19，低于19的仍可调用此方法不过只会返回true，即默认为用户已经开启了通知。
        boolean isOpened = manager.areNotificationsEnabled();

        if (isOpened) {


        } else {
            //mBinding.tvMsg.setText("还没有开启通知权限，点击去开启");
        }
    }
}
