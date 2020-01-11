package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.animation.Animator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.develop.wallet.eth.Wallet;
import com.gongwen.marqueen.SimpleMF;
import com.gongwen.marqueen.SimpleMarqueeView;
import com.gongwen.marqueen.util.OnItemClickListener;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.qingyun.mvpretrofitrx.mvp.activity.AddCoinActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.AssetDetailActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.AssetWalletActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.BusinessGetMoneyActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.ChooseBottomLsvelToCreateActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.CreateWalletActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.GetMoneyActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.InvestActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.MainActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.MakeCopyWalletActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.PlatformNoticActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.TransferActivity;
import com.qingyun.mvpretrofitrx.mvp.adapter.AssetAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.AssetModleAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.CoinTypeAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.MyWalletAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.ProportionAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.contract.WalletAssetContact;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetModle;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinType;
import com.qingyun.mvpretrofitrx.mvp.entity.GasPrice;
import com.qingyun.mvpretrofitrx.mvp.entity.Proportion;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLogResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.VersionInfo;
import com.qingyun.mvpretrofitrx.mvp.presenter.WalletAssetPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.AppUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.KeyboardChangeListener;
import com.qingyun.mvpretrofitrx.mvp.utils.KeyboardUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ScanUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.SpUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.qingyun.mvpretrofitrx.mvp.weight.GridSpacingItemDecoration;
import com.qingyun.mvpretrofitrx.mvp.weight.MultistageProgress;
import com.qingyun.mvpretrofitrx.mvp.weight.dialog.ProgressDialogUtils;
import com.senon.mvpretrofitrx.R;
import com.trello.rxlifecycle2.android.FragmentEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cc.shinichi.library.tool.utility.ui.PhoneUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
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
    CheckBox btnVisiable;
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
    @BindView(R.id.rcy)
    RecyclerView rcyWallet;
    Unbinder unbinder;
    AssetModleAdapter assetModleAdapter;
    AssetAdapter assetAdapter;
    @BindView(R.id.progress)
    MultistageProgress progress;
    @BindView(R.id.rcy_proportion)
    RecyclerView rcyProportion;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.ly)
    ConstraintLayout ly;
    @BindView(R.id.nest_)
    NestedScrollView nest;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    private List<AssetModle> modleList;
    private List<com.qingyun.mvpretrofitrx.mvp.entity.Wallet> assetList;
    private List<com.qingyun.mvpretrofitrx.mvp.entity.Wallet> searchList;

    private ProportionAdapter proportionAdapter;
    private AssetResponse assetResponse;
    private List<Proportion> proportionList;
    private float[] weight;
    private int[] colors;
    private static final String INVISIABLE_STR = "****";
    private ConstraintLayout.LayoutParams oldLp;
    private int height;
    private ConstraintLayout.LayoutParams lp2;
    private ConstraintLayout.LayoutParams lp3;

    private ConstraintLayout.LayoutParams c;
    ConstraintLayout.LayoutParams lp1;

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
        nestScrollListener();
        lp1 = (ConstraintLayout.LayoutParams) moreAsset.getLayoutParams();
        lp2 = (ConstraintLayout.LayoutParams) rcyWallet.getLayoutParams();
        lp3 = (ConstraintLayout.LayoutParams) getRefreash().getLayoutParams();

        rcyWallet.setNestedScrollingEnabled(false);
        oldLp = (ConstraintLayout.LayoutParams) etSearch.getLayoutParams();
        if (getRefreash() != null)
            getRefreash().setEnableLoadMore(false);
        EventBus.getDefault().register(this);
        refreashData();
        if (ApplicationUtil.getCurrentWallet().getStatus() == Wallet.STATUS_NO_MAKE_COPY) {
            DialogUtils.showConfirmDialog(getActivity(), 0, R.string.to_wallet_safe, R.string.cancel, R.string.beifen, new View.OnClickListener() {
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

        c = oldLp;
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                return false;
            }
        });
