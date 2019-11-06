package com.qingyun.mvpretrofitrx.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qingyun.mvpretrofitrx.mvp.adapter.MainViewPagerAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.fragment.BusinessMineFragment;
import com.qingyun.mvpretrofitrx.mvp.fragment.BusinessQueueingFragment;
import com.qingyun.mvpretrofitrx.mvp.fragment.BusinessingFragment;
import com.qingyun.mvpretrofitrx.mvp.utils.IndicatorUtils;
import com.qingyun.mvpretrofitrx.mvp.weight.StickyLayout;
import com.senon.mvpretrofitrx.R;
import net.lucode.hackware.magicindicator.MagicIndicator;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusinessActivity extends BaseActivity{
    @BindView(R.id.tv_btn_us)
    TextView tvBtnUs;
    @BindView(R.id.tv_btn_sell_s)
    TextView tvBtnSellS;
    @BindView(R.id.tv_btn_buy)
    TextView tvBtnBuy;
    @BindView(R.id.tv_btn_sell)
    TextView tvBtnSell;
    @BindView(R.id.tv_queue_num)
    TextView tvQueueNum;
    @BindView(R.id.tv_asset_all)
    TextView tvAssetAll;
    @BindView(R.id.rcy)
    ViewPager vpBusiness;
    List<String> titles;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.m_content)
    StickyLayout mContent;
    @BindView(R.id.btn_back1)
    ImageView btnBack1;
    @BindView(R.id.m_title)
    ConstraintLayout mTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    @BindView(R.id.magic_indicator3)
    MagicIndicator magicIndicator3;
    private boolean isBuy = true;
    BusinessQueueingFragment fragment0;
    BusinessingFragment fragment1;
    BusinessMineFragment fragment2;
    int position = 0;

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
        return getResources().getString(R.string.business);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_business;
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
//        List<Business> list = new ArrayList<>();
//        list.add(new Business());
//        list.add(new Business());
//        list.add(new Business());
//        list.add(new Business());
//
//        BusinessAdapter adapter = new BusinessAdapter(BusinessActivity.this,list);
        titles = new ArrayList<>();
        titles.add(getResources().getString(R.string.status_queueing));
        titles.add(getResources().getString(R.string.status_completed));
        titles.add(getResources().getString(R.string.nav_mine));
        fragment0 = new BusinessQueueingFragment();
        fragment1 = new BusinessingFragment();
        fragment2 = new BusinessMineFragment();
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(fragment0);
        fragments.add(fragment1);
        fragments.add(fragment2);
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(BusinessActivity.this, fragments, titles, getSupportFragmentManager());

        mContent.setAdapter(adapter);
        mContent.setShowTitleListener(new StickyLayout.HeadershowListener() {
            @Override
            public void showHeader(Boolean isShow) {
                if (isShow) {
                    mTitle.setVisibility(View.VISIBLE);
                } else {
                    mTitle.setVisibility(View.INVISIBLE);

                }
            }
        });
        mContent.setShowHeaderListener(new StickyLayout.HeadershowListener() {
            @Override
            public void showHeader(Boolean isShow) {
                if (isShow) {
                    tvTitle.setText(getResources().getString(R.string.business));
                    tvTitle1.setText(getResources().getString(R.string.business));

                } else {
                    if (isBuy) {
                        tvTitle.setText(getResources().getString(R.string.buy));
                        tvTitle1.setText(getResources().getString(R.string.buy));
                    } else {
                        tvTitle.setText(getResources().getString(R.string.sell));
                        tvTitle1.setText(getResources().getString(R.string.sell));
                    }
                }
            }
        });
        mContent.setTitleView(mTitle);
        vpBusiness.setAdapter(adapter);
        vpBusiness.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                position = i;
                page = 1;
                isLoadMore = false;



            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        IndicatorUtils.initMagicIndicator3(magicIndicator3,vpBusiness,titles,this);


    }

    @Override
    protected void refresh() {
        super.refresh();
    }

    public void refresh(int page, int per_page) {
        super.refresh();
        isLoadMore = false;



    }

    public void loadMore(int page, int per_page) {
        super.loadMore();
        isLoadMore = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_btn_us, R.id.tv_btn_sell_s, R.id.tv_btn_buy, R.id.tv_btn_sell})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_btn_us:
                Intent intent = new Intent(BusinessActivity.this, BuyTActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_btn_sell_s:
                Intent intent1 = new Intent(BusinessActivity.this, SellTActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_btn_buy:
                page = 1;
                isLoadMore = false;
                tvBtnBuy.setBackground(getResources().getDrawable(R.drawable.bg_btn_small_check));
                tvBtnSell.setBackground(getResources().getDrawable(R.drawable.bg_btn_small_uncheck));
                isBuy = true;
                break;
            case R.id.tv_btn_sell:
                page = 1;
                isLoadMore = false;
                tvBtnSell.setBackground(getResources().getDrawable(R.drawable.bg_btn_small_check));
                tvBtnBuy.setBackground(getResources().getDrawable(R.drawable.bg_btn_small_uncheck));
                isBuy = false;
                break;
        }
    }

    @OnClick({R.id.btn_back, R.id.btn_back1})
    public void onViewClicked() {
        finish();
    }



}
