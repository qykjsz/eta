package com.qingyun.mvpretrofitrx.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.utils.AppUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.LocalManageUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.senon.mvpretrofitrx.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.ButterKnife;
import ezy.ui.layout.LoadingLayout;

import static com.qingyun.mvpretrofitrx.mvp.utils.StatusBarUtil.setRootViewFitsSystemWindows;

/**
 * 父类->基类->动态指定类型->泛型设计（通过泛型指定动态类型->由子类指定，父类只需要规定范围即可）
 */
public abstract class BaseActivity<V extends BaseView,P extends BasePresenter<V>> extends RxAppCompatActivity {

    //引用V层和P层
    private P presenter;
    private V view;
    private ImageView ivBack;
    private TextView tvTitleLeft;
    private TextView tvTitle;
    private FrameLayout flyContent;
    private View lyHeader;
    private TextView tvTitleRight;
    private View.OnClickListener tvTitleRightClick;
    private ImageView ivTitleRight;
    private LinearLayout lyBack;
    private ImageView nodata;
    public boolean isLoadMore = false;
    public int page = 0;
    public int per_page = 10;
    private SmartRefreshLayout refreash;
    private LoadingLayout freash_loading;

    public P getPresenter(){
        return presenter;
    }


    public ImageView getIvTitleRight() {
        return ivTitleRight;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ApplicationUtil.activityList.add(this);
        ApplicationUtil.setCurrentContext(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_base,null);
        ivBack = (ImageView) rootView.findViewById(R.id.iv_back);
        lyHeader = rootView.findViewById(R.id.ly_header);
        tvTitleRight = (TextView) rootView.findViewById(R.id.tv_title_right);
        tvTitleLeft = (TextView) rootView.findViewById(R.id.tv_title_left);
        tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
        flyContent = (FrameLayout) rootView.findViewById(R.id.fly_content);
        ivTitleRight = (ImageView) rootView.findViewById(R.id.iv_title_right);
        lyBack = (LinearLayout) rootView.findViewById(R.id.ly_back);
        View content = LayoutInflater.from(this).inflate(getLayoutId(),flyContent,false);
        flyContent.addView(content);
        lyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setHeaderData();
        setContentView(rootView);
        ButterKnife.bind(this);
        hideBar();
        refreash = rootView.findViewById(R.id.refreash);
        nodata = rootView.findViewById(R.id.nodata);
        freash_loading = rootView.findViewById(R.id.refreash_loading);
        initRefreshLayout(refreash);
        requestList();
        init();
    }

    protected void requestList() {

    }


    public Context getContext(){
        return this;
    }

    public Activity getActivity(){
        return this;
    }


    public void refreashView(List list, RecyclerView recyclerView){
        if (list.size() == 0){
            if (nodata!=null)
                nodata.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            if (freash_loading!=null)
                freash_loading.showEmpty();
        }else {
            if (nodata!=null)
                nodata.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            if (freash_loading!=null)
                freash_loading.showContent();
        }
//        if (list.size()==0){
//            ImageView imageView = new ImageView(BaseActivity.this);
//            imageView.setImageResource(R.drawable.otc_kby);
////            imageView.setTop(refreash.getMeasuredHeight()/2);
////            imageView.setLeft(refreash.getMeasuredWidth()/2);
//            refreash.addView(imageView);
//        }

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


    protected  void refresh(){
        page = 1;
        requestList();

    }

    protected  void loadMore(){
        page++;
        requestList();
    };

    private void setHeaderData() {

        if (!haveHeader()){
            lyHeader.setVisibility(View.GONE);
        }else {
            tvTitleRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tvTitleRightClick!=null)
                        tvTitleRightClick.onClick(view);
                }
            });
            tvTitle.setText(TextUtils.isEmpty(getTitleText())?"":getTitleText());
            if (!TextUtils.isEmpty(tvTitle.getText().toString()))
                tvTitle.setVisibility(View.VISIBLE);
            tvTitleLeft.setText(TextUtils.isEmpty(getTitleLeftText())?"":getTitleLeftText());
            tvTitleRight.setText(TextUtils.isEmpty(getTitleRightText())?"":getTitleRightText());
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            ivTitleRight.setVisibility(View.GONE);
            if (getTitleRightImgId()!=0)
            ivTitleRight.setImageResource(getTitleRightImgId());
            ivTitleRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tvTitleRightClick!=null)
                        tvTitleRightClick.onClick(v);
                }
            });
        }
    }

    public int getTitleRightImgId() {
        return 0;
    }

    public void setTitleRightClick(View.OnClickListener tvTitleRightClick) {
        this.tvTitleRightClick = tvTitleRightClick;
    }

    public TextView getTvTitleRight() {
        return tvTitleRight;
    }

    public TextView getTvTitile(){
        return tvTitle;
    }
    public void setTitleRightTextColor(int color){
        tvTitleRight.setTextColor(color);
    }
    protected abstract String getTitleRightText();

    protected abstract String getTitleLeftText();

    protected abstract String getTitleText();

    public void setTitleLeftText(String s){
        tvTitleLeft.setText(s);
    }
    public void setTitle(String s){
        tvTitle.setText(s);
    }
    public void setTitleRightText(String s){
        tvTitleRight.setText(s);
    }
    public boolean haveHeader() {
        return false;
    }

    //沉浸式
    private void hideBar() {
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        setRootViewFitsSystemWindows(this,false);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);

        if (AppUtils.getPackageName(BaseActivity.this).equals("com.qingyun.cet")){
            if (!StatusBarUtil.setStatusBarDarkTheme(BaseActivity.this,false)){
//                StatusBarUtil.setStatusBarColor(this,0x55000000);
            }
        }
        if(presenter == null){
            presenter = createPresenter();
        }
        if(view == null){
            view = createView();
        }
        if(presenter != null && view != null){
            presenter.attachView(view);
        }

    }


    //由子类指定具体类型
    public abstract int getLayoutId();
    public abstract P createPresenter();
    public abstract V createView();
    public abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
        EventBus.getDefault().unregister(this);
        if(presenter != null){
            presenter.detachView();
        }
        ApplicationUtil.activityList.remove(this);
    }


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


    public void refreashUser(){


    }
    public void startActivity(Class c){
        Intent intent = new Intent(getContext(),c);
        startActivity(intent);
    }

    public void startActivity(Class c,Bundle bundle){
        Intent intent = new Intent(getContext(),c);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocalManageUtil.setLocal(newBase));
    }

}
