package com.qingyun.mvpretrofitrx.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.develop.wallet.eth.Wallet;
import com.develop.wallet.eth.WalletManager;
import com.qingyun.mvpretrofitrx.mvp.adapter.ProportionAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Proportion;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.CopyUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.SpUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.qingyun.mvpretrofitrx.mvp.weight.MultistageProgress;
import com.qingyun.mvpretrofitrx.mvp.weight.dialog.ProgressDialogUtils;
import com.senon.mvpretrofitrx.R;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils.ASSET_RESPONSE;

public class AssetDetailActivity extends BaseActivity {
    @BindView(R.id.tv_income_today)
    TextView tvIncomeToday;
    @BindView(R.id.tv_wallet_name)
    TextView tvWalletName;
    @BindView(R.id.tv_publish_key)
    TextView tvPublishKey;
    @BindView(R.id.tv_asset)
    TextView tvAsset;
    @BindView(R.id.rcy_proportion)
    RecyclerView rcyProportion;
    @BindView(R.id.progress)
    MultistageProgress progress;
    @BindView(R.id.btn_visiable)
    CheckBox btnVisiable;
    private AssetResponse assetResponse;
    private List<Proportion> proportionList;
    private ProportionAdapter proportionAdapter;
    private int[] colors;
    private float[] weight;
    private static final String INVISIABLE_STR="****";

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
    public boolean haveHeader() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_asset_detail;
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
        assetResponse = (AssetResponse) getIntent().getSerializableExtra(ASSET_RESPONSE);
        tvPublishKey.setText(ApplicationUtil.getCurrentWallet().getAddress());
        tvWalletName.setText(ApplicationUtil.getCurrentWallet().getWalletName());

        proportionList = assetResponse.getProportion();
        proportionAdapter = new ProportionAdapter(getContext(), proportionList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        //调整RecyclerView的排列方向
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcyProportion.setLayoutManager(linearLayoutManager);
        rcyProportion.setAdapter(proportionAdapter);

        btnVisiable.setChecked(SpUtils.getBoolenToShare(getContext(), "is_asset_visiable"));
        btnVisiable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SpUtils.setBoolenToShare(getContext(), isChecked, "is_asset_visiable");

                refreashAsset(isChecked);
            }
        });
        refreashAsset(btnVisiable.isChecked());

        colors = new int[assetResponse.getProportion().size()];
        for (int i = 0; i < colors.length; i++) {
            int color = getResources().getColor(R.color.green);
            switch (i) {
                case 0:
                    color = getResources().getColor(R.color.color_FFFFFF);
                    break;
                case 1:
                    color = getResources().getColor(R.color.color_93AEFC);

                    break;
                case 2:
                    color = getResources().getColor(R.color.color_FFB632);

                    break;
                case 3:
                    color = getResources().getColor(R.color.color_EA566D);

                    break;
            }
            colors[i] = color;
        }
        weight = new float[assetResponse.getProportion().size()];
        boolean all0 = true;
        for (int i=0;i<assetResponse.getProportion().size();i++){
            weight[i] = Float.parseFloat(assetResponse.getProportion().get(i).getBili());
            if (weight[i]!=0) all0 = false;
        }
//        if (all0){
//            colors[0] = getResources().getColor(R.color.color_F8F8FF);
//
//        }
        progress.setProgress(0);
        progress.setColors(colors, weight);
        progress.invalidate();


    }

    private void refreashAsset(boolean visiable){
        if (visiable){
            if (assetResponse!=null)
                tvAsset.setText(assetResponse.getAllnumber());
            tvIncomeToday.setText(assetResponse.getToday());
        }else {
            tvIncomeToday.setText(INVISIABLE_STR);
            tvAsset.setText(INVISIABLE_STR);

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_change_password, R.id.btn_export_private_key, R.id.btn_export_key_store})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_change_password:

                startActivity(ChangeWalletPasswordActivity.class);

//                DialogUtils.showPayDialog(getContext(), new DialogUtils.SureListener() {
//                    @Override
//                    public void onSure(Object o) {
//                        WalletManager.decrypt(o.toString(), ApplicationUtil.getCurrentWallet(), new WalletManager.CheckPasswordListener() {
//                            @Override
//                            public void onSuccess() {
//                                startActivity(ChangeWalletPasswordActivity.class);
//
//
//                            }
//
//                            @Override
//                            public void onFailure(Exception e) {
//                                ToastUtil.showShortToast(R.string.pass_err);
//
//                            }
//                        });
//
//                    }
//                });
//

                break;
            case R.id.btn_export_private_key:
                DialogUtils.showPayDialog(getContext(), new DialogUtils.SureListener() {
                    @Override
                    public void onSure(Object o) {
                        ProgressDialogUtils.getInstances().showDialog();
                        WalletManager.decrypt(o.toString(), ApplicationUtil.getCurrentWallet(), new WalletManager.CheckPasswordListener() {
                            @Override
                            public void onSuccess() {
                                startActivity(ExportPrivateKeyActivity.class);
                                ProgressDialogUtils.getInstances().cancel();

                            }

                            @Override
                            public void onFailure(Exception e) {
                                ToastUtil.showShortToast(R.string.pass_err);
                                ProgressDialogUtils.getInstances().cancel();

                            }
                        });

                    }
                });
                break;
            case R.id.btn_export_key_store:


                DialogUtils.showPayDialog(getContext(), new DialogUtils.SureListener() {
                    @Override
                    public void onSure(Object o) {
                        ProgressDialogUtils.getInstances().showDialog();
                        WalletManager.decrypt(o.toString(), ApplicationUtil.getCurrentWallet(), new WalletManager.CheckPasswordListener() {
                            @Override
                            public void onSuccess() {
                                ProgressDialogUtils.getInstances().cancel();

                                startActivity(ExportKeystoreActivity.class);


                            }

                            @Override
                            public void onFailure(Exception e) {
                                ProgressDialogUtils.getInstances().cancel();

                                ToastUtil.showShortToast(R.string.pass_err);

                            }
                        });

                    }
                });


                break;
        }
    }

    @OnClick({R.id.btn_back, R.id.btn_visiable, R.id.btn_copy_publish_key, R.id.btn_delete_wallet})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_visiable:
                break;
            case R.id.btn_delete_wallet:
                DialogUtils.showConfirmDialog(getActivity(), 0, R.string.sure_to_delete_wallet, R.string.cancel, R.string.delete_wallet, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean haveWallet = false;
                        ApplicationUtil.deleteWallet(ApplicationUtil.getCurrentWallet());
                        if (ApplicationUtil.getWallet().entrySet() != null && ApplicationUtil.getWallet().entrySet().size() > 0) {
                            for (Map.Entry<String, List<Wallet>> entry : ApplicationUtil.getWallet().entrySet()) {
                                if (entry.getValue() != null && entry.getValue().size() > 0) {
                                    haveWallet = true;
                                    ApplicationUtil.setCurrentWallet(entry.getValue().get(0));
                                    break;
                                }
                            }
                        }
                        if (haveWallet == false) {
                            ApplicationUtil.setCurrentWallet(null);
                        }
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
                break;
            case R.id.btn_copy_publish_key:
                CopyUtils.copy(getContext(), tvPublishKey.getText().toString());
                break;
        }
    }

}
