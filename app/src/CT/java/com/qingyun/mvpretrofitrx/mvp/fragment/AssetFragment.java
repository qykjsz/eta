package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.animation.Animator;
import android.content.res.AssetManager;
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

import com.develop.wallet.eth.WalletManager;
import com.gongwen.marqueen.SimpleMarqueeView;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.qingyun.mvpretrofitrx.mvp.activity.AssetDetailActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.AssetWalletActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.CreateWalletActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.GetMoneyActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.MakeCopyWalletActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.QuickExchangeActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.TransferActivity;
import com.qingyun.mvpretrofitrx.mvp.adapter.AssetAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.AssetModleAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.Asset;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetModle;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.SpUtils;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.qingyun.mvpretrofitrx.mvp.weight.GridSpacingItemDecoration;
import com.senon.mvpretrofitrx.R;

import org.web3j.abi.datatypes.Int;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import per.goweii.anylayer.AnimHelper;
import per.goweii.anylayer.AnyLayer;
import per.goweii.anylayer.LayerManager;

public class AssetFragment extends BaseFragment {
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
    private List<AssetModle> modleList;
    private List<Asset> assetList;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what==ETH_BLANCE){
                tvAsset.setText((String)msg.obj);
            }
            return false;
        }
    });
    @Override
    public int getLayoutId() {
        return R.layout.fragment_asset;
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
        if (((int)SpUtils.getObjectFromShare(getContext(),"is_make_copy"))!=1){
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
        assetList.add(new Asset());
        assetList.add(new Asset());
        assetList.add(new Asset());
        assetList.add(new Asset());

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

    @OnClick({R.id.ly_asset, R.id.btn_transfer, R.id.btn_get_money, R.id.btn_shan_dui, R.id.btn_packet, R.id.btn_scan, R.id.btn_visiable, R.id.btn_more_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
                    public void bind(AnyLayer anyLayer) {
                        // TODO 绑定数据
                       ImageView addWallet =  anyLayer.getView(R.id.btn_aa_wallet);
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
}