//        etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                Log.e(">>>>>>>>>>>hasFouse",hasFocus+"");
//                if (hasFocus) {
//                    Message msg = new Message();
//                    msg.what=1;
//                    handler.sendMessage(msg);
//                } else {
//                    Message msg = new Message();
//                    msg.what=2;
//                    handler.sendMessage(msg);
//
//                }
//            }
//        });
        getPresenter().getVersion();
        modleList = new ArrayList<>();
        modleList.add(new AssetModle(R.mipmap.sy_ziyuan, R.string.ziyuan));
        modleList.add(new AssetModle(R.mipmap.sy_cz_icon, R.string.inverst_center));
        modleList.add(new AssetModle(R.mipmap.sy_maibi, R.string.b_business));
        modleList.add(new AssetModle(R.mipmap.sy_more, R.string.more_modle));

        assetModleAdapter = new AssetModleAdapter(getContext(), modleList);
        rcyModle.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rcyModle.addItemDecoration(new GridSpacingItemDecoration(4, getResources().getDimensionPixelSize(R.dimen.dp_12), false));
        rcyModle.setAdapter(assetModleAdapter);
        assetModleAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                if (position == 1) {
                    startActivity(InvestActivity.class);
                }
            }
        });
        refreashView(modleList, rcyModle);
        assetList = new ArrayList<>();


        assetAdapter = new AssetAdapter(getContext(), assetList);
        assetAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(IntentUtils.ASSET, (com.qingyun.mvpretrofitrx.mvp.entity.Wallet) assetList.get(position));
                startActivity(AssetWalletActivity.class, bundle);
            }
        });
        rcyWallet.setLayoutManager(new LinearLayoutManager(getContext()));
//        rcyWallet.addItemDecoration(DividerHelper.getMy10PaddingDivider(getContext()));
        rcyWallet.setAdapter(assetAdapter);
        refreashView(assetList, rcyWallet);
        proportionList = new ArrayList<>();
        proportionAdapter = new ProportionAdapter(getContext(), proportionList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        //调整RecyclerView的排列方向
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcyProportion.setLayoutManager(linearLayoutManager);
        rcyProportion.setAdapter(proportionAdapter);
        btnVisiable.setChecked(SpUtils.getBoolenToShare(getContext(), "is_asset_visiable"));
        btnVisiable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SpUtils.setBoolenToShare(getContext(), isChecked, "is_asset_visiable");
                refreashAsset(isChecked);
            }
        });
    }

    private void nestScrollListener() {
        nest.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                //Log.e(TAG, "onScrollChange: " + scrollX +"---" + scrollY + "----" +oldScrollX + "---" + oldScrollY );
                //监听滚动状态

                if (scrollY > oldScrollY) {//向下滚动

                }
                if (scrollY < oldScrollY) {//向上滚动

                }

                if (scrollY == 0) {// 滚动到顶

                }
                oldLp = (ConstraintLayout.LayoutParams) etSearch.getLayoutParams();
                c = oldLp;
                // 滚动到底
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
//                    c.width = 0;
//                    etSearch.setLayoutParams(c);
                }


