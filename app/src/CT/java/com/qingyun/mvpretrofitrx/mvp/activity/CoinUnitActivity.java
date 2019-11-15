package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qingyun.mvpretrofitrx.mvp.adapter.CoinUnitAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinUnit;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoinUnitActivity extends BaseActivity {

    CoinUnitAdapter coinUnitAdapter;
    @BindView(R.id.rcy)
    RecyclerView rcy;
    private List<CoinUnit> list;

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
        return getResources().getString(R.string.coin_unit);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_coin_unit;
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
        list.add(new CoinUnit("AUD"));
        list.add(new CoinUnit("CNY"));
        list.add(new CoinUnit("EOS"));
        list.add(new CoinUnit("EUR"));
        list.add(new CoinUnit("GBP"));
        list.add(new CoinUnit("MYR"));
        list.add(new CoinUnit("USD"));

        coinUnitAdapter = new CoinUnitAdapter(getContext(), list);
        coinUnitAdapter.setDefaultSelectItem(0);
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.addItemDecoration(DividerHelper.getMyDivider(getContext()));
        rcy.setAdapter(coinUnitAdapter);
        refreashView(list, rcy);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.confirm)
    public void onViewClicked() {
        ToastUtil.showShortToast(R.string.not_open);
    }
}
