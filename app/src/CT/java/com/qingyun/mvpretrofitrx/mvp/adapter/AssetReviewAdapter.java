package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qingyun.mvpretrofitrx.mvp.entity.Assets;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;
import drawthink.expandablerecyclerview.adapter.BaseRecyclerViewAdapter;
import drawthink.expandablerecyclerview.bean.RecyclerViewData;
import drawthink.expandablerecyclerview.holder.BaseViewHolder;

/**
 * Created by jhj_Plus on 2016/9/2.
 */
public class AssetReviewAdapter extends BaseRecyclerViewAdapter<Assets, Wallet, AssetReviewAdapter.AssetReviewViewHolder> {


    private Context ctx;
    private List datas;
    private LayoutInflater mInflater;

    public AssetReviewAdapter(Context ctx, List<RecyclerViewData> datas) {
        super(ctx, datas);
        mInflater = LayoutInflater.from(ctx);
        this.ctx = ctx;
        this.datas = datas;
    }

    @Override
    public View getGroupView(ViewGroup parent) {
        return mInflater.inflate(R.layout.item_asset_review_group, parent, false);
    }

    @Override
    public View getChildView(ViewGroup parent) {
        return mInflater.inflate(R.layout.item_asset, parent, false);

    }

    @Override
    public AssetReviewViewHolder createRealViewHolder(Context ctx, View view, int viewType) {
        return new AssetReviewViewHolder(ctx,view,viewType);
    }

    @Override
    public void onBindGroupHolder(AssetReviewViewHolder holder, int groupPos, int position, Assets groupData) {
        holder.tvAssetReviewAddress.setText(groupData.getAddress());
    }

    @Override
    public void onBindChildpHolder(AssetReviewViewHolder holder, int groupPos, int childPos, int position, Wallet childData) {
        holder.tvAmount.setText(childData.getNumber());
        holder.tvAmountUsd.setText(childData.getUsdtnumber());
        holder.tvName.setText(childData.getName());
        Glide.with(ctx).load(childData.getImg()).into(holder.ivPic);
    }


    public void notifyDataSetChanged(List<RecyclerViewData> list) {
        this.datas = list;
        notifyDataSetChanged();
    }

    class AssetReviewViewHolder extends BaseViewHolder {

        TextView tvAssetReviewAddress;
        ImageView ivPic;
        BoldTextView tvName;
        BoldTextView tvAmount;
        TextView tvAmountUsd;
        public AssetReviewViewHolder(Context ctx, View itemView, int viewType) {
            super(ctx, itemView, viewType);
            tvAssetReviewAddress = itemView.findViewById(R.id.tv_asset_review_address);
            ivPic = itemView.findViewById(R.id.iv_pic);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            tvAmountUsd = itemView.findViewById(R.id.tv_amount_usd);

        }

        @Override
        public int getChildViewResId() {
            return R.id.child;
        }

        @Override
        public int getGroupViewResId() {
            return R.id.parent;
        }
    }
}
