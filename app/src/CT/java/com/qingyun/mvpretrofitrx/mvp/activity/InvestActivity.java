package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.develop.wallet.eth.WalletManager;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.contract.InvestContact;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinTypeRate;
import com.qingyun.mvpretrofitrx.mvp.entity.CurrencyRate;
import com.qingyun.mvpretrofitrx.mvp.entity.InvestLog;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Platform;
import com.qingyun.mvpretrofitrx.mvp.presenter.InvestPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.qingyun.mvpretrofitrx.mvp.weight.dialog.ProgressDialogUtils;
import com.senon.mvpretrofitrx.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class InvestActivity extends BaseActivity<InvestContact.View, InvestContact.Presenter> implements InvestContact.View {
    @BindView(R.id.tv_platform)
    BoldTextView tvPlatform;
    @BindView(R.id.tv_account)
    EditText tvAccount;
    @BindView(R.id.et_amount)
    EditText etAmount;
    @BindView(R.id.tv_coin_type)
    TextView tvCoinType;
    @BindView(R.id.cb)
    CheckBox cb;
    @BindView(R.id.tv_price)
    BoldTextView tvPrice;
    @BindView(R.id.iv_account_true)
    ImageView ivAccountTrue;
    @BindView(R.id.btn_account)
    TextView btnAccount;
    @BindView(R.id.tv_coin_name)
    TextView tvCoinName;
    private List<Platform> platformList;
    private List<String> amountList;
    private CoinTypeRate currentCoin;
    private Platform currentPlatform;
    private String currentRatio;
    private Boolean checkAccountSuccess = false;

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
        return getResources().getString(R.string.inverst_center);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_invest;
    }

    @Override
    public InvestContact.Presenter createPresenter() {
        return new InvestPresenter(this);
    }

    @Override
    public InvestContact.View createView() {
        return this;
    }

    @Override
    public void init() {
        getPresenter().getCurrencyRate();
        getPresenter().getCoinTypeRate();
        getPresenter().getSuprtPlatform();
        getPresenter().getInvestAmountList();

        tvAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ivAccountTrue.setVisibility(View.GONE);
                btnAccount.setVisibility(View.VISIBLE);
            }
        });
        addSearch();
    }

    BigDecimal amount;
    BigDecimal platformRatio;
    BigDecimal coinRatio;
    BigDecimal price;

    private void addSearch() {

        RxTextView.textChanges(etAmount)//当EditText发生改变
                //每500毫秒发射一次
                //仅在过了一段指定的时间还没发射数据时才发射一个数据
                //如果原始Observable在这个新生成的Observable终止之前发射了另一个数据， debounce 会抑制(suppress)这个数据项。
                .debounce(200, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())//内容监听操作需要在主线程操作
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(CharSequence charSequence) throws Exception {
                        if (charSequence.toString().startsWith(".")) {
                            charSequence = null;
                            etAmount.setText(charSequence);
                        }
                        return charSequence.length() >= 0;
                    }
                })
                .observeOn(Schedulers.io())
                .switchMap(new Function<CharSequence, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(CharSequence charSequence) throws Exception {
                        Observable<String> just = Observable.just(charSequence.toString());
                        return just;

                    }
                })

