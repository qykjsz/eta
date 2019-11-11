package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.News;
import com.qingyun.mvpretrofitrx.mvp.utils.TimeUtils;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class FlashAdapter extends BaseAdapter<News, FlashAdapter.SelectheappViewHolder> {


    public FlashAdapter(Context context, List<News> list) {
        super(context, list);
    }

    @Override
    protected SelectheappViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_flash, parent, false);
        return new SelectheappViewHolder(view);
    }

    @Override
    protected void onItemReset(SelectheappViewHolder holder) {

    }

    @Override
    protected void onItemSelect(SelectheappViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(SelectheappViewHolder holder, int position) {
        holder.tvTime.setText(TimeUtils.getTime(Long.parseLong(getList().get(position).getTime()), TimeUtils.DEL_FORMAT_DATE_mm));
        holder.tvContent.setText(getList().get(position).getContent());
        holder.tvTitle.setText(getList().get(position).getTitle());
    }

    class SelectheappViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;

        public SelectheappViewHolder(View itemView) {
            super(itemView);
        }
    }
}
