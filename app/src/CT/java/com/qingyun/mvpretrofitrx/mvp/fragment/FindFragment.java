package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Outline;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.qingyun.mvpretrofitrx.mvp.activity.ScanActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.ScanQrCodeActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.SearchActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.WebActivity;
import com.qingyun.mvpretrofitrx.mvp.adapter.FindFragmentPageAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.FindGridViewAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.SelectTheAppAdapter;
import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetModle;
import com.qingyun.mvpretrofitrx.mvp.entity.DApp;
import com.qingyun.mvpretrofitrx.mvp.entity.GameData;
import com.qingyun.mvpretrofitrx.mvp.entity.MessageEvent;
import com.qingyun.mvpretrofitrx.mvp.entity.RecentlyAppData;
import com.qingyun.mvpretrofitrx.mvp.net.HttpParamsUtils;
import com.qingyun.mvpretrofitrx.mvp.net.XCallBack;
import com.qingyun.mvpretrofitrx.mvp.utils.GlideRoundTransform;
import com.qingyun.mvpretrofitrx.mvp.utils.SystemUtil;

import com.qingyun.mvpretrofitrx.mvp.view.TabScrollView;
import com.qingyun.mvpretrofitrx.mvp.weight.GridSpacingItemDecoration;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.senon.mvpretrofitrx.R;
import org.greenrobot.eventbus.EventBus;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cc.shinichi.library.ImagePreview;
import cc.shinichi.library.tool.utility.image.ImageUtil;
import cn.bingoogolapple.bgabanner.BGABanner;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.http.PATCH;

