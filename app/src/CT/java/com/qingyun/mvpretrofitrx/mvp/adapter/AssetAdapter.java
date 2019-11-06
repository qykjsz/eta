package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.Asset;
import com.senon.mvpretrofitrx.R;

import java.util.List;

public class AssetAdapter extends BaseAdapter<Asset, AssetAdapter.AssetViewHolder> {


    public AssetAdapter(Context context, List<Asset> list) {
        super(context, list);
    }

    @Override
    protected AssetViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_asset,parent,false);
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

    }

    class  AssetViewHolder extends BaseViewHolder{
        public AssetViewHolder(View itemView) {
            super(itemView);
        }
    }
}
