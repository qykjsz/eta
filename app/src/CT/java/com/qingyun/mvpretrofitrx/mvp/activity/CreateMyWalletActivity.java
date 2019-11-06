package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.develop.wallet.eth.Wallet;
import com.develop.wallet.eth.WalletManager;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.progress.ProgressCancelListener;
import com.qingyun.mvpretrofitrx.mvp.progress.ProgressDialogHandler;
import com.qingyun.mvpretrofitrx.mvp.progress.ProgressObserver;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.LoadingUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.qingyun.mvpretrofitrx.mvp.weight.dialog.ProgressDialogUtils;
import com.senon.mvpretrofitrx.R;

import org.web3j.crypto.WalletUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CreateMyWalletActivity extends BaseActivity implements Observer, ProgressCancelListener {
    @BindView(R.id.et_wallet_name)
    EditText etWalletName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password_confirm)
    EditText etPasswordConfirm;
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
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
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
        if (etPassword.getText().length()<8){
            ToastUtil.showShortToast(R.string.not_lower_8);
            return;
        }
        if (!etPassword.getText().toString().equals(etPasswordConfirm.getText().toString())){
            ToastUtil.showShortToast(R.string.tow_input_must_same);
            return;
        }
        LoadingUtils.showLoading(getContext());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Wallet wallet = WalletManager.generateWalletAddress();
                Log.e("------------", wallet.getAddress());
                Log.e("------------", wallet.getPrivateKey());
                ApplicationUtil.setCurrentWallet(wallet);
//                Wallet wallet1 = WalletManager.generateWalletKeystore(etPassword.getText().toString(), wallet.getMnemonic());
                handler.sendEmptyMessage(1);
            }
        });
        thread.start();
//        mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();

    }


     Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
           if ( msg.what == 1){
               LoadingUtils.loadingDismiss();
               ToastUtil.showShortToast(R.string.create_wallet_success);

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
}
