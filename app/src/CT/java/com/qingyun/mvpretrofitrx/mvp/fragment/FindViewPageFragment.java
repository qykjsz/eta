package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.qingyun.mvpretrofitrx.mvp.utils.ChePackUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.SystemUtil;
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
    ListView listView;
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

    int index;
    @Subscribe(sticky = true)
    public void getEventBusMsg(MessageEvent event) {
        index = event.getId();
        JSONArray jsonArray = new JSONArray(FindFragment.list.get(event.getId()));
        gameData = JSON.parseArray(jsonArray.toJSONString(), GameData.class);
        if(gameData != null && gameData.size() > 0){
            mAdapter.setDatas(gameData);
            mAdapter.notifyDataSetChanged();
        }else{
            mAdapter.clear();
        }
    }

    /**解决点击0坐标与最后坐标错位问题*/
    public void doRefresh() {
        JSONArray jsonArray = new JSONArray(FindFragment.list.get(index));
        gameData = JSON.parseArray(jsonArray.toJSONString(), GameData.class);
        if(gameData != null && gameData.size() > 0){
            mAdapter.setDatas(gameData);
            mAdapter.notifyDataSetChanged();
        }else{
            mAdapter.clear();
        }
    }



    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }
    private long lastClickTime = 0;

    @Override
    public void init() {
        mAdapter = new FindViewPageAdapter(mContext);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long now = System.currentTimeMillis();
                if(now - lastClickTime > 3000) {
                    lastClickTime = now;
                    getUuid(mAdapter.getData().get(position).id);
                    Intent intent;
                    switch (mAdapter.getData().get(position).types){//1.跳转Web页面 2.跳转app
                        case 1:
                            intent = new Intent(mContext, WebActivity.class);
                            intent.putExtra("url",mAdapter.getData().get(position).url);
                            intent.putExtra("title",mAdapter.getData().get(position).name);
                            mContext.startActivity(intent);
                            break;
                        case 2:
                            if (ChePackUtil.checkPackInfo(mAdapter.getData().get(position).android,mContext)) {//程序已安装
                                intent = mContext.getPackageManager().getLaunchIntentForPackage(mAdapter.getData().get(position).android);
                                if (intent != null) {
                                    //            intent.putExtra("data", "");//传递数据
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    mContext.startActivity(intent);
                                }
                            } else {//未安装 跳转下载地址
                                intent = new Intent();
                                intent.setAction("android.intent.action.VIEW");
                                Uri content_url = Uri.parse(mAdapter.getData().get(position).appdown);
                                intent.setData(content_url);
                                mContext.startActivity(intent);
                            }
                            break;
                    }

                }
            }
        });
//        requestGameList();
    }

    //点击保存到最近使用
    private void requestGetUUid(String uuid,String id) {
        RequestParams params = HttpParamsUtils.getX3Params(Api.returnEtUrl() + "et_appnew");
        params.addBodyParameter("appid", id);
        params.addBodyParameter("contacts", uuid);
        x.http().post(params, new XCallBack() {
            @Override
            public void onAfterFinished() {

            }

            @Override
            public void onAfterSuccessOk(JSONObject object) {

            }

            @Override
            public void onAfterSuccessErr(JSONObject object, String msg) {

            }
        });
    }
    //获取设备号
    private void getUuid(final String id) {
        SystemUtil.getMyUUID(getActivity(), new SystemUtil.RequestPermissionListener() {
            @Override
            public void requestSuccess(String uuid) {
                requestGetUUid(uuid,id);
            }
        });
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


    public class FindViewPageAdapter extends FatherAdapter<GameData> {
        public FindViewPageAdapter(Context ctx) {
            super(ctx);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_searchs, parent, false);
                new ViewHolder(convertView);
            }
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            final GameData item = getItem(position);
            setUi(viewHolder, item);
//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    long now = System.currentTimeMillis();
//                    if(now - lastClickTime >2000) {
//                        Intent intent = new Intent(mContext, WebActivity.class);
//                        intent.putExtra("url", item.url);
//                        mContext.startActivity(intent);
//                    }
//                }
//            });
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
//            viewHolder.iv_icon.setVisibility(View.GONE);
        }

        public class ViewHolder extends SuperViewHolder {
            TextView tv_name;//名称
            TextView tv_introduce;//简介
            ImageView iv_image;//图片
//            ImageView iv_icon;//icon

            public ViewHolder(View view) {
                super(view);
                iv_image = view.findViewById(R.id.iv_image);
                tv_name = view.findViewById(R.id.tv_name);
                tv_introduce = view.findViewById(R.id.tv_introduce);
//                iv_icon = view.findViewById(R.id.iv_icon);
            }
        }
    }

}
