package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingyun.mvpretrofitrx.mvp.adapter.FlashAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.contract.FlashContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Flash;
import com.qingyun.mvpretrofitrx.mvp.entity.News;
import com.qingyun.mvpretrofitrx.mvp.presenter.Flashpresenter;
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

public class FlashFragment extends BaseFragment<FlashContact.View, FlashContact.Presenter> implements FlashContact.View {

    Unbinder unbinder;
    FlashAdapter flashAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    List<News> list;
    @BindView(R.id.freash_loading)
    LoadingLayout freashLoading;


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
        return R.layout.fragment_flash;
    }

    @Override
    public FlashContact.Presenter createPresenter() {
        return new Flashpresenter(getContext());
    }

    @Override
    public FlashContact.View createView() {
        return this;
    }

    @Override
    public void init() {
        getPresenter().getContacFlashtList(page - 1 + "");
        list = new ArrayList<>();
        flashAdapter = new FlashAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 50, false));
        //
        recyclerView.setAdapter(flashAdapter);
        refreashView(list, recyclerView);
        initRefreshLayout(srl);

    }

    @Override
    protected void loadMore() {
        super.loadMore();
        getPresenter().getContacFlashtList(page + "");

    }

    @Override
    protected void refresh() {
        super.refresh();
        getPresenter().getContacFlashtList(page + "");

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
    public void getContactListFlash(Flash contactList) {
        Log.i("exp", "==========" + list.size());
        if (isLoadMore) {
            list.addAll(contactList.getNews());
        } else {
            list = contactList.getNews();
        }
        refreashView(list, recyclerView);
        flashAdapter.notifyDataSetChanged(list);

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }
}
