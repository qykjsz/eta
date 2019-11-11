package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.Quotation;
import com.senon.mvpretrofitrx.R;

import java.util.List;
import java.util.Queue;

import butterknife.BindView;

public class MakieAdapter extends BaseAdapter<Quotation, MakieAdapter.SelectheappViewHolder> {


    public MakieAdapter(Context context, List<Quotation> list) {
        super(context, list);
    }

    @Override
    protected SelectheappViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_makie, parent, false);
        return new SelectheappViewHolder(view);
    }

    @Override
    protected void onItemReset(SelectheappViewHolder holder) {

    }

    @Override
    protected void onItemSelect(SelectheappViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(SelectheappViewHolder holder, int position) {
        holder.tvName.setText(getList().get(position).getName());
        holder.tvShangmoney.setText(getList().get(position).getShangmoney());
        holder.tvXiamoney.setText(getList().get(position).getXiamoney());
        holder.tvZb.setText(getList().get(position).getZd());
        Glide.with(getContext()).load(getList().get(position).getImg()).into(holder.ivImg);
    }

    class SelectheappViewHolder extends BaseViewHolder {

        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_shangmoney)
        TextView tvShangmoney;
        @BindView(R.id.tv_xiamoney)
        TextView tvXiamoney;
        @BindView(R.id.tv_zb)
        TextView tvZb;

        public SelectheappViewHolder(View itemView) {
            super(itemView);
        }
    }
}
