package com.qingyun.mvpretrofitrx.mvp.activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.qingyun.mvpretrofitrx.mvp.adapter.PlatformNoticAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.contract.NoticContact;
import com.qingyun.mvpretrofitrx.mvp.entity.NoticeDetail;
import com.qingyun.mvpretrofitrx.mvp.entity.PlatformNotic;
import com.qingyun.mvpretrofitrx.mvp.entity.PlatformNoticResponse;
import com.qingyun.mvpretrofitrx.mvp.presenter.NoticPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.SystemUtil;
import com.senon.mvpretrofitrx.R;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.ObservableTransformer;




public class PlatformNoticActivity extends BaseActivity<NoticContact.View,NoticContact.Presenter> implements NoticContact.View {
    PlatformNoticAdapter platformNoticAdapter;
    @BindView(R.id.rcy)
    RecyclerView rcy;
    private List<PlatformNotic> list;

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
        return getResources().getString(R.string.platform_notice);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_simple_list;
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
        list = new ArrayList<>();
        platformNoticAdapter = new PlatformNoticAdapter(getContext(), list);
        platformNoticAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(IntentUtils.NEW_ID,Integer.parseInt(((PlatformNotic)list.get(position)).getId()));
                startActivity(NoticeDetailActivity.class,bundle);
            }
        });
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.addItemDecoration(DividerHelper.getMyDivider(getContext()));
        rcy.setAdapter(platformNoticAdapter);
        SystemUtil.getMyUUID(getActivity(), new SystemUtil.RequestPermissionListener() {
            @Override
            public void requestSuccess(String uuid) {
                getPresenter().getNoticeList(page,uuid);

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
        if (isLoadMore){
            list.addAll(platformNoticResponse.getNews()==null?new ArrayList<PlatformNotic>():platformNoticResponse.getNews());
        }else {
            list = platformNoticResponse.getNews()==null?new ArrayList<PlatformNotic>():platformNoticResponse.getNews();
        }
        platformNoticAdapter.notifyDataSetChanged(list);
        refreashView(list,rcy);
    }

    @Override
    public void getNoticeDetailSuccess(NoticeDetail noticeDetail) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
