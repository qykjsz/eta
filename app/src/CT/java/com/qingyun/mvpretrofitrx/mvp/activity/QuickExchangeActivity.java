package com.qingyun.mvpretrofitrx.mvp.activity;

import android.animation.Animator;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.senon.mvpretrofitrx.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import per.goweii.anylayer.AnimHelper;
import per.goweii.anylayer.AnyLayer;
import per.goweii.anylayer.LayerManager;

public class QuickExchangeActivity extends BaseActivity {
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
    public int getLayoutId() {
        return R.layout.activity_quick_exchange;
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

    @OnClick({R.id.btn_back, R.id.ben_choose_wallet, R.id.btn_exchange})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                break;
            case R.id.ben_choose_wallet:
                break;
            case R.id.btn_exchange:



                AnyLayer.with(getContext())
                        .contentView(R.layout.dialog_exchange_detail)
                        .backgroundColorInt(getResources().getColor(R.color.bg_dialog))
                        .gravity(Gravity.BOTTOM)

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
                                ImageView addWallet =  anyLayer.getView(R.id.btn_aa_wallet);
                                addWallet.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(CreateWalletActivity.class);
                                    }
                                });

                            }
                        })
                        .show();


                break;
        }
    }
}
