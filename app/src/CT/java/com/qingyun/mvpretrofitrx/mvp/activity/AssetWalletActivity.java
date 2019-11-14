package com.qingyun.mvpretrofitrx.mvp.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.qingyun.mvpretrofitrx.mvp.adapter.AssetWalletLogAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.MainViewPagerAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.contract.WalletAssetContact;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLogResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.qingyun.mvpretrofitrx.mvp.fragment.EmptyFragment;
import com.qingyun.mvpretrofitrx.mvp.presenter.WalletAssetPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.qingyun.mvpretrofitrx.mvp.utils.IndicatorUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import net.lucode.hackware.magicindicator.MagicIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class AssetWalletActivity extends BaseActivity<WalletAssetContact.View, WalletAssetContact.Presenter> implements WalletAssetContact.View {
    @BindView(R.id.magic_indicator3)
    MagicIndicator magicIndicator3;
    MainViewPagerAdapter mainViewPagerAdapter;
    @BindView(R.id.tv_asset)
    TextView tvAsset;
    @BindView(R.id.tv_income_today)
    TextView tvIncomeToday;
    @BindView(R.id.rcy)
    RecyclerView rcy;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tv_usdt)
    TextView tvUsdt;
    @BindView(R.id.et_search)
    EditText etSearch;
    private List<BaseFragment> fragments;
    private List<String> titles;
    private Wallet asset;
    private int type;
    private AssetWalletLogAdapter assetWalletLogAdapter;
    private List<TransferLog> list;

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
        return R.layout.activity_asset_wallet;
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
        titles = new ArrayList<>();
        titles.add(getResources().getString(R.string.all));
        titles.add(getResources().getString(R.string.transfer_in));
        titles.add(getResources().getString(R.string.transfer_out));
        asset = (Wallet) getIntent().getSerializableExtra(IntentUtils.ASSET);
        fragments = new ArrayList<>();
        fragments.add(new EmptyFragment());
        fragments.add(new EmptyFragment());
        fragments.add(new EmptyFragment());

        mainViewPagerAdapter = new MainViewPagerAdapter(getContext(), fragments, getSupportFragmentManager());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                page = 0;
                switch (i) {
                    case 0:
                        type = 3;
                        break;
                    case 1:
                        type = 1;
                        break;
                    case 2:
                        type = 2;
                        break;
                }
                getPresenter().getLog(ApplicationUtil.getCurrentWallet().getAddress(), asset.getName(), type, page);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewPager.setAdapter(mainViewPagerAdapter);
        IndicatorUtils.initMagicIndicator3(magicIndicator3, viewPager, titles, getActivity());

        list = new ArrayList<>();
        assetWalletLogAdapter = new AssetWalletLogAdapter(getContext(), list);
        assetWalletLogAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(IntentUtils.TRANSFER_LOG,((TransferLog)list.get(position)));
                startActivity(BusinessDetailActivity.class,bundle);
            }
        });
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.addItemDecoration(DividerHelper.getMyDivider(getContext()));
        rcy.setAdapter(assetWalletLogAdapter);
        refreashView(list, rcy);
        getPresenter().getLog(ApplicationUtil.getCurrentWallet().getAddress(), asset.getName(), type, page);


        addSearch();
    }

    private void addSearch() {

        RxTextView.textChanges(etSearch)//当EditText发生改变
                //每500毫秒发射一次
                //仅在过了一段指定的时间还没发射数据时才发射一个数据
                //如果原始Observable在这个新生成的Observable终止之前发射了另一个数据， debounce 会抑制(suppress)这个数据项。
                .debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())

                .subscribeOn(AndroidSchedulers.mainThread())//内容监听操作需要在主线程操作
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(CharSequence charSequence) throws Exception {
                        return charSequence.length() > 0;
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
                        if (s!=null)
                        {
                            getPresenter().searchLogByHash(ApplicationUtil.getCurrentWallet().getAddress(),s,asset.getName());
                        }else {
                            page=1;
                            getPresenter().getLog(ApplicationUtil.getCurrentWallet().getAddress(), asset.getName(), type, page);

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
    protected void refresh() {
        super.refresh();
        getPresenter().getLog(ApplicationUtil.getCurrentWallet().getAddress(), asset.getName(), type, page);

    }

    @Override
    protected void loadMore() {
        super.loadMore();
        getPresenter().getLog(ApplicationUtil.getCurrentWallet().getAddress(), asset.getName(), type, page);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDetail(TransferLogResponse transferLogResponse) {
        tvAsset.setText(transferLogResponse.getUsdtnumber());
        tvIncomeToday.setText(transferLogResponse.getToday());

    }


    @OnClick({R.id.btn_back, R.id.btn_visiable, R.id.btn_get_money, R.id.btn_transfer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_transfer:
                Bundle bundle = new Bundle();
                bundle.putSerializable(IntentUtils.ASSET, asset);
                startActivity(TransferActivity.class, bundle);
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_visiable:
                break;
            case R.id.btn_get_money:
                startActivity(GetMoneyActivity.class);
                break;
        }
    }

    @Override
    public void getWalletInfoSuccess(AssetResponse assetResponse) {

    }

    @Override
    public void addWalletSuccess() {

    }

    @Override
    public void getLogSuccess(TransferLogResponse transferLogResponse) {
        tvAsset.setText(transferLogResponse.getUsdtnumber());
        tvIncomeToday.setText(transferLogResponse.getToday());
        tvUsdt.setText(transferLogResponse.getUsdtnumber());
        if (isLoadMore) {
            list.addAll(transferLogResponse.getOrder());
        } else {
            list = transferLogResponse.getOrder();
        }
        if (list == null) list = new ArrayList<>();
        assetWalletLogAdapter.notifyDataSetChanged(list);
        refreashView(list, rcy);
    }

    @Override
    public void getNodeSuccess(String node) {

    }

    @Override
    public void searchLogByHashSuccess(TransferLog transferLog) {
        list = new ArrayList<>();
        list.add(transferLog);
        assetWalletLogAdapter.notifyDataSetChanged(list);
        refreashView(list, rcy);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
