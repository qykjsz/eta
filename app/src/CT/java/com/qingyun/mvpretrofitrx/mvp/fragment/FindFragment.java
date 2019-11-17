package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.qingyun.mvpretrofitrx.mvp.adapter.SelectTheAppAdapter;
import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetModle;
import com.qingyun.mvpretrofitrx.mvp.entity.DApp;
import com.qingyun.mvpretrofitrx.mvp.net.HttpParamsUtils;
import com.qingyun.mvpretrofitrx.mvp.net.XCallBack;
import com.qingyun.mvpretrofitrx.mvp.utils.GlideRoundTransform;
import com.qingyun.mvpretrofitrx.mvp.utils.ZLog;
import com.qingyun.mvpretrofitrx.mvp.weight.GridSpacingItemDecoration;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.senon.mvpretrofitrx.R;

import org.xutils.http.RequestParams;
import org.xutils.x;

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

public class FindFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks{
//    @BindView(R.id.serachview)
//    SearchView serachview;
    Unbinder unbinder;
//    @BindView(R.id.btn_scan)
//    ImageView btnScan;
//    @BindView(R.id.iv)
//    ImageView iv;
//    @BindView(R.id.rcy_modle1)
//    RecyclerView rcyModle1;
//    @BindView(R.id.rcy_modle)
//    RecyclerView rcyModle;
//    private List<AssetModle> modleList;
//    SelectTheAppAdapter selectTheAppAdapter;
    @BindView(R.id.srl)
    RefreshLayout mSmartRefreshLayout;
    List<DApp> dApps;
    @BindView(R.id.container_browse)
    View mBrowse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
    private static final int PERMISSIONS_CAMERA = 2;
    @OnClick({R.id.tv_search,R.id.iv_scan})
    public void onViewClicked(View view){
        switch (view.getId()){
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
//        SearchView.SearchAutoComplete textView = (SearchView.SearchAutoComplete) serachview.findViewById(R.id.search_src_text);
//        textView.setTextColor(getResources().getColor(R.color.color_FFFFFF));
//        textView.setHintTextColor(getResources().getColor(R.color.color_FFFFFF));
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);

//        modleList = new ArrayList<>();
//        modleList.add(new AssetModle(R.mipmap.fa_02, R.string.ziyuan));
//        modleList.add(new AssetModle(R.mipmap.fa_02, R.string.vote));
//        modleList.add(new AssetModle(R.mipmap.fx_01, R.string.b_business));
//        modleList.add(new AssetModle(R.mipmap.fa_02, R.string.more_modle));
//        modleList.add(new AssetModle(R.mipmap.fa_02, R.string.more_modle));
//
//        selectTheAppAdapter = new SelectTheAppAdapter(getContext(), modleList);
//        rcyModle.setLayoutManager(new GridLayoutManager(getContext(), 5));
//        rcyModle.addItemDecoration(new GridSpacingItemDecoration(4, getResources().getDimensionPixelSize(R.dimen.dp_5), false));
//        rcyModle.setAdapter(selectTheAppAdapter);
//        refreashView(modleList, rcyModle);
//        rcyModle1.setLayoutManager(new GridLayoutManager(getContext(), 5));
//        rcyModle1.addItemDecoration(new GridSpacingItemDecoration(4, getResources().getDimensionPixelSize(R.dimen.dp_5), false));
//        rcyModle1.setAdapter(selectTheAppAdapter);
//
//        selectTheAppAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(List list, int position) {
//
//            }
//        });
        requestBannerImage();
        request();
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
            }
        });
        mSmartRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
    }
    @BindView(R.id.banner)
    //轮播图
     BGABanner mCubeBanner;
    //Banner轮播图的List
    private List<String> bannerList;
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

//    @OnClick(R.id.btn_scan)
//    public void onViewClicked() {
//         startActivity(ScanActivity.class);
//    }

    /**
     * 轮播图请求以及设置
     */
    private void requestBannerImage() {
        RequestParams params = HttpParamsUtils.getX3Params(Api.returnEtUrl()+"api_banner");
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
                    for (int i = 0; i < data.size(); i++) {
                        JSONObject obj = data.getJSONObject(i);
                        //循环将图片url存入
                        bannerList.add(obj.getString("url"));
                    }
                    //设置图片进入banner。第二个参数为设置文字
                    mCubeBanner.setData(bannerList, null);
                    /**轮播图点击事件*/
                    mCubeBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
                        @Override
                        public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
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

    private void request(){
        RequestParams params = HttpParamsUtils.getX3Params(Api.returnEtUrl()+"et_app");
        x.http().post(params, new XCallBack() {
            @Override
            public void onAfterFinished() {
                mSmartRefreshLayout.finishRefresh();//刷新完成
            }

            @Override
            public void onAfterSuccessOk(JSONObject object) {
                JSONArray array = object.getJSONArray("data");
                if(array.size() > 0){
                    parseData(array);
                    setData();
                }
            }

            @Override
            public void onAfterSuccessErr(JSONObject object, String msg) {
                mSmartRefreshLayout.finishRefresh(false);//结束刷新（刷新失败）
            }
        });
    }

    private List<DApp> parseData(JSONArray userList) {
        dApps = new ArrayList<>();
        if (userList.size() > 0) {
            for (int i = 0; i < userList.size(); i++) {
                DApp data = new DApp();
                JSONObject res = userList.getJSONObject(i);
                data.setImg(res.getString("img"));
                data.setName(res.getString("name"));
                data.setUrl(res.getString("url"));
                dApps.add(data);
            }
        } else {
            dApps.add(null);
        }
        return dApps;
    }

    private void setData() {
        if (null != dApps && dApps.size() > 0) {
            mBrowse.setVisibility(View.VISIBLE);//不等于空 显示
            LinearLayout ll_products = mBrowse.findViewById(R.id.ll_browse);//mBrowse容器装载 LinearLayout
            ll_products.removeAllViews();//清空数据
            for (int i = 0; i < dApps.size(); i++) {
                View view = getLayoutInflater().inflate(R.layout.item_dapp, ll_products, false);//绑定此布局在LinearLayout下
                ImageView image = view.findViewById(R.id.iv_image);
                TextView tv_name = view.findViewById(R.id.tv_name);
                final DApp data = dApps.get(i);
                if (null != data) {
                    RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.mipmap.app_icon);
                    requestOptions.circleCropTransform();
                    requestOptions.transforms( new RoundedCorners(20));
                    Glide.with(mContext).load(data.getImg()).apply(requestOptions).into(image);
                    ZLog.showPost("post==="+data.getImg());
                    tv_name.setText(data.name);
                    image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mContext,WebActivity.class);
                            intent.putExtra("url",data.url);
                            startActivity(intent);
                        }
                    });
                    ll_products.addView(view);//将item_fans_header_image_list设置进LinearLayout下
                }
            }
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        startActivity(ScanQrCodeActivity.class);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
}
