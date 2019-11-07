package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;


public interface WalletAssetContact {

    interface View extends BaseView {

        void getWalletInfoSuccess(AssetResponse assetResponse);
        void addWalletSuccess();

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getWalletInfo(String walletAddress);
        public abstract void addWallet(String walletAddress);
    }
}
