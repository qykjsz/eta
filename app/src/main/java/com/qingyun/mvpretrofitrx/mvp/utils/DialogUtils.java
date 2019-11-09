package com.qingyun.mvpretrofitrx.mvp.utils;

import android.Manifest;
import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import com.senon.mvpretrofitrx.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;
import per.goweii.anylayer.AnimHelper;
import per.goweii.anylayer.AnyLayer;
import per.goweii.anylayer.LayerManager;

public class DialogUtils {

    public static final int CODE_CHOOSE_PICTURE_PHONE = 10000;
    public static final int CODE_CHOOSE_PICTURE_CAMEAR = 10001;

//    public static void showPayDialog(Context context, final SureListener sureListener){
//        if (ApplicationUtil.getUser().getHas_pay_password()){
//            AnyLayer.with(context)
//                    .contentView(R.layout.dialog_pay_dialog)
//                    .backgroundColorInt(context.getResources().getColor(R.color.bg_dialog))
//                    .gravity(Gravity.CENTER)
//                    .onClickToDismiss(R.id.btn_cancel)
//                    .onClick(R.id.btn_sure, new LayerManager.OnLayerClickListener() {
//                        @Override
//                        public void onClick(AnyLayer anyLayer, View v) {
//                            EditText etPass =  anyLayer.getView(R.id.et_pass);
//                            if (TextUtils.isEmpty(etPass.getText().toString())){
//                                ToastUtil.showShortToast(R.string.pass_not_null);
//                                return;
//                            }
//                            if (sureListener!=null){
//                                sureListener.onSure(etPass.getText().toString());
//                                anyLayer.dismiss();
//                            }
//                        }
//                    })
//                    .bindData(new LayerManager.IDataBinder() {
//                        @Override
//                        public void bind(AnyLayer anyLayer) {
//                            // TODO 绑定数据
//
//                        }
//                    })
//                    .show();
//        }else {
////            Intent intent = new Intent(context,ChangePayPasswordActivity.class);
////            context.startActivity(intent);
//        }
//
//    }


    public static void showPictureChooseDialog(final Activity context, final boolean circle) {
        AnyLayer.with(context)
                .contentView(R.layout.dialog_add_img)
                .backgroundColorInt(context.getResources().getColor(R.color.bg_dialog))
                .gravity(Gravity.BOTTOM)
                .onClickToDismiss(R.id.btn_cancel)
                .onClickToDismiss(R.id.btn_select_divice, new LayerManager.OnLayerClickListener() {
                    @Override
                    public void onClick(AnyLayer anyLayer, View v) {
                        com.luck.picture.lib.PictureSelector.create(context).openGallery(PictureMimeType.ofImage())
                                .selectionMode(PictureConfig.SINGLE)
                                .isCamera(false)
                                .compress(true)
                                .videoQuality(50)
                                .cropCompressQuality(50)
                                .circleDimmedLayer(circle)
                                .enableCrop(false)
                                .forResult(CODE_CHOOSE_PICTURE_PHONE);
                    }
                })
                .onClickToDismiss(R.id.btn_open_camear, new LayerManager.OnLayerClickListener() {
                    @Override
                    public void onClick(AnyLayer anyLayer, View v) {
                        /**
                         * create()方法参数一是上下文，在activity中传activity.this，在fragment中传fragment.this。参数二为请求码，用于结果回调onActivityResult中判断
                         * selectPicture()方法参数分别为 是否裁剪、裁剪后图片的宽(单位px)、裁剪后图片的高、宽比例、高比例。都不传则默认为裁剪，宽200，高200，宽高比例为1：1。
                         */

                        RxPermissions rxPermissions=new RxPermissions(context);
                        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean){

                                    com.luck.picture.lib.PictureSelector.create(context).openCamera(PictureMimeType.ofImage())
                                            .selectionMode(PictureConfig.SINGLE)
                                            .isCamera(false)
                                            .compress(true)
                                            .videoQuality(50)
                                            .cropCompressQuality(50)
                                            .circleDimmedLayer(circle)
                                            .enableCrop(false)
                                            .forResult(CODE_CHOOSE_PICTURE_CAMEAR);

                                }else{
                                    //只要有一个权限被拒绝，就会执行
                                    ToastUtil.showShortToast(R.string.storage_permission_need_open);
                                }
                            }
                        });



                    }
                })

                .contentAnim(new LayerManager.IAnim() {
                    @Override
                    public Animator inAnim(View content) {
                        return AnimHelper.createBottomAlphaInAnim(content);
                    }

                    @Override
                    public Animator outAnim(View content) {
                        return AnimHelper.createBottomAlphaOutAnim(content);
                    }
                })
                .bindData(new LayerManager.IDataBinder() {
                    @Override
                    public void bind(AnyLayer anyLayer) {
                        // TODO 绑定数据

                    }
                })
                .show();
    }