//                Log.e(TAG, "onScrollChange: ------------" + scrollY +"------"+ tvscrollthree.getTop() );
//                //判断某个控件是否滚到顶部
//                if (scrollY == tvscrollthree.getTop()){
//                    Log.i(TAG, "onScrollChange: ------top-------" );
//                }
//
//                Log.e(TAG, "onScrollChange: bottmo" + scrollY +"-----"+ (tvscrollthree.getTop() + tvscrollthree.getHeight()) );


            }
        });

    }


    public void startActivity(Class c) {
        Intent intent = new Intent(getContext(), c);
        startActivity(intent);
    }

    public void startActivity(Class c, Bundle bundle) {
        Intent intent = new Intent(getContext(), c);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void refresh() {
        super.refresh();
        if (ApplicationUtil.getCurrentWallet() != null)
            getPresenter().getWalletInfo(ApplicationUtil.getCurrentWallet().getAddress());
    }

    @Override
    public void refreashData() {
        super.refreashData();
        if (ApplicationUtil.getCurrentWallet() != null)
            getPresenter().getWalletInfo(ApplicationUtil.getCurrentWallet().getAddress());

        tvName.setText(ApplicationUtil.getCurrentWallet().getWalletName());


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

    @OnClick({R.id.btn_add_coin, R.id.ly_asset, R.id.btn_transfer, R.id.btn_get_money, R.id.btn_shan_dui, R.id.btn_packet, R.id.btn_scan, R.id.btn_more_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add_coin:
                startActivity(AddCoinActivity.class);
                break;
            case R.id.ly_asset:
                if (assetResponse == null) {
                    ToastUtil.showShortToast(R.string.requst_err);
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable(IntentUtils.ASSET_RESPONSE, assetResponse);
                startActivity(AssetDetailActivity.class, bundle);
                break;
            case R.id.btn_transfer:
                startActivity(TransferActivity.class);
                break;
            case R.id.btn_get_money:
                startActivity(GetMoneyActivity.class);
                break;
            case R.id.btn_shan_dui:
                startActivity(BusinessGetMoneyActivity.class);
//                ToastUtil.showShortToast(R.string.not_open);
//                startActivity(QuickExchangeActivity.class);
                break;
            case R.id.btn_packet:
//                ToastUtil.showShortToast(R.string.not_open);
                startActivity(ChooseBottomLsvelToCreateActivity.class);
                break;
            case R.id.btn_scan:


                ScanUtil.start(getActivity());
//                startActivity(CaptureActivity.class);
                break;
            case R.id.btn_more_info:

                startActivity(PlatformNoticActivity.class);
                break;
        }
    }

    @OnClick(R.id.tv_name)
    public void onViewClicked() {
        if (getContext()==null) return;
        KeyboardUtils.hideKeyboard(getActivity());

        AnyLayer.with(getContext())
                .contentView(R.layout.dialog_wallet_list)
                .backgroundColorInt(getResources().getColor(R.color.bg_dialog))
                .gravity(Gravity.BOTTOM)
                .onClickToDismiss(R.id.imageView14)
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
                        final ImageView addWallet = anyLayer.getView(R.id.btn_aa_wallet);
                        RecyclerView recyclerView = anyLayer.getView(R.id.recyclerView2);
                        RecyclerView rcyWallet = anyLayer.getView(R.id.rcy_wallet);
                        List<CoinType> list = new ArrayList<>();
                        final TextView tv_wallet_name = anyLayer.getView(R.id.tv_wallet_name);
                        List<Wallet> mylist = new ArrayList<>();
                        final MyWalletAdapter myWalletAdapter = new MyWalletAdapter(getContext(), mylist);
                        rcyWallet.setLayoutManager(new LinearLayoutManager(getContext()));
                        rcyWallet.addItemDecoration(DividerHelper.getPaddingDivider(getContext(), R.dimen.dp_20));
                        rcyWallet.setAdapter(myWalletAdapter);
                        myWalletAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(List list, int position) {
                                ApplicationUtil.setCurrentWallet((Wallet) list.get(position));
                                EventBus.getDefault().post((Wallet) list.get(position));
                                if (ApplicationUtil.getCurrentWallet().getStatus() == Wallet.STATUS_NO_MAKE_COPY) {
                                    DialogUtils.showConfirmDialog(getActivity(), 0, R.string.to_wallet_safe, R.string.cancel, R.string.beifen, new View.OnClickListener() {
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

                                anyLayer.dismiss();
                                refreashData();
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
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(coinTypeAdapter);
                        coinTypeAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(List list, int position) {
                                if (position == 1) {
                                    addWallet.setVisibility(View.VISIBLE);
                                } else {
                                    addWallet.setVisibility(View.GONE);
                                }
                                tv_wallet_name.setText(((CoinType) list.get(position)).getCoinType());
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


    private void refreashAsset(boolean visiable) {
        if (visiable) {
            if (assetResponse != null)
                tvAsset.setText(assetResponse.getAllnumber());
            tvIncomeToday.setText(assetResponse.getToday().startsWith("-") ? assetResponse.getToday() : "+" + assetResponse.getToday());
        } else {
            tvIncomeToday.setText(INVISIABLE_STR);
            tvAsset.setText(INVISIABLE_STR);

        }
    }

    @Override
    public void getWalletInfoSuccess(AssetResponse assetResponse) {
        this.assetResponse = assetResponse;
        Log.e(">>>>>>>>>>",moreAsset.getMeasuredHeight()+"");
        Log.e(">>>>>>>>>>",lp1.topMargin+"");
        Log.e(">>>>>>>>>>",lp2.topMargin+"");
        Log.e(">>>>>>>>>>",lp3.topMargin+"");
        Log.e(">>>>>>>>>>",lp2.bottomMargin+"");

        height = constraintLayout.getHeight()-moreAsset.getMeasuredHeight()-lp1.topMargin-lp2.topMargin-lp3.topMargin-lp2.bottomMargin;
//        height = PhoneUtil.getPhoneHei(getActivity()) - getResources().getDimensionPixelSize(R.dimen.dp_56) - getResources().getDimensionPixelSize(R.dimen.dp_172) - getResources().getDimensionPixelSize(R.dimen.dp_25) - lp1.height;
        lp2.height = height;
        rcyWallet.setLayoutParams(lp2);



        assetList = assetResponse.getGlod();
        assetAdapter.notifyDataSetChanged(assetList);
        btnVisiable.setChecked(SpUtils.getBoolenToShare(getContext(), "is_asset_visiable"));
        refreashAsset(btnVisiable.isChecked());
//        refreashView(assetList, rcyWallet);

        SimpleMF<String> marqueeFactory = new SimpleMF(getContext());
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < assetResponse.getNews().size(); i++) {
            if (!TextUtils.isEmpty(assetResponse.getNews().get(i).getName()))
                newList.add(assetResponse.getNews().get(i).getName());
        }
        if (newList.size() == 1) {
            newList.add(newList.get(0));
        }
        marqueeFactory.setData(newList);
        mvInfoList.setMarqueeFactory(marqueeFactory);
        mvInfoList.startFlipping();
        mvInfoList.setOnItemClickListener(new OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(TextView mView, String mData, int mPosition) {
//                Intent intent = new Intent(getContext(), InformActivity.class);
//                startActivity(intent);

                startActivity(PlatformNoticActivity.class);
            }
        });

        proportionList = assetResponse.getProportion();
        proportionAdapter.notifyDataSetChanged(proportionList);


        colors = new int[assetResponse.getProportion().size()];
        for (int i = 0; i < colors.length; i++) {
            int color = getResources().getColor(R.color.green);
            switch (i) {
                case 0:
                    color = getResources().getColor(R.color.color_FFFFFF);
                    break;
                case 1:
                    color = getResources().getColor(R.color.color_93AEFC);

                    break;
                case 2:
                    color = getResources().getColor(R.color.color_FFB632);

                    break;
                case 3:
                    color = getResources().getColor(R.color.color_EA566D);

                    break;
            }
            colors[i] = color;
        }
        weight = new float[assetResponse.getProportion().size()];
        boolean all0 = true;
        for (int i = 0; i < assetResponse.getProportion().size(); i++) {
            weight[i] = Float.parseFloat(assetResponse.getProportion().get(i).getBili());
            if (weight[i] != 0) all0 = false;
        }

//        weight[0] = 50;
//        weight[1] = 20;
//        weight[2] = 10;
//        weight[3] = 10;
//        weight[4] = 10;

//        if (all0){
//            colors[0] = getResources().getColor(R.color.color_F8F8FF);
//
//        }
        progress.setProgress(0);
        progress.setColors(colors, weight);
        progress.invalidate();
//        int[] colors = new int[assetResponse.getProportion().];
//        int[] colors = new int[]{assetResponse.getProportion()};
//        float[] weights;
//        progress.setColors(colors,weights);
        addSearch();
    }

    @Override
    public void addWalletSuccess() {

    }

    @Override
    public void getLogSuccess(TransferLogResponse transferLogResponse) {


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
    public void getVersionSuccess(final VersionInfo versionInfo) {
        ApplicationUtil.mVersionInfo = versionInfo;
        if (!ApplicationUtil.isApkInDebug(ApplicationUtil.getContext()))
            if (!versionInfo.getName().equals(AppUtils.getAppVersionName(getContext()))) {
                AnyLayer anyLayer = AnyLayer.with(getContext())
                        .contentView(R.layout.dialog_new_version)
                        .backgroundColorInt(getResources().getColor(R.color.bg_dialog))
                        .gravity(Gravity.CENTER)
                        .cancelableOnTouchOutside(false)
                        .cancelableOnClickKeyBack(false)
                        .bindData(new LayerManager.IDataBinder() {
                            @Override
                            public void bind(final AnyLayer anyLayer) {

                                TextView updata = anyLayer.getView(R.id.btn_updata);
                                TextView tvContent = anyLayer.getView(R.id.tv_content);
                                TextView tvVersion = anyLayer.getView(R.id.tv_version);
                                ImageView cancel = anyLayer.getView(R.id.btn_close);
                                updata.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (TextUtils.isEmpty(versionInfo.getDownload())) return;
                                        Intent intent = new Intent();
                                        intent.setData(Uri.parse(versionInfo.getDownload()));//Url 就是你要打开的网址
                                        intent.setAction(Intent.ACTION_VIEW);
                                        startActivity(intent); //启动浏览器
                                    }
                                });
                                cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ToastUtil.showShortToast(R.string.need_updata);
                                    }
                                });
                                tvVersion.setText(versionInfo.getName());
                                if (!TextUtils.isEmpty(versionInfo.getRemark()))
                                    tvContent.setText(versionInfo.getRemark().replace("\\n", "\n"));

                            }
                        });
                anyLayer.show();

            }

    }

    @Override
    public void checkCanTransferSuccess() {

    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            if (msg.what == 1) {
                c.width = 0;
                etSearch.setLayoutParams(c);
                nest.fullScroll(NestedScrollView.FOCUS_DOWN);
//                rcyWallet.clearFocus();
//                etSearch.findFocus();
            } else if (msg.what == 2) {
                c.width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                etSearch.setLayoutParams(c);
//                etSearch.clearFocus();
//                rcyWallet.clearFocus();
            }
            return true;
        }
    });


    private void addSearch() {

//        final View mRoot = getActivity().getWindow().getDecorView();
//        mRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            int oldRootInvisibleHeight=-1;
//            boolean isVisiable = false;
//            @Override
//            public void onGlobalLayout() {
//
//                Rect rect = new Rect();
//                mRoot.getWindowVisibleDisplayFrame(rect);
//               int rootInvisibleHeight = mRoot.getRootView().getHeight()-rect.bottom;
//
//               if (isVisiable&&rootInvisibleHeight==0||oldRootInvisibleHeight!=rootInvisibleHeight){
//                   etSearch.clearFocus();
//                   isVisiable=false;
//               }else if (!isVisiable&&rootInvisibleHeight!=0||oldRootInvisibleHeight!=rootInvisibleHeight){
//                   isVisiable = true;
//                   etSearch.findFocus();
//               }
//                oldRootInvisibleHeight = rootInvisibleHeight;
//
//                Log.e("rootInvisibleHeight>>",rootInvisibleHeight+"");
//            }
//        });

        new KeyboardChangeListener(getActivity()).setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                Log.e(">>>>>>>>>>>", isShow + "");

                if (MainActivity.index==0){
                    if (isShow) {
                        Message msg = new Message();
                        msg.what = 1;
                        handler.sendMessage(msg);
                    } else {
                        Message msg = new Message();
                        msg.what = 2;
                        handler.sendMessage(msg);

                    }
                }

            }
        });


        RxTextView.textChanges(etSearch)//当EditText发生改变
                //每500毫秒发射一次
                //仅在过了一段指定的时间还没发射数据时才发射一个数据
                //如果原始Observable在这个新生成的Observable终止之前发射了另一个数据， debounce 会抑制(suppress)这个数据项。
                .debounce(200, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())//内容监听操作需要在主线程操作
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(CharSequence charSequence) throws Exception {
                        return charSequence.length() >= 0;
                    }
                })
                .observeOn(Schedulers.io())
                .switchMap(new Function<CharSequence, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(CharSequence charSequence) throws Exception {
                        Observable<String> just = Observable.just(charSequence.toString());
                        return just;

                    }
                })

//
                .retry()//凡是请求出错就重试（例如超时、数据解析异常等），直到正确为止。（如果不retry的话就会调用onError。onError会导致整个订阅链条死掉，无法触发下一次了）
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        if (TextUtils.isEmpty(s)) {
                            assetAdapter.notifyDataSetChanged(assetList);
//                            refreashView(assetList, rcyWallet);
                            rcyWallet.setVisibility(View.VISIBLE);
                            return;
                        }
                        searchList = new ArrayList<>();
                        for (int i = 0; i < assetList.size(); i++) {
                            if (assetList.get(i).getName().toLowerCase().contains(s.toLowerCase())) {
                                searchList.add(assetList.get(i));
                            }
                        }
//                        KeyboardUtils.hideKeyboard(etSearch);
//                        etSearch.clearFocus();


                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                assetAdapter.notifyDataSetChanged(searchList);
//                                refreashView(searchList, rcyWallet);
                                rcyWallet.setVisibility(View.VISIBLE);


                            }
                        }, 300);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreashWallet(Wallet wallet) {
        ProgressDialogUtils.getInstances().cancel();
        refreashData();
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);

    }
}
