package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.develop.wallet.eth.WalletManager;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.contract.CoinContact;
import com.qingyun.mvpretrofitrx.mvp.contract.WalletAssetContact;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Contact;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLogResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.qingyun.mvpretrofitrx.mvp.presenter.CoinPresenter;
import com.qingyun.mvpretrofitrx.mvp.presenter.WalletAssetPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class TransferImmediateActivity extends BaseActivity<WalletAssetContact.View, WalletAssetContact.Presenter> implements WalletAssetContact.View {
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
    @BindView(R.id.btn_coin_type)
    TextView btnCoinType;
    private List<Wallet> coins;
    private Wallet currentCoin;

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
        String address = getIntent().getStringExtra(IntentUtils.TRANSFER_ADDRESS);
        currentCoin = (Wallet) getIntent().getSerializableExtra(IntentUtils.ASSET);

        if (address != null)
            tvAssdrss.setText(address);
        getPresenter().getWalletInfo(ApplicationUtil.getCurrentWallet().getAddress());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_contact, R.id.btn_all, R.id.btn_transfer,R.id.btn_coin_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_coin_type:
                if (coins!=null){
                    DialogUtils.showCoinChooseDialog(getActivity(), coins, new BaseAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(List list, int position) {
                            currentCoin = (Wallet) list.get(position);
                            refreashData();
                        }
                    });
                }
                break;
            case R.id.btn_contact:
                break;
            case R.id.btn_all:
                break;
            case R.id.btn_transfer:
                getPresenter().getNode();

                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getContact(Contact contact) {
        tvAssdrss.setText(contact.getAddress());
    }




    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }

    @Override
    public void getWalletInfoSuccess(AssetResponse assetResponse) {
        this.coins = assetResponse.getGlod();
        if (currentCoin==null&&coins!=null&&coins.size()>1){
            currentCoin = coins.get(0);
        }
        if (currentCoin!=null){
            refreashData();
        }
    }

    private void refreashData() {
        btnCoinType.setText(currentCoin.getName());
        tvEthBanlance.setText(currentCoin.getNumber());
    }

    @Override
    public void addWalletSuccess() {

    }

    @Override
    public void getLogSuccess(TransferLogResponse transferLogResponse) {

    }

    @Override
    public void getNodeSuccess(String node) {
        WalletManager.config(node,currentCoin.getHyaddress(),false);
        WalletManager.sendTransactionByPrivateKey(ApplicationUtil.getCurrentWallet().getAddress(),
                ApplicationUtil.getCurrentWallet().getPrivateKey(), tvAssdrss.getText().toString(), tvAmount.getText().toString()
        );
        ToastUtil.showShortToast(R.string.transfer_success);
    }
}
