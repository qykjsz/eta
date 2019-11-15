package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qingyun.mvpretrofitrx.mvp.activity.ExpressTheDetailsActivity;
import com.qingyun.mvpretrofitrx.mvp.adapter.FlashAdapter;
import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.FatherAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.SuperViewHolder;
import com.qingyun.mvpretrofitrx.mvp.contract.FlashContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Flash;
import com.qingyun.mvpretrofitrx.mvp.entity.NewFlashData;
import com.qingyun.mvpretrofitrx.mvp.entity.News;
import com.qingyun.mvpretrofitrx.mvp.net.HttpParamsUtils;
import com.qingyun.mvpretrofitrx.mvp.net.XCallBack;
import com.qingyun.mvpretrofitrx.mvp.presenter.Flashpresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.TestMain;
import com.qingyun.mvpretrofitrx.mvp.utils.TimeUtils;
import com.qingyun.mvpretrofitrx.mvp.weight.GridSpacingItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.senon.mvpretrofitrx.R;
import com.trello.rxlifecycle2.android.FragmentEvent;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    SmartRefreshLayout mSmartRefreshLayout;
    List<News> list;
    @BindView(R.id.freash_loading)
    LoadingLayout freashLoading;
    News news;
    @BindView(R.id.tv_nian)
    TextView tvNian;
    @BindView(R.id.listView)
    ListView listView;

    private NewFlashAdapter mAdapter;
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
//        getPresenter().getContacFlashtList(page - 1 + "");
//        list = new ArrayList<>();
//
        tvNian.setText(TestMain.getsetdata());
//        flashAdapter = new FlashAdapter(getContext(), list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 50, false));
//        //recyclerView.addItemDecoration(new TimeLineItemDecoration());
//        recyclerView.setAdapter(flashAdapter);
//        refreashView(list, recyclerView);
        initRefreshLayout(mSmartRefreshLayout);
//        flashAdapter.setAddListener(new BaseAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(List list, int position) {
//                news = (News) list.get(position);
//                Intent intent = new Intent();
//                intent.setClass(getContext(), ExpressTheDetailsActivity.class);
//                intent.putExtra("people", news);
//                startActivity(intent);
//            }
//        });
        request();
        mAdapter = new NewFlashAdapter(mContext);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getContext(), ExpressTheDetailsActivity.class);
                intent.putExtra("people", news);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadMore() {
        super.loadMore();
        request();
//        getPresenter().getContacFlashtList(page + "");
    }

    @Override
    protected void refresh() {
        super.refresh();
//        getPresenter().getContacFlashtList(page + "");
        currentPage = 0;
        requestList();
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
//        Log.i("exp", "==========" + list.size());
//        if (isLoadMore) {
//            list.addAll(contactList.getNews());
//        } else {
//            list = contactList.getNews();
//        }
//        refreashView(list, recyclerView);
//        flashAdapter.notifyDataSetChanged(list);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }



    public class NewFlashAdapter extends FatherAdapter<NewFlashData> {
        public NewFlashAdapter(Context ctx) {
            super(ctx);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_new_flash, parent, false);
                new ViewHolder(convertView);
            }
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            final NewFlashData item = getItem(position);
            setUi(viewHolder, item);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, ExpressTheDetailsActivity.class);
                    intent.putExtra("people", JSON.toJSONString(item));
                    startActivity(intent);
                }
            });
            return convertView;
        }
        private void setUi(final ViewHolder viewHolder, final NewFlashData item) {
            viewHolder.tv_time.setText(TimeUtils.getTime(item.time, TimeUtils.DEL_FORMAT_DATE_mm));
            viewHolder.tv_title.setText(item.title);
            viewHolder.tv_content.setText(item.content);
            viewHolder.tv_source.setText("来源："+item.source);
        }

        public class ViewHolder extends SuperViewHolder {
            public TextView tv_time;//时间
            public TextView tv_title;//标题
            public TextView tv_content;//内容
            public TextView tv_source;//来源
            public TextView tv_see;//查看

            public ViewHolder(View view) {
                super(view);
                tv_time = view.findViewById(R.id.tv_time);
                tv_title = view.findViewById(R.id.tv_title);
                tv_content = view.findViewById(R.id.tv_content);
                tv_source = view.findViewById(R.id.tv_source);
                tv_see = view.findViewById(R.id.tv_see);
            }
        }
    }

    private void request(){
        RequestParams params = HttpParamsUtils.getX3Params(Api.returnEtUrl()+"et_newsflash");
        params.addBodyParameter("page",currentPage+"");
        currentPage++;
        x.http().post(params, new XCallBack() {
            @Override
            public void onAfterFinished() {
                mSmartRefreshLayout.finishRefresh();//刷新完成
                mSmartRefreshLayout.finishLoadMore();//加载完成
            }

            @Override
            public void onAfterSuccessOk(JSONObject object) {
                JSONObject data = object.getJSONObject("data");
                JSONArray News = data.getJSONArray("News");
                List<NewFlashData> list = JSON.parseArray(News.toJSONString(),NewFlashData.class);
                if(list.size() > 0){
                    if(currentPage == 1){
                        mAdapter.setDatas(list);
                    }else{
                        mAdapter.addData(list);
                    }
                }
            }

            @Override
            public void onAfterSuccessErr(JSONObject object, String msg) {
                mSmartRefreshLayout.finishLoadMore(false);//结束加载（加载失败）


                mSmartRefreshLayout.finishRefresh(false);//结束刷新（刷新失败）
            }
        });
    }
}
