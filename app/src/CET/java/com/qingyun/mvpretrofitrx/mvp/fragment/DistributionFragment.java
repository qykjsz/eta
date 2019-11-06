package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingyun.mvpretrofitrx.mvp.activity.BusinessActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.ComposeActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.DistributionActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DistributionFragment extends BaseFragment {
    @BindView(R.id.tv_yue_c)
    BoldTextView tvYueC;
    @BindView(R.id.tv_yue_cetc)
    BoldTextView tvYueCetc;
    @BindView(R.id.tv_yue_t)
    BoldTextView tvYueT;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_distribution;
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
    public boolean haveHeader() {
        getLyHeader().setBackgroundColor(getResources().getColor(R.color.color_bg_title));
        return true;
    }

    @Override
    public void init() {

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
        return getResources().getString(R.string.nav_distribution);
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

    @OnClick({R.id.ly_exchange, R.id.ly_distribution, R.id.ly_compose, R.id.ly_business})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ly_exchange:
                break;
            case R.id.ly_distribution:
                startActivity(DistributionActivity.class);
                break;
            case R.id.ly_compose:
                startActivity(ComposeActivity.class);
                break;
            case R.id.ly_business:
                startActivity(BusinessActivity.class);

                break;
        }
    }
}
