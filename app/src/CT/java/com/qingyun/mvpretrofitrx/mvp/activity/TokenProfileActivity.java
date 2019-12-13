package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.contract.TokenProfileContact;
import com.qingyun.mvpretrofitrx.mvp.entity.GoldInfo;
import com.qingyun.mvpretrofitrx.mvp.presenter.TokenProflePresenter;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.ObservableTransformer;

import static com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils.TRANSFER_COIN_NAME;

public class TokenProfileActivity extends BaseActivity<TokenProfileContact.View, TokenProfileContact.Presenter> implements TokenProfileContact.View {
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_coin_name)
    BoldTextView tvCoinName;
    @BindView(R.id.ly1)
    ConstraintLayout ly1;
    @BindView(R.id.textView135)
    BoldTextView textView135;
    @BindView(R.id.textView140)
    TextView textView140;
    @BindView(R.id.textView145)
    TextView textView145;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_web_url)
    TextView tvWebUrl;
    @BindView(R.id.tv_while_book)
    TextView tvWhileBook;
    @BindView(R.id.ly2)
    ConstraintLayout ly2;
    @BindView(R.id.textView152)
    BoldTextView textView152;
    @BindView(R.id.textView155)
    TextView textView155;
    @BindView(R.id.textView156)
    TextView textView156;
    @BindView(R.id.textView157)
    TextView textView157;
    @BindView(R.id.tv_publish_time)
    TextView tvPublishTime;
    @BindView(R.id.tv_publish_amount)
    TextView tvPublishAmount;
    @BindView(R.id.tv_publish_price)
    TextView tvPublishPrice;
    @BindView(R.id.ly3)
    ConstraintLayout ly3;
    @BindView(R.id.textView161)
    BoldTextView textView161;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.ly_null)
    ConstraintLayout lyNull;
    @BindView(R.id.scroll)
    NestedScrollView scroll;

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
        return getResources().getString(R.string.token_profile);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_token_profile;
    }

    @Override
    public TokenProfileContact.Presenter createPresenter() {
        return new TokenProflePresenter(this);
    }

    @Override
    public TokenProfileContact.View createView() {
        return this;
    }


    @Override
    public void init() {
        getPresenter().getGoldInfo(getIntent().getStringExtra(TRANSFER_COIN_NAME));
    }

    @Override
    public void getGoldInfoSuccess(GoldInfo goldInfo) {
        Glide.with(getActivity()).load(goldInfo.getImg()).into(ivImg);
        tvCoinName.setText(goldInfo.getName());
        tvWebUrl.setText(goldInfo.getWebsite());
        tvWhileBook.setText(goldInfo.getPaper());
        tvPublishTime.setText(goldInfo.getIssuetime());
        tvPublishAmount.setText(goldInfo.getIssuenumber());
        tvPublishPrice.setText(goldInfo.getIssueprice());
        tvInfo.setText(goldInfo.getIntroduction());
        lyNull.setVisibility(View.GONE);
        scroll.setVisibility(View.VISIBLE);

    }


    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
