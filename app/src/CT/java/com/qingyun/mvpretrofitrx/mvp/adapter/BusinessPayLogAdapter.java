package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessPayLog;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class BusinessPayLogAdapter extends BaseAdapter<BusinessPayLog, BusinessPayLogAdapter.BusinessPayLogViewHolder> {



    public BusinessPayLogAdapter(Context context, List<BusinessPayLog> list) {
        super(context, list);
    }

    @Override
    protected BusinessPayLogViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_business_pay_log, parent, false);
        return new BusinessPayLogViewHolder(view);
    }

    @Override
    protected void onItemReset(BusinessPayLogViewHolder holder) {

    }

    @Override
    protected void onItemSelect(BusinessPayLogViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(BusinessPayLogViewHolder holder, int position) {
        holder.tvAddress.setText(getList().get(position).getFrom().substring(0,5)+"****"+getList().get(position).getFrom().substring(getList().get(position).getFrom().length()-5,getList().get(position).getFrom().length()));
        holder.tvStatus.setText(getList().get(position).getType());

        holder.tvAmount.setText(getList().get(position).getMoney());
        holder.tvCoinName.setText(getList().get(position).getMoneyname());
        holder.tvPrice.setText(getList().get(position).getFimoney());
        holder.tvPriceName.setText(getList().get(position).getFimoneyname());

        holder.tvTime.setText(getList().get(position).getTime());
    }

    class BusinessPayLogViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_price_name)
        TextView tvPriceName;
        @BindView(R.id.tv_coin_name)
        TextView tvCoinName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_amount)
        TextView tvAmount;

        public BusinessPayLogViewHolder(View itemView) {
            super(itemView);
        }
    }
}
