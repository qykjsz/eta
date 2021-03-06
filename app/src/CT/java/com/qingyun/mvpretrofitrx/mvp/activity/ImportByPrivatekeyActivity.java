package com.qingyun.mvpretrofitrx.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.develop.wallet.eth.Wallet;
import com.develop.wallet.eth.WalletManager;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.contract.WalletAssetContact;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.GasPrice;
import com.qingyun.mvpretrofitrx.mvp.entity.ImportScanResult;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLogResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.VersionInfo;
import com.qingyun.mvpretrofitrx.mvp.presenter.WalletAssetPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.ScanUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.qingyun.mvpretrofitrx.mvp.weight.dialog.ProgressDialogUtils;
import com.senon.mvpretrofitrx.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class ImportByPrivatekeyActivity extends BaseActivity<WalletAssetContact.View, WalletAssetContact.Presenter> implements WalletAssetContact.View {
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password_connfirm)
    EditText etPasswordConnfirm;
    @BindView(R.id.et_wallet_system)
    TextView etWalletSystem;
    @BindView(R.id.et_wallet_name)
    EditText etWalletName;
    @BindView(R.id.cb_read)
    CheckBox cbRead;

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
        return getResources().getString(R.string.import_private_key);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_import_by_privatekey;
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
        EventBus.getDefault().register(this);
        setIvTitleRight(R.mipmap.icon_scan, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanUtil.startImportScan(getActivity());

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_import)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etContent.getText().toString())){
            ToastUtil.showShortToast(R.string.private_key_must);

            return;
        }

        if (TextUtils.isEmpty(etPassword.getText().toString())){
            ToastUtil.showShortToast(R.string.password_must);

            return;
        }

        if (TextUtils.isEmpty(etPasswordConnfirm.getText().toString())){
            ToastUtil.showShortToast(R.string.connfirm_password_must);

            return;
        }

        if (TextUtils.isEmpty(etWalletName.getText().toString())){
            ToastUtil.showShortToast(R.string.wallet_name_must);
            return;
        }
        if (!etPassword.getText().toString().equals(etPasswordConnfirm.getText().toString()))
        {
            ToastUtil.showShortToast(R.string.tow_input_must_same);
            return;
        }
        if (!cbRead.isChecked()){
            ToastUtil.showShortToast(R.string.sure_have_read_agree);
            return;
        }
        ProgressDialogUtils.getInstances().showDialog();

        WalletManager.importWalletByPrivateKey(etContent.getText().toString(), etWalletName.getText().toString(), etPassword.getText().toString(), new WalletManager.ImportWalletListener() {
            @Override
            public void importSuccess(Wallet wallet) {
                wallet.setStatus(Wallet.STATUS_CAN_NOT_MAKE_COPY);
                ApplicationUtil.setCurrentWallet(wallet);
                ApplicationUtil.addWallet(wallet);
                getPresenter().addWallet(wallet.getAddress());
                ProgressDialogUtils.getInstances().cancel();

            }

            @Override
            public void importFailure(Exception e) {
                ToastUtil.showShortToast(e.toString());
                ProgressDialogUtils.getInstances().cancel();

            }
        });
    }


    @Override
    public void getWalletInfoSuccess(AssetResponse assetResponse) {

    }

    @Override
    public void addWalletSuccess() {
        finish();
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
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
    public void getVersionSuccess(VersionInfo versionInfo) {

    }

    @Override
    public void checkCanTransferSuccess() {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreashWallet(ImportScanResult importScanResult) {
        etContent.setText(importScanResult.getAddress());
    }

}
