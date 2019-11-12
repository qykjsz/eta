package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 帮助中心
 */
public class SupportActivity extends BaseActivity {


    @BindView(R.id.tv_na)
    TextView tvNa;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.lv_back)
    LinearLayout lvBack;
    @BindView(R.id.Rl_c)
    RelativeLayout RlC;
    private boolean aBoolean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_support);
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
        return getResources().getString(R.string.support_center);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_support;
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

    @OnClick(R.id.Rl_c)
    public void onViewClicked() {
        if (aBoolean == false) {
            aBoolean = true;
            lvBack.setVisibility(View.GONE);
        } else {
             aBoolean = false;
            lvBack.setVisibility(View.VISIBLE);
        }
    }
}
