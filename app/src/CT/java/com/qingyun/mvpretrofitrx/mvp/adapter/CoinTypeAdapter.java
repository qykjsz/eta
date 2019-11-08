package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinType;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class CoinTypeAdapter extends BaseAdapter<CoinType, CoinTypeAdapter.CoinTypeViewHolder> {



    public CoinTypeAdapter(Context context, List<CoinType> list) {
        super(context, list);
    }

    @Override
    protected CoinTypeViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_coin_type, parent, false);
        return new CoinTypeViewHolder(view);
    }

    @Override
    protected void onItemReset(CoinTypeViewHolder holder) {
        holder.itemView.setBackgroundColor(getContext().getResources().getColor(R.color.color_f2fefe));
    }

    @Override
    protected void onItemSelect(CoinTypeViewHolder holder) {
        holder.itemView.setBackgroundColor(getContext().getResources().getColor(R.color.color_FFFFFF));

    }

    @Override
    protected void viewHolderBind(CoinTypeViewHolder holder, int position) {
        if (getSelectPosition()==position){
            holder.ivImg.setImageResource(getList().get(position).getSelectResId());

        }else {
            holder.ivImg.setImageResource(getList().get(position).getResId());

        }

    }

    class CoinTypeViewHolder extends BaseViewHolder {

        @BindView(R.id.iv_img)
        ImageView ivImg;

        public CoinTypeViewHolder(View itemView) {
            super(itemView);
        }
    }
}
