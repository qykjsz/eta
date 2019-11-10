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
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinType;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.BINANCE;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.BOS;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.COSMOS;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.EOS;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.ETH;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.IOST;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.MOAC;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.Tron;

/**
 * 钱包管理
 */
public class ThewalletManagementActivity extends BaseActivity {


    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.textView8)
    BoldTextView textView8;
    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rcy_wallet)
    RecyclerView rcyWallet;
    @BindView(R.id.addWallet)
    ImageView addWallet;
    List<CoinType> list;
    List<Wallet> mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_thewallet_management);
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
    public int getLayoutId() {
        return R.layout.activity_thewallet_management;
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
        mylist = new ArrayList<>();
        final MyWalletAdapter myWalletAdapter = new MyWalletAdapter(getContext(), mylist);
        rcyWallet.setLayoutManager(new LinearLayoutManager(getContext()));
        rcyWallet.addItemDecoration(DividerHelper.getPaddingDivider(getContext(), R.dimen.dp_20));
        rcyWallet.setAdapter(myWalletAdapter);
        myWalletAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                ApplicationUtil.setCurrentWallet((Wallet) list.get(position));

                // refreashData();
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

        CoinTypeAdapter coinTypeAdapter = new CoinTypeAdapter(getContext(), list);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setAdapter(coinTypeAdapter);
        coinTypeAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {

                tvName.setText(((CoinType) list.get(position)).getCoinType());
                List<Wallet> mlist = (List<Wallet>) ApplicationUtil.getWalletBuyCoinType(((CoinType) list.get(position)).getCoinType());
                myWalletAdapter.notifyDataSetChanged(mlist);

            }
        });
        coinTypeAdapter.setDefaultSelectItem(1);
        tvName.setText(ETH);
        List<Wallet> mlist = (List<Wallet>) ApplicationUtil.getWalletBuyCoinType(((CoinType) list.get(1)).getCoinType());
        myWalletAdapter.notifyDataSetChanged(mlist);
        addWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CreateWalletActivity.class);
            }
        });

    }
}
