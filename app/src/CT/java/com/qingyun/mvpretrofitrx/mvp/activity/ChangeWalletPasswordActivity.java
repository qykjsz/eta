package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.develop.wallet.eth.Wallet;
import com.develop.wallet.eth.WalletManager;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.qingyun.mvpretrofitrx.mvp.weight.dialog.ProgressDialogUtils;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeWalletPasswordActivity extends BaseActivity {
    @BindView(R.id.et_current_password)
    EditText etCurrentPassword;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.ben_confirm)
    TextView benConfirm;

    @Override
    protected String getTitleRightText() {
        return null;
    }


    @Override
    public boolean haveHeader() {
        return true;
    }

    @Override
    protected String getTitleLeftText() {
        return null;
    }

    @Override
    protected String getTitleText() {
        return getResources().getString(R.string.change_password);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_wallet_password;
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

    @OnClick(R.id.ben_confirm)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etCurrentPassword.getText().toString()))
        {
            ToastUtil.showShortToast(R.string.old_password_must);
            return;

        }

        if (TextUtils.isEmpty(etNewPassword.getText().toString()))
        {
            ToastUtil.showShortToast(R.string.password_must);
            return;
        }


        if (TextUtils.isEmpty(etConfirmPassword.getText().toString()))
        {
            ToastUtil.showShortToast(R.string.connfirm_password_must);
            return;
        }

        if (!etNewPassword.getText().toString().equals(etConfirmPassword.getText().toString()))
        {
            ToastUtil.showShortToast(R.string.tow_input_must_same);
            return;
        }

        ProgressDialogUtils.getInstances().showDialog();

        WalletManager.changePassword(getContext(), etCurrentPassword.getText().toString(), etNewPassword.getText().toString(), ApplicationUtil.getCurrentWallet(), new WalletManager.ImportWalletListener() {
            @Override
            public void importSuccess(Wallet wallet) {
                ToastUtil.showShortToast(R.string.change_password_success);
                wallet.setStatus(ApplicationUtil.getCurrentWallet().getStatus());
                ApplicationUtil.setCurrentWallet(wallet);
                ApplicationUtil.deleteWallet(wallet);
                ApplicationUtil.addWallet(wallet);
                finish();
                ProgressDialogUtils.getInstances().cancel();

            }

            @Override
            public void importFailure(Exception e) {
                ToastUtil.showShortToast(R.string.pass_err);
                ProgressDialogUtils.getInstances().cancel();

            }
        });

    }
}
