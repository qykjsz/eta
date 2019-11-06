package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.qingyun.mvpretrofitrx.mvp.activity.AssetDetailActivity;
import com.qingyun.mvpretrofitrx.mvp.adapter.AssetAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.Asset;
import com.qingyun.mvpretrofitrx.mvp.entity.BinnerData;
import com.qingyun.mvpretrofitrx.mvp.utils.GlideRoundTransform;
import com.senon.mvpretrofitrx.R;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WalletFragment extends BaseFragment {
    @BindView(R.id.banner2)
    XBanner banner2;
    Unbinder unbinder;
    List<BinnerData> binnerDataList;
    @BindView(R.id.rcy)
    RecyclerView rcy;
    List<Asset> assetList;
    private AssetAdapter assetAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wallet;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public boolean haveHeader() {
        return true;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
        binnerDataList = new ArrayList<>();
        binnerDataList.add(new BinnerData("", ""));
        binnerDataList.add(new BinnerData("", ""));
        initBinner(binnerDataList);

        assetList = new ArrayList<>();
        assetList.add(new Asset());
        assetList.add(new Asset());
        assetAdapter = new AssetAdapter(getContext(),assetList);
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        assetAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                startActivity(AssetDetailActivity.class);
            }
        });
        rcy.setAdapter(assetAdapter);
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
        return getResources().getString(R.string.nav_wallet);
    }


    private void initBinner(List<BinnerData> banners) {
        initBanner(banner2);
        //刷新数据之后，需要重新设置是否支持自动轮播
        banner2.setAutoPlayAble(banners.size() > 1);
//        banner2.setIsClipChildrenMode(true);
        banner2.setBannerData(R.layout.item_banner, banners);

    }

    /**
     * 初始化XBanner
     */
    private void initBanner(XBanner banner) {
        //设置广告图片点击事件
        banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
//                Intent intent = new Intent(getContext(), BannerDetailActivity.class);
//                intent.putExtra(IntentUtils.URL, ((BinnerData) model).getUrl());
//                startActivity(intent);
            }
        });
        //加载广告图片
        banner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                ImageView draweeView = (ImageView) view.findViewById(R.id.iv);
                GlideRoundTransform roundedCorners = new GlideRoundTransform(getContext(), 6);
                RequestOptions options = new RequestOptions()
                        .transform(new RoundedCorners(20))
                        .priority(Priority.HIGH);
//                Glide.with(getContext()).load(((BinnerData) model).getResid()).apply(options).into(draweeView);
                draweeView.setBackgroundResource(R.mipmap.eth_kp);
//                加载本地图片展示
//                ((ImageView)view).setImageResource(((LocalImageInfo) model).getXBannerUrl());
            }
        });
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
}
