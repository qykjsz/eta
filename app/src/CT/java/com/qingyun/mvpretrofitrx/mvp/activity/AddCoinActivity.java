package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.qingyun.mvpretrofitrx.mvp.adapter.WalletAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.contract.CoinContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.qingyun.mvpretrofitrx.mvp.presenter.CoinPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.qingyun.mvpretrofitrx.mvp.utils.KeyboardUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;

import org.greenrobot.eventbus.EventBus;

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

public class AddCoinActivity extends BaseActivity<CoinContact.View, CoinContact.Presenter> implements CoinContact.View {
    @BindView(R.id.rcy)
    RecyclerView rcy;
    WalletAdapter walletAdapter;
    @BindView(R.id.editText)
    EditText editText;
    private List<Wallet> list;
    private List<Wallet> searchList;

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
    public int getLayoutId() {
        return R.layout.activity_add_coin;
    }

    @Override
    public CoinContact.Presenter createPresenter() {
        return new CoinPresenter(this);
    }

    @Override
    public CoinContact.View createView() {
        return this;
    }

    @Override
    public void init() {
        getPresenter().getSupportCoinList(ApplicationUtil.getCurrentWallet().getAddress());
        list = new ArrayList<>();
        walletAdapter = new WalletAdapter(getContext(), list);
        walletAdapter.setAddListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final List list, final int position) {


                if (((Wallet) list.get(position)).getHave().equals("1")) {
                    DialogUtils.showConfirmDialog(getActivity(), 0, R.string.sure_to_unbind_coin, R.string.cancel, R.string.confirm, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getPresenter().bindCoin("2", ApplicationUtil.getCurrentWallet().getAddress(), ((Wallet) list.get(position)).getId());


                        }
                    });


                } else {
                    DialogUtils.showConfirmDialog(getActivity(), 0, R.string.sure_to_bind_coin, R.string.cancel, R.string.confirm, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getPresenter().bindCoin("1", ApplicationUtil.getCurrentWallet().getAddress(), ((Wallet) list.get(position)).getId());


                        }
                    });


                }


            }
        });
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.setAdapter(walletAdapter);
        rcy.addItemDecoration(DividerHelper.getMyDivider(getContext()));
        refreashView(list, rcy);
        addSearch();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_cancle)
    public void onViewClicked() {
        finish();
    }



    private void addSearch() {

        RxTextView.textChanges(editText)//当EditText发生改变
                //每500毫秒发射一次
                //仅在过了一段指定的时间还没发射数据时才发射一个数据
                //如果原始Observable在这个新生成的Observable终止之前发射了另一个数据， debounce 会抑制(suppress)这个数据项。
                .debounce(1000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())

                .subscribeOn(AndroidSchedulers.mainThread())//内容监听操作需要在主线程操作
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(CharSequence charSequence) throws Exception {
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
                            walletAdapter.notifyDataSetChanged(list);
                            refreashView(list,rcy);
                            return;
                        }
                        searchList = new ArrayList<>();
                        for (int i = 0;i<list.size();i++){
                            if (list.get(i).getName().toLowerCase().contains(s.toLowerCase())){
                                searchList.add(list.get(i));
                            }
                        }
                        KeyboardUtils.hideKeyboard(editText);



                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                walletAdapter.notifyDataSetChanged(searchList);
                                refreashView(searchList,rcy);

                            }
                        },300);
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
    public void getSupportCoinListSuccess(List<Wallet> coins) {
        list = coins;
        walletAdapter.notifyDataSetChanged(list);
        refreashView(list, rcy);
    }

    @Override
    public void bindCoinSuccess(String operationtype) {
        if (operationtype.equals("1")) {
            ToastUtil.showShortToast(R.string.bind_coin_success);
            finish();
        } else {
            getPresenter().getSupportCoinList(ApplicationUtil.getCurrentWallet().getAddress());
        }
        EventBus.getDefault().post(ApplicationUtil.getCurrentWallet());
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return bindToLifecycle();
    }
}
