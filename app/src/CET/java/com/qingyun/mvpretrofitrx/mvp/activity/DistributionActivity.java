package com.qingyun.mvpretrofitrx.mvp.activity;

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

public class DistributionActivity extends BaseActivity {
    @BindView(R.id.tv_can_use_cet)
    TextView tvCanUseCet;
    @BindView(R.id.tv_can_distribution)
    TextView tvCanDistribution;
    @BindView(R.id.tv_min)
    BoldTextView tvMin;
    @BindView(R.id.tv_amount)
    EditText tvAmount;
    @BindView(R.id.tv_c)
    TextView tvC;
    @BindView(R.id.tv_t)
    TextView tvT;

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
        return getResources().getString(R.string.distribution);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_distribution;
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
                bundle.putString(IntentUtils.HISTORY_TYPE,IntentUtils.DISTRIBUTION);
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

    @OnClick(R.id.btn_distribution)
    public void onViewClicked() {
    }
}
