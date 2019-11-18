package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qingyun.mvpretrofitrx.mvp.adapter.BottomLevelAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.BottomLevel;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinType;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseBottomLsvelActivity extends BaseActivity {
    @BindView(R.id.rcy)
    RecyclerView rcy;

    BottomLevelAdapter bottomLevelAdapterp;
    private List<CoinType> list;

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
        return R.layout.activity_choose_bottom_level;
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
        list = new ArrayList<>();
        list.add(new CoinType(R.mipmap.dc_eth,0,CoinType.ETH,getResources().getString(R.string.bottom_name_eth)));
        list.add(new CoinType(R.mipmap.dc_eos,0,CoinType.EOS,getResources().getString(R.string.bottom_name_eos)));
        list.add(new CoinType(R.mipmap.dc_iost,0,CoinType.IOST,getResources().getString(R.string.bottom_name_iost)));
        list.add(new CoinType(R.mipmap.dc_tron,0,CoinType.Tron,getResources().getString(R.string.bottom_name_tron)));
        list.add(new CoinType(R.mipmap.dc_binance,0,CoinType.BINANCE,getResources().getString(R.string.bottom_name_binance)));
        list.add(new CoinType(R.mipmap.dc_bos,0,CoinType.BOS,getResources().getString(R.string.bottom_name_bos)));
        list.add(new CoinType(R.mipmap.dc_cosmos,0,CoinType.COSMOS,getResources().getString(R.string.bottom_name_cosmos)));
        list.add(new CoinType(R.mipmap.dc_mk,0,CoinType.MOAC,getResources().getString(R.string.bottom_name_mk)));


        bottomLevelAdapterp = new BottomLevelAdapter(getContext(),list);
        bottomLevelAdapterp.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                if (position==0){
                    finish();
                }else {
                    ToastUtil.showShortToast(R.string.not_open);
                }
            }
        });
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.setAdapter(bottomLevelAdapterp);
        refreashView(list,rcy);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }
}
