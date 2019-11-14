package com.qingyun.mvpretrofitrx.mvp.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

import com.develop.wallet.eth.Wallet;
import com.develop.wallet.eth.WalletManager;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.contract.WalletAssetContact;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.GasPrice;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLogResponse;
import com.qingyun.mvpretrofitrx.mvp.presenter.WalletAssetPresenter;
import com.qingyun.mvpretrofitrx.mvp.progress.ProgressCancelListener;
import com.qingyun.mvpretrofitrx.mvp.progress.ProgressDialogHandler;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.LoadingUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class CreateMyWalletActivity extends BaseActivity<WalletAssetContact.View, WalletAssetContact.Presenter> implements Observer, ProgressCancelListener, WalletAssetContact.View {
    @BindView(R.id.et_wallet_name)
    EditText etWalletName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password_confirm)
    EditText etPasswordConfirm;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    private ProgressDialogHandler mProgressDialogHandler;

    @Override
    protected String getTitleRightText() {
        return null;
    }

    @Override
    protected String getTitleLeftText() {
        return null;
    }

    @Override
    public boolean haveHeader() {
        return true;
    }

    @Override
    protected String getTitleText() {
        return getResources().getString(R.string.create_wallet);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_my_wallet;
    }

    @Override
    public WalletAssetContact.Presenter createPresenter() {
        return new WalletAssetPresenter(this);
    }

    @Override
    public WalletAssetContact.View createView() {
        return this;
    }


    @Override
    public void init() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_create_wallet)
    public void onViewClicked() {
        if (!checkBox.isChecked()){
            ToastUtil.showShortToast(R.string.sure_have_read_agree);
            return;
        }
        if (etPassword.getText().length() < 8) {
            ToastUtil.showShortToast(R.string.not_lower_8);
            return;
        }
        if (!etPassword.getText().toString().equals(etPasswordConfirm.getText().toString())) {
            ToastUtil.showShortToast(R.string.tow_input_must_same);
            return;
        }
        LoadingUtils.showLoading(getContext());


        RxPermissions rxPermissions=new RxPermissions(getActivity());
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean){

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {


                            Wallet wallet = WalletManager.generateWalletAddress(etWalletName.getText().toString());
                            wallet = WalletManager.generateWalletKeystore(etPassword.getText().toString(), wallet.getMnemonic(),getContext().getFilesDir());
                            Log.e("------------", wallet.getAddress());
                            Log.e("------------", wallet.getPrivateKey());
                            wallet.setWalletName(etWalletName.getText().toString());
                            ApplicationUtil.setCurrentWallet(wallet);
                            ApplicationUtil.addWallet(wallet);
                            EventBus.getDefault().post(wallet);
//                Wallet wallet1 = WalletManager.generateWalletKeystore(etPassword.getText().toString(), wallet.getMnemonic());
                            handler.sendEmptyMessage(1);
                        }
                    });
                    thread.start();
//        mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();

                }else{
                    //只要有一个权限被拒绝，就会执行
                    ToastUtil.showShortToast(R.string.storage_permission_need_open);
                }
            }
        });





    }


    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                getPresenter().addWallet(ApplicationUtil.getCurrentWallet().getAddress());

//               LoadingUtils.loadingDismiss();
//               ToastUtil.showShortToast(R.string.create_wallet_success);
//               finish();
//               startActivity(MakeCopyWalletActivity.class);
            }
            return true;
        }
    });

    @Override
    public void onCancelProgress() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Object o) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void getWalletInfoSuccess(AssetResponse assetResponse) {

    }

    @Override
    public void addWalletSuccess() {
        LoadingUtils.loadingDismiss();
        ToastUtil.showShortToast(R.string.create_wallet_success);
        finish();
        startActivity(MakeCopyWalletActivity.class);
    }

    @Override
    public void getLogSuccess(TransferLogResponse transferLogResponse) {


    }

    @Override
    public void getNodeSuccess(String node) {

    }

    @Override
    public void searchLogByHashSuccess(TransferLog transferLog) {

    }

    @Override
    public void getGasPriceSuccess(List<GasPrice> gasPrices) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
