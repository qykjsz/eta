package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.Proportion;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class ProportionAdapter extends BaseAdapter<Proportion, ProportionAdapter.ProportionViewHolder> {


    public ProportionAdapter(Context context, List<Proportion> list) {
        super(context, list);
    }

    @Override
    protected ProportionViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_proportion, parent, false);
        return new ProportionViewHolder(view);
    }

    @Override
    protected void onItemReset(ProportionViewHolder holder) {

    }

    @Override
    protected void onItemSelect(ProportionViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(ProportionViewHolder holder, int position) {
        holder.tvName.setText(getList().get(position).getName());
        holder.tvPercent.setText(getList().get(position).getBili());

    }

    class ProportionViewHolder extends BaseViewHolder {

        @BindView(R.id.iv_dot)
        ImageView ivDot;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_percent)
        TextView tvPercent;


        public ProportionViewHolder(View itemView) {
            super(itemView);
        }
    }
}
