package com.qingyun.mvpretrofitrx.mvp.activity.chat;

import android.Manifest;
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
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.utils.DensityUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.FileUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.GlideUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.ZXingUtils;
import com.senon.mvpretrofitrx.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MyQrcodeActivity extends BaseActivity {
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.textView166)
    TextView textView166;
    private Bitmap bitmap;
    private GroupMember groupMember;

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
        return getResources().getString(R.string.my_qrcode);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_qrcode;
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
        groupMember = (GroupMember) getIntent().getSerializableExtra(IntentUtils.GROUP_MEMBER);
        textView166.setText(getResources().getString(R.string.id_) + groupMember.getId());
        ChatCode chatCode = new ChatCode();
        chatCode.setChatCode(groupMember.getId() + "");
        if (groupMember != null) {
            Gson gson = new Gson();
            bitmap = ZXingUtils.createQRImage(gson.toJson(chatCode), DensityUtil.dip2px(getContext(), 178), DensityUtil.dip2px(getContext(), 178));
            Glide.with(getActivity()).load(bitmap).into(ivCode);
            Glide.with(getContext()).load(groupMember.getPhoto()).apply(GlideUtils.getChatAvaterOptions()).into(ivAvatar);
            tvName.setText(groupMember.getName());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_save_code)
    public void onViewClicked() {
        RxPermissions rxPermissions=new RxPermissions(getActivity());
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean){
                    FileUtils.savePicture(getContext(),bitmap , System.currentTimeMillis() + ".png");
                    ToastUtil.showShortToast(R.string.save_success);
                }else{
                    //只要有一个权限被拒绝，就会执行
                    ToastUtil.showShortToast(R.string.storage_permission_need_open);
                }
            }
        });
    }
}
