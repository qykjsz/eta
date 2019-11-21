package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.develop.wallet.eth.Wallet;
import com.qingyun.mvpretrofitrx.mvp.adapter.CoinTypeAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.MyWalletAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.WalletManagerCoinAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinType;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.BINANCE;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.BOS;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.COSMOS;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.EOS;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.ETH;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.IOST;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.MOAC;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.Tron;

public class WalletManagerActivity extends BaseActivity {
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;
    @BindView(R.id.tv_wallet_name)
    TextView tvWalletName;
    @BindView(R.id.rcy_wallet)
    RecyclerView rcyWallet;
    @BindView(R.id.btn_aa_wallet)
    ImageView btn_aa_wallet;
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
        return getResources().getString(R.string.wallet_manager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_wallet_manager;
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

        List<CoinType> list = new ArrayList<>();
        List<Wallet> mylist = new ArrayList<>();
        final MyWalletAdapter myWalletAdapter = new MyWalletAdapter(getContext(), mylist);
        rcyWallet.setLayoutManager(new LinearLayoutManager(getContext()));
        rcyWallet.addItemDecoration(DividerHelper.getPaddingDivider(getContext(), R.dimen.dp_20));
        rcyWallet.setAdapter(myWalletAdapter);
        myWalletAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
//                ApplicationUtil.setCurrentWallet((Wallet) list.get(position));
                Bundle bundle = new Bundle();
                bundle.putSerializable(IntentUtils.WALLET, ((Wallet) list.get(position)));
                startActivity(AssetDetailActivity.class, bundle);
            }
        });


        list.add(new CoinType(R.mipmap.qbgl_eos, R.mipmap.qbgl_eos_xz, EOS));
        list.add(new CoinType(R.mipmap.qbgl_eth, R.mipmap.qbgl_eth_xz, ETH));
        list.add(new CoinType(R.mipmap.qbgl_iost, R.mipmap.qbgl_iost_xz, IOST));
        list.add(new CoinType(R.mipmap.qbgl_tron, R.mipmap.qbgl_tron_xz, Tron));
        list.add(new CoinType(R.mipmap.qbgl_binance, R.mipmap.qbgl_binance_xz, BINANCE));
        list.add(new CoinType(R.mipmap.qbgl_bos, R.mipmap.qbgl_bos_xz, BOS));
        list.add(new CoinType(R.mipmap.qbgl_cosmos, R.mipmap.qbgl_cosmos_xz, COSMOS));
        list.add(new CoinType(R.mipmap.qbgl_moac, R.mipmap.qbgl_moac_xz, MOAC));

        WalletManagerCoinAdapter coinTypeAdapter = new WalletManagerCoinAdapter(getContext(), list);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setAdapter(coinTypeAdapter);
        coinTypeAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                if(position == 1){
                    btn_aa_wallet.setVisibility(View.VISIBLE);
                }else{
                    btn_aa_wallet.setVisibility(View.GONE);
                }
                tvWalletName.setText(((CoinType) list.get(position)).getCoinType());
                List<Wallet> mlist = (List<Wallet>) ApplicationUtil.getWalletBuyCoinType(((CoinType) list.get(position)).getCoinType());
                myWalletAdapter.notifyDataSetChanged(mlist);

            }
        });
        coinTypeAdapter.setDefaultSelectItem(1);
        tvWalletName.setText(ETH);
        List<Wallet> mlist = (List<Wallet>) ApplicationUtil.getWalletBuyCoinType(((CoinType) list.get(1)).getCoinType());
        myWalletAdapter.notifyDataSetChanged(mlist);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_back, R.id.btn_aa_wallet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_aa_wallet:
                startActivity(CreateWalletActivity.class);

                break;
        }
    }
}
