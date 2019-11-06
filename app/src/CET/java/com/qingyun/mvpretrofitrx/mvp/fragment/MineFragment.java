package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.activity.InformActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.UserInfoActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MineFragment extends BaseFragment {
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_nick_name)
    BoldTextView tvNickName;
    @BindView(R.id.tv_invi_code)
    TextView tvInviCode;
    @BindView(R.id.iv_copy)
    ImageView ivCopy;
    @BindView(R.id.tv_current_version)
    TextView tvCurrentVersion;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
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

    @OnClick({R.id.iv_avatar,R.id.iv_copy, R.id.btn_frends_list, R.id.btn_address_note, R.id.btn_system_inform, R.id.btn_user_agress, R.id.btn_help_feedback, R.id.btn_about_us})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar:
                startActivity(UserInfoActivity.class);
                break;
            case R.id.iv_copy:
                break;
            case R.id.btn_frends_list:
                break;
            case R.id.btn_address_note:
                break;
            case R.id.btn_system_inform:
                startActivity(InformActivity.class);
                break;
            case R.id.btn_user_agress:
                break;
            case R.id.btn_help_feedback:
                break;
            case R.id.btn_about_us:
                break;
        }
    }
}
