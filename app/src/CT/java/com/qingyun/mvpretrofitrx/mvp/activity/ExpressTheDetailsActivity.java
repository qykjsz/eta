package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.NewFlashData;
import com.qingyun.mvpretrofitrx.mvp.entity.News;
import com.qingyun.mvpretrofitrx.mvp.utils.SaveImgUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.TimeUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ZXingUtils;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ExpressTheDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_source)
    TextView tv_source;
    @BindView(R.id.iv_qr_code)
    ImageView iv_qr_code;
    @BindView(R.id.rl)
    RelativeLayout rl;
    private NewFlashData news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_express_the_details);
    }

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
//        return R.layout.activity_express_the_details;
        return R.layout.layout_express_details;
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
        String jsonString  = getIntent().getStringExtra("people");
        if(!TextUtils.isEmpty(jsonString)){
            news = JSON.parseObject(jsonString,NewFlashData.class);
            tvTime.setText(TimeUtils.getTime(news.time, TimeUtils.DEL_FORMAT_DATE_mm));
            tvTitle1.setText(news.title);
            tvContent.setText(news.content);
            tv_source.setText("来源："+news.source);
            iv_qr_code.setImageBitmap(ZXingUtils.createQRImage(news.url,700,700));
            tvContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        }
    }
    @OnClick({R.id.tv_save_image})
    public void onViewClicked() {
        SaveImgUtil savePhoto = new SaveImgUtil(ExpressTheDetailsActivity.this);
        savePhoto.SaveBitmapFromView(rl);
    }
}
