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
import com.qingyun.mvpretrofitrx.mvp.entity.Asset;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class AssetAdapter extends BaseAdapter<Asset, AssetAdapter.AssetViewHolder> {




    public AssetAdapter(Context context, List<Asset> list) {
        super(context, list);
    }

    @Override
    protected AssetViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_asset, parent, false);
        return new AssetViewHolder(view);
    }

    @Override
    protected void onItemReset(AssetViewHolder holder) {

    }

    @Override
    protected void onItemSelect(AssetViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(AssetViewHolder holder, int position) {
        holder.tvAmount.setText(getList().get(position).getNumber());
        holder.tvAmountUsd.setText(getList().get(position).getUsdtnumber());
        holder.tvName.setText(getList().get(position).getName());
        Glide.with(getContext()).load(getList().get(position).getImg()).into(holder.ivPic);

    }

    class AssetViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_name)
        BoldTextView tvName;
        @BindView(R.id.tv_amount)
        BoldTextView tvAmount;
        @BindView(R.id.tv_amount_usd)
        TextView tvAmountUsd;
        public AssetViewHolder(View itemView) {
            super(itemView);
        }
    }
}
