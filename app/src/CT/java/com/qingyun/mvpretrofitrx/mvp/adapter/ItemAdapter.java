package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class ItemAdapter extends BaseAdapter<Item, ItemAdapter.ItemViewHolder> {


    public ItemAdapter(Context context, List<Item> list) {
        super(context, list);
    }

    @Override
    protected ItemViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    protected void onItemReset(ItemViewHolder holder) {

    }

    @Override
    protected void onItemSelect(ItemViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(ItemViewHolder holder, int position) {
        holder.tvName.setText(getList().get(position).getName());

    }

    class ItemViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;

        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
