package com.qingyun.mvpretrofitrx.mvp.utils;

import android.Manifest;
import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import com.qingyun.mvpretrofitrx.mvp.adapter.CoinChooseAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.CoinRateChooseAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.CurrencyChooseAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.PlatformAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.StringDialogAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinTypeRate;
import com.qingyun.mvpretrofitrx.mvp.entity.CurrencyRate;
import com.qingyun.mvpretrofitrx.mvp.entity.Platform;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.senon.mvpretrofitrx.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import io.reactivex.functions.Consumer;
import per.goweii.anylayer.AnimHelper;
import per.goweii.anylayer.AnyLayer;
import per.goweii.anylayer.LayerManager;

public class DialogUtils {

    public static final int CODE_CHOOSE_PICTURE_PHONE = 10000;
    public static final int CODE_CHOOSE_PICTURE_CAMEAR = 10001;

    public static void showPayDialog(Context context, final SureListener sureListener){
//        if (ApplicationUtil.getUser().getHas_pay_password()){
            AnyLayer.with(context)
                    .contentView(R.layout.dialog_pay_dialog)
                    .backgroundColorInt(context.getResources().getColor(R.color.bg_dialog))
                    .gravity(Gravity.CENTER)
                    .onClickToDismiss(R.id.btn_cancel)
                    .onClick(R.id.btn_sure, new LayerManager.OnLayerClickListener() {
                        @Override
                        public void onClick(AnyLayer anyLayer, View v) {
                            EditText etPass =  anyLayer.getView(R.id.et_pass);
                            if (TextUtils.isEmpty(etPass.getText().toString())){
                                ToastUtil.showShortToast(R.string.pass_not_null);
                                return;
                            }
                            if (sureListener!=null){
                                sureListener.onSure(etPass.getText().toString());
                                anyLayer.dismiss();
                            }
                        }
                    })
                    .bindData(new LayerManager.IDataBinder() {
                        @Override
                        public void bind(AnyLayer anyLayer) {
                            final EditText etPass =  anyLayer.getView(R.id.et_pass);
                            // TODO 绑定数据
                            CheckBox checkBox = anyLayer.getView(R.id.btn_visi);
                            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if (isChecked){
                                        etPass.setInputType(128);//设置为隐藏密码
                                    }else {
                                        etPass.setInputType(129);//设置为隐藏密码
                                    }
                                    etPass.setSelection(etPass.getText().toString().length());

                                }
                            });
                        }
                    })
                    .show();
