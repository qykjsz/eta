package com.qingyun.mvpretrofitrx.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.adapter.WithdrawAddressAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.contract.WithdrawAddressContract;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.WithdrawAddress;
import com.qingyun.mvpretrofitrx.mvp.presenter.WithdrawAddressPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class WithdrawAddressActivity extends BaseActivity<WithdrawAddressContract.View,WithdrawAddressContract.Presenter> implements WithdrawAddressContract.View {

    @BindView(R.id.rcy)
    RecyclerView rcy;
    @BindView(R.id.btn_add_address)
    TextView btnAddAddress;
    WithdrawAddressAdapter withdrawAddressAdapter;
    private List<WithdrawAddress> list;

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
        return getResources().getString(R.string.address_manager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_address_manager;
    }

    @Override
    public WithdrawAddressContract.Presenter createPresenter() {
        return new WithdrawAddressPresenter(this);
    }

    @Override
    public WithdrawAddressContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        list = new ArrayList<>();
        withdrawAddressAdapter = new WithdrawAddressAdapter(getContext(), list);
        withdrawAddressAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                Intent intent = new Intent();
                intent.putExtra(IntentUtils.WITHDRAW_ADDRESS,(WithdrawAddress)list.get(position));
                setResult(1,intent);
                finish();

            }
        });
        withdrawAddressAdapter.setDeleteListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                getPresenter().deleteWithdrawAddress(((WithdrawAddress)list.get(position)).getId());
            }
        });
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.addItemDecoration(DividerHelper.getMyDivider(getContext()));
        rcy.setAdapter(withdrawAddressAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void refresh() {
        super.refresh();
        getPresenter().getWithdrawAddressList(page,per_page);
    }

    @Override
    protected void loadMore() {
        super.loadMore();
        getPresenter().getWithdrawAddressList(page,per_page);

    }

    @OnClick(R.id.btn_add_address)
    public void onViewClicked() {
        Intent intent = new Intent(WithdrawAddressActivity.this,AddWithdrawAddressActivity.class);
        intent.putExtra(IntentUtils.ADDRESS_INDEX,list.size());
        startActivity(intent);
    }

    @Override
    public void getWithdrawAddressListSuccess(List<WithdrawAddress> withdrawAddresses) {
        if (isLoadMore){
            list.addAll(withdrawAddresses);
        }else {
            list = withdrawAddresses;
        }
        refreashView(list,rcy);
        withdrawAddressAdapter.notifyDataSetChanged(list);
    }

    @Override
    public void addWithdrawAddressSuccess(NormalResponse normalResponse) {

    }

    @Override
    public void deleteWithdrawAddressSuccess(NormalResponse normalResponse) {
        ToastUtil.showShortToast(normalResponse.getDetail());
        page=1;
        isLoadMore = false;
        getPresenter().getWithdrawAddressList(page,per_page);
    }

    @Override
    protected void onResume() {
        super.onResume();
        page=1;
        isLoadMore = false;
        getPresenter().getWithdrawAddressList(page,per_page);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
