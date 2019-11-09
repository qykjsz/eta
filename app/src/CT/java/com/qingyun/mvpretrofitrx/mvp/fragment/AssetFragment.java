package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.animation.Animator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.develop.wallet.eth.Wallet;
import com.develop.wallet.eth.WalletManager;
import com.gongwen.marqueen.SimpleMarqueeView;
import com.qingyun.mvpretrofitrx.mvp.activity.AddCoinActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.AssetDetailActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.AssetWalletActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.CreateWalletActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.GetMoneyActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.MakeCopyWalletActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.QuickExchangeActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.ScanActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.TransferActivity;
import com.qingyun.mvpretrofitrx.mvp.adapter.AssetAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.AssetModleAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.CoinTypeAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.MyWalletAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.contract.WalletAssetContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Asset;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetModle;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinType;
import com.qingyun.mvpretrofitrx.mvp.presenter.WalletAssetPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.qingyun.mvpretrofitrx.mvp.utils.SpUtils;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.qingyun.mvpretrofitrx.mvp.weight.GridSpacingItemDecoration;
import com.qingyun.mvpretrofitrx.mvp.weight.MultistageProgress;
import com.senon.mvpretrofitrx.R;
import com.trello.rxlifecycle2.android.FragmentEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.ObservableTransformer;
import per.goweii.anylayer.AnimHelper;
import per.goweii.anylayer.AnyLayer;
import per.goweii.anylayer.LayerManager;

import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.BINANCE;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.BOS;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.COSMOS;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.EOS;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.ETH;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.IOST;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.MOAC;
import static com.qingyun.mvpretrofitrx.mvp.entity.CoinType.Tron;

public class AssetFragment extends BaseFragment<WalletAssetContact.View, WalletAssetContact.Presenter> implements WalletAssetContact.View {
    private static final int ETH_BLANCE = 1;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.btn_transfer)
    TextView btnTransfer;
    @BindView(R.id.btn_get_money)
    TextView btnGetMoney;
    @BindView(R.id.btn_shan_dui)
    TextView btnShanDui;
    @BindView(R.id.btn_packet)
    ImageView btnPacket;
    @BindView(R.id.btn_scan)
    ImageView btnScan;
    @BindView(R.id.btn_visiable)
    ImageView btnVisiable;
    @BindView(R.id.tv_asset)
    TextView tvAsset;
    @BindView(R.id.tv_income_today)
    TextView tvIncomeToday;
    @BindView(R.id.mv_info_list)
    SimpleMarqueeView mvInfoList;
    @BindView(R.id.rcy_modle)
    RecyclerView rcyModle;
    @BindView(R.id.more_asset)
    BoldTextView moreAsset;
    @BindView(R.id.rcy_wallet)
    RecyclerView rcyWallet;
    Unbinder unbinder;
    AssetModleAdapter assetModleAdapter;
    AssetAdapter assetAdapter;
    @BindView(R.id.progress)
    MultistageProgress progress;
    private List<AssetModle> modleList;
    private List<Asset> assetList;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == ETH_BLANCE) {
                tvAsset.setText((String) msg.obj);
            }
            return false;
        }
    });

    @Override
    public int getLayoutId() {
        return R.layout.fragment_asset;
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

        EventBus.getDefault().register(this);
        refreashData();

        modleList = new ArrayList<>();
        modleList.add(new AssetModle(R.mipmap.sy_ziyuan, R.string.ziyuan));
        modleList.add(new AssetModle(R.mipmap.sy_toupiao, R.string.vote));
        modleList.add(new AssetModle(R.mipmap.sy_maibi, R.string.b_business));
        modleList.add(new AssetModle(R.mipmap.sy_more, R.string.more_modle));

        assetModleAdapter = new AssetModleAdapter(getContext(), modleList);
        rcyModle.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rcyModle.addItemDecoration(new GridSpacingItemDecoration(4, getResources().getDimensionPixelSize(R.dimen.dp_5), false));
        rcyModle.setAdapter(assetModleAdapter);
        refreashView(modleList, rcyModle);


        assetList = new ArrayList<>();

        assetAdapter = new AssetAdapter(getContext(), assetList);
        assetAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                startActivity(AssetWalletActivity.class);
            }
        });
        rcyWallet.setLayoutManager(new LinearLayoutManager(getContext()));
