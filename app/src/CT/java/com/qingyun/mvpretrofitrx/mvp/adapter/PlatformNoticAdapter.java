package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.PlatformNotic;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class PlatformNoticAdapter extends BaseAdapter<PlatformNotic, PlatformNoticAdapter.PlatformNoticViewHolder> {


    public PlatformNoticAdapter(Context context, List<PlatformNotic> list) {
        super(context, list);
    }

    @Override
    protected PlatformNoticViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_platform_notic, parent, false);
        return new PlatformNoticViewHolder(view);
    }

    @Override
    protected void onItemReset(PlatformNoticViewHolder holder) {

    }

    @Override
    protected void onItemSelect(PlatformNoticViewHolder holder) {
    }

    @Override
    protected void viewHolderBind(PlatformNoticViewHolder holder, int position) {
        holder.tvTime.setText(getList().get(position).getTime());
        holder.tvTitle.setText(getList().get(position).getName());
        if (getList().get(position).getIslook().equals("0")){
            holder.tvIsread.setBackground(getBackgroundDrawable(getContext().getResources().getColor(R.color.main_blue),getContext().getResources().getDimensionPixelSize(R.dimen.dp_3)));

        }else {
            holder.tvIsread.setBackground(getBackgroundDrawable(getContext().getResources().getColor(R.color.color_DBDBDB),getContext().getResources().getDimensionPixelSize(R.dimen.dp_3)));

        }
    }

    public static GradientDrawable getBackgroundDrawable(int solidColor, float radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(solidColor);
        drawable.setCornerRadius(radius);
        return drawable;
    }


    class PlatformNoticViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_isread)
        TextView tvIsread;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        public PlatformNoticViewHolder(View itemView) {
            super(itemView);
        }
    }
}
