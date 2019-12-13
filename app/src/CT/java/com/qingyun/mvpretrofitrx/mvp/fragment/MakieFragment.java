package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.qingyun.mvpretrofitrx.mvp.activity.ContactActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.ScanActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.TransferImmediateActivity;
import com.qingyun.mvpretrofitrx.mvp.adapter.MakieAdapter;
import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.base.FatherAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.SuperViewHolder;
import com.qingyun.mvpretrofitrx.mvp.contract.MakieContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Quotation;
import com.qingyun.mvpretrofitrx.mvp.entity.Quotation1;
import com.qingyun.mvpretrofitrx.mvp.net.HttpParamsUtils;
import com.qingyun.mvpretrofitrx.mvp.net.XCallBack;
import com.qingyun.mvpretrofitrx.mvp.presenter.Makiepresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ZLog;
import com.qingyun.mvpretrofitrx.mvp.weight.GridSpacingItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.senon.mvpretrofitrx.R;
import com.trello.rxlifecycle2.android.FragmentEvent;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ezy.ui.layout.LoadingLayout;
import io.reactivex.ObservableTransformer;

public class MakieFragment extends BaseFragment<MakieContact.View, MakieContact.Presenter> implements MakieContact.View {
//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
    Unbinder unbinder;
//    private List<Quotation> quotations;
//    MakieAdapter adapter;
    @BindView(R.id.listView)
    ListView listView;
//    @BindView(R.id.iv_money_icon)
//    ImageView iv_money_icon;
//    @BindView(R.id.iv_range_icon)
//    ImageView iv_range_icon;
    private List<Quotation> list;
    private boolean isMoney;
    private boolean isRange;
    List<String> stringList;

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
//        getPresenter().getContacMakieList("padg");
//        quotations = new ArrayList<>();
//        adapter = new MakieAdapter(getContext(), quotations);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, 50, false));
//        //   //添加Android自带的分割线
//            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
//        recyclerView.setAdapter(adapter);
//        refreashView(quotations, recyclerView);
        // initRefreshLayout(srl);
        request();
        mAdapter = new MakieAdapter(mContext);
        listView.setAdapter(mAdapter);

    }

    @OnClick({R.id.ll_money, R.id.ll_range})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_money://最新价格
//                isMoney = !isMoney;
//                if(isMoney){//跌
//                    iv_money_icon.setBackgroundResource(R.mipmap.hq_xjian_01);
//                    requestTrends(1);
//                }else{//涨
//                    iv_money_icon.setBackgroundResource(R.mipmap.hq_sjian);
//                    requestTrends(2);
//                }
                break;
            case R.id.ll_range://涨跌幅
