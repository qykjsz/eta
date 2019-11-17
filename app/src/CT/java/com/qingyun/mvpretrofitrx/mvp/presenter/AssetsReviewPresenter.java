package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.contract.AboutUsContact;
import com.qingyun.mvpretrofitrx.mvp.contract.AssetsReviewContact;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetReviewResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.qingyun.mvpretrofitrx.mvp.model.AboutUsModel;
import com.qingyun.mvpretrofitrx.mvp.model.AssetReviewModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import java.util.List;

import okhttp3.RequestBody;

public class AssetsReviewPresenter extends AssetsReviewContact.Presenter {
    private AssetReviewModel model;
    private Context context;


    public AssetsReviewPresenter(Context context) {
        this.model = new AssetReviewModel();
        this.context = context;
    }




    public void getAssetsReview(RequestBody address) {
        model.getAssetsReview(context,address,getView().bindLifecycle(), new ObserverResponseListener<AssetReviewResponse>() {

            @Override
            public void onNext(AssetReviewResponse assetReviewResponse) {
                if(getView() != null){
                    getView().getAssetsReviewSuccess(assetReviewResponse);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }
}
