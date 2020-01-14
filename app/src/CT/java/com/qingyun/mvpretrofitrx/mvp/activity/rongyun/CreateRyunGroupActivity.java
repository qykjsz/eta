package com.qingyun.mvpretrofitrx.mvp.activity.rongyun;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateRyunGroupActivity extends BaseActivity {
    @BindView(R.id.iv_group_avatar)
    ImageView ivGroupAvatar;
    @BindView(R.id.et_group_name)
    EditText etGroupName;
    @BindView(R.id.et_group_explain)
    EditText etGroupExplain;
    @BindView(R.id.tv_text_num)
    TextView tvTextNum;

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
        return getResources().getString(R.string.create_chat_group);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_ryun_group;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_group_avatar, R.id.btn_create_group})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_group_avatar:
                break;
            case R.id.btn_create_group:

                startActivity(GroupChatInfoActivity.class);
                break;
        }
    }
}