//
                .retry()//凡是请求出错就重试（例如超时、数据解析异常等），直到正确为止。（如果不retry的话就会调用onError。onError会导致整个订阅链条死掉，无法触发下一次了）
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        if (TextUtils.isEmpty(s)) {
                            s = "0";
                        }
                        amount = new BigDecimal(s);
                        if (currentPlatform == null || currentCoin == null || currentRatio == null)
                            return;
                        platformRatio = new BigDecimal(currentPlatform.getProportion());
                        coinRatio = new BigDecimal(currentCoin.getRate());
                        price = amount.multiply(new BigDecimal(currentRatio)).multiply(platformRatio).divide(coinRatio, 4, RoundingMode.UP);
                        tvPrice.setText(price.toString());
                        tvCoinName.setText(currentCoin.getName());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getNodeSuccess(String node) {
        WalletManager.config(node, currentCoin.getAddress(), false);
        Log.e("node>>>>", node);
        Log.e("Hyaddress>>>>", currentCoin.getAddress() == null ? "----" : currentCoin.getAddress());
        getPresenter().checkInvestInfo(currentPlatform.getId(), etAmount.getText().toString(), currentCoin.getName(), tvPrice.getText().toString());

    }

    @Override
    public void getCoinTypeRateSuccess(List<CoinTypeRate> coinTypeRateList) {
        if (currentCoin == null && coinTypeRateList != null && coinTypeRateList.size() > 0) {
            currentCoin = coinTypeRateList.get(0);
            tvCoinType.setText(currentCoin.getName());
            tvCoinName.setText(currentCoin.getName());

            return;
        }
        DialogUtils.showCoinRateChooseDialog(getActivity(), coinTypeRateList, new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                currentCoin = (CoinTypeRate) list.get(position);
                tvCoinName.setText(currentCoin.getName());
                tvCoinType.setText(currentCoin.getName());
                if (TextUtils.isEmpty(etAmount.getText().toString())) {
                    amount = new BigDecimal("0");

                } else {
                    amount = new BigDecimal(etAmount.getText().toString());
                }
                platformRatio = new BigDecimal(currentPlatform.getProportion());
                coinRatio = new BigDecimal(currentCoin.getRate());
                price = amount.multiply(new BigDecimal(currentRatio)).multiply(platformRatio).divide(coinRatio, 4, RoundingMode.UP);
                tvPrice.setText(price.toString());

            }
        });
    }

    @Override
    public void checkAccountSuccess(NormalResponse normalResponse) {
        ivAccountTrue.setVisibility(View.VISIBLE);
        btnAccount.setVisibility(View.GONE);
        checkAccountSuccess = true;

        Glide.with(getContext()).load(R.mipmap.yz_cg_icon).into(ivAccountTrue);
    }

    @Override
    public void checkAccountFailure() {
        ivAccountTrue.setVisibility(View.VISIBLE);
        btnAccount.setVisibility(View.GONE);
        checkAccountSuccess = false;
        Glide.with(getContext()).load(R.mipmap.yz_sb_icon).into(ivAccountTrue);

    }

    @Override
    public void addInvestInfoSuccess(NormalResponse normalResponse) {

    }

    @Override
    public void getSuprtPlatformSuccess(List<Platform> platformList) {
        if (currentPlatform == null && platformList != null && platformList.size() > 0) {
            currentPlatform = platformList.get(0);
            tvPlatform.setText(currentPlatform.getName());
            return;
        }
        DialogUtils.showPlatformChooseDialog(getActivity(), platformList, new BaseAdapter.OnItemClickListener() {
            Platform platform;

            @Override
            public void onItemClick(List list, int position) {
                platform = (Platform) list.get(position);
                currentPlatform = platform;
                tvPlatform.setText(platform.getName());
                if (TextUtils.isEmpty(etAmount.getText().toString())) {
                    amount = new BigDecimal("0");

                } else {
                    amount = new BigDecimal(etAmount.getText().toString());
                }
                platformRatio = new BigDecimal(currentPlatform.getProportion());
                coinRatio = new BigDecimal(currentCoin.getRate());
                price = amount.multiply(new BigDecimal(currentRatio)).multiply(platformRatio).divide(coinRatio, 4, RoundingMode.UP);
                tvPrice.setText(price.toString());

            }
        });
    }

    @Override
    public void getInvestAmountListSuccess(List<String> amounts) {
        if (amountList == null && amounts != null && amounts.size() > 0) {
            amountList = amounts;
            etAmount.setText(amountList.get(0));
            return;
        }
        DialogUtils.showStringDialog(getActivity(), getResources().getString(R.string.invest_amount), amountList, new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                etAmount.setText((String) list.get(position));
            }
        });
    }

    @Override
    public void investSuccess() {
        ToastUtil.showShortToast(R.string.subbmit_success);
//        finish();
    }

    @Override
    public void getInvestLogSuccess(List<InvestLog> investLogList) {

    }

    @Override
    public void checkInvestInfoSuccess(String s) {
        DialogUtils.showInvestPayDialog(getContext(), s, currentCoin.getName(), 60, new DialogUtils.SureListener() {
            @Override
            public void onSure(Object o) {
                ProgressDialogUtils.getInstances().showDialog();
                WalletManager.decrypt(o.toString(), ApplicationUtil.getCurrentWallet(), new WalletManager.CheckPasswordListener() {
                    @Override
                    public void onSuccess() {
                        String hash = WalletManager.sendTransactionByPrivateKey(ApplicationUtil.getCurrentWallet().getAddress(),
                                ApplicationUtil.getCurrentWallet().getPrivateKey(), currentPlatform.getAddress(), tvPrice.getText().toString(), new BigDecimal(currentCoin.getGasprice()).floatValue(), currentCoin.getGaslimit(), Integer.parseInt(currentCoin.getDecimal()));
                        if (!TextUtils.isEmpty(hash)) {
                            ToastUtil.showShortToast(R.string.transfer_success);
                            getPresenter().invest(ApplicationUtil.getCurrentWallet().getAddress(),
                                    currentPlatform.getAddress(),
                                    hash,
                                    currentCoin.getName(),
                                    currentPlatform.getId(),
                                    tvPrice.getText().toString(),
                                    etAmount.getText().toString(),
                                    tvAccount.getText().toString());
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
    public void getCurrencyRateSuccess(List<CurrencyRate> currencyRateList) {
        for (int i = 0; i < currencyRateList.size(); i++) {
            if (currencyRateList.get(i).getId() == 1) {
                currentRatio = currencyRateList.get(i).getRate();
            }
        }

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ly_other, R.id.ly_log, R.id.btn_choose_platform, R.id.btn_account, R.id.btn_amount, R.id.btn_coin_type, R.id.btn_sure_invest})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ly_other:
                break;
            case R.id.ly_log:
                startActivity(InvestLogActivity.class);
                break;
            case R.id.btn_choose_platform:
                getPresenter().getSuprtPlatform();
                break;
            case R.id.btn_account:
                getPresenter().checkAccount(tvAccount.getText().toString());
                break;
            case R.id.btn_amount:

                getPresenter().getInvestAmountList();
                break;
            case R.id.btn_coin_type:
                getPresenter().getCoinTypeRate();
                break;
            case R.id.btn_sure_invest:
                if (TextUtils.isEmpty(tvAccount.getText().toString())) {
                    ToastUtil.showShortToast(R.string.account_not_null);
                    return;
                }
                if (TextUtils.isEmpty(etAmount.getText().toString())) {
                    ToastUtil.showShortToast(R.string.amount_not_null);
                    return;
                }
                BigDecimal amount = new BigDecimal(etAmount.getText().toString());
                if (amount.floatValue() == 0) {
                    ToastUtil.showShortToast(R.string.amount_not_0);
                    return;
                }
                if (ivAccountTrue.getVisibility() == View.GONE || !checkAccountSuccess) {
                    ToastUtil.showShortToast(R.string.must_check_account);
                    return;
                }

                if (!cb.isChecked()) {
                    ToastUtil.showShortToast(R.string.sure_have_read_agree);
                    return;
                }


                getPresenter().getNode();
                break;
        }
    }
}
