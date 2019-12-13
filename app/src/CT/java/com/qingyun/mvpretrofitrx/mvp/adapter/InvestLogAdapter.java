package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.InvestLog;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class InvestLogAdapter extends BaseAdapter<InvestLog, InvestLogAdapter.InvestLogViewHolder> {



    public InvestLogAdapter(Context context, List<InvestLog> list) {
        super(context, list);
    }

    @Override
    protected InvestLogViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_invest_log, parent, false);
        return new InvestLogViewHolder(view);
    }

    @Override
    protected void onItemReset(InvestLogViewHolder holder) {


    }

    @Override
    protected void onItemSelect(InvestLogViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(InvestLogViewHolder holder, int position) {
//        0 充值中   1 成功  2 失败 3充值金额不符 4 其他  没有5
        holder.tvAccount.setText("("+getList().get(position).getGameuser()+")");
        holder.tvPlatformName.setText(getList().get(position).getGamename());
        holder.tvCoinIn.setText("+"+getList().get(position).getGamenumber());
        holder.tvMoneyOut.setText("-"+getList().get(position).getMoney()+getList().get(position).getMoneystate());
        holder.tvTime.setText(getList().get(position).getTime());
        holder.tvStatus.setText(getList().get(position).getState());
        if (getList().get(position).getState().equals(getContext().getResources().getString(R.string.status_invest_success))){
            holder.tvStatus.setTextColor(getContext().getResources().getColor(R.color.color_B7BCDC));

        }else {
            holder.tvStatus.setTextColor(getContext().getResources().getColor(R.color.color_24CE94));

        }
//        if (getList().get(position).getState()==1){
//            holder.tvStatus.setTextColor(getContext().getResources().getColor(R.color.color_B7BCDC));
//
//        }else {
//            holder.tvStatus.setTextColor(getContext().getResources().getColor(R.color.color_24CE94));
//
//        }
//        if (getList().get(position).getState()==0){
//            holder.tvStatus.setText(getContext().getResources().getString(R.string.status_investing));
//        }else if (getList().get(position).getState()==1){
//            holder.tvStatus.setText(getContext().getResources().getString(R.string.status_invest_success));
//        }else {
//            holder.tvStatus.setText(getContext().getResources().getString(R.string.status_invest_failure));
//        }

    }

    class InvestLogViewHolder extends BaseViewHolder {

        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_platform_name)
        TextView tvPlatformName;
        @BindView(R.id.tv_account)
        TextView tvAccount;
        @BindView(R.id.tv_coin_in)
        TextView tvCoinIn;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_money_out)
        TextView tvMoneyOut;
        @BindView(R.id.tv_time)
        TextView tvTime;
        public InvestLogViewHolder(View itemView) {
            super(itemView);
        }
    }
}
