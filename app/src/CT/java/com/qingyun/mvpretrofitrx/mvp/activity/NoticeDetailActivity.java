package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.contract.NoticContact;
import com.qingyun.mvpretrofitrx.mvp.entity.NoticeDetail;
import com.qingyun.mvpretrofitrx.mvp.entity.PlatformNoticResponse;
import com.qingyun.mvpretrofitrx.mvp.presenter.NoticPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.SystemUtil;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.ObservableTransformer;

public class NoticeDetailActivity extends BaseActivity<NoticContact.View,NoticContact.Presenter> implements NoticContact.View {
    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.wb)
    WebView wb;
    private int id;

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
        return getResources().getString(R.string.notice_details);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_notice_detail;
    }

    @Override
    public NoticContact.Presenter createPresenter() {
        return new NoticPresenter(this);
    }

    @Override
    public NoticContact.View createView() {
        return this;
    }


    @Override
    public void init() {
        id = getIntent().getIntExtra(IntentUtils.NEW_ID,0);
        SystemUtil.getMyUUID(getActivity(), new SystemUtil.RequestPermissionListener() {
            @Override
            public void requestSuccess(String uuid) {
                getPresenter().getNoticeDetail(id, uuid);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void getNoticeListSuccess(PlatformNoticResponse platformNoticResponse) {

    }

    @Override
    public void getNoticeDetailSuccess(NoticeDetail noticeDetail) {
        tvTime.setText(noticeDetail.getTime());
        tvTitle1.setText(noticeDetail.getName());
//        \URLEncoder.encode(data, "utf-8")
        wb.loadData(noticeDetail.getText(), "text/html;charset=UTF-8","UTF-8");
//        wb.loadData(noticeDetail.getText(), "text/html",  "utf-8");

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
