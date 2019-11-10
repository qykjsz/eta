package com.qingyun.mvpretrofitrx.mvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.senon.mvpretrofitrx.R;

/**
 * 邀请好友
 */
public class InvitefriendsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_invitefriends);
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
    public boolean haveHeader() {
        return true;
    }
    @Override
    protected String getTitleText() {
        return getResources().getString(R.string.invite_friends);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_invitefriends;
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
}
