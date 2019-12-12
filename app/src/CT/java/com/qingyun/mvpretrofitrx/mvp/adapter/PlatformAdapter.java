package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.Platform;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class PlatformAdapter extends BaseAdapter<Platform, PlatformAdapter.PlatformViewHolder> {



    public PlatformAdapter(Context context, List<Platform> list) {
        super(context, list);
    }

    @Override
    protected PlatformViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_platform, parent, false);
        return new PlatformViewHolder(view);
    }

    @Override
    protected void onItemReset(PlatformViewHolder holder) {

    }

    @Override
    protected void onItemSelect(PlatformViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(PlatformViewHolder holder, int position) {
        holder.tvName.setText(getList().get(position).getName());
    }

    class PlatformViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        public PlatformViewHolder(View itemView) {
            super(itemView);
        }
    }
}
