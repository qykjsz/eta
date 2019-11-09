package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qingyun.mvpretrofitrx.mvp.activity.AssetWalletActivity;
import com.qingyun.mvpretrofitrx.mvp.adapter.AssetAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.AssetModleAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetModle;
import com.qingyun.mvpretrofitrx.mvp.weight.GridSpacingItemDecoration;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FindFragment extends BaseFragment {
    @BindView(R.id.serachview)
    SearchView serachview;
    Unbinder unbinder;
    @BindView(R.id.btn_scan)
    ImageView btnScan;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.rcy_modle1)
    RecyclerView rcyModle1;
    @BindView(R.id.rcy_modle)
    RecyclerView rcyModle;
    private List<AssetModle> modleList;
    AssetModleAdapter assetModleAdapter;

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
    public int getLayoutId() {
        return R.layout.fragment_find;
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
        SearchView.SearchAutoComplete textView = (SearchView.SearchAutoComplete) serachview.findViewById(R.id.search_src_text);
        textView.setTextColor(getResources().getColor(R.color.material_blue_grey_90));
        textView.setHintTextColor(getResources().getColor(R.color.color_FFFFFF));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);

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

        assetModleAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {

            }
        });


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

}
