package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.Asset;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class AssetAdapter extends BaseAdapter<Asset, AssetAdapter.AssetViewHolder> {


    public AssetAdapter(Context context, List<Asset> list) {
        super(context, list);
    }

    @Override
    protected AssetViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new AssetViewHolder(getView(R.layout.item_asset, parent));
    }

    @Override
    protected void onItemReset(AssetViewHolder holder) {

    }

    @Override
    protected void onItemSelect(AssetViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(AssetViewHolder holder, int position) {

    }

    class AssetViewHolder extends BaseViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_amount)
        TextView tvAmount;
        @BindView(R.id.tv_price_cny)
        TextView tvPriceCny;
        public AssetViewHolder(View itemView) {
            super(itemView);
        }
    }
}