//    public static void showPictureChooseDialog(final Activity context, final boolean circle) {
//        AnyLayer.with(context)
//                .contentView(R.layout.dialog_add_img)
//                .backgroundColorInt(context.getResources().getColor(R.color.bg_dialog))
//                .gravity(Gravity.BOTTOM)
//                .onClickToDismiss(R.id.btn_cancel)
//                .onClickToDismiss(R.id.btn_select_divice, new LayerManager.OnLayerClickListener() {
//                    @Override
//                    public void onClick(AnyLayer anyLayer, View v) {
//                        com.luck.picture.lib.PictureSelector.create(context).openGallery(PictureMimeType.ofImage())
//                                .selectionMode(PictureConfig.SINGLE)
//                                .isCamera(false)
//                                .compress(true)
//                                .circleDimmedLayer(circle)
//                                .enableCrop(true)
//                                .forResult(CODE_CHOOSE_PICTURE_PHONE);
//                    }
//                })
//                .onClickToDismiss(R.id.btn_open_camear, new LayerManager.OnLayerClickListener() {
//                    @Override
//                    public void onClick(AnyLayer anyLayer, View v) {
//                        /**
//                         * create()方法参数一是上下文，在activity中传activity.this，在fragment中传fragment.this。参数二为请求码，用于结果回调onActivityResult中判断
//                         * selectPicture()方法参数分别为 是否裁剪、裁剪后图片的宽(单位px)、裁剪后图片的高、宽比例、高比例。都不传则默认为裁剪，宽200，高200，宽高比例为1：1。
//                         */
//                        com.luck.picture.lib.PictureSelector.create(context).openCamera(PictureMimeType.ofImage())
//                                .selectionMode(PictureConfig.SINGLE)
//                                .isCamera(false)
//                                .compress(true)
//                                .circleDimmedLayer(circle)
//                                .enableCrop(true)
//                                .forResult(CODE_CHOOSE_PICTURE_CAMEAR);
//                    }
//                })
//
//                .contentAnim(new LayerManager.IAnim() {
//                    @Override
//                    public Animator inAnim(View content) {
//                        return AnimHelper.createBottomAlphaInAnim(content);
//                    }
//
//                    @Override
//                    public Animator outAnim(View content) {
//                        return AnimHelper.createBottomAlphaOutAnim(content);
//                    }
//                })
//                .bindData(new LayerManager.IDataBinder() {
//                    @Override
//                    public void bind(AnyLayer anyLayer) {
//                        // TODO 绑定数据
//
//                    }
//                })
//                .show();
//    }



    public static void showConfirmDialog(final Activity context, final int resId, final int strId, final int leftStrId, final int rightStrId, final View.OnClickListener leftClick, final View.OnClickListener rightClick) {
        AnyLayer.with(context)
                .contentView(R.layout.dialog_confirm)
                .backgroundColorInt(context.getResources().getColor(R.color.bg_dialog))
                .gravity(Gravity.CENTER)

                .bindData(new LayerManager.IDataBinder() {
                    @Override
                    public void bind(final AnyLayer anyLayer) {
                        ImageView ivIcon = (ImageView) anyLayer.getView(R.id.iv_icon);
                        TextView tvRemark = (TextView) anyLayer.getView(R.id.tv_remark);
                        TextView btnLeft = (TextView) anyLayer.getView(R.id.btn_left);
                        TextView btnRight = (TextView) anyLayer.getView(R.id.btn_right);
                        if (resId!=0)
                        ivIcon.setImageResource(resId);
                        if (strId!=0)
                            tvRemark.setText(strId);
                        if (leftStrId!=0)
                            btnLeft.setText(leftStrId);
                        if (rightStrId!=0)
                            btnRight.setText(rightStrId);
                        btnLeft.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (leftClick!=null)
                                {
                                    anyLayer.dismiss();
                                    leftClick.onClick(v);

                                }
                            }
                        });
                        btnRight.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (rightClick!=null)
                                {
                                    anyLayer.dismiss();
                                    rightClick.onClick(v);
                                }
                            }
                        });
                    }
                })
                .show();
    }




    public interface SureListener{
        void onSure(Object o);
    }
}
