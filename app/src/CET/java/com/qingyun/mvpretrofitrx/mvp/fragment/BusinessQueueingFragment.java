package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingyun.mvpretrofitrx.mvp.adapter.BusinessAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessSellOrBuy;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessSellOrBuyResponse;
import com.senon.mvpretrofitrx.R;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BusinessQueueingFragment extends BaseFragment{
    @BindView(R.id.m_rcy)
    RecyclerView rcy;
    Unbinder unbinder;
    private BusinessAdapter adapter;
    private List<BusinessSellOrBuy> list;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_business;
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
        adapter = new BusinessAdapter(getContext(),list,false);
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext())
                .color(getResources().getColor(R.color.color_line))
                .sizeResId(R.dimen.divider)
                .marginResId(R.dimen.dp_padding_x_min, R.dimen.dp_padding_x_min)
                .build());
        rcy.setAdapter(adapter);
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
    protected void refresh() {

    }

    @Override
    protected void loadMore() {

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

    public void notifyDataSetChange(boolean isLoadMore, BusinessSellOrBuyResponse businessSellOrBuyResponse, String string){
        if (isLoadMore){
            list.addAll(businessSellOrBuyResponse.getData());
        }else {
            list = businessSellOrBuyResponse.getData();
        }
        refreashView(list,rcy);
        adapter.notifyDataSetChanged(list);
        adapter.setType(string);
    }



}
