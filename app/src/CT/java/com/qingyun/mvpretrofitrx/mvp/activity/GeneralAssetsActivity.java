package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.adapter.GeneralAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.General;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.qingyun.mvpretrofitrx.mvp.weight.GridSpacingItemDecoration;
import com.qingyun.mvpretrofitrx.mvp.weight.MultistageProgress;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GeneralAssetsActivity extends BaseActivity {


    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.textView8)
    BoldTextView textView8;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.btn_visiable)
    ImageView btnVisiable;
    @BindView(R.id.tv_asset)
    TextView tvAsset;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.tv_income_today)
    TextView tvIncomeToday;
    @BindView(R.id.progress)
    MultistageProgress progress;
    @BindView(R.id.ly_asset)
    ConstraintLayout lyAsset;
    @BindView(R.id.rcy_modle)
    RecyclerView rcyModle;
    private GeneralAdapter generalAdapter;
     private List<General> generals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_general_assets);
        ButterKnife.bind(this);

    }

    @Override
    public boolean haveHeader() {
        return false;
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
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_general_assets;
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
        generals=new ArrayList<>();
        generals.add(new General(R.mipmap.dc_eth,"theeopartal（ETH）","ETH","300","54453456"));
        generals.add(new General(R.mipmap.sd_usdt,"theeopartal（ETH）","ETH","300","54453456"));
        generals.add(new General(R.mipmap.sd_usdt,"theeopartal（ETH）","ETH","300","54453456"));
        generals.add(new General(R.mipmap.dc_eth,"theeopartal（ETH）","ETH","300","54453456"));
        generalAdapter=new GeneralAdapter(getContext(),generals);
        rcyModle.setLayoutManager( new LinearLayoutManager(getContext()));
        rcyModle.setAdapter(generalAdapter);
        refreashView(generals, rcyModle);



    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }
}
