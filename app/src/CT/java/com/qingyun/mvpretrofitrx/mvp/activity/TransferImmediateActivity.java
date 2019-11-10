package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.develop.wallet.eth.Wallet;
import com.develop.wallet.eth.WalletManager;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.Contact;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransferImmediateActivity extends BaseActivity {
    @BindView(R.id.tv_assdrss)
    EditText tvAssdrss;
    @BindView(R.id.tv_amount)
    EditText tvAmount;
    @BindView(R.id.tv_note)
    EditText tvNote;
    @BindView(R.id.tv_mining)
    TextView tvMining;
    @BindView(R.id.tv_eth_banlance)
    TextView tvEthBanlance;


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
        return getResources().getString(R.string.transfer_immediate);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_transfer_immediate;
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
        EventBus.getDefault().register(this);
        String address = getIntent().getStringExtra(IntentUtils.TRANSFER_ADDRESS);
        if (address!=null)
        tvAssdrss.setText(address);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_contact, R.id.btn_all,R.id.btn_transfer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_contact:
                break;
            case R.id.btn_all:
                break;
            case R.id.btn_transfer:
                WalletManager.sendTransactionByPrivateKey(ApplicationUtil.getCurrentWallet().getAddress(),
                        ApplicationUtil.getCurrentWallet().getPrivateKey(), tvAssdrss.getText().toString(),tvAmount.getText().toString()
                );
                ToastUtil.showShortToast(R.string.transfer_success);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getContact(Contact contact) {
        tvAssdrss.setText(contact.getAddress());
    }

}
