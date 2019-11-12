package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.contract.TheArticleDetailsContact;
import com.qingyun.mvpretrofitrx.mvp.entity.TheArticleDetails;
import com.qingyun.mvpretrofitrx.mvp.entity.Time;
import com.qingyun.mvpretrofitrx.mvp.presenter.Timepresenterpresenter;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;

public class TheArticleDetailsActivity extends BaseActivity<TheArticleDetailsContact.View, TheArticleDetailsContact.Presenter> implements TheArticleDetailsContact.View {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_laiyuan)
    TextView tvLaiyuan;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.iv_background)
    ImageView ivBackground;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    private Time.NewsBean newsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_the_article_details);
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
        return getResources().getString(R.string.the_article_details);
    }

    @Override
    public boolean haveHeader() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_the_article_details;
    }

    @Override
    public TheArticleDetailsContact.Presenter createPresenter() {
        return new Timepresenterpresenter(getContext());
    }

    @Override
    public TheArticleDetailsContact.View createView() {
        return this;
    }

    @Override
    public void init() {
        newsBean = getIntent().getParcelableExtra("newsBean");
        getPresenter().getTheArticleDetails(newsBean.getId() + "");

    }

    @Override
    public void getContactListFlash(TheArticleDetails theArticleDetails) {
        tvTitle1.setText(theArticleDetails.getTime());
        tvContent.setText(Html.fromHtml(theArticleDetails.getText()));
        tvTime.setText(theArticleDetails.getTime());

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
