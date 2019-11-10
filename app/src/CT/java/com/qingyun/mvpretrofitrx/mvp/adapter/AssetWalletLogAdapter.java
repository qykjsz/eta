package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetWalletLog;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.senon.mvpretrofitrx.R;

import java.util.List;

public class AssetWalletLogAdapter extends BaseAdapter<TransferLog, AssetWalletLogAdapter.AssetWalletLogViewHolder> {


    public AssetWalletLogAdapter(Context context, List<TransferLog> list) {
        super(context, list);
    }

    @Override
    protected AssetWalletLogViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_asset_wallet,parent,false);
        return new AssetWalletLogViewHolder(view);
    }

    @Override
    protected void onItemReset(AssetWalletLogViewHolder holder) {

    }

    @Override
    protected void onItemSelect(AssetWalletLogViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(AssetWalletLogViewHolder holder, int position) {

    }

    class AssetWalletLogViewHolder extends BaseViewHolder{


        public AssetWalletLogViewHolder(View itemView) {
            super(itemView);
        }
    }
}
