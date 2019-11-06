package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qingyun.mvpretrofitrx.mvp.adapter.HistoryAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.History;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends BaseActivity {
    @BindView(R.id.rcy)
    RecyclerView rcy;
    private List<History> list;
    private HistoryAdapter distributionHistoryAdapter;

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
        return getResources().getString(R.string.distribution_history);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_simple_list;
    }


    @Override
    protected void requestList() {
        super.requestList();

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
        String type = getIntent().getExtras().getString(IntentUtils.HISTORY_TYPE);
        if (type.equals(IntentUtils.COMPOSE)){
            setTitle(getResources().getString(R.string.compose_history));

        }else if (type.equals(IntentUtils.DISTRIBUTION)){
            setTitle(getResources().getString(R.string.distribution_history));
        }
        list = new ArrayList<>();
        list.add(new History());
        list.add(new History());

        distributionHistoryAdapter  = new HistoryAdapter(getContext(),list);
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.addItemDecoration(DividerHelper.getNotMarginDivider(getContext()));
        rcy.setAdapter(distributionHistoryAdapter);
        refreashView(list,rcy);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
