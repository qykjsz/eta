package com.qingyun.mvpretrofitrx.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComposeActivity extends BaseActivity {
    @BindView(R.id.tv_c_can_use)
    TextView tvCCanUse;
    @BindView(R.id.tv_t_can_use)
    TextView tvTCanUse;
    @BindView(R.id.tv_s)
    BoldTextView tvS;
    @BindView(R.id.tv_amount)
    EditText tvAmount;
    @BindView(R.id.et_c)
    TextView etC;
    @BindView(R.id.et_t)
    TextView etT;

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
        return getResources().getString(R.string.compose);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_compose;
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
        setIvTitleRight(R.mipmap.history_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(IntentUtils.HISTORY_TYPE,IntentUtils.COMPOSE);
                startActivity(HistoryActivity.class,bundle);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_compose)
    public void onViewClicked() {
    }
}
