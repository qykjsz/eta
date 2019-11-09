package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qingyun.mvpretrofitrx.mvp.adapter.WalletAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.contract.CoinContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.qingyun.mvpretrofitrx.mvp.presenter.CoinPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class AddCoinActivity extends BaseActivity<CoinContact.View,CoinContact.Presenter> implements CoinContact.View {
    @BindView(R.id.rcy)
    RecyclerView rcy;
    WalletAdapter walletAdapter;
    private List<Wallet> list;

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
        return R.layout.activity_add_coin;
    }

    @Override
    public CoinContact.Presenter createPresenter() {
        return new CoinPresenter(this);
    }

    @Override
    public CoinContact.View createView() {
        return this;
    }

    @Override
    public void init() {
        getPresenter().getSupportCoinList(ApplicationUtil.getCurrentWallet().getAddress());
        list = new ArrayList<>();
        walletAdapter = new WalletAdapter(getContext(), list);
        walletAdapter.setAddListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final List list, final int position) {


                if (((Wallet)list.get(position)).getHave().equals("1")){
                    DialogUtils.showConfirmDialog(getActivity(), 0, R.string.sure_to_unbind_coin, R.string.cancel, R.string.confirm, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getPresenter().bindCoin("2",ApplicationUtil.getCurrentWallet().getAddress(),((Wallet)list.get(position)).getId());


                        }
                    });



                }else {
                    DialogUtils.showConfirmDialog(getActivity(), 0,R.string.sure_to_bind_coin, R.string.cancel, R.string.confirm, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getPresenter().bindCoin("1",ApplicationUtil.getCurrentWallet().getAddress(),((Wallet)list.get(position)).getId());


                        }
                    });


                }




            }
        });
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.setAdapter(walletAdapter);
        rcy.addItemDecoration(DividerHelper.getMyDivider(getContext()));
        refreashView(list, rcy);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_cancle)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getSupportCoinListSuccess(List<Wallet> coins) {
        list = coins;
        walletAdapter.notifyDataSetChanged(list);
        refreashView(list, rcy);
    }

    @Override
    public void bindCoinSuccess(String operationtype) {
        if (operationtype.equals("1"))
        {
            ToastUtil.showShortToast(R.string.bind_coin_success);
            finish();
        }else {
            getPresenter().getSupportCoinList(ApplicationUtil.getCurrentWallet().getAddress());
        }
        EventBus.getDefault().post(ApplicationUtil.getCurrentWallet());
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return bindToLifecycle();
    }
}
