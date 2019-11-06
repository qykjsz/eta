package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetHistory;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class AssetHistoryAdapter extends BaseAdapter<AssetHistory, AssetHistoryAdapter.AssetHistoryViewHolder> {


    public AssetHistoryAdapter(Context context, List<AssetHistory> list) {
        super(context, list);
    }

    @Override
    protected AssetHistoryViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new AssetHistoryViewHolder(getView(R.layout.item_asset_history, parent));
    }

    @Override
    protected void onItemReset(AssetHistoryViewHolder holder) {

    }

    @Override
    protected void onItemSelect(AssetHistoryViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(AssetHistoryViewHolder holder, int position) {

    }

    class AssetHistoryViewHolder extends BaseViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_amount)
        TextView tvAmount;
        public AssetHistoryViewHolder(View itemView) {
            super(itemView);
        }
    }
}
