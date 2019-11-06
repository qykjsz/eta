package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.senon.mvpretrofitrx.R;

import java.util.List;

public class TransferLogAdapter extends BaseAdapter<TransferLog, TransferLogAdapter.TransferLogViewHolder> {


    public TransferLogAdapter(Context context, List<TransferLog> list) {
        super(context, list);
    }

    @Override
    protected TransferLogViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_transfer_log,parent,false);
        return new TransferLogViewHolder(view);
    }

    @Override
    protected void onItemReset(TransferLogViewHolder holder) {

    }

    @Override
    protected void onItemSelect(TransferLogViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(TransferLogViewHolder holder, int position) {

    }

    class TransferLogViewHolder extends BaseViewHolder{

        public TransferLogViewHolder(View itemView) {
            super(itemView);
        }
    }
}
