package com.qingyun.mvpretrofitrx.mvp.activity.rongyun;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.utils.IndicatorUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class ContactSettingActivity extends BaseActivity {
    @BindView(R.id.cb_top)
    CheckBox cbTop;
    @BindView(R.id.cb_no_push)
    CheckBox cbNoPush;
    private String convesationType;
    private String targetid;

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
        return getResources().getString(R.string.contact_setting);
    }

    @Override
    public boolean haveHeader() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_contact_setting;
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

//        convesationType = getIntent().getStringExtra(IntentUtils.CONVESATIONTYPE);
//        targetid = getIntent().getStringExtra(IntentUtils.TARGETID);
//        cbTop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                RongIM.getInstance().setConversationToTop(convesationType, targetid, isChecked, new RongIMClient.ResultCallback<Boolean>() {
//                    @Override
//                    public void onSuccess(Boolean aBoolean) {
//
//                    }
//
//                    @Override
//                    public void onError(RongIMClient.ErrorCode errorCode) {
//
//                    }
//                });
//
//            }
//        });

        cbNoPush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
