package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qingyun.mvpretrofitrx.mvp.adapter.TransferLogAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.contract.WalletAssetContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Asset;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessDetail;
import com.qingyun.mvpretrofitrx.mvp.entity.GasPrice;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLogResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.VersionInfo;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.qingyun.mvpretrofitrx.mvp.presenter.WalletAssetPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ScanUtil;
import com.senon.mvpretrofitrx.R;

import org.web3j.abi.Utils;
import org.web3j.protocol.Web3j;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class TransferActivity extends BaseActivity<WalletAssetContact.View,WalletAssetContact.Presenter> implements  WalletAssetContact.View {
    @BindView(R.id.rcy)
    RecyclerView rcy;
    TransferLogAdapter transferLogAdapter;
    private List<TransferLog> list;
    private Wallet asset;

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
        return R.layout.activity_transfer;
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

        asset = (Wallet) getIntent().getSerializableExtra(IntentUtils.ASSET);
        list = new ArrayList<>();
        transferLogAdapter = new TransferLogAdapter(getContext(), list);
        transferLogAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(IntentUtils.TRANSFER_LOG,((TransferLog)list.get(position)));
                startActivity(BusinessDetailActivity.class,bundle);
            }
        });
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.addItemDecoration(DividerHelper.getMyDivider(getContext()));
        rcy.setAdapter(transferLogAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.btn_transfer_immediate, R.id.btn_transfer_scan, R.id.btn_transfer_contact})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_transfer_immediate:
                Bundle bundle = new Bundle();
                bundle.putSerializable(IntentUtils.ASSET,asset);
                startActivity(TransferImmediateActivity.class,bundle);
                break;
            case R.id.btn_transfer_scan:
                ScanUtil.start( getActivity());
                break;
            case R.id.btn_transfer_contact:
                startActivity(ContactActivity.class);
                break;
        }
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
        if (list==null) list = new ArrayList<>();
        transferLogAdapter.notifyDataSetChanged(list);
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
    public void checkCanTransferSuccess() {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
