package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.News;
import com.qingyun.mvpretrofitrx.mvp.utils.TimeUtils;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class FlashAdapter extends BaseAdapter<News, FlashAdapter.SelectheappViewHolder> {


    private OnItemClickListener addListener;

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
    protected void viewHolderBind(SelectheappViewHolder holder, final int position) {
        holder.tvTime.setText(TimeUtils.getTime(Long.parseLong(getList().get(position).getTime()), TimeUtils.DEL_FORMAT_DATE_mm));
        holder.tvContent.setText(getList().get(position).getContent());
        holder.tvTitle.setText(getList().get(position).getTitle());
        holder.llOntiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addListener!=null){
                    addListener.onItemClick(getList(),position);
                }
            }
        });
    }

    public void setAddListener(OnItemClickListener addListener) {
        this.addListener = addListener;
    }

    class SelectheappViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.ll_ontiem)
        LinearLayout llOntiem;

        public SelectheappViewHolder(View itemView) {
            super(itemView);
        }
    }
}
