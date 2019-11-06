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

public class BusinessAdapter extends BaseAdapter<BusinessSellOrBuy, BusinessAdapter.BusinessViewHolder> {


    private  Boolean isComplete;
    private String type="";

    public BusinessAdapter(Context context, List<BusinessSellOrBuy> list,Boolean isComplete) {
        super(context, list);
        this.isComplete =isComplete;
    }

    @Override
    protected BusinessViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (isComplete){
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_business, parent, false);

        }else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_business_queueing, parent, false);

        }
        return new BusinessViewHolder(view);
    }

    @Override
    protected void onItemReset(BusinessViewHolder holder) {

    }

    @Override
    protected void onItemSelect(BusinessViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(BusinessViewHolder holder, int position) {
//
        if (isComplete){
            holder.tvAcount.setText(getList().get(position).getAmount());

        }else {
            holder.tvAcount.setText(getList().get(position).getAmount_lost());

        }
        holder.tvTime.setText(TimeUtils.getTime(getList().get(position).getCreated_at()));
        holder.tvType.setText(type);


    }

    public void setType(String type) {
        this.type = type;
    }

    class BusinessViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_acount)
        TextView tvAcount;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public BusinessViewHolder(View itemView) {
            super(itemView);
        }
    }
}