//                isRange = !isRange;
//                if(isRange){//跌
//                    iv_range_icon.setBackgroundResource(R.mipmap.hq_xjian_01);
//                    requestTrends(3);
//                }else{//涨
//                    iv_range_icon.setBackgroundResource(R.mipmap.hq_sjian);
//                    requestTrends(4);
//                }
                break;
        }
    }
    //获取动态 价格 涨跌幅排序 start 1.按价格降序 2.按价格升序 3.按涨跌降序 4.按涨跌升序
    private void requestTrends(int start){
        if(list.size() > 0){
            JSONObject object = new JSONObject();
            stringList = new ArrayList<>();
            for(int i = 0; i < list.size(); i++){
                stringList.add(list.get(i).getName());
            }
            String[] strings = new String[list.size()];
            stringList.toArray(strings);
            object.put("allglods",strings);
            object.put("sort",start+"");
            ZLog.showPost("post"+object.toJSONString());

            RequestParams params = HttpParamsUtils.getX3Params(Api.returnEtUrl()+"et_quotationsort",object);
            x.http().post(params, new XCallBack() {
                @Override
                public void onAfterFinished() {
                }

                @Override
                public void onAfterSuccessOk(JSONObject object) {
                    JSONArray data = object.getJSONArray("data");
                    list = JSON.parseArray(data.toJSONString(), Quotation.class);
                    if (list.size() > 0) {
                        mAdapter.setDatas(list);
                    }
                }

                @Override
                public void onAfterSuccessErr(JSONObject object, String msg) {
                }
            });
        }
    }

    @Override
    protected void loadMore() {
        super.loadMore();
//        getPresenter().getContacMakieList("padg");

    }

    @Override
    protected void refresh() {
        super.refresh();
//        getPresenter().getContacMakieList("padg");

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
//        Log.i("exp", "==========" + contactList.size());
//        if (isLoadMore) {
//            quotations.addAll(contactList);
//        } else {
//            quotations = contactList;
//        }
//        refreashView(quotations, recyclerView);
//        adapter.notifyDataSetChanged(quotations);

    }
    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }

    //初始化请求
    private void request() {
        RequestParams params = HttpParamsUtils.getX3Params(Api.returnEtUrl() + "et_quotation");
        x.http().post(params, new XCallBack() {




            @Override
            public void onAfterFinished() {

            }

            @Override
            public void onAfterSuccessOk(JSONObject object) {
                JSONArray data = object.getJSONArray("data");
                ZLog.c("Sampostaa",object.toJSONString());
                list = JSON.parseArray(data.toJSONString(), Quotation.class);
                if (list.size() > 0) {
                    mAdapter.setDatas(list);
                }
            }

            @Override
            public void onAfterSuccessErr(JSONObject object, String msg) {

            }
        });
    }

    private MakieAdapter mAdapter;

    public class MakieAdapter extends FatherAdapter<Quotation> {

        public MakieAdapter(Context ctx) {
            super(ctx);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.adapter_makie, parent, false);
                new ViewHolder(convertView);
            }
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            final Quotation item = getItem(position);
            setUi(viewHolder, item,position);
            return convertView;
        }

        private void setUi(final ViewHolder viewHolder, final Quotation item,int position) {
            position++;
            viewHolder.tv_position.setText(position+"");
            viewHolder.tvName.setText(item.getName());
            viewHolder.tv_nik_name.setText(item.allname);
            viewHolder.tv_money.setText("$" + item.getXiamoney());
            viewHolder.tv_issue_num.setText("$" +item.vol);
            viewHolder.tv_value.setText("$"+item.shizhi);

//            viewHolder.tvShangmoney.setText(item.getShangmoney());
//            viewHolder.tvZb.setText(item.getZd() + "%");
//            Glide.with(getContext()).load(item.getImg()).into(viewHolder.ivImg);
            float s_zd = Float.parseFloat(item.zd);

            if (s_zd > 0) {//涨
                viewHolder.tv_zd.setText("+"+item.zd + "%");
                viewHolder.tv_zd.setTextColor(mContext.getResources().getColor(R.color.color_00B794));
                viewHolder.tv_money.setTextColor(mContext.getResources().getColor(R.color.color_00B794));
            } else if (item.zd.startsWith("-")) {//跌
                viewHolder.tv_zd.setText(item.zd + "%");
                viewHolder.tv_zd.setTextColor(mContext.getResources().getColor(R.color.color_E04159));
                viewHolder.tv_money.setTextColor(mContext.getResources().getColor(R.color.color_E04159));
            } else {
                viewHolder.tv_zd.setText(item.zd + "%");
                viewHolder.tv_zd.setTextColor(mContext.getResources().getColor(R.color.color_999999));
                viewHolder.tv_money.setTextColor(mContext.getResources().getColor(R.color.color_999999));
            }
        }

        public class ViewHolder extends SuperViewHolder {
//            ImageView ivImg;
            TextView tv_position;
            TextView tvName;
            TextView tv_nik_name;
            TextView tv_zd;
            TextView tv_money;
            TextView tv_issue_num;
            TextView tv_value;
//            TextView tvShangmoney;
//            TextView tvXiamoney;
//            TextView tvZb;

            public ViewHolder(View view) {
                super(view);
                tv_position = view.findViewById(R.id.tv_position);
                tvName = view.findViewById(R.id.tv_name);
                tv_nik_name = view.findViewById(R.id.tv_nik_name);
                tv_zd = view.findViewById(R.id.tv_zd);
                tv_money = view.findViewById(R.id.tv_money);
                tv_issue_num = view.findViewById(R.id.tv_issue_num);
                tv_value = view.findViewById(R.id.tv_value);
//                tvShangmoney = view.findViewById(R.id.tv_shangmoney);
//                tvXiamoney = view.findViewById(R.id.tv_xiamoney);
//                tvZb = view.findViewById(R.id.tv_zb);
            }
        }
    }
}
