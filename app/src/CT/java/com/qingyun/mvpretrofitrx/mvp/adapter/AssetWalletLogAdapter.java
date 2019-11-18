package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.TransferLog;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class AssetWalletLogAdapter extends BaseAdapter<TransferLog, AssetWalletLogAdapter.AssetWalletLogViewHolder> {




    public AssetWalletLogAdapter(Context context, List<TransferLog> list) {
        super(context, list);
    }

    @Override
    protected AssetWalletLogViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_asset_wallet, parent, false);
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
        if (getList().get(position).getType().equals("1")){
            holder.tvCoinName.setText(getContext().getResources().getString(R.string.transfer_in)+getList().get(position).getName());
            holder.tvCoinName.setTextColor(getContext().getResources().getColor(R.color.main_blue));


        }else
        {
            holder.tvCoinName.setText(getContext().getResources().getString(R.string.transfer_out)+getList().get(position).getName());
            holder.tvCoinName.setTextColor(getContext().getResources().getColor(R.color.color_00C176));
        }
        holder.tvAmount.setText(getList().get(position).getAmount());
        if (getList().get(position).getStatus().equals("1")){
            holder.tvStatus.setText(getContext().getResources().getString(R.string.status_success));
        }else {
            holder.tvStatus.setText(getContext().getResources().getString(R.string.status_failure));

        }
        holder.tvTime.setText(getList().get(position).getTime());


    }

    class AssetWalletLogViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_coin_name)
        BoldTextView tvCoinName;
        @BindView(R.id.tv_amount)
        TextView tvAmount;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_time)
        TextView tvTime;
        public AssetWalletLogViewHolder(View itemView) {
            super(itemView);
        }
    }
}
