package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.develop.wallet.eth.Wallet;
import com.google.gson.Gson;
import com.qingyun.mvpretrofitrx.mvp.adapter.AssetReviewAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.contract.AssetsReviewContact;
import com.qingyun.mvpretrofitrx.mvp.entity.AllAddress;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetReviewResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Assets;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.qingyun.mvpretrofitrx.mvp.presenter.AssetsReviewPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.qingyun.mvpretrofitrx.mvp.utils.SpUtils;
import com.senon.mvpretrofitrx.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import drawthink.expandablerecyclerview.bean.RecyclerViewData;
import io.reactivex.ObservableTransformer;
import okhttp3.RequestBody;

public class AssetReviewActivity extends BaseActivity<AssetsReviewContact.View,AssetsReviewContact.Presenter> implements AssetsReviewContact.View {
    @BindView(R.id.btn_visiable)
    CheckBox btnVisiable;
    @BindView(R.id.tv_asset)
    TextView tvAsset;
    @BindView(R.id.tv_income_today)
    TextView tvIncomeToday;
    @BindView(R.id.ly_asset)
    ConstraintLayout lyAsset;
    @BindView(R.id.rcy)
    RecyclerView rcy;
    AssetReviewAdapter assetReviewAdapter;
    private List<RecyclerViewData> list;
    private List<String> addressList;
    final public static String MEDIA_TYPE_Json = "application/json;charset=utf-8";
    private AssetReviewResponse assetReviewResponse;
    private static final String INVISIABLE_STR="****";


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
        return getResources().getString(R.string.all_asset);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_asset_review;
    }

    @Override
    public AssetsReviewContact.Presenter createPresenter() {
        return new AssetsReviewPresenter(this);
    }

    @Override
    public AssetsReviewContact.View createView() {
        return this;
    }


    @Override
    public void init() {
        list = new ArrayList<>();
        addressList = new ArrayList<>();
        List<com.qingyun.mvpretrofitrx.mvp.entity.Wallet> walletList = new ArrayList<>();
        for (Map.Entry<String,List<Wallet>> map : ApplicationUtil.getWallet().entrySet()){
            for (int i=0;i<map.getValue().size();i++){
                addressList.add(map.getValue().get(i).getAddress());
                com.qingyun.mvpretrofitrx.mvp.entity.Wallet wallet = new com.qingyun.mvpretrofitrx.mvp.entity.Wallet();
                wallet.setAddress(map.getValue().get(i).getAddress());
                walletList.add(wallet);
            }
        }

        Log.e("addressList>>>",addressList.toString());
//        String addressArr =  addressList.toString().substring(1,addressList.toString().length()-1);
        String addressArr = addressList.toString().replaceAll(" ","");
        addressArr = "{"+addressArr+"}";
        Log.e("addressArr>>>",addressArr);
        AllAddress allAddress = new AllAddress();
        allAddress.setAlladdress(walletList);
        Log.e("wallListStr>>>",new Gson().toJson(allAddress));

        RequestBody body=RequestBody.create(okhttp3.MediaType.parse(MEDIA_TYPE_Json),new Gson().toJson(allAddress,AllAddress.class));

        assetReviewAdapter  = new AssetReviewAdapter(getContext(),list);
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.setAdapter(assetReviewAdapter);
        getPresenter().getAssetsReview(body);
        btnVisiable.setChecked(SpUtils.getBoolenToShare(getContext(),"is_asset_visiable"));
        btnVisiable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SpUtils.setBoolenToShare(getContext(),isChecked,"is_asset_visiable");

                refreashAsset(isChecked);
            }
        });

    }


    private void refreashAsset(boolean visiable){
        if (visiable){
            if (assetReviewResponse!=null)
                tvAsset.setText(assetReviewResponse.getAllnumber());
            tvIncomeToday.setText(assetReviewResponse.getToday());
        }else {
            tvIncomeToday.setText(INVISIABLE_STR);
            tvAsset.setText(INVISIABLE_STR);

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getAssetsReviewSuccess(AssetReviewResponse assetReviewResponse) {
        this.assetReviewResponse =assetReviewResponse;
        refreashAsset(btnVisiable.isChecked());
     list = getRecyclerViewData(assetReviewResponse.getAssets());
     assetReviewAdapter.setAllDatas(list);
//        assetReviewAdapter.notifyDataSetChanged(list);
     refreashView(list,rcy);
    }



    private List<RecyclerViewData> getRecyclerViewData(List<Assets> itemList) {
        for (int i = 0;i<itemList.size();i++){
            list.add(new RecyclerViewData(itemList.get(i),itemList.get(i).getGlods(),true));
        }
        return list;
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
