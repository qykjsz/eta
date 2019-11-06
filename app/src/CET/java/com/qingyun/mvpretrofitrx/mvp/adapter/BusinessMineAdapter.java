package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.BusinessSellOrBuy;
import com.qingyun.mvpretrofitrx.mvp.utils.TimeUtils;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class BusinessMineAdapter extends BaseAdapter<BusinessSellOrBuy, BusinessMineAdapter.BusinessViewHolder> {



    private Boolean isComplete;
    private String type = "";
    private OnItemClickListener cancelOrderListener;

    public BusinessMineAdapter(Context context, List<BusinessSellOrBuy> list, Boolean isComplete) {
        super(context, list);
        this.isComplete = isComplete;
    }

    @Override
    protected BusinessViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_business_mine, parent, false);
        return new BusinessViewHolder(view);
    }

    @Override
    protected void onItemReset(BusinessViewHolder holder) {

    }

    @Override
    protected void onItemSelect(BusinessViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(BusinessViewHolder holder, final int position) {

        BusinessSellOrBuy businessSellOrBuy = getList().get(position);

        holder.tvTime.setText(TimeUtils.getTime(businessSellOrBuy.getCreated_at()));
        if (businessSellOrBuy.getIs_over() == 0) {
            holder.tvStatus.setText(getContext().getResources().getString(R.string.status_doing));
            holder.tvStatus.setTextColor(getContext().getResources().getColor(R.color.main_blue));
            holder.btnStatus.setText(getContext().getResources().getString(R.string.cancel_order));
            holder.btnStatus.setTextColor(getContext().getResources().getColor(R.color.main_blue));
            holder.tvAll.setText(businessSellOrBuy.getAmount_lost());
            holder.tv.setText(getContext().getResources().getString(R.string.surplus_quatity));
            holder.btnStatus.setEnabled(true);

        } else if (businessSellOrBuy.getIs_over() == 1) {
            holder.tvStatus.setText(getContext().getResources().getString(R.string.status_completed));
            holder.tvStatus.setTextColor(getContext().getResources().getColor(R.color.color_999999));
            holder.btnStatus.setText(getContext().getResources().getString(R.string.status_completed));
            holder.btnStatus.setTextColor(getContext().getResources().getColor(R.color.color_999999));
            holder.tvAll.setText(businessSellOrBuy.getAmount());
            holder.tv.setText(getContext().getResources().getString(R.string.business_all));
            holder.btnStatus.setEnabled(false);


        } else if (businessSellOrBuy.getIs_over() == 2) {
            holder.tvStatus.setText(getContext().getResources().getString(R.string.status_had_cancel));
            holder.tvStatus.setTextColor(getContext().getResources().getColor(R.color.color_999999));
            holder.btnStatus.setText(getContext().getResources().getString(R.string.status_had_cancel));
            holder.btnStatus.setTextColor(getContext().getResources().getColor(R.color.color_999999));
            holder.tvAll.setText(businessSellOrBuy.getAmount());
            holder.tv.setText(getContext().getResources().getString(R.string.business_all));
            holder.btnStatus.setEnabled(false);

        }
        holder.btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelOrderListener!=null){
                    cancelOrderListener.onItemClick(getList(),position);
                }
            }
        });
    }

    public void setType(String type) {
        this.type = type;
    }

    class BusinessViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_all)
        TextView tvAll;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.btn_status)
        TextView btnStatus;
        @BindView(R.id.tv_)
        TextView tv;
        public BusinessViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void setCancelOrderListener(OnItemClickListener cancelOrderListener){
        this.cancelOrderListener =cancelOrderListener;
    }
}
