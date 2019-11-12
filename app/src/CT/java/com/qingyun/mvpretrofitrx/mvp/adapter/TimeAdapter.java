package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.Time;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class TimeAdapter extends BaseAdapter<Time.NewsBean, TimeAdapter.SelectheappViewHolder> {
    private OnItemClickListener clickListener;

    public TimeAdapter(Context context, List<Time.NewsBean> list) {
        super(context, list);
    }

    @Override
    protected SelectheappViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_time, parent, false);
        return new SelectheappViewHolder(view);
    }

    @Override
    protected void onItemReset(SelectheappViewHolder holder) {

    }

    @Override
    protected void onItemSelect(SelectheappViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(SelectheappViewHolder holder, final int position) {
        Glide.with(getContext()).load(getList().get(position).getImg()).into(holder.ivBack);
        holder.tvName.setText(getList().get(position).getName());
        holder.tvTime.setText("来源：ET APP  " + getList().get(position).getTime());
        holder.rlTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener!=null){
                    clickListener.onItemClick(getList(),position);
                }
            }
        });

    }
    public void setAddListener(OnItemClickListener addListener) {
        this.clickListener = addListener;
    }
    class SelectheappViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.iv_back)
        ImageView ivBack;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.rl_time)
        RelativeLayout rlTime;

        public SelectheappViewHolder(View itemView) {
            super(itemView);
        }
    }
}
