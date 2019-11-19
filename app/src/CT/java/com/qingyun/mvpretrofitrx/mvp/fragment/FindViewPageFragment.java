package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.qingyun.mvpretrofitrx.mvp.activity.WebActivity;
import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.base.FatherAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.SuperViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.GameData;
import com.qingyun.mvpretrofitrx.mvp.entity.MessageEvent;
import com.qingyun.mvpretrofitrx.mvp.net.HttpParamsUtils;
import com.qingyun.mvpretrofitrx.mvp.net.XCallBack;
import com.qingyun.mvpretrofitrx.mvp.utils.ZLog;
import com.qingyun.mvpretrofitrx.mvp.view.ListViewForScrollView;
import com.senon.mvpretrofitrx.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Date 2019/11/18.
 * Created by Sam
 * ClassExplain：
 */
public class FindViewPageFragment extends BaseFragment {
    FindViewPageAdapter mAdapter ;
    Unbinder unbinder;
    @BindView(R.id.listView)
    ListViewForScrollView listView;
    public List<JSONArray> list = new ArrayList<>();
    List<GameData> gameData;
    public static FindViewPageFragment getInstance() {
        FindViewPageFragment transactionFragment = new FindViewPageFragment();
        Bundle bundle = new Bundle();
        transactionFragment.setArguments(bundle);
        return transactionFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find_viewpage;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getEventBusMsg(MessageEvent event) {
        JSONArray jsonArray = new JSONArray(FindFragment.list.get(event.getId()));
        gameData = JSON.parseArray(jsonArray.toJSONString(), GameData.class);
        if(gameData != null && gameData.size() > 0){
            mAdapter.setDatas(gameData);
        }else{
            mAdapter.clear();
        }
    }


    public void doRefresh() {

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
        mAdapter = new FindViewPageAdapter(mContext);
        listView.setAdapter(mAdapter);
//        requestGameList();
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

    JSONArray jsonArray;
    /**
     * 游戏分类
     */
    private void requestGameList() {
        RequestParams params = HttpParamsUtils.getX3Params(Api.returnEtUrl() + "et_appantype");
        x.http().post(params, new XCallBack() {
            @Override
            public void onAfterFinished() {

            }

            @Override
            public void onAfterSuccessOk(JSONObject object) {
                JSONArray data = object.getJSONArray("data");
                if (data.size() > 0) {
                    for (int i = 0; i < data.size(); i++) {
                        JSONObject obj = data.getJSONObject(i);
                        JSONArray apps = obj.getJSONArray("apps");
                        if(apps.size() > 0){
                            list.add(apps);
                            jsonArray = new JSONArray(list.get(0));
                            gameData = JSON.parseArray(jsonArray.toJSONString(), GameData.class);
                            mAdapter.setDatas(gameData);
                        }else{
                            list.add(null);
                        }

                    }
                }
            }

            @Override
            public void onAfterSuccessErr(JSONObject object, String msg) {

            }
        });
    }


    public class FindViewPageAdapter extends FatherAdapter<GameData> {

        public FindViewPageAdapter(Context ctx) {
            super(ctx);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_search, parent, false);
                new ViewHolder(convertView);
            }
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            final GameData item = getItem(position);
            setUi(viewHolder, item);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, WebActivity.class);
                    intent.putExtra("url", item.url);
                    mContext.startActivity(intent);
                }
            });
            return convertView;
        }

        private void setUi(final ViewHolder viewHolder, final GameData item) {
            viewHolder.tv_name.setText(item.name);
            viewHolder.tv_introduce.setText(item.text);
            RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.mipmap.app_icon);
            requestOptions.circleCropTransform();
            requestOptions.transforms( new RoundedCorners(20));
            Glide.with(mContext).load(item.img).apply(requestOptions).into(viewHolder.iv_image);
            viewHolder.iv_icon.setVisibility(View.GONE);
        }

        public class ViewHolder extends SuperViewHolder {
            TextView tv_name;//名称
            TextView tv_introduce;//简介
            ImageView iv_image;//图片
            ImageView iv_icon;//icon

            public ViewHolder(View view) {
                super(view);
                iv_image = view.findViewById(R.id.iv_image);
                tv_name = view.findViewById(R.id.tv_name);
                tv_introduce = view.findViewById(R.id.tv_introduce);
                iv_icon = view.findViewById(R.id.iv_icon);
            }
        }
    }

}
