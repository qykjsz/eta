package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.History;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class HistoryAdapter extends BaseAdapter<History, HistoryAdapter.HistoryViewHolder> {


    public HistoryAdapter(Context context, List<History> list) {
        super(context, list);
    }

    @Override
    protected HistoryViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new HistoryViewHolder(getView(R.layout.item_history, parent));
    }

    @Override
    protected void onItemReset(HistoryViewHolder holder) {

    }

    @Override
    protected void onItemSelect(HistoryViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(HistoryViewHolder holder, int position) {

    }

    class HistoryViewHolder extends BaseViewHolder {


        @BindView(R.id.tv_amount)
        BoldTextView tvAmount;
        @BindView(R.id.tv_status)
        BoldTextView tvStatus;
        @BindView(R.id.tv_time)
        BoldTextView tvTime;
        public HistoryViewHolder(View itemView) {
            super(itemView);
        }
    }
}