//        }else {
////            Intent intent = new Intent(context,ChangePayPasswordActivity.class);
////            context.startActivity(intent);
//        }

    }


    public static void shoEditDialog(final String editStr, Context context, final SureListener sureListener){
//        if (ApplicationUtil.getUser().getHas_pay_password()){
        AnyLayer.with(context)
                .contentView(R.layout.dialog_edit)
                .backgroundColorInt(context.getResources().getColor(R.color.bg_dialog))
                .gravity(Gravity.CENTER)
                .onClickToDismiss(R.id.btn_cancel)
                .onClick(R.id.btn_sure, new LayerManager.OnLayerClickListener() {
                    @Override
                    public void onClick(AnyLayer anyLayer, View v) {
                        EditText etPass =  anyLayer.getView(R.id.et_pass);
                        if (TextUtils.isEmpty(etPass.getText().toString())){
                            ToastUtil.showShortToast(R.string.pass_not_null);
                            return;
                        }
                        if (sureListener!=null){
                            sureListener.onSure(etPass.getText().toString());
                            anyLayer.dismiss();
                        }
                    }
                })
                .bindData(new LayerManager.IDataBinder() {
                    @Override
                    public void bind(AnyLayer anyLayer) {
                        EditText etPass =  anyLayer.getView(R.id.et_pass);
                        etPass.setText(editStr);

                    }
                })
                .show();
//        }else {
////            Intent intent = new Intent(context,ChangePayPasswordActivity.class);
////            context.startActivity(intent);
//        }

    }




    static CountDownUtils countDownUtils;

    public static void showInvestPayDialog(Context context, final String amount, final String coinNmae, final int start, final SureListener sureListener){
//        if (ApplicationUtil.getUser().getHas_pay_password()){
        AnyLayer.with(context)
                .contentView(R.layout.dialog_invest_pay)
                .backgroundColorInt(context.getResources().getColor(R.color.bg_dialog))
                .gravity(Gravity.CENTER)
                .onClickToDismiss(R.id.btn_cancel)
                .onLayerDismissListener(new LayerManager.OnLayerDismissListener() {
                    @Override
                    public void onDismissing(AnyLayer anyLayer) {

                    }

                    @Override
                    public void onDismissed(AnyLayer anyLayer) {
                        if (countDownUtils!=null&&!countDownUtils.getCall().isDisposed())
                            countDownUtils.getCall().dispose();

                    }
                })
                .onClick(R.id.btn_sure, new LayerManager.OnLayerClickListener() {
                    @Override
                    public void onClick(AnyLayer anyLayer, View v) {
                        EditText etPass =  anyLayer.getView(R.id.et_pass);
                        if (TextUtils.isEmpty(etPass.getText().toString())){
                            ToastUtil.showShortToast(R.string.pass_not_null);
                            return;
                        }
                        if (sureListener!=null){
                            sureListener.onSure(etPass.getText().toString());
                            anyLayer.dismiss();
                        }
                    }
                })
                .bindData(new LayerManager.IDataBinder() {
                    @Override
                    public void bind(final AnyLayer anyLayer) {
                        final EditText etPass =  anyLayer.getView(R.id.et_pass);
                        // TODO 绑定数据
                        CheckBox checkBox = anyLayer.getView(R.id.btn_visi);
                        TextView tvAmount = anyLayer.getView(R.id.tv_amount);
                        TextView tvCoinName = anyLayer.getView(R.id.tv_coin_name);
                        tvAmount.setText(amount);
                        tvCoinName.setText(coinNmae);

                        final TextView tvTime = anyLayer.getView(R.id.tv_time);
                        countDownUtils = new CountDownUtils().showCloseCountDown(1, start, new CountDownUtils.CountDownListener() {
                            @Override
                            public void next(Long aLong) {
                                tvTime.setText(aLong+"s");
                            }

                            @Override
                            public void onComplete() {
                                if (!countDownUtils.getCall().isDisposed())
                                countDownUtils.getCall().dispose();
                                anyLayer.dismiss();
                            }
                        });
                        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked){
                                    etPass.setInputType(128);//设置为隐藏密码
                                }else {
                                    etPass.setInputType(129);//设置为隐藏密码
                                }
                                etPass.setSelection(etPass.getText().toString().length());

                            }
                        });
                    }
                })
                .show();
