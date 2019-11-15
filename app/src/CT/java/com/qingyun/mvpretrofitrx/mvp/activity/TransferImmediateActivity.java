package com.qingyun.mvpretrofitrx.mvp.activity;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.develop.wallet.eth.WalletManager;
import com.qingyun.mvpretrofitrx.mvp.adapter.CoinChooseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.contract.WalletAssetContact;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Contact;
import com.qingyun.mvpretrofitrx.mvp.entity.GasPrice;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLogResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.qingyun.mvpretrofitrx.mvp.presenter.WalletAssetPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.qingyun.mvpretrofitrx.mvp.weight.dialog.ProgressDialogUtils;
import com.senon.mvpretrofitrx.R;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;
import per.goweii.anylayer.AnimHelper;
import per.goweii.anylayer.AnyLayer;
import per.goweii.anylayer.LayerManager;

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
    @BindView(R.id.tv_coin_type)
    TextView tvCoinType;
    @BindView(R.id.btn_gas_price)
    TextView btnGasPrice;
    private List<Wallet> coins;
    private Wallet currentCoin;
    private float mGasPrice;
    private String gasLitmit;

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

    @OnClick({R.id.btn_contact, R.id.btn_all, R.id.btn_transfer, R.id.btn_coin_type,R.id.btn_gas_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_coin_type:
                if (coins != null) {
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
            case R.id.btn_gas_price:
                getPresenter().getGasPrice();
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
        if (currentCoin == null && coins != null && coins.size() > 1) {
            currentCoin = coins.get(0);
        }
        if (currentCoin != null) {
            refreashData();
        }
    }

    private void refreashData() {
        tvCoinType.setText(currentCoin.getName());
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
        WalletManager.config(node, currentCoin.getAddress(), false);
        Log.e("node>>>>",node);
        Log.e("Hyaddress>>>>",currentCoin.getAddress());

        DialogUtils.showPayDialog(getContext(), new DialogUtils.SureListener() {
            @Override
            public void onSure(Object o) {
                ProgressDialogUtils.getInstances().showDialog();
                WalletManager.decrypt(o.toString(), ApplicationUtil.getCurrentWallet(), new WalletManager.CheckPasswordListener() {
                    @Override
                    public void onSuccess() {
                        String hash = WalletManager.sendTransactionByPrivateKey(ApplicationUtil.getCurrentWallet().getAddress(),
                                ApplicationUtil.getCurrentWallet().getPrivateKey(), tvAssdrss.getText().toString(), tvAmount.getText().toString(),mGasPrice,gasLitmit);
                        if (!TextUtils.isEmpty(hash)) {
                            ToastUtil.showShortToast(R.string.transfer_success);
                        } else {
                            ToastUtil.showShortToast(R.string.transfer_failure);

                        }
                        ProgressDialogUtils.getInstances().cancel();

                    }

                    @Override
                    public void onFailure(Exception e) {
                        ToastUtil.showShortToast(R.string.pass_err);
                        ProgressDialogUtils.getInstances().cancel();

                    }
                });
            }
        });

    }

    @Override
    public void searchLogByHashSuccess(TransferLog transferLog) {

    }

    @Override
    public void getGasPriceSuccess(final List<GasPrice> gasPrices) {

        AnyLayer.with(getContext())
                .contentView(R.layout.dialog_gas_price)
                .backgroundColorInt(getResources().getColor(R.color.bg_dialog))
                .gravity(Gravity.BOTTOM)
                .onClickToDismiss(R.id.imageView2)
                .bindData(new LayerManager.IDataBinder() {
                    @Override
                    public void bind(final AnyLayer anyLayer) {
                        final TextView tvGas = anyLayer.getView(R.id.tv_gas);
                        final TextView btnConfirm = anyLayer.getView(R.id.textView53);

                        final IndicatorSeekBar seekBar = anyLayer.getView(R.id.sb);
                        GasPrice gasPrice = null;
                        if (currentCoin.getName().equals("ETH"))
                        {
                            for (int i=0;i<gasPrices.size();i++){
                                if ( gasPrices.get(i).getName().equals("ETH")){
                                    gasPrice = gasPrices.get(i);
                                }
                            }
                        }else {
                            for (int i=0;i<gasPrices.size();i++){
                                if ( gasPrices.get(i).getName().equals("other")){
                                    gasPrice = gasPrices.get(i);
                                }
                            }
                        }
                        gasLitmit = gasPrice.getGasmax();
                        seekBar.setMin(Float.parseFloat(gasPrice.getGweimin()));
                        seekBar.setMax(Float.parseFloat(gasPrice.getGweimax()));
                        seekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
                            @Override
                            public void onSeeking(SeekParams seekParams) {
                                tvGas.setText(seekParams.progressFloat+"USDT");
                            }

                            @Override
                            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

                            }
                        });

                        btnConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvMining.setText(seekBar.getProgressFloat()+"");
                                mGasPrice = seekBar.getProgressFloat();
                                anyLayer.dismiss();
                            }
                        });
                    }
                })

                .contentAnim(new LayerManager.IAnim() {
                    @Override
                    public Animator inAnim(View content) {
                        return AnimHelper.createBottomAlphaInAnim(content);
                    }

                    @Override
                    public Animator outAnim(View content) {
                        return AnimHelper.createBottomAlphaOutAnim(content);
                    }
                })
                .show();
    }
}
