package com.qingyun.mvpretrofitrx.mvp.activity.chat;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatCode;
import com.qingyun.mvpretrofitrx.mvp.entity.Group;
import com.qingyun.mvpretrofitrx.mvp.utils.DensityUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.ZXingUtils;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils.GROUP;

public class GroupShareActivity extends BaseActivity {
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.tv_avatar)
    BoldTextView tvAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    private Bitmap bitmap;

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
        return getResources().getString(R.string.group_share);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_group_share;
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
        Group group = (Group) getIntent().getSerializableExtra(GROUP);
        if (group != null) {
            Gson gson = new Gson();
            ChatCode chatCode = new ChatCode();
            chatCode.setChatCode(group.getCode());
            bitmap = ZXingUtils.createQRImage(gson.toJson(chatCode), DensityUtil.dip2px(getContext(), 178), DensityUtil.dip2px(getContext(), 178));
            Glide.with(getActivity()).load(bitmap).into(ivCode);
            tvName.setText(group.getName());
            tvAvatar.setText(group.getName().substring(0,1));

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
