package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qingyun.mvpretrofitrx.mvp.adapter.InvestLogAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.contract.InvestContact;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinTypeRate;
import com.qingyun.mvpretrofitrx.mvp.entity.InvestLog;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Platform;
import com.qingyun.mvpretrofitrx.mvp.presenter.InvestPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.ObservableTransformer;

public class InvestLogActivity extends BaseActivity<InvestContact.View,InvestContact.Presenter>implements InvestContact.View {
    @BindView(R.id.rcy)
    RecyclerView rcy;
    InvestLogAdapter investLogAdapter;
    private List<InvestLog> list;

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
        return getResources().getString(R.string.invest_log);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_simple_list;
    }

    @Override
    public InvestContact.Presenter createPresenter() {
        return new InvestPresenter(this);
    }

    @Override
    public InvestContact.View createView() {
        return this;
    }


    @Override
    public void init() {
        list = new ArrayList<>();
        getPresenter().getInvestLog(ApplicationUtil.getCurrentWallet().getAddress(),page);
        investLogAdapter = new InvestLogAdapter(getContext(),list);
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.setAdapter(investLogAdapter);
    }


    @Override
    protected void refresh() {
        super.refresh();
        getPresenter().getInvestLog(ApplicationUtil.getCurrentWallet().getAddress(),page);

    }

    @Override
    protected void loadMore() {
        super.loadMore();
        getPresenter().getInvestLog(ApplicationUtil.getCurrentWallet().getAddress(),page);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getNodeSuccess(String node) {

    }

    @Override
    public void getCoinTypeRateSuccess(List<CoinTypeRate> coinTypeRateList) {

    }

    @Override
    public void checkAccountSuccess(NormalResponse normalResponse) {


    }

    @Override
    public void addInvestInfoSuccess(NormalResponse normalResponse) {

    }

    @Override
    public void getSuprtPlatformSuccess(List<Platform> platformList) {

    }

    @Override
    public void getInvestAmountListSuccess(List<String> amounts) {

    }

    @Override
    public void investSuccess() {

    }

    @Override
    public void getInvestLogSuccess(List<InvestLog> investLogList) {

        if (isLoadMore){
            list.addAll(investLogList);
        }else {
            list = investLogList;
        }
        investLogAdapter.notifyDataSetChanged(list);
        refreashView(list,rcy);
    }

    @Override
    public void checkInvestInfoSuccess(String s) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
