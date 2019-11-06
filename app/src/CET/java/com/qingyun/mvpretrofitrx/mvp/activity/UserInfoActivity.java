package com.qingyun.mvpretrofitrx.mvp.activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import per.goweii.anylayer.AnimHelper;
import per.goweii.anylayer.AnyLayer;
import per.goweii.anylayer.LayerManager;

public class UserInfoActivity extends BaseActivity {
    @BindView(R.id.iv_avater)
    ImageView ivAvater;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.btn_exit)
    BoldTextView btnExit;

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
        return getResources().getString(R.string.my_iden);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_iden;
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

    @OnClick({R.id.iv_avater, R.id.tv_nick_name, R.id.btn_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_avater:
                DialogUtils.showPictureChooseDialog(UserInfoActivity.this,true);
                break;
            case R.id.tv_nick_name:

                AnyLayer.with(getContext())
                        .contentView(R.layout.dialog_edit_nick_name)
                        .backgroundColorInt(getContext().getResources().getColor(R.color.bg_dialog))
                        .gravity(Gravity.CENTER)
                        .onClickToDismiss(R.id.btn_cancel)
                        .onClickToDismiss(R.id.btn_sure, new LayerManager.OnLayerClickListener() {
                            @Override
                            public void onClick(AnyLayer anyLayer, View v) {
                                EditText etNickNmae = anyLayer.getView(R.id.et_nick_name);

                                tvNickName.setText(etNickNmae.getText().toString());
                            }
                        })

                        .bindData(new LayerManager.IDataBinder() {
                            @Override
                            public void bind(AnyLayer anyLayer) {
                                // TODO 绑定数据
                                EditText etNickNmae = anyLayer.getView(R.id.et_nick_name);
                                etNickNmae.setText(tvNickName.getText().toString());
                            }
                        })
                        .show();

                break;
            case R.id.btn_exit:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/
        if (requestCode == DialogUtils.CODE_CHOOSE_PICTURE_CAMEAR||requestCode == DialogUtils.CODE_CHOOSE_PICTURE_PHONE) {
            if (data != null) {
                List<LocalMedia> selectList = com.luck.picture.lib.PictureSelector.obtainMultipleResult(data);
                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                String picturePath = selectList.get(0).getCompressPath();
                Glide.with(getContext()).load(new File(picturePath)).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(ivAvater);
//                RequestBody imageBody_head = RequestBody.create(MediaType.parse("image/jpeg"), new File(picturePath));
//                avater = MultipartBody.Part.createFormData("avatar", new File(picturePath).getName(), imageBody_head);

            }
        }


    }

}
