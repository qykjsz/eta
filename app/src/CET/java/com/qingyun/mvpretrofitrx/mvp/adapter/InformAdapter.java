package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.activity.InformDetailActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.Inform;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class InformAdapter extends BaseAdapter<Inform, InformAdapter.InformViewHolder> {

    public InformAdapter(Context context, List<Inform> list) {
        super(context, list);
    }

    @Override
    protected InformViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new InformViewHolder(getView(R.layout.item_inform, parent));
    }

    @Override
    protected void onItemReset(InformViewHolder holder) {

    }

    @Override
    protected void onItemSelect(InformViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(InformViewHolder holder, int position) {
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),InformDetailActivity.class);
                getContext().startActivity(intent);
            }
        });

    }

    class InformViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.btn_detail)
        TextView btnDetail;

        public InformViewHolder(View itemView) {
            super(itemView);
        }
    }
}
