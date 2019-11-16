package com.qingyun.mvpretrofitrx.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.utils.LocalManageUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.SpUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseLanguageActivity extends BaseActivity {
    int choose = 0;
    @BindView(R.id.iv_chinese)
    ImageView ivChinese;
    @BindView(R.id.traditional_chinese)
    ImageView traditionalChinese;
    @BindView(R.id.iv_english)
    ImageView ivEnglish;
    @BindView(R.id.iv_korea)
    ImageView ivKorea;


    @Override
    protected String getTitleRightText() {
        return getResources().getString(R.string.complete);
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
        return getResources().getString(R.string.choose_language);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_choose_language;
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
        if (!TextUtils.isEmpty(SpUtils.getObjectFromShare(ChooseLanguageActivity.this, "language") + ""))
            choose = (int) SpUtils.getObjectFromShare(ChooseLanguageActivity.this, "language");
        resetView();
        switch (choose) {
            case 1:
                ivChinese.setVisibility(View.VISIBLE);
                break;
            case 2:
                traditionalChinese.setVisibility(View.VISIBLE);
                break;
            case 3:
                ivEnglish.setVisibility(View.VISIBLE);
                break;
            case 4:
                ivKorea.setVisibility(View.VISIBLE);
                break;
        }
        setTitleRightTextColor(getResources().getColor(R.color.main_blue));
        setTitleRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 应用用户选择语言
                ToastUtil.showShortToast(R.string.not_open);
//                LocalManageUtil.saveLocal(getActivity(), choose);
//                Intent intent = new Intent(ChooseLanguageActivity.this, MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//                Api.UpdataToken();

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_chinese, R.id.btn_traditional_chinese, R.id.btn_english, R.id.btn_korea})
    public void onViewClicked(View view) {
        resetView();
        switch (view.getId()) {
            case R.id.btn_chinese:
                choose = 1;
                YoYo.with(Techniques.BounceIn)
                        .duration(700)
                        .playOn(ivChinese);
                ivChinese.setVisibility(View.VISIBLE);

                break;
            case R.id.btn_traditional_chinese:
                choose = 2;
                YoYo.with(Techniques.BounceIn)
                        .duration(700)
                        .playOn(traditionalChinese);
                traditionalChinese.setVisibility(View.VISIBLE);

                break;
            case R.id.btn_english:
                choose = 3;
                YoYo.with(Techniques.BounceIn)
                        .duration(700)
                        .playOn(ivEnglish);
                ivEnglish.setVisibility(View.VISIBLE);

                break;
            case R.id.btn_korea:
                choose = 4;
                YoYo.with(Techniques.BounceIn)
                        .duration(700)
                        .playOn(ivKorea);
                ivKorea.setVisibility(View.VISIBLE);

                break;

        }
    }

    private void resetView() {
        ivChinese.setVisibility(View.GONE);
        ivEnglish.setVisibility(View.GONE);
        ivKorea.setVisibility(View.GONE);
        traditionalChinese.setVisibility(View.GONE);

    }


}
