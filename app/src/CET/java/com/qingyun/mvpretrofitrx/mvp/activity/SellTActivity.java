package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.NumberFormatUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class SellTActivity extends BaseActivity {

    @BindView(R.id.tv_s)
    TextView tvS;
    @BindView(R.id.et_amount)
    EditText etAmount;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    @BindView(R.id.tv_get)
    TextView tvGet;
    @BindView(R.id.tv_publish)
    TextView tvPublish;
    private int digits = 2;

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
        return getResources().getString(R.string.sell_t);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sell_s;
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
    public boolean haveHeader() {
        return true;
    }


    @Override
    public void init() {
//        etAmount.addTextChangedListener(new MoneyTextWatcher(etAmount));
        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //删除“.”后面超过2位后的数据


                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > digits) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + digits+1);
                        etAmount.setText(s);
                        etAmount.setSelection(s.length()); //光标移到最后

                    }
                }
                //如果"."在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    etAmount.setText(s);
                    etAmount.setSelection(2);
                    tvGet.setText(R.string.format_amount);
                    tvPay.setText(R.string.format_amount);
                }

                //如果起始位置为0,且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        etAmount.setText(s.subSequence(0, 1));
                        etAmount.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > digits) {
                        return;

                    }
                }

                if (!TextUtils.isEmpty(s.toString())){
                    try {
                        float amouunt = Float.parseFloat(s.toString());
                        tvGet.setText(NumberFormatUtils.format2Down(amouunt*0.98) +getResources().getString(R.string.cet));
                        tvPay.setText(amouunt+getResources().getString(R.string.t));
                    }catch (Exception e){

                    }
                }else {
                    tvGet.setText(R.string.format_amount);
                    tvPay.setText(R.string.format_amount);
                }

            }
        });
    }

    @OnClick(R.id.tv_publish)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etAmount.getText().toString())) {
            ToastUtil.showShortToast(R.string.amount_not_null);
            return;
        }

        DialogUtils.showPayDialog(SellTActivity.this, new DialogUtils.SureListener() {
            @Override
            public void onSure(Object o) {



            }
        });


    }
}
