package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinUnit;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class CoinUnitAdapter extends BaseAdapter<CoinUnit, CoinUnitAdapter.CoinUnitViewHolder> {



    public CoinUnitAdapter(Context context, List<CoinUnit> list) {
        super(context, list);
    }

    @Override
    protected CoinUnitViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_coin_unit, parent, false);
        return new CoinUnitViewHolder(view);
    }

    @Override
    protected void onItemReset(CoinUnitViewHolder holder) {

    }

    @Override
    protected void onItemSelect(CoinUnitViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(CoinUnitViewHolder holder, int position) {
        holder.tvName.setText(getList().get(position).getName());
        if (position==0){
            holder.tvName.setTextColor(getContext().getResources().getColor(R.color.main_blue));
            holder.ivVisiable.setVisibility(View.VISIBLE);
        }else {
            holder.tvName.setTextColor(getContext().getResources().getColor(R.color.color_main_text));
            holder.ivVisiable.setVisibility(View.GONE);
        }

    }


    class CoinUnitViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.iv_visiable)
        ImageView ivVisiable;

        public CoinUnitViewHolder(View itemView) {
            super(itemView);
        }
    }
}