//        }else {
////            Intent intent = new Intent(context,ChangePayPasswordActivity.class);
////            context.startActivity(intent);
//        }

    }


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





    public static void showPictureChooseDialogAvatar(final Activity context, final boolean circle) {
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
                                .withAspectRatio(1,1)
                                .cropCompressQuality(50)
                                .imageFormat(PictureMimeType.PNG)

                                .circleDimmedLayer(circle)
                                .enableCrop(true)
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
                                            .withAspectRatio(1,1)
                                            .cropCompressQuality(50)
                                            .imageFormat(PictureMimeType.PNG)
                                            .circleDimmedLayer(circle)
                                            .enableCrop(true)
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



    public static void showCoinChooseDialog(final Activity context, final List<Wallet> coins, final BaseAdapter.OnItemClickListener onItemClickListener) {
        AnyLayer.with(context)
                .contentView(R.layout.dialog_choose_coin)
                .backgroundColorInt(context.getResources().getColor(R.color.bg_dialog))
                .gravity(Gravity.BOTTOM)

                .bindData(new LayerManager.IDataBinder() {
                    @Override
                    public void bind(final AnyLayer anyLayer) {
                       RecyclerView recyclerView =  anyLayer.getView(R.id.rcy);
                        CoinChooseAdapter coinChooseAdapter = new CoinChooseAdapter(context,coins);
                        coinChooseAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(List list, int position) {
                                onItemClickListener.onItemClick(list,position);
                                anyLayer.dismiss();
                            }
                        });
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        recyclerView.addItemDecoration(DividerHelper.getMyDivider(context));
                        recyclerView.setAdapter(coinChooseAdapter);
                    }
                })
                .onClickToDismiss(R.id.cancel)
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
                .show();
    }






    public static void showCurrencyChooseDialog(final Activity context, final List<CurrencyRate> coins, final BaseAdapter.OnItemClickListener onItemClickListener) {
        AnyLayer.with(context)
                .contentView(R.layout.dialog_choose_coin)
                .backgroundColorInt(context.getResources().getColor(R.color.bg_dialog))
                .gravity(Gravity.BOTTOM)

                .bindData(new LayerManager.IDataBinder() {
                    @Override
                    public void bind(final AnyLayer anyLayer) {
                        RecyclerView recyclerView =  anyLayer.getView(R.id.rcy);
                        CurrencyChooseAdapter coinChooseAdapter = new CurrencyChooseAdapter(context,coins);
                        coinChooseAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(List list, int position) {
                                onItemClickListener.onItemClick(list,position);
                                anyLayer.dismiss();
                            }
                        });
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        recyclerView.addItemDecoration(DividerHelper.getMyDivider(context));
                        recyclerView.setAdapter(coinChooseAdapter);
                    }
                })
                .onClickToDismiss(R.id.cancel)
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
                .show();
    }




    public static void showCoinRateChooseDialog(final Activity context, final List<CoinTypeRate> coins, final BaseAdapter.OnItemClickListener onItemClickListener) {
        AnyLayer.with(context)
                .contentView(R.layout.dialog_choose_coin)
                .backgroundColorInt(context.getResources().getColor(R.color.bg_dialog))
                .gravity(Gravity.BOTTOM)

                .bindData(new LayerManager.IDataBinder() {
                    @Override
                    public void bind(final AnyLayer anyLayer) {
                        RecyclerView recyclerView =  anyLayer.getView(R.id.rcy);
                        CoinRateChooseAdapter coinChooseAdapter = new CoinRateChooseAdapter(context,coins);
                        coinChooseAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(List list, int position) {
                                onItemClickListener.onItemClick(list,position);
                                anyLayer.dismiss();
                            }
                        });
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        recyclerView.addItemDecoration(DividerHelper.getMyDivider(context));
                        recyclerView.setAdapter(coinChooseAdapter);
                    }
                })
                .onClickToDismiss(R.id.cancel)
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
                .show();
    }





    public static void showPlatformChooseDialog(final Activity context, final List<Platform> platformList, final BaseAdapter.OnItemClickListener onItemClickListener) {
        AnyLayer.with(context)
                .contentView(R.layout.dialog_choose_coin)
                .backgroundColorInt(context.getResources().getColor(R.color.bg_dialog))
                .gravity(Gravity.BOTTOM)

                .bindData(new LayerManager.IDataBinder() {
                    @Override
                    public void bind(final AnyLayer anyLayer) {
                        RecyclerView recyclerView =  anyLayer.getView(R.id.rcy);
                        TextView textView = anyLayer.getView(R.id.textView4);
                        textView.setText(context.getResources().getString(R.string.platform_choose));
                        PlatformAdapter platformAdapter = new PlatformAdapter(context,platformList);
                        platformAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(List list, int position) {
                                onItemClickListener.onItemClick(list,position);
                                anyLayer.dismiss();
                            }
                        });
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        recyclerView.addItemDecoration(DividerHelper.getMyDivider(context));
                        recyclerView.setAdapter(platformAdapter);
                    }
                })
                .onClickToDismiss(R.id.cancel)
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
                .show();
    }




    public static void showStringDialog(final Activity context, final String title,final List<String> stringList, final BaseAdapter.OnItemClickListener onItemClickListener) {
        AnyLayer.with(context)
                .contentView(R.layout.dialog_choose_coin)
                .backgroundColorInt(context.getResources().getColor(R.color.bg_dialog))
                .gravity(Gravity.BOTTOM)

                .bindData(new LayerManager.IDataBinder() {
                    @Override
                    public void bind(final AnyLayer anyLayer) {
                        TextView textView = anyLayer.getView(R.id.textView4);
                        textView.setText(title);
                        RecyclerView recyclerView =  anyLayer.getView(R.id.rcy);
                        StringDialogAdapter stringDialogAdapter = new StringDialogAdapter(context,stringList);
                        stringDialogAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(List list, int position) {
                                onItemClickListener.onItemClick(list,position);
                                anyLayer.dismiss();
                            }
                        });
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        recyclerView.addItemDecoration(DividerHelper.getMyDivider(context));
                        recyclerView.setAdapter(stringDialogAdapter);
                    }
                })
                .onClickToDismiss(R.id.cancel)
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
                .show();
    }





    public interface SureListener{
        void onSure(Object o);
    }
}
