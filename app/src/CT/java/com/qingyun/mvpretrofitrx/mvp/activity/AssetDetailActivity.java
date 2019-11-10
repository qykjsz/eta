package com.qingyun.mvpretrofitrx.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.develop.wallet.eth.Wallet;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.CopyUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.senon.mvpretrofitrx.R;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils.ASSET_RESPONSE;

public class AssetDetailActivity extends BaseActivity {
    @BindView(R.id.tv_income_today)
    TextView tvIncomeToday;
    @BindView(R.id.tv_wallet_name)
    TextView tvWalletName;
    @BindView(R.id.tv_publish_key)
    TextView tvPublishKey;
    @BindView(R.id.tv_asset)
    TextView tvAsset;

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
        return null;
    }

    @Override
    public boolean haveHeader() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_asset_detail;
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
        AssetResponse assetResponse = (AssetResponse) getIntent().getSerializableExtra(ASSET_RESPONSE);
        tvPublishKey.setText(ApplicationUtil.getCurrentWallet().getPublicKey());
        tvWalletName.setText(ApplicationUtil.getCurrentWallet().getWalletName());
        tvIncomeToday.setText(assetResponse.getToday());
        tvAsset.setText(assetResponse.getAllnumber());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_change_password, R.id.btn_export_private_key, R.id.btn_export_key_store})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_change_password:
                startActivity(ChangeWalletPasswordActivity.class);
                break;
            case R.id.btn_export_private_key:
                startActivity(ExportPrivateKeyActivity.class);
                break;
            case R.id.btn_export_key_store:
                startActivity(ExportKeystoreActivity.class);

                break;
        }
    }

    @OnClick({R.id.btn_back, R.id.btn_visiable, R.id.btn_copy_publish_key, R.id.btn_delete_wallet})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_visiable:
                break;
            case R.id.btn_delete_wallet:
                DialogUtils.showConfirmDialog(getActivity(), 0, R.string.sure_to_delete_wallet, R.string.cancel, R.string.delete_wallet, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean haveWallet = false;
                        ApplicationUtil.deleteWallet(ApplicationUtil.getCurrentWallet());
                        if (ApplicationUtil.getWallet().entrySet() != null && ApplicationUtil.getWallet().entrySet().size() > 0) {
                            for (Map.Entry<String, List<Wallet>> entry : ApplicationUtil.getWallet().entrySet()) {
                                if (entry.getValue() != null && entry.getValue().size() > 0) {
                                    haveWallet = true;
                                    ApplicationUtil.setCurrentWallet(entry.getValue().get(0));
                                    break;
                                }
                            }
                        }
                        if (haveWallet == false) {
                            ApplicationUtil.setCurrentWallet(null);
                        }
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
                break;
            case R.id.btn_copy_publish_key:
                CopyUtils.copy(getContext(), tvPublishKey.getText().toString());
                break;
        }
    }

}
