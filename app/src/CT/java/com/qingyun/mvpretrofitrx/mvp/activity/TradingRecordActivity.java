package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qingyun.mvpretrofitrx.mvp.adapter.AssetWalletLogAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.contract.TradingRecordContact;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLogResponse;
import com.qingyun.mvpretrofitrx.mvp.presenter.TradingRecordPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import ezy.ui.layout.LoadingLayout;
import io.reactivex.ObservableTransformer;

public class TradingRecordActivity extends BaseActivity<TradingRecordContact.View, TradingRecordContact.Presenter> implements TradingRecordContact.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    AssetWalletLogAdapter assetWalletLogAdapter;
    private List<TransferLog> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_trading_record);
    }

    @Override
    public boolean haveHeader() {
        return true;
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
        return getResources().getString(R.string.trading_record);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_trading_record;
    }

    @Override
    public TradingRecordContact.Presenter createPresenter() {
        return new TradingRecordPresenter(getContext());
    }

    @Override
    public TradingRecordContact.View createView() {
        return this;
    }

    @Override
    protected void loadMore() {
        super.loadMore();
        getPresenter().getLog(ApplicationUtil.getCurrentWallet().getAddress(), 0 + "", 3, page);

    }

    @Override
    protected void refresh() {
        super.refresh();
        getPresenter().getLog(ApplicationUtil.getCurrentWallet().getAddress(), 0 + "", 3, page);
    }

    @Override
    public void init() {
        if (ApplicationUtil.getCurrentWallet() != null) {
            getPresenter().getLog(ApplicationUtil.getCurrentWallet().getAddress(), 0 + "", 3, page);
            list = new ArrayList<>();
            assetWalletLogAdapter = new AssetWalletLogAdapter(getContext(), list);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.addItemDecoration(DividerHelper.getMyDivider(getContext()));
            recyclerView.setAdapter(assetWalletLogAdapter);
              initRefreshLayout(srl);
            refreashView(list, recyclerView);

        }


    }

    @Override
    public void getLogSuccess(TransferLogResponse transferLogResponse) {
        if (transferLogResponse.getOrder() != null) {
            if (isLoadMore) {
                list.addAll(transferLogResponse.getOrder());
            } else {
                list = transferLogResponse.getOrder();
            }
            refreashView(list, recyclerView);
            assetWalletLogAdapter.notifyDataSetChanged(list);
        }

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
