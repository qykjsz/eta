package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class TransferLogAdapter extends BaseAdapter<TransferLog, TransferLogAdapter.TransferLogViewHolder> {




    public TransferLogAdapter(Context context, List<TransferLog> list) {
        super(context, list);
    }

    @Override
    protected TransferLogViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_transfer_log, parent, false);
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
//        if (getList().get(position).getType().equals("1")){
//            holder.tvCoinName.setText(getContext().getResources().getString(R.string.transfer_in));
//
//        }else
//        {
//            holder.tvCoinName.setText(getContext().getResources().getString(R.string.transfer_out));
//
//        }
        holder.tvAmount.setText(getList().get(position).getAmount());
        if (getList().get(position).getStatus().equals("1")){
            holder.tvStatus.setText(getContext().getResources().getString(R.string.status_success));
        }else {
            holder.tvStatus.setText(getContext().getResources().getString(R.string.status_failure));

        }
        holder.tvTime.setText(getList().get(position).getTime());

    }

    class TransferLogViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_amount)
        TextView tvAmount;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_time)
        TextView tvTime;
        public TransferLogViewHolder(View itemView)
        {
            super(itemView);
        }
    }
}
