package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingyun.mvpretrofitrx.mvp.adapter.MainViewPagerAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.utils.IndicatorUtils;
import com.senon.mvpretrofitrx.R;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class InformationFragment extends BaseFragment {
    @BindView(R.id.magic_indicator3)
    MagicIndicator magicIndicator3;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    private List<BaseFragment> fragments;
    private List<String> titles;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_information;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void init() {
        titles = new ArrayList<>();
        titles.add("快讯");
//        titles.add("文章");
        titles.add("行情");
        fragments = new ArrayList<>();
        fragments.add(new FlashFragment());
//        fragments.add(new TiteFragment());
        fragments.add(new MakieFragment());
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getContext(), fragments, getActivity().getSupportFragmentManager());
        viewPager.setAdapter(mainViewPagerAdapter);
        IndicatorUtils.initMagicIndicator3M(viewPager, titles,  getActivity(),magicIndicator3,0,10);
    }
}
