package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class CoinChooseAdapter extends BaseAdapter<Wallet, CoinChooseAdapter.CoinChooseViewHolder> {


    public CoinChooseAdapter(Context context, List<Wallet> list) {
        super(context, list);
    }

    @Override
    protected CoinChooseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_coin_choose, parent, false);
        return new CoinChooseViewHolder(view);
    }

    @Override
    protected void onItemReset(CoinChooseViewHolder holder) {

    }

    @Override
    protected void onItemSelect(CoinChooseViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(CoinChooseViewHolder holder, int position) {
        holder.tvName.setText(getList().get(position).getName());
        Glide.with(ApplicationUtil.getContext()).load(getList().get(position).getImg()).into(holder.imageView10);

    }

    class CoinChooseViewHolder extends BaseViewHolder {


        @BindView(R.id.imageView10)
        ImageView imageView10;
        @BindView(R.id.tv_name)
        TextView tvName;

        public CoinChooseViewHolder(View itemView) {
            super(itemView);
        }
    }
}