public class FindFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks {
    Unbinder unbinder;
    @BindView(R.id.srl)
    RefreshLayout mSmartRefreshLayout;
    List<DApp> dApps;
    //    @BindView(R.id.container_browse)
//    View mBrowse;
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.banner)
    //轮播图
    BGABanner mCubeBanner;
    //Banner轮播图的List
    private List<String> bannerList;
    private List<String> bannerLinkList;
    private FindGridViewAdapter mAdapter;
    private long lastClickTime = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    private static final int PERMISSIONS_CAMERA = 2;

    @OnClick({R.id.tv_search, R.id.iv_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                startActivity(SearchActivity.class);
                break;
            case R.id.iv_scan:
                String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
                if (EasyPermissions.hasPermissions(mContext, perms)) {
                    //已有权限
                    startActivity(ScanQrCodeActivity.class);
                } else {
                    // 没有权限
                    EasyPermissions.requestPermissions(getActivity(), "请开起相机权限，以正常使用", PERMISSIONS_CAMERA, perms);
                }
                break;
        }
    }


    /**
     * 检查包是否存在
     *
     * @param packname
     * @return
     */
    private boolean checkPackInfo(String packname) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = mContext.getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void init() {
        requestBannerImage();
        request();
        requestGameList();
        getUuid();
        mCubeBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                Glide.with(FindFragment.this)
                        .load(model)
                        .into(itemView);
            }
        });

        mCubeBanner.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 30);
            }
        });
        mCubeBanner.setClipToOutline(true);

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                request();
                requestBannerImage();
                requestGameList();
                getUuid();
            }
        });
        mSmartRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        mAdapter = new FindGridViewAdapter(mContext);
        gridView.setAdapter(mAdapter);

        mTabScrollView.setOnItemSelectListener(new TabScrollView.OnItemSelectListener() {
            @Override
            public void onItemSelect(int index, View itemView) {
//                FindViewPageFragment.setData(index);
                //动态设置字体大小
                for (int i = 0; i < tabs.length; i++) {
                    if (i == index) {
                        ((TextView) tabs[i]).setTextSize(16);
                    } else {
                        ((TextView) tabs[i]).setTextSize(13);
                    }
                }
                //数据传递
                EventBus.getDefault().postSticky(new MessageEvent(index, ""));
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lastClickTimes = 0;
                long now = System.currentTimeMillis();
                if(now - lastClickTimes >3000){
                    lastClickTimes = now;
                    requestGetUUid(mAdapter.getData().get(position).id);
                    Intent intent = new Intent(mContext, WebActivity.class);
                    intent.putExtra("url", mAdapter.getData().get(position).getUrl());
                    intent.putExtra("title",mAdapter.getData().get(position).getName());
                    mContext.startActivity(intent);
                }
            }
        });
    }

    private long lastClickTimes = 0;


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

    private void requestGetUUid(String id) {
        RequestParams params = HttpParamsUtils.getX3Params(Api.returnEtUrl() + "et_appnew");
        params.addBodyParameter("appid", id);
        params.addBodyParameter("contacts", uuId);
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

    /**
     * 轮播图请求以及设置
     */
    private void requestBannerImage() {
        RequestParams params = HttpParamsUtils.getX3Params(Api.returnEtUrl() + "api_banner");
        x.http().post(params, new XCallBack() {
            @Override
            public void onAfterFinished() {
                mSmartRefreshLayout.finishRefresh();//刷新完成
            }

            @Override
            public void onAfterSuccessOk(JSONObject object) {
                JSONArray data = object.getJSONArray("data");
                if (data.size() > 0) {
                    bannerList = new ArrayList<>();
                    bannerLinkList = new ArrayList<>();
                    for (int i = 0; i < data.size(); i++) {
                        JSONObject obj = data.getJSONObject(i);
                        //循环将图片url存入
                        bannerList.add(obj.getString("url"));
                        bannerLinkList.add(obj.getString("link"));
                    }
                    //设置图片进入banner。第二个参数为设置文字
                    mCubeBanner.setData(bannerList, null);
                    /**轮播图点击事件*/
                    mCubeBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
                        @Override
                        public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
                            if(bannerLinkList.get(position) != null){

                            if(bannerLinkList.get(position).startsWith("http") || bannerLinkList.get(position).startsWith("https")){
                                Intent intent = new Intent(mContext, WebActivity.class);
                                intent.putExtra("url", bannerLinkList.get(position));
                                intent.putExtra("title","");
                                mContext.startActivity(intent);
                            }
                            }

//                            if(bannerData.get(position).type.equals("2")){//1为普通(不可跳转) 2为带外链的（可跳转）
//                                PublicJump.startWebActivity(mContext, bannerData.get(position).title, bannerData.get(position).external_link);
//                            }
                        }
                    });
                }

            }

            @Override
            public void onAfterSuccessErr(JSONObject object, String msg) {
                mSmartRefreshLayout.finishRefresh(false);//结束刷新（刷新失败）
            }
        });
    }


    /**
     * 精选DAPP
     */
    private void request() {
        RequestParams params = HttpParamsUtils.getX3Params(Api.returnEtUrl() + "et_app");
        x.http().post(params, new XCallBack() {
            @Override
            public void onAfterFinished() {
                mSmartRefreshLayout.finishRefresh();//刷新完成
            }

            @Override
            public void onAfterSuccessOk(JSONObject object) {
                JSONArray array = object.getJSONArray("data");
                if (array.size() > 0) {
                    List<DApp> dAppList = JSON.parseArray(array.toJSONString(), DApp.class);
                    mAdapter.setDatas(dAppList);
                }
            }

            @Override
            public void onAfterSuccessErr(JSONObject object, String msg) {
                mSmartRefreshLayout.finishRefresh(false);//结束刷新（刷新失败）
            }
        });
    }

    /**
     * 权限
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        startActivity(ScanQrCodeActivity.class);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    /**
     * 游戏分类
     */
    private List<Fragment> fragments;
    int position = 0; //地标
    private FindFragmentPageAdapter findFragmentPageAdapter;
    private int module;//上个界面传过来的模式
    @BindView(R.id.tabscrollview)
    TabScrollView mTabScrollView;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    public static List<JSONArray> list;
    TextView tab;
    View tabs[];


    private void requestGameList() {
        RequestParams params = HttpParamsUtils.getX3Params(Api.returnEtUrl() + "et_appantype");
        x.http().post(params, new XCallBack() {
            @Override
            public void onAfterFinished() {

            }

            @Override
            public void onAfterSuccessOk(JSONObject object) {
                JSONArray data = object.getJSONArray("data");
                fragments = new ArrayList<Fragment>();
                tabs = new View[data.size()];
                if (data.size() > 0) {
                    list = new ArrayList<>();
                    for (int i = 0; i < data.size(); i++) {
                        JSONObject obj = data.getJSONObject(i);
                        //动态设置标题
                        fragments.add(FindViewPageFragment.getInstance());
                        tab = new TextView(mContext);
                        tab.setText(obj.getString("typename"));
                        tab.setBackgroundColor(Color.TRANSPARENT);//背景颜色
                        tab.setGravity(Gravity.CENTER);//字体居中
                        tab.setTextColor(getResources().getColorStateList(R.color.selector_tab_text_alter_colour));//没被选中的颜色
                        tabs[i] = tab;
                        position++;
                        JSONArray apps = obj.getJSONArray("apps");
                        if (apps.size() > 0) {
                            list.add(apps);
                        } else {
                            list.add(null);
                        }
                    }
                }
                findFragmentPageAdapter = new FindFragmentPageAdapter(mContext, tabs, fragments, getChildFragmentManager(), mViewPager, module);
                mViewPager.setAdapter(findFragmentPageAdapter);
                mTabScrollView.setViewPager(mViewPager);//关联ViewPager
                mTabScrollView.setOnPageChangeListener(findFragmentPageAdapter);//绑定Adapter
                mViewPager.setCurrentItem(module);//绑定模式
                mTabScrollView.outerClickForMove(module);//手动
//                mTabScrollView.setWrapContentViewPagerUnable(mViewPager);
            }

            @Override
            public void onAfterSuccessErr(JSONObject object, String msg) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getUuid();
    }
    String uuId;
    //获取设备号
    private void getUuid() {
        SystemUtil.getMyUUID(getActivity(), new SystemUtil.RequestPermissionListener() {
            @Override
            public void requestSuccess(String uuid) {
                uuId = uuid;
                requestGetRecentlyApp(uuid);
            }
        });
    }

    //最近使用
    private void requestGetRecentlyApp(String uuid) {
        RequestParams params = HttpParamsUtils.getX3Params(Api.returnEtUrl() + "et_appnews");
        params.addBodyParameter("contacts", uuid);
        x.http().post(params, new XCallBack() {
            @Override
            public void onAfterFinished() {

            }

            @Override
            public void onAfterSuccessOk(JSONObject object) {
                JSONArray data = object.getJSONArray("data");
                if (data.size() > 0) {
                    parseRecentlyAppData(data);
                    setRecentlyAppData();
                }
            }

            @Override
            public void onAfterSuccessErr(JSONObject object, String msg) {

            }
        });
    }

    /**
     * 最近使用
     */
    private List<RecentlyAppData> recentlyAppDataList;
    @BindView(R.id.container)
    View mAppItem;

    private List<RecentlyAppData> parseRecentlyAppData(JSONArray userList) {
        recentlyAppDataList = new ArrayList<>();
        if (userList.size() > 0) {
            for (int i = 0; i < userList.size(); i++) {
                RecentlyAppData data = new RecentlyAppData();
                JSONObject res = userList.getJSONObject(i);
                data.setId(res.getString("id"));
                data.setImg(res.getString("img"));
                data.setName(res.getString("name"));
                data.setText(res.getString("text"));
                data.setUrl(res.getString("url"));
                recentlyAppDataList.add(data);
            }
        } else {
            recentlyAppDataList.add(null);
        }
        return recentlyAppDataList;
    }

    private void setRecentlyAppData() {
        if (null != recentlyAppDataList && recentlyAppDataList.size() > 0) {
            mAppItem.setVisibility(View.VISIBLE);
            LinearLayout ll_products = mAppItem.findViewById(R.id.ll_container);
            ll_products.removeAllViews();
            for (int i = 0; i < recentlyAppDataList.size(); i++) {
                View view = getLayoutInflater().inflate(R.layout.item_find_app, ll_products, false);
                ImageView image = view.findViewById(R.id.iv_image);
                TextView tv_name = view.findViewById(R.id.tv_name);
                final RecentlyAppData data = recentlyAppDataList.get(i);
                if (null != data) {
                    tv_name.setText(data.getName());
                    RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.mipmap.app_icon);
                    requestOptions.circleCropTransform();
                    requestOptions.transforms(new RoundedCorners(20));
                    Glide.with(mContext).load(data.getImg()).apply(requestOptions).into(image);
                    image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            long now = System.currentTimeMillis();
                            if(now - lastClickTime >3000) {
                                lastClickTime = now;
                                if (data.getId().equals("1")) {//跳转ETAPP
                                    if (checkPackInfo("com.example.et")) {//程序已安装
                                        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage("com.example.et");
                                        if (intent != null) {
                                            //            intent.putExtra("data", "");//传递数据
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            mContext.startActivity(intent);
                                        }
                                    } else {//未安装 跳转下载地址
                                        Intent intent = new Intent();
                                        intent.setAction("android.intent.action.VIEW");
                                        Uri content_url = Uri.parse(data.getUrl());
                                        intent.setData(content_url);
                                        mContext.startActivity(intent);
                                    }
                                } else if (data.getId().equals("2")) {//跳转指定H5
                                    Intent intent = new Intent(mContext, WebActivity.class);
                                    intent.putExtra("url", data.getUrl()+"?" + uuId);
                                    intent.putExtra("title",data.getName());
                                    mContext.startActivity(intent);
                                } else {
                                    Intent intent = new Intent(mContext, WebActivity.class);
                                    intent.putExtra("url", data.getUrl());
                                    intent.putExtra("title",data.getName());
                                    mContext.startActivity(intent);
                                }
                            }

                        }
                    });
                    ll_products.addView(view);
                }
            }
        }
    }
}
