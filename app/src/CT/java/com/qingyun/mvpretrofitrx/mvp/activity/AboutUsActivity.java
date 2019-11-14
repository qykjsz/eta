package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qingyun.mvpretrofitrx.mvp.adapter.ItemAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.contract.AboutUsContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.qingyun.mvpretrofitrx.mvp.presenter.AboutUsPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;

/***
 * 帮助中心
 */
public class AboutUsActivity extends BaseActivity<AboutUsContact.View,AboutUsContact.Presenter> implements AboutUsContact.View {


    @BindView(R.id.rcy)
    RecyclerView rcy;

    private ItemAdapter itemAdapter;
    private List<Item> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView();
    }

    @Override
    public boolean haveHeader() {
        return true;
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
        return getResources().getString(R.string.About_Us);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    public AboutUsContact.Presenter createPresenter() {
        return new AboutUsPresenter(this);
    }

    @Override
    public AboutUsContact.View createView() {
        return this;
    }

    @Override
    public void init() {
        list = new ArrayList<>();
        itemAdapter = new ItemAdapter(getContext(),list);
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.addItemDecoration(DividerHelper.getMyDivider(getContext()));
        itemAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                Bundle bundle  = new Bundle();
                bundle.putSerializable(IntentUtils.ITEM,(Item)list.get(position));
                startActivity(MyWebViewActivity.class,bundle);
            }
        });
        rcy.setAdapter(itemAdapter);
        getPresenter().getAboutUsList();

    }

    @Override
    public void getAboutUsListSuccess(List<Item> itemList) {
        itemAdapter.notifyDataSetChanged(itemList);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
