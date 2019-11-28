package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.develop.wallet.eth.WalletManager;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.contract.BusinessPayContact;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessPayEntity;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessPayLog;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinTypeRate;
import com.qingyun.mvpretrofitrx.mvp.entity.CurrencyRate;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;
import com.qingyun.mvpretrofitrx.mvp.presenter.BusinessPayPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
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

import static com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils.BUSINESS_PAY_ENTITY;

public class BuyerPayActivity extends BaseActivity<BusinessPayContact.View, BusinessPayContact.Presenter> implements BusinessPayContact.View {
    BusinessPayEntity businessPayEntity;
    @BindView(R.id.tv_to)
    TextView tvTo;
    @BindView(R.id.tv_price_name)
    TextView tvPriceName;
    @BindView(R.id.tv_price)
    EditText tvPrice;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.iv_coin_img)
    ImageView ivCoinImg;
    @BindView(R.id.btn_reset_price)
    TextView btnResetPrice;
    @BindView(R.id.btn_reset_amount)
    TextView btnResetAmount;
    @BindView(R.id.tv_coin_name)
    TextView tvCoinName;
    private CurrencyRate currencyRate;
    private CoinTypeRate coinTypeRate;
    private BigDecimal amount;

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
        return getResources().getString(R.string.pay_amount);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_buyer_pay;
    }

    @Override
    public BusinessPayContact.Presenter createPresenter() {
        return new BusinessPayPresenter(this);
    }

    @Override
    public BusinessPayContact.View createView() {
        return this;
    }


    @Override
    public void init() {
        businessPayEntity = (BusinessPayEntity) getIntent().getSerializableExtra(BUSINESS_PAY_ENTITY);
        if (!TextUtils.isEmpty(businessPayEntity.getAmount())) {
            btnResetPrice.setVisibility(View.GONE);
            btnResetAmount.setVisibility(View.GONE);
            tvTo.setText(businessPayEntity.getAdddress().substring(0, 5) + "****" + businessPayEntity.getAdddress().substring(businessPayEntity.getAdddress().length() - 5, businessPayEntity.getAdddress().length()));
            tvPriceName.setText(businessPayEntity.getPriceName());
            tvPrice.setText(businessPayEntity.getPrice());
            tvAmount.setText(businessPayEntity.getAmount());
            tvPrice.setEnabled(false);
            tvCoinName.setText(businessPayEntity.getCoinName());
            Glide.with(getActivity()).load(businessPayEntity.getImg()).into(ivCoinImg);


        } else {
            btnResetPrice.setVisibility(View.VISIBLE);
            btnResetAmount.setVisibility(View.VISIBLE);
            tvPrice.setEnabled(true);
            tvTo.setText(businessPayEntity.getAdddress().substring(0, 5) + "****" + businessPayEntity.getAdddress().substring(businessPayEntity.getAdddress().length() - 5, businessPayEntity.getAdddress().length()));
            getPresenter().getCoinTypeRate();
            getPresenter().getCurrencyRate();
            addSearch();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_pay, R.id.btn_reset_price, R.id.btn_reset_amount})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_pay:
                getPresenter().getNode();

                break;
            case R.id.btn_reset_price:
                getPresenter().getCurrencyRate();
                break;
            case R.id.btn_reset_amount:
                getPresenter().getCoinTypeRate();
                break;
        }
    }

    @Override
    public void getCurrencyRateSuccess(List<CurrencyRate> currencyRateList) {

        if (currencyRate == null && currencyRateList != null && currencyRateList.size() > 0) {
            currencyRate = currencyRateList.get(0);
            tvPriceName.setText(currencyRate.getName());

            return;
        }
        DialogUtils.showCurrencyChooseDialog(getActivity(), currencyRateList, new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {

                currencyRate = (CurrencyRate) list.get(position);
                tvPriceName.setText(currencyRate.getName());
                if (!TextUtils.isEmpty(tvPrice.getText().toString()) && currencyRate != null && coinTypeRate != null) {
                    amount = new BigDecimal(tvPrice.getText().toString()).multiply(new BigDecimal(currencyRate.getRate())).divide(new BigDecimal(coinTypeRate.getRate()), 4, RoundingMode.DOWN);
                    tvAmount.setText(amount.setScale(4, RoundingMode.DOWN).toString());

                }
            }
        });

    }

    @Override
    public void getCoinTypeRateSuccess(List<CoinTypeRate> coinTypeRateList) {

        if (coinTypeRate == null && coinTypeRateList != null && coinTypeRateList.size() > 0) {
            coinTypeRate = coinTypeRateList.get(0);
            Glide.with(getContext()).load(coinTypeRate.getImg()).into(ivCoinImg);
            tvCoinName.setText(coinTypeRate.getName());

            return;
        }
        DialogUtils.showCoinRateChooseDialog(getActivity(), coinTypeRateList, new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                coinTypeRate = (CoinTypeRate) list.get(position);
                Glide.with(getContext()).load(coinTypeRate.getImg()).into(ivCoinImg);
                if (!TextUtils.isEmpty(tvPrice.getText().toString()) && currencyRate != null && coinTypeRate != null) {
                    amount = new BigDecimal(tvPrice.getText().toString()).multiply(new BigDecimal(currencyRate.getRate())).divide(new BigDecimal(coinTypeRate.getRate()), 4, RoundingMode.DOWN);
                    tvCoinName.setText(coinTypeRate.getName());
                    tvAmount.setText(amount.setScale(4, RoundingMode.DOWN).toString());

                }
            }
        });

    }

    private void addSearch() {
        RxTextView.textChanges(tvPrice)//当EditText发生改变
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
                            tvPrice.setText(charSequence);
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
                            tvAmount.setText(getResources().getString(R.string._000));
                            return;
                        }
                        if (!TextUtils.isEmpty(s) && currencyRate != null && coinTypeRate != null) {
                            amount = new BigDecimal(s).multiply(new BigDecimal(currencyRate.getRate())).divide(new BigDecimal(coinTypeRate.getRate()), 4, RoundingMode.DOWN);
                            tvAmount.setText(amount.setScale(4, RoundingMode.DOWN).toString());
                        }
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
    public void getBusinessPayLogSuccess(List<BusinessPayLog> businessPayLogList) {

    }

    @Override
    public void addBusinessPayLogSuccess(NormalResponse normalResponse) {
//        ToastUtil.showShortToast(normalResponse.getDetail());
        ToastUtil.showShortToast(R.string.transfer_success);
    }

    @Override
    public void getNodeSuccess(String node) {
        WalletManager.config(node, businessPayEntity.getToken(), false);
        Log.e("node>>>>", node);
        Log.e("Hyaddress>>>>", businessPayEntity.getToken() == null ? "----" : businessPayEntity.getToken());

        DialogUtils.showPayDialog(getContext(), new DialogUtils.SureListener() {
            @Override
            public void onSure(Object o) {
                ProgressDialogUtils.getInstances().showDialog();
                WalletManager.decrypt(o.toString(), ApplicationUtil.getCurrentWallet(), new WalletManager.CheckPasswordListener() {
                    @Override
                    public void onSuccess() {
                        String hash;
                        if (TextUtils.isEmpty(businessPayEntity.getAmount())) {
                            hash = WalletManager.sendTransactionByPrivateKey(ApplicationUtil.getCurrentWallet().getAddress(),
                                    ApplicationUtil.getCurrentWallet().getPrivateKey(), businessPayEntity.getAdddress(), tvAmount.getText().toString(), new BigDecimal(coinTypeRate.getGasprice()).floatValue(), coinTypeRate.getGaslimit(), Integer.parseInt(coinTypeRate.getDecimal()));
                        } else {
                            hash = WalletManager.sendTransactionByPrivateKey(ApplicationUtil.getCurrentWallet().getAddress(),
                                    ApplicationUtil.getCurrentWallet().getPrivateKey(), businessPayEntity.getAdddress(), tvAmount.getText().toString(), new BigDecimal(businessPayEntity.getGasprice()).floatValue(), businessPayEntity.getGaslimit(), Integer.parseInt(businessPayEntity.getDecimal()));
                        }

                        if (!TextUtils.isEmpty(hash)) {
                            if (TextUtils.isEmpty(businessPayEntity.getAmount())) {
                                getPresenter().addBusinessPayLog(ApplicationUtil.getCurrentWallet().getAddress(), businessPayEntity.getAdddress(),
                                        hash, tvPrice.getText().toString(), currencyRate.getName(),
                                        tvAmount.getText().toString(), coinTypeRate.getName());
                            } else {
                                getPresenter().addBusinessPayLog(ApplicationUtil.getCurrentWallet().getAddress(), businessPayEntity.getAdddress(),
                                        hash, businessPayEntity.getPrice(), businessPayEntity.getPriceName(),
                                        businessPayEntity.getAmount(), businessPayEntity.getCoinName());
                            }


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
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
