package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qingyun.mvpretrofitrx.mvp.adapter.SupportAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.contract.SupportContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.qingyun.mvpretrofitrx.mvp.presenter.SupportPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import drawthink.expandablerecyclerview.bean.RecyclerViewData;
import drawthink.expandablerecyclerview.listener.OnRecyclerViewListener;
import io.reactivex.ObservableTransformer;

/**
 * 帮助中心
 */
public class SupportActivity extends BaseActivity<SupportContact.View, SupportContact.Presenter> implements SupportContact.View {


    @BindView(R.id.rcy)
    RecyclerView rcy;
    SupportAdapter supportItemAdapter;
    private List<RecyclerViewData> list;

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
        return getResources().getString(R.string.support_center);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_support;
    }

    @Override
    public SupportContact.Presenter createPresenter() {
        return new SupportPresenter(this);
    }

    @Override
    public SupportContact.View createView() {
        return this;
    }

    @Override
    public void init() {
        list = new ArrayList<>();

        getPresenter().getSupportList();
    }

    @Override
    public void getSupportListSuccess(List<Item> itemList) {


        list = getRecyclerViewData(itemList);
        supportItemAdapter = new SupportAdapter(getContext(),list);
        supportItemAdapter.setAllDatas(list);
        supportItemAdapter.setOnItemClickListener(new OnRecyclerViewListener.OnItemClickListener() {
            @Override
            public void onGroupItemClick(int position, int groupPosition, View view) {

            }

            @Override
            public void onChildItemClick(int position, int groupPosition, int childPosition, View view) {
                Bundle bundle = new Bundle();
                bundle.putString(IntentUtils.TITLE,((Item)list.get(groupPosition).getGroupData()).getName());
                bundle.putString(IntentUtils.URL,((Item)list.get(groupPosition).getGroupData()).getContent().get(childPosition).getUrl());
                startActivity(SupportWebViewActivity.class,bundle);
            }
        });
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.addItemDecoration(DividerHelper.getMyDivider(getContext()));
        rcy.setAdapter(supportItemAdapter);
        refreashView(list,rcy);

    }

    private List<RecyclerViewData> getRecyclerViewData(List<Item> itemList) {
        for (int i = 0;i<itemList.size();i++){
            list.add(new RecyclerViewData(itemList.get(i),itemList.get(i).getContent()));
        }
        return list;
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
