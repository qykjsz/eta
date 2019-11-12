package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import com.qingyun.mvpretrofitrx.mvp.activity.TheArticleDetailsActivity;
import com.qingyun.mvpretrofitrx.mvp.adapter.TimeAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.contract.TimeContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Time;
import com.qingyun.mvpretrofitrx.mvp.presenter.Timepresenter;
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

public class TiteFragment extends BaseFragment<TimeContact.View, TimeContact.Presenter> implements TimeContact.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.freash_loading)
    LoadingLayout freashLoading;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    Unbinder unbinder;
    List<Time.NewsBean> times;
    TimeAdapter timeAdapter;
    Time.NewsBean newsBean;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tite;
    }

    @Override
    public TimeContact.Presenter createPresenter() {
        return new Timepresenter(getContext());
    }

    @Override
    public TimeContact.View createView() {
        return this;
    }

    @Override
    public void init() {
        getPresenter().getContacFlashtList(page - 1 + "");
        times = new ArrayList<>();
        timeAdapter = new TimeAdapter(getContext(), times);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 50, false));
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(timeAdapter);
        refreashView(times, recyclerView);
        initRefreshLayout(srl);
        timeAdapter.setAddListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                 newsBean= (Time.NewsBean) list.get(position);
              Intent intent=new Intent();
              intent.setClass(getContext(), TheArticleDetailsActivity.class);
              intent.putExtra("newsBean", newsBean);
              startActivity(intent);
            }
        });
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
    public void getContactListFlash(Time contactList) {
        Log.i("exp", "=====times=====" + times.size());

        if (contactList.getNews() != null) {
            if (isLoadMore) {

                times.addAll(contactList.getNews());
            } else {
                times = contactList.getNews();
            }
            refreashView(times, recyclerView);
            timeAdapter.notifyDataSetChanged(times);
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }
}
