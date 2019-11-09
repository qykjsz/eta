package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetModle;
import com.senon.mvpretrofitrx.R;

import java.util.List;

public class SelectTheAppAdapter extends BaseAdapter<AssetModle, SelectTheAppAdapter.SelectheappViewHolder> {


    public SelectTheAppAdapter(Context context, List<AssetModle> list) {
        super(context, list);
    }

    @Override
    protected SelectheappViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_select_the_app, parent, false);
        return null;
    }

    @Override
    protected void onItemReset(SelectheappViewHolder holder) {

    }

    @Override
    protected void onItemSelect(SelectheappViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(SelectheappViewHolder holder, int position) {

    }

    class SelectheappViewHolder extends BaseViewHolder {

        public SelectheappViewHolder(View itemView) {
            super(itemView);
        }
    }
}
