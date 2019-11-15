package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qingyun.mvpretrofitrx.mvp.activity.ThewalletManagementActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinType;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class WalletManagerCoinAdapter extends BaseAdapter<CoinType, WalletManagerCoinAdapter.CoinTypeViewHolder> {


    public WalletManagerCoinAdapter(Context context, List<CoinType> list) {
        super(context, list);
    }

    @Override
    protected CoinTypeViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_coin_type, parent, false);
        return new CoinTypeViewHolder(view);
    }

    @Override
    protected void onItemReset(CoinTypeViewHolder holder) {

    }

    @Override
    protected void onItemReset(CoinTypeViewHolder holder, int position) {
        super.onItemReset(holder, position);
        if (position==0){
            holder.itemView.setBackground(getContext().getResources().getDrawable(R.drawable.bg_f2fefe_lefttop_round_25));

        }else {
            holder.itemView.setBackgroundColor(getContext().getResources().getColor(R.color.color_f2fefe));

        }

    }

    @Override
    protected void onItemSelect(CoinTypeViewHolder holder, int position) {
        super.onItemSelect(holder, position);
        if (position==0){
            holder.itemView.setBackground(getContext().getResources().getDrawable(R.drawable.bg_ffffff_lefttop_round_25));
        }else {
            holder.itemView.setBackgroundColor(getContext().getResources().getColor(R.color.color_FFFFFF));


        }
    }

    @Override
    protected void onItemSelect(CoinTypeViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(CoinTypeViewHolder holder, int position) {
        if (getContext() instanceof ThewalletManagementActivity) {
            if (position == 0) {
                holder.cl.setBackground(getContext().getResources().getDrawable(R.drawable.bg_ffffff_top_ret25));
            }
        }
        if (getSelectPosition() == position) {
            holder.ivImg.setImageResource(getList().get(position).getSelectResId());

        } else {
            holder.ivImg.setImageResource(getList().get(position).getResId());

        }

    }

    class CoinTypeViewHolder extends BaseViewHolder {
        @BindView(R.id.cl)
        ConstraintLayout cl;
        @BindView(R.id.iv_img)
        ImageView ivImg;

        public CoinTypeViewHolder(View itemView) {
            super(itemView);
        }
    }
}
