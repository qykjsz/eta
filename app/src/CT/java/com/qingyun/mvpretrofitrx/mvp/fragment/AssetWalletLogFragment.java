package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingyun.mvpretrofitrx.mvp.adapter.AssetWalletLogAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.contract.WalletAssetContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Asset;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetWalletLog;
import com.qingyun.mvpretrofitrx.mvp.entity.GasPrice;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLogResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.VersionInfo;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.qingyun.mvpretrofitrx.mvp.presenter.WalletAssetPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.senon.mvpretrofitrx.R;
import com.trello.rxlifecycle2.android.FragmentEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.ObservableTransformer;

public class AssetWalletLogFragment extends BaseFragment<WalletAssetContact.View,WalletAssetContact.Presenter> implements WalletAssetContact.View {
    private final int type;
    private final Wallet asset;
    @BindView(R.id.rcy)
    RecyclerView rcy;
    Unbinder unbinder;
    AssetWalletLogAdapter assetWalletLogAdapter;
    private List<TransferLog> list;

    public AssetWalletLogFragment(int type, Wallet asset) {
        this.type = type;
        this.asset = asset;
    }


    @Override
    public int getLayoutId() {
        return R.layout.layout_simple_list;
    }

    @Override
    public WalletAssetContact.Presenter createPresenter() {
        return new WalletAssetPresenter(getContext());
    }

    @Override
    public WalletAssetContact.View createView() {
        return this;
    }


    @Override
    public void init() {
        list = new ArrayList<>();
        assetWalletLogAdapter = new AssetWalletLogAdapter(getContext(),list);
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.addItemDecoration(DividerHelper.getMyDivider(getContext()));
        rcy.setAdapter(assetWalletLogAdapter);
        refreashView(list,rcy);

        getPresenter().getLog(ApplicationUtil.getCurrentWallet().getAddress(),asset.getName(),type,page);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
            list.addAll(transferLogResponse.getOrder());
        }else {
            list = transferLogResponse.getOrder();
        }
        if (list==null) list = new ArrayList<>();
        assetWalletLogAdapter.notifyDataSetChanged(list);
        refreashView(list,rcy);
        EventBus.getDefault().post(transferLogResponse);
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
        return this.bindUntilEvent(FragmentEvent.PAUSE);

    }



}
