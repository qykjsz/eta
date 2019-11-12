package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingyun.mvpretrofitrx.mvp.adapter.MakieAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.contract.MakieContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Quotation;
import com.qingyun.mvpretrofitrx.mvp.entity.Quotation1;
import com.qingyun.mvpretrofitrx.mvp.presenter.Makiepresenter;
import com.qingyun.mvpretrofitrx.mvp.weight.GridSpacingItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.senon.mvpretrofitrx.R;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ezy.ui.layout.LoadingLayout;
import io.reactivex.ObservableTransformer;

public class MakieFragment extends BaseFragment<MakieContact.View, MakieContact.Presenter> implements MakieContact.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private List<Quotation> quotations;
    MakieAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_makie;
    }

    @Override
    public MakieContact.Presenter createPresenter() {
        return new Makiepresenter(getContext());
    }

    @Override
    public MakieContact.View createView() {
        return this;
    }

    @Override
    public void init() {
        getPresenter().getContacMakieList("padg");
        quotations = new ArrayList<>();
        adapter = new MakieAdapter(getContext(), quotations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 50, false));
        //   //添加Android自带的分割线
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        refreashView(quotations, recyclerView);
        // initRefreshLayout(srl);

    }

    @Override
    protected void loadMore() {
        super.loadMore();
        getPresenter().getContacMakieList("padg");

    }

    @Override
    protected void refresh() {
        super.refresh();
        getPresenter().getContacMakieList("padg");

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

    @Override
    public void getMakieListFlash(List<Quotation> contactList) {
        Log.i("exp", "==========" + contactList.size());
        if (isLoadMore) {
            quotations.addAll(contactList);
        } else {
            quotations = contactList;
        }
        refreashView(quotations, recyclerView);
        adapter.notifyDataSetChanged(quotations);


    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }
}
