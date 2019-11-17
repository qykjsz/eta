package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetReviewResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;


public interface AssetsReviewContact {

    interface View extends BaseView {

        void getAssetsReviewSuccess(AssetReviewResponse assetReviewResponse);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getAssetsReview(RequestBody address);


    }
}
