package com.qingyun.mvpretrofitrx.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.develop.wallet.eth.Wallet;
import com.develop.wallet.eth.WalletManager;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.ArrayUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.SpUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import am.widget.wraplayout.WrapLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmCommemorationActivity extends BaseActivity {
    @BindView(R.id.flex_input)
    WrapLayout flexInput;
    @BindView(R.id.flex_normal)
    WrapLayout flexNormal;
    private List<String> myMnemonicStr;

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
        return getResources().getString(R.string.confirm_commemoration);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_commemoration;
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
        myMnemonicStr = new ArrayList<>();
        String mnemonicStr = ApplicationUtil.getCurrentWallet().getMnemonic();
        String[] mnemonicArr = mnemonicStr.split(" ");
        flexNormal.setTag(1);
        flexInput.setTag(2);
        ArrayUtils.shuffle(mnemonicArr);
        for (int i = 0; i < mnemonicArr.length; i++) {
            final TextView textView = new TextView(getContext());
            textView.setTextColor(getResources().getColor(R.color.color_666666));
            textView.setBackgroundColor(getResources().getColor(R.color.color_EBEBEB));
            textView.setHeight(getResources().getDimensionPixelSize(R.dimen.dp_28));
            textView.setPadding(getResources().getDimensionPixelSize(R.dimen.dp_8), 0, getResources().getDimensionPixelSize(R.dimen.dp_8), 0);
            textView.setGravity(Gravity.CENTER);
            textView.setText(mnemonicArr[i]);
            flexNormal.addView(textView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                            if (((int) ((WrapLayout) textView.getParent()).getTag()) == 1) {
                                flexNormal.removeView(textView);
                                flexInput.addView(textView);
                                myMnemonicStr.add(textView.getText().toString());
                            } else {

                                flexInput.removeView(textView);
                                flexNormal.addView(textView);
                                myMnemonicStr.remove(textView.getText().toString());
                            }
                            flexNormal.postInvalidate();
                            flexInput.postInvalidate();

                }
            });

        }
        flexNormal.invalidate();
        flexInput.invalidate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_complete)
    public void onViewClicked() {

        String mnemonicStr = ApplicationUtil.getCurrentWallet().getMnemonic();
        String[] mnemonicArr = mnemonicStr.split(" ");
        boolean isSuccess = true;
        if (myMnemonicStr.size()<12){
            ToastUtil.showShortToast(R.string.comme_complete);
            return;
        }else {
            for (int i=0 ;i<myMnemonicStr.size();i++){
                if (myMnemonicStr.get(i).equals(mnemonicArr[i].trim())){

                }else {
                    Log.e("vvv",myMnemonicStr.get(i)+"=="+mnemonicArr[i]);
                    isSuccess = false;
                    break;
                }

            }
            if (isSuccess){
                ToastUtil.showShortToast(R.string.makecopy_success);
                Wallet currentWallet = ApplicationUtil.getCurrentWallet();
                currentWallet.setStatus(Wallet.STATUS_HANE_MAKE_COPY);
                ApplicationUtil.setCurrentWallet(currentWallet);
                ApplicationUtil.deleteWallet(currentWallet);
                ApplicationUtil.addWallet(currentWallet);


                EventBus.getDefault().post(ApplicationUtil.getCurrentWallet());
                finish();
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }else {
                ToastUtil.showShortToast(R.string.makecopy_failure);
            }
        }
    }
}
