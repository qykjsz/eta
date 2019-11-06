package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingyun.mvpretrofitrx.mvp.activity.BusinessActivity;
import com.qingyun.mvpretrofitrx.mvp.adapter.BusinessMineAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;

import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessSellOrBuy;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessSellOrBuyResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.ObservableTransformer;
import per.goweii.anylayer.AnyLayer;
import per.goweii.anylayer.LayerManager;

public class BusinessMineFragment extends BaseFragment {
    @BindView(R.id.m_rcy)
    RecyclerView rcy;
    Unbinder unbinder;
    private BusinessMineAdapter adapter;
    private List<BusinessSellOrBuy> list;
    private boolean isBuy= false;
    int index;
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
        adapter = new BusinessMineAdapter(getContext(),list,true);
        adapter.setCancelOrderListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final List list, final int position) {

                AnyLayer.with(getContext())
                        .contentView(R.layout.dialog_cancle_order)
                        .backgroundColorInt(getResources().getColor(R.color.bg_dialog))
                        .gravity(Gravity.CENTER)
                        .onClickToDismiss(R.id.btn_sure, new LayerManager.OnLayerClickListener() {
                            @Override
                            public void onClick(AnyLayer anyLayer, View v) {
                                index = position;

                            }
                        })
                        .onClickToDismiss(R.id.btn_cancel)
                        .show();

            }
        });
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
        super.refresh();
        ((BusinessActivity)getContext()).refresh(page,per_page);
    }

    @Override
    protected void loadMore() {
        super.loadMore();
        ((BusinessActivity)getContext()).loadMore(page,per_page);

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

    public void notifyDataSetChange(boolean isLoadMore, BusinessSellOrBuyResponse businessSellOrBuyResponse, boolean isBuy){
        this.isBuy =isBuy;
        if (isLoadMore){
            list.addAll(businessSellOrBuyResponse.getData());
        }else {
            list = businessSellOrBuyResponse.getData();
        }
        refreashView(list,rcy);
        adapter.notifyDataSetChanged(list);
    }



}
