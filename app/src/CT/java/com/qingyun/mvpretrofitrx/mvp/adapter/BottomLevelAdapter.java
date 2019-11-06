package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.BottomLevel;
import com.senon.mvpretrofitrx.R;

import java.util.List;

public class BottomLevelAdapter extends BaseAdapter<BottomLevel, BottomLevelAdapter.BottomLevelViewHolder> {


    public BottomLevelAdapter(Context context, List<BottomLevel> list) {
        super(context, list);
    }

    @Override
    protected BottomLevelViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_bottom_level,parent,false);
        return new BottomLevelViewHolder(view);
    }

    @Override
    protected void onItemReset(BottomLevelViewHolder holder) {

    }

    @Override
    protected void onItemSelect(BottomLevelViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(BottomLevelViewHolder holder, int position) {

    }

    class BottomLevelViewHolder extends BaseViewHolder{


        public BottomLevelViewHolder(View itemView) {
            super(itemView);
        }
    }
}
