package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.weight.FlexBoxLayout;
import com.senon.mvpretrofitrx.R;

import am.widget.wraplayout.WrapLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MakeCopyCommemorationActivity extends BaseActivity {
    @BindView(R.id.flex)
    WrapLayout flex;

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
        return getResources().getString(R.string.makecopy_commemoration);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_makecopy_commemoration;
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
        String mnemonicStr = ApplicationUtil.getCurrentWallet().getMnemonic();
        String[] mnemonicArr = mnemonicStr.split(" ");
        for (int i = 0;i<mnemonicArr.length;i++){
            TextView textView = new TextView(getContext());
            textView.setTextColor(getResources().getColor(R.color.color_666666));
            textView.setBackgroundColor(getResources().getColor(R.color.color_EBEBEB));
            textView.setHeight(getResources().getDimensionPixelSize(R.dimen.dp_28));
            textView.setPadding(getResources().getDimensionPixelSize(R.dimen.dp_8),0,getResources().getDimensionPixelSize(R.dimen.dp_8),0);
            textView.setGravity(Gravity.CENTER);
            textView.setText(mnemonicArr[i]);
            flex.addView(textView);

        }
        flex.invalidate();
        setIvTitleRight(R.mipmap.zhu_ma, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        startActivity(ConfirmCommemorationActivity.class);
        finish();
    }
}
