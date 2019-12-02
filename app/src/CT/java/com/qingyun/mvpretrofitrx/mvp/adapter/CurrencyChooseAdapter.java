package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.CurrencyRate;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class CurrencyChooseAdapter extends BaseAdapter<CurrencyRate, CurrencyChooseAdapter.CoinChooseViewHolder> {

    public CurrencyChooseAdapter(Context context, List<CurrencyRate> list) {
        super(context, list);
    }

    @Override
    protected CoinChooseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_currency_choose, parent, false);
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

    }

    class CoinChooseViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;

        public CoinChooseViewHolder(View itemView) {
            super(itemView);
        }
    }
}