//        rcyWallet.addItemDecoration(DividerHelper.getMy10PaddingDivider(getContext()));
        rcyWallet.setAdapter(assetAdapter);
        refreashView(assetList, rcyWallet);

    }

    @Override
    public void refreashData() {
        super.refreashData();
        if (ApplicationUtil.getCurrentWallet() != null)
            getPresenter().getWalletInfo(ApplicationUtil.getCurrentWallet().getAddress());


        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what=ETH_BLANCE;
                msg.obj = WalletManager.getEthBalance(ApplicationUtil.getCurrentWallet().getAddress()).toString();
                handler.sendMessage(msg);
            }
        }).start();

        tvName.setText(ApplicationUtil.getCurrentWallet().getWalletName());
        if ((SpUtils.getObjectFromShare(getContext(), "is_make_copy")) == null || ((int) SpUtils.getObjectFromShare(getContext(), "is_make_copy")) != 1) {
            DialogUtils.showConfirmDialog(getActivity(), 0, R.string.to_wallet_safe, R.string.delete_wallet, R.string.beifen, new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(MakeCopyWalletActivity.class);
                }
            });
        }

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

    @OnClick({R.id.btn_add_coin,R.id.ly_asset, R.id.btn_transfer, R.id.btn_get_money, R.id.btn_shan_dui, R.id.btn_packet, R.id.btn_scan, R.id.btn_visiable, R.id.btn_more_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add_coin:
                startActivity(AddCoinActivity.class);
                break;
            case R.id.ly_asset:
                startActivity(AssetDetailActivity.class);
                break;
            case R.id.btn_transfer:
                startActivity(TransferActivity.class);
                break;
            case R.id.btn_get_money:
                startActivity(GetMoneyActivity.class);
                break;
            case R.id.btn_shan_dui:
                startActivity(QuickExchangeActivity.class);

                break;
            case R.id.btn_packet:
                break;
            case R.id.btn_scan:
                startActivity(ScanActivity.class);
                break;
            case R.id.btn_visiable:
                break;
            case R.id.btn_more_info:
                break;
        }
    }

    @OnClick(R.id.tv_name)
    public void onViewClicked() {


        AnyLayer.with(getContext())
                .contentView(R.layout.dialog_wallet_list)
                .backgroundColorInt(getResources().getColor(R.color.bg_dialog))
                .gravity(Gravity.BOTTOM)

                .contentAnim(new LayerManager.IAnim() {
                    @Override
                    public Animator inAnim(View content) {
                        return AnimHelper.createBottomAlphaInAnim(content);
                    }

                    @Override
                    public Animator outAnim(View content) {
                        return AnimHelper.createBottomAlphaOutAnim(content);
                    }
                })
                .bindData(new LayerManager.IDataBinder() {
                    @Override
                    public void bind(final AnyLayer anyLayer) {
                        // TODO 绑定数据
                        ImageView addWallet = anyLayer.getView(R.id.btn_aa_wallet);
                        RecyclerView recyclerView = anyLayer.getView(R.id.recyclerView2);
                        RecyclerView rcyWallet = anyLayer.getView(R.id.rcy_wallet);
                        List<CoinType> list = new ArrayList<>();
                        final TextView tv_wallet_name = anyLayer.getView(R.id.tv_wallet_name);
                        List<Wallet> mylist = new ArrayList<>();
                        final MyWalletAdapter myWalletAdapter = new MyWalletAdapter(getContext(),mylist);
                        rcyWallet.setLayoutManager(new LinearLayoutManager(getContext()));
                        rcyWallet.addItemDecoration(DividerHelper.getPaddingDivider(getContext(),R.dimen.dp_20));
                        rcyWallet.setAdapter(myWalletAdapter);
                        myWalletAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(List list, int position) {
                               ApplicationUtil.setCurrentWallet ((Wallet)list.get(position));
                               anyLayer.dismiss();
                               refreashData();
                            }
                        });


                        list.add(new CoinType(R.mipmap.qbgl_eos,R.mipmap.qbgl_eos_xz,EOS));
                        list.add(new CoinType(R.mipmap.qbgl_eth,R.mipmap.qbgl_eth_xz,ETH));
                        list.add(new CoinType(R.mipmap.qbgl_iost,R.mipmap.qbgl_iost_xz,IOST));
                        list.add(new CoinType(R.mipmap.qbgl_tron,R.mipmap.qbgl_tron_xz,Tron));
                        list.add(new CoinType(R.mipmap.qbgl_binance,R.mipmap.qbgl_binance_xz,BINANCE));
                        list.add(new CoinType(R.mipmap.qbgl_bos,R.mipmap.qbgl_bos_xz,BOS));
                        list.add(new CoinType(R.mipmap.qbgl_cosmos,R.mipmap.qbgl_cosmos_xz,COSMOS));
                        list.add(new CoinType(R.mipmap.qbgl_moac,R.mipmap.qbgl_moac_xz,MOAC));

                        CoinTypeAdapter coinTypeAdapter = new CoinTypeAdapter(getContext(),list);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(coinTypeAdapter);
                        coinTypeAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(List list, int position) {

                                tv_wallet_name.setText(((CoinType)list.get(position)).getCoinType());
                                List<Wallet> mlist = (List<Wallet>) ApplicationUtil.getWalletBuyCoinType(((CoinType) list.get(position)).getCoinType());
                                myWalletAdapter.notifyDataSetChanged(mlist);

                            }
                        });
                        coinTypeAdapter.setDefaultSelectItem(1);
                        tv_wallet_name.setText(ETH);
                        List<Wallet> mlist = (List<Wallet>) ApplicationUtil.getWalletBuyCoinType(((CoinType) list.get(1)).getCoinType());
                        myWalletAdapter.notifyDataSetChanged(mlist);
                        addWallet.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(CreateWalletActivity.class);
                            }
                        });



                    }
                })
                .show();
    }

    @Override
    public void getWalletInfoSuccess(AssetResponse assetResponse) {
        assetList = assetResponse.getGlod();
        tvAsset.setText(assetResponse.getAllnumber());
        assetAdapter.notifyDataSetChanged(assetList);
        refreashView(assetList, rcyWallet);
//        int[] colors = new int[assetResponse.getProportion().];
//        int[] colors = new int[]{assetResponse.getProportion()};
//        float[] weights;
//        progress.setColors(colors,weights);
    }

    @Override
    public void addWalletSuccess() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreashWallet(Wallet wallet) {
       refreashData();
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);

    }
}
