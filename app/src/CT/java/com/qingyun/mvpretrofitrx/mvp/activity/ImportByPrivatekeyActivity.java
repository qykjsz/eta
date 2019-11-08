package com.qingyun.mvpretrofitrx.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.develop.wallet.eth.Wallet;
import com.develop.wallet.eth.WalletManager;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.contract.WalletAssetContact;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.presenter.WalletAssetPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class ImportByPrivatekeyActivity extends BaseActivity<WalletAssetContact.View,WalletAssetContact.Presenter> implements WalletAssetContact.View {
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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_import)
    public void onViewClicked() {
        WalletManager.importWalletByPrivateKey(etContent.getText().toString(), etWalletName.getText().toString(),etPassword.getText().toString(), new WalletManager.ImportWalletListener() {
            @Override
            public void importSuccess(Wallet wallet) {
                ApplicationUtil.setCurrentWallet(wallet);
                ApplicationUtil.addWallet(wallet);
                getPresenter().addWallet(wallet.getAddress());

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
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }

}
