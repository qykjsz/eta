package com.qingyun.mvpretrofitrx.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.develop.wallet.eth.Wallet;
import com.develop.wallet.eth.WalletManager;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.contract.WalletAssetContact;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLogResponse;
import com.qingyun.mvpretrofitrx.mvp.presenter.WalletAssetPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class ImportByKeystoreActivity extends BaseActivity<WalletAssetContact.View, WalletAssetContact.Presenter> implements WalletAssetContact.View {
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.et_wallet_name)
    EditText etWalletName;
    @BindView(R.id.et_keystore_password)
    EditText etKeystorePassword;
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
        return getResources().getString(R.string.keystore);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_import_by_keystore;
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
        setIvTitleRight(R.mipmap.icon_scan, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        if (TextUtils.isEmpty(etKeystorePassword.getText().toString())){
            ToastUtil.showShortToast(R.string.password_must);

            return;
        }


        if (TextUtils.isEmpty(etWalletName.getText().toString())){
            ToastUtil.showShortToast(R.string.wallet_name_must);
            return;
        }
        if (!cbRead.isChecked()){
            ToastUtil.showShortToast(R.string.sure_have_read_agree);
            return;
        }

        WalletManager.importWalletByKeystore(etKeystorePassword.getText().toString(), etContent.getText().toString(), etWalletName.getText().toString(), new WalletManager.ImportWalletListener() {
            @Override
            public void importSuccess(Wallet wallet) {
                getPresenter().addWallet(wallet.getAddress());
                ApplicationUtil.setCurrentWallet(wallet);
                ApplicationUtil.addWallet(wallet);

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
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
