package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qingyun.mvpretrofitrx.mvp.adapter.AssetWalletLogAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.TransferLogAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.contract.WalletAssetContact;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.GasPrice;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLogResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.VersionInfo;
import com.qingyun.mvpretrofitrx.mvp.presenter.WalletAssetPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.ObservableTransformer;

public class BusinessLogActivity extends BaseActivity<WalletAssetContact.View, WalletAssetContact.Presenter> implements WalletAssetContact.View {
    @BindView(R.id.rcy)
    RecyclerView rcy;
    AssetWalletLogAdapter assetWalletLogAdapter;
    private List<TransferLog> list;

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
        return getResources().getString(R.string.business_log);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_simple_list;
    }

    @Override
    public WalletAssetContact.Presenter createPresenter() {
        return new WalletAssetPresenter(this);
    }

    @Override
    public WalletAssetContact.View createView() {
        return this;
    }


    @Override
    public void init() {
        list = new ArrayList<>();
        assetWalletLogAdapter = new AssetWalletLogAdapter(getContext(),list);
        assetWalletLogAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(IntentUtils.TRANSFER_LOG, ((TransferLog) list.get(position)));
                startActivity(BusinessDetailActivity.class, bundle);
            }
        });
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.addItemDecoration(DividerHelper.getMyDivider(getContext()));
        rcy.setAdapter(assetWalletLogAdapter);
        getPresenter().getLog(ApplicationUtil.getCurrentWallet().getAddress(),"0",3,page);

    }

    @Override
    protected void refresh() {
        super.refresh();
        getPresenter().getLog(ApplicationUtil.getCurrentWallet().getAddress(),"0",3,page);

    }

    @Override
    protected void loadMore() {
        super.loadMore();
        getPresenter().getLog(ApplicationUtil.getCurrentWallet().getAddress(),"0",3,page);

    }

    @Override
    public void getWalletInfoSuccess(AssetResponse assetResponse) {

    }

    @Override
    public void addWalletSuccess() {

    }

    @Override
    public void getLogSuccess(TransferLogResponse transferLogResponse) {

        if (isLoadMore){
            list.addAll(transferLogResponse.getOrder()==null?new ArrayList<TransferLog>():transferLogResponse.getOrder());
        }else {
            list = transferLogResponse.getOrder()==null?new ArrayList<TransferLog>():transferLogResponse.getOrder();
        }
        assetWalletLogAdapter.notifyDataSetChanged(list);
        refreashView(list,rcy);
    }

    @Override
    public void getNodeSuccess(String node) {

    }

    @Override
    public void searchLogByHashSuccess(TransferLog transferLog) {

    }

    @Override
    public void getGasPriceSuccess(List<GasPrice> gasPrices) {

    }

    @Override
    public void getVersionSuccess(VersionInfo versionInfo) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
