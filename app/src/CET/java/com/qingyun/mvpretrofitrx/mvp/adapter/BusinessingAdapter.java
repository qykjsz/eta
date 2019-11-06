package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.Businessing;
import com.senon.mvpretrofitrx.R;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;

public class BusinessingAdapter extends BaseAdapter<Businessing, BusinessingAdapter.BusinessingViewHolder> {


    private final boolean isBuy;
    @BindView(R.id.progress)
    TextView progress;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_amount)
    TextView tvAmount;

    public BusinessingAdapter(Context context, List<Businessing> list, boolean isBuy) {
        super(context, list);
        this.isBuy = isBuy;
    }

    @Override
    protected BusinessingViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_businessing, parent, false);
        return new BusinessingViewHolder(view);
    }

    @Override
    protected void onItemReset(BusinessingViewHolder holder) {

    }

    @Override
    protected void onItemSelect(BusinessingViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(BusinessingViewHolder holder, int position) {
        if (isBuy) {
            holder.tvPrice.setTextColor(getContext().getResources().getColor(R.color.color_buy));
            holder.progress.setBackgroundColor(getContext().getResources().getColor(R.color.color_FFF6F8));
        }else {
            holder.tvPrice.setTextColor(getContext().getResources().getColor(R.color.color_sell));
            holder.progress.setBackgroundColor(getContext().getResources().getColor(R.color.color_F0FCFA));
        }

        if (TextUtils.isEmpty(getList().get(position).getAmount_total())) {
            holder.tvAmount.setText("--");
            holder.tvPrice.setText("--");
        }else {
            BigDecimal a =  new BigDecimal(getList().get(position).getAmount_total());
            BigDecimal b =  new BigDecimal("1000");
            holder.tvAmount.setText(Float.parseFloat(getList().get(position).getAmount_total())<1000?getList().get(position).getAmount_total():a.divide(b,2,BigDecimal.ROUND_DOWN)+"k");
            holder.tvPrice.setText(getList().get(position).getPrice());
        }


    }

    class BusinessingViewHolder extends BaseViewHolder {


        @BindView(R.id.progress)
        TextView progress;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_amount)
        TextView tvAmount;

        public BusinessingViewHolder(View itemView) {
            super(itemView);
        }
    }

}
