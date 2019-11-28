package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qingyun.mvpretrofitrx.mvp.adapter.BusinessPayLogAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.contract.BusinessPayContact;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessPayLog;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinTypeRate;
import com.qingyun.mvpretrofitrx.mvp.entity.CurrencyRate;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;
import com.qingyun.mvpretrofitrx.mvp.presenter.BusinessPayPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.ObservableTransformer;

public class BusinessGetMoneyLogActivity extends BaseActivity<BusinessPayContact.View,BusinessPayContact.Presenter> implements BusinessPayContact.View {
    @BindView(R.id.rcy)
    RecyclerView rcy;
    BusinessPayLogAdapter businessPayLogAdapter;
    private List<BusinessPayLog> list;

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
        return getResources().getString(R.string.business_get_money_log);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_simple_list;
    }

    @Override
    public BusinessPayContact.Presenter createPresenter() {
        return new BusinessPayPresenter(this);
    }

    @Override
    public BusinessPayContact.View createView() {
        return this;
    }

    @Override
    public void init() {
        list = new ArrayList<>();

        businessPayLogAdapter = new BusinessPayLogAdapter(getContext(),list);
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.addItemDecoration(DividerHelper.getMyDivider(getContext()));
        rcy.setAdapter(businessPayLogAdapter);
        getPresenter().getBusinessPayLog(ApplicationUtil.getCurrentWallet().getAddress(),page);
    }

    @Override
    protected void refresh() {
        super.refresh();
        getPresenter().getBusinessPayLog(ApplicationUtil.getCurrentWallet().getAddress(),page);

    }

    @Override
    protected void loadMore() {
        super.loadMore();
        getPresenter().getBusinessPayLog(ApplicationUtil.getCurrentWallet().getAddress(),page);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getCurrencyRateSuccess(List<CurrencyRate> currencyRateList) {

    }

    @Override
    public void getCoinTypeRateSuccess(List<CoinTypeRate> coinTypeRateList) {

    }

    @Override
    public void getBusinessPayLogSuccess(List<BusinessPayLog> businessPayLogList) {
        if (isLoadMore){
            list.addAll(businessPayLogList==null?new ArrayList<BusinessPayLog>():businessPayLogList);
        }else {
            list = businessPayLogList==null?new ArrayList<BusinessPayLog>():businessPayLogList;
        }
        businessPayLogAdapter.notifyDataSetChanged(list);
        refreashView(list,rcy);

    }

    @Override
    public void addBusinessPayLogSuccess(NormalResponse normalResponse) {

    }

    @Override
    public void getNodeSuccess(String node) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
