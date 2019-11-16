package com.qingyun.mvpretrofitrx.mvp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.senon.mvpretrofitrx.R;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ezy.ui.layout.LoadingLayout;

/**
 * 父类->基类->动态指定类型->泛型设计（通过泛型指定动态类型->由子类指定，父类只需要规定范围即可）
 */
public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends RxFragment {

    //引用V层和P层
    private P presenter;
    private V view;
    public Context mContext;
    private Unbinder unbinder;
    private View mView;
    private ImageView ivBack;
    private View lyHeader;
    private TextView tvTitleRight;
    private TextView tvTitleLeft;
    private TextView tvTitle;
    private ImageView ivTitleRight;
    private LinearLayout lyBack;
    private View.OnClickListener tvTitleRightClick;
    private View nodata;
    public int page = 1;
    public int per_page = 10;
    public SmartRefreshLayout refreash;
    private LoadingLayout freash_loading;
    private View lyHeader1;
    protected int currentPage;
    public P getPresenter() {
        return presenter;
    }

    public boolean isLoadMore = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_base, container, false);

        ivBack = (ImageView) mView.findViewById(R.id.iv_back);
        lyHeader = mView.findViewById(R.id.ly_header);
        lyHeader1 = mView.findViewById(R.id.ly_header1);
        tvTitleRight = (TextView) mView.findViewById(R.id.tv_title_right);
        tvTitleLeft = (TextView) mView.findViewById(R.id.tv_title_left);
        tvTitle = (TextView) mView.findViewById(R.id.tv_title);
        ivTitleRight = (ImageView) mView.findViewById(R.id.iv_title_right);
        lyBack = (LinearLayout) mView.findViewById(R.id.ly_back);
        lyBack.setVisibility(View.GONE);
        setHeaderData();
        LinearLayout mLyContent = mView.findViewById(R.id.ly_content);
        mLyContent.addView(LayoutInflater.from(getContext()).inflate(getLayoutId(), container, false));
        unbinder = ButterKnife.bind(this, mView);
        mContext = getActivity();
        if (presenter == null) {
            presenter = createPresenter();
        }
        if (this.view == null) {
            this.view = createView();
        }
        if (presenter != null && mView != null) {
            presenter.attachView(this.view);
        }
        refreash = mView.findViewById(R.id.refreash);
        nodata = mView.findViewById(R.id.nodata);
        freash_loading = mView.findViewById(R.id.refreash_loading);
        initRefreshLayout(refreash);

        requestList();
        init();
        return mView;
    }


    public SmartRefreshLayout getRefreash() {
        return refreash;
    }

    public void setTvTitle(String title) {

        tvTitle.setText(title);
    }

    public void refreashView(List list, RecyclerView recyclerView) {
        if (list.size() == 0) {
            if (nodata != null)
                nodata.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

            if (freash_loading != null)
                freash_loading.showEmpty();
        } else {
            if (nodata != null)
                nodata.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            if (freash_loading != null)
                freash_loading.showContent();
        }
    }

    protected void initRefreshLayout(SmartRefreshLayout refreshLayout) {
        if (refreshLayout == null)
            return;
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();//关闭
                isLoadMore = false;
                refresh();
            }
        });
        //加载更多
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();//关闭\
                isLoadMore = true;

                loadMore();
            }
        });
    }


    public View getLyHeader() {
        return lyHeader1;
    }

    public boolean haveHeader() {
        return false;
    }

    private void setHeaderData() {

        if (!haveHeader()) {
            lyHeader.setVisibility(View.GONE);
        } else {
            tvTitleRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tvTitleRightClick != null)
                        tvTitleRightClick.onClick(view);
                }
            });
            tvTitle.setText(TextUtils.isEmpty(getTitleText()) ? "" : getTitleText());
            if (!TextUtils.isEmpty(tvTitle.getText().toString()))
                tvTitle.setVisibility(View.VISIBLE);
            tvTitleLeft.setText(TextUtils.isEmpty(getTitleLeftText()) ? "" : getTitleLeftText());
            tvTitleRight.setText(TextUtils.isEmpty(getTitleRightText()) ? "" : getTitleRightText());
            ivTitleRight.setVisibility(View.VISIBLE);
            ivTitleRight.setImageResource(getTitleRightImgId());
            ivTitleRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tvTitleRightClick != null)
                        tvTitleRightClick.onClick(v);
                }
            });
        }
    }

    //由子类指定具体类型
    public abstract int getLayoutId();

    public abstract P createPresenter();

    public abstract V createView();

    public abstract void init();

    public void setIvTitleRight(int resid, View.OnClickListener onClickListener) {
        ivTitleRight.setImageResource(resid);
        ivTitleRight.setOnClickListener(onClickListener);
    }

    public void setIvBack(int resid, View.OnClickListener onClickListener) {
        lyBack.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setImageResource(resid);
        lyBack.setOnClickListener(onClickListener);
    }

    public View getCurrentView() {
        return mView;
    }




    public void startActivity(Class c,Bundle bundle){
        Intent intent = new Intent(getContext(),c);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);

        if (presenter != null) {
            presenter.detachView();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public int getTitleRightImgId() {
        return 0;
    }

    public void setTitleRightClick(View.OnClickListener tvTitleRightClick) {
        this.tvTitleRightClick = tvTitleRightClick;
    }


    protected abstract String getTitleRightText();

    protected abstract String getTitleLeftText();

    protected abstract String getTitleText();

    protected  void refresh(){
        page = 1;
        requestList();

    }

    protected void requestList() {

    }

    protected  void loadMore(){
        page++;
        requestList();
    };

    public void setTitleLeftText(String s) {
        tvTitleLeft.setText(s);
    }

    public void refreashPage(int page, int per_page, boolean isLoadMore) {
        this.page = page;
        this.per_page = per_page;
        this.isLoadMore = isLoadMore;
    }

    public void startActivity(Class c){
        Intent intent = new Intent(getContext(),c);
        startActivity(intent);
    }

    public void refreashData() {
    }
}