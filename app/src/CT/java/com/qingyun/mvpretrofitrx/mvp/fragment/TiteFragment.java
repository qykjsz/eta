package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.qingyun.mvpretrofitrx.mvp.activity.TheArticleDetailsActivity;
import com.qingyun.mvpretrofitrx.mvp.adapter.TimeAdapter;
import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.base.FatherAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.SuperViewHolder;
import com.qingyun.mvpretrofitrx.mvp.contract.TimeContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Time;
import com.qingyun.mvpretrofitrx.mvp.net.HttpParamsUtils;
import com.qingyun.mvpretrofitrx.mvp.net.XCallBack;
import com.qingyun.mvpretrofitrx.mvp.presenter.Timepresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.GlideRoundTransform;
import com.qingyun.mvpretrofitrx.mvp.utils.TestMain;
import com.qingyun.mvpretrofitrx.mvp.weight.GridSpacingItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.senon.mvpretrofitrx.R;
import com.trello.rxlifecycle2.android.FragmentEvent;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ezy.ui.layout.LoadingLayout;
import io.reactivex.ObservableTransformer;

public class TiteFragment extends BaseFragment<TimeContact.View, TimeContact.Presenter> {
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
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.tv_nian)
    TextView tvNian;
    private TiteAdpater mAdapter;


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
        return null;
    }

//    @Override
//    public TimeContact.View createView() {
//        return this;
//    }

    @Override
    public void init() {
        tvNian.setText(TestMain.getsetdata());


//        getPresenter().getContacFlashtList(page - 1 + "");
//        times = new ArrayList<>();
//        timeAdapter = new TimeAdapter(getContext(), times);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 50, false));
//        //添加Android自带的分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
//        recyclerView.setAdapter(timeAdapter);
//        refreashView(times, recyclerView);
        initRefreshLayout(srl);
//        timeAdapter.setAddListener(new BaseAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(List list, int position) {
//                 newsBean= (Time.NewsBean) list.get(position);
//              Intent intent=new Intent();
//              intent.setClass(getContext(), TheArticleDetailsActivity.class);
//              intent.putExtra("newsBean", newsBean);
//              startActivity(intent);
//            }
//        });

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {  //Fragment 可见
            currentPage = 0;
            request();
        } else {  //Fragment 不可见
        }
    }


    @Override
    protected void loadMore() {
        super.loadMore();
//        getPresenter().getContacFlashtList(page + "");
        request();
    }

    @Override
    protected void refresh() {
        super.refresh();
//        getPresenter().getContacFlashtList(page + "");
        currentPage = 0;
        request();
    }

    @Override
    public void onResume() {
        super.onResume();
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
        mAdapter = new TiteAdpater(mContext);
        listView.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
//
//    @Override
//    public void getContactListFlash(Time contactList) {
////        Log.i("exp", "=====times=====" + times.size());
////        if (contactList.getNews() != null) {
////            if (isLoadMore) {
////                times.addAll(contactList.getNews());
////            } else {
////                times = contactList.getNews();
////            }
////            refreashView(times, recyclerView);
////            timeAdapter.notifyDataSetChanged(times);
////        }
//    }
//
//    @Override
//    public <T> ObservableTransformer<T, T> bindLifecycle() {
//        return this.bindUntilEvent(FragmentEvent.PAUSE);
//    }

    private void request() {
        RequestParams params = HttpParamsUtils.getX3Params(Api.returnEtUrl() + "et_news");
        params.addBodyParameter("page", currentPage + "");
        x.http().post(params, new XCallBack() {
            @Override
            public void onAfterFinished() {
                srl.finishRefresh();//刷新完成
                srl.finishLoadMore();//加载完成
            }

            @Override
            public void onAfterSuccessOk(JSONObject object) {
                JSONObject data = object.getJSONObject("data");
                JSONArray News = data.getJSONArray("News");
                List<Time.NewsBean> list = JSON.parseArray(News.toJSONString(), Time.NewsBean.class);
                if (list.size() > 0) {
                    if (currentPage == 0) {
                        mAdapter.setDatas(list);
                    } else {
                        mAdapter.addData(list);
                    }
                    currentPage++;
                }
            }

            @Override
            public void onAfterSuccessErr(JSONObject object, String msg) {
                srl.finishLoadMore(false);//结束加载（加载失败）
                srl.finishRefresh(false);//结束刷新（刷新失败）
            }
        });
    }

    public class TiteAdpater extends FatherAdapter<Time.NewsBean> {

        public TiteAdpater(Context ctx) {
            super(ctx);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.adapter_time, parent, false);
                new ViewHolder(convertView);
            }
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            final Time.NewsBean item = getItem(position);
            setUi(viewHolder, item);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newsBean = item;
                    Intent intent = new Intent();
                    intent.setClass(getContext(), TheArticleDetailsActivity.class);
                    intent.putExtra("newsBean", newsBean);
                    startActivity(intent);
                }
            });
            return convertView;
        }

        private void setUi(final ViewHolder viewHolder, final Time.NewsBean item) {
            Glide.with(mContext).load(item.getImg()).into(viewHolder.ivBack);
            viewHolder.tvName.setText(item.getName());
            viewHolder.tvTime.setText(/*"来源：ET APP  " + */item.getTime());
        }

        public class ViewHolder extends SuperViewHolder {
            TextView tvName;
            ImageView ivBack;
            TextView tvTime;

            public ViewHolder(View view) {
                super(view);
                tvName = view.findViewById(R.id.tv_name);
                ivBack = view.findViewById(R.id.iv_back);
                tvTime = view.findViewById(R.id.tv_time);
            }
        }
    }
}
