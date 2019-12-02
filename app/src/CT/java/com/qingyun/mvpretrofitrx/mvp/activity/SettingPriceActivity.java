package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
import com.qingyun.mvpretrofitrx.mvp.utils.KeyboardUtils;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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

public class SettingPriceActivity extends BaseActivity<BusinessPayContact.View, BusinessPayContact.Presenter> implements BusinessPayContact.View {
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_amount)
    TextView etAmount;
    @BindView(R.id.tv_coin_type)
    TextView tvCoinType;
    @BindView(R.id.iv_coin_type)
    ImageView ivCoinType;
    @BindView(R.id.btn_huobi)
    BoldTextView btnHuobi;
    private CoinTypeRate coinTypeRate;
    private CurrencyRate currencyRate;
    private BigDecimal amount;
    private int digits = 2;

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
        return getResources().getString(R.string.setting_price);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting_price;
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
        addSearch();
        getPresenter().getCurrencyRate();
        getPresenter().getCoinTypeRate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_huobi, R.id.btn_confirm, R.id.tv_coin_type,R.id.btn_currency})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_huobi:
            case R.id.btn_currency:
                getPresenter().getCurrencyRate();
                break;
            case R.id.tv_coin_type:
                getPresenter().getCoinTypeRate();
                break;
            case R.id.btn_confirm:
                BusinessPayEntity businessPayEntity = new BusinessPayEntity();
                businessPayEntity.setAdddress(ApplicationUtil.getCurrentWallet().getAddress());
                businessPayEntity.setAmount(etAmount.getText().toString());
                businessPayEntity.setCoinName(tvCoinType.getText().toString());
                businessPayEntity.setDecimal(coinTypeRate.getDecimal());
                businessPayEntity.setGaslimit(coinTypeRate.getGaslimit());
                businessPayEntity.setGasprice(coinTypeRate.getGasprice());
                businessPayEntity.setImg(coinTypeRate.getImg());
                businessPayEntity.setPrice(etPrice.getText().toString());
                businessPayEntity.setPriceName(btnHuobi.getText().toString());
                businessPayEntity.setToken(coinTypeRate.getAddress());
                EventBus.getDefault().post(businessPayEntity);
                finish();
                break;
        }
    }

    @Override
    public void getCurrencyRateSuccess(List<CurrencyRate> currencyRateList) {
        if (currencyRate==null&&currencyRateList!=null&&currencyRateList.size()>0){
            currencyRate=currencyRateList.get(0);
            btnHuobi.setText(currencyRate.getName());
            return;
        }
        DialogUtils.showCurrencyChooseDialog(getActivity(), currencyRateList, new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {

                currencyRate = (CurrencyRate) list.get(position);
                btnHuobi.setText(currencyRate.getName());
                if (!TextUtils.isEmpty(etPrice.getText().toString())&&currencyRate!=null&&coinTypeRate!=null)
                {
                    amount = new BigDecimal(etPrice.getText().toString()).multiply(new BigDecimal(currencyRate.getRate())).divide(new BigDecimal(coinTypeRate.getRate()),4, RoundingMode.DOWN);
                    etAmount.setText(amount.setScale(4, RoundingMode.DOWN).toString());

                }
            }
        });
    }

    @Override
    public void getCoinTypeRateSuccess(List<CoinTypeRate> coinTypeRateList) {
        if (coinTypeRate==null&&coinTypeRateList!=null&&coinTypeRateList.size()>0){
            coinTypeRate=coinTypeRateList.get(0);
            tvCoinType.setText(coinTypeRate.getName());
            Glide.with(getContext()).load(coinTypeRate.getImg()).into(ivCoinType);
            return;
        }
        DialogUtils.showCoinRateChooseDialog(getActivity(), coinTypeRateList, new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                coinTypeRate = (CoinTypeRate) list.get(position);
                tvCoinType.setText(coinTypeRate.getName());
                Glide.with(getContext()).load(coinTypeRate.getImg()).into(ivCoinType);
                if (!TextUtils.isEmpty(etPrice.getText().toString())&&currencyRate!=null&&coinTypeRate!=null)
                {
                    amount = new BigDecimal(etPrice.getText().toString()).multiply(new BigDecimal(currencyRate.getRate())).divide(new BigDecimal(coinTypeRate.getRate()),4, RoundingMode.DOWN);

                    etAmount.setText(amount.setScale(4, RoundingMode.DOWN).toString());

                }
            }
        });
    }

    @Override
    public void getBusinessPayLogSuccess(List<BusinessPayLog> businessPayLogList) {

    }

    @Override
    public void addBusinessPayLogSuccess(NormalResponse normalResponse) {

    }

    @Override
    public void getNodeSuccess(String node) {

    }

    private void addSearch() {
        RxTextView.textChanges(etPrice)//当EditText发生改变
                //每500毫秒发射一次
                //仅在过了一段指定的时间还没发射数据时才发射一个数据
                //如果原始Observable在这个新生成的Observable终止之前发射了另一个数据， debounce 会抑制(suppress)这个数据项。
                .debounce(200, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())//内容监听操作需要在主线程操作
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(CharSequence charSequence) throws Exception {
                        if (charSequence.toString().startsWith("."))
                        {
                            charSequence=null;
                            etPrice.setText(charSequence);
                        }

                        if (charSequence.toString().contains(".")) {
                            if (charSequence.length() - 1 - charSequence.toString().indexOf(".") > digits) {
                                charSequence = charSequence.toString().subSequence(0,
                                        charSequence.toString().indexOf(".") + digits + 1);
                                etPrice.setText(charSequence);
                                etPrice.setSelection(charSequence.length()); //光标移到最后

                            }
                        }
                        return charSequence.length() >=0;
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
                        if (TextUtils.isEmpty(s)){
                            etAmount.setText(getResources().getString(R.string._000));
                            return;
                        }
                        if (!TextUtils.isEmpty(s)&&currencyRate!=null&&coinTypeRate!=null)
                        {
                            amount = new BigDecimal(s).multiply(new BigDecimal(currencyRate.getRate())).divide(new BigDecimal(coinTypeRate.getRate()),4, RoundingMode.DOWN);
                            etAmount.setText(amount.setScale(4, RoundingMode.DOWN).toString());
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
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
