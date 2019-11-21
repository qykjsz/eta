package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.GasPrice;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLogResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.VersionInfo;

import java.util.List;


public interface WalletAssetContact {

    interface View extends BaseView {

        void getWalletInfoSuccess(AssetResponse assetResponse);
        void addWalletSuccess();
        void getLogSuccess(TransferLogResponse transferLogResponse);
        void getNodeSuccess(String node);
        void searchLogByHashSuccess(TransferLog transferLog);
        void getGasPriceSuccess(List<GasPrice> gasPrices);
        void getVersionSuccess(VersionInfo versionInfo);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getWalletInfo(String walletAddress);
        public abstract void addWallet(String walletAddress);
//        glod 复制	[string]	是	查询币种 如 ETH 传0为全部币种
//        type	[string]	是	交易类型 1.转入 2.转入 3.全部
        public abstract void getLog(String address,String glod,int type,int page);
        public abstract void getNode();
        public abstract void searchLogByHash(String address,String hash,String glod);
        public abstract void getGasPrice();
        public abstract void getVersion();

    }
}
