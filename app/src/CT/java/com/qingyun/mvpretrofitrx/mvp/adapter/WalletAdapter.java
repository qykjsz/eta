package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.senon.mvpretrofitrx.R;

import java.util.List;

public class WalletAdapter extends BaseAdapter<Wallet, WalletAdapter.WalletViewHolder> {

    public WalletAdapter(Context context, List<Wallet> list) {
        super(context, list);
    }

    @Override
    protected WalletViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_wallet,parent,false);
        return new WalletViewHolder(view);
    }

    @Override
    protected void onItemReset(WalletViewHolder holder) {

    }

    @Override
    protected void onItemSelect(WalletViewHolder holder) {
    }

    @Override
    protected void viewHolderBind(WalletViewHolder holder, int position) {

    }

    class WalletViewHolder extends BaseViewHolder{

        public WalletViewHolder(View itemView) {
            super(itemView);
        }
    }
}
