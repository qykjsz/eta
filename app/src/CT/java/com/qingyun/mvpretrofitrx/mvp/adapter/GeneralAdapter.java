package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.General;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

/**
 * 总资产
 */
public class GeneralAdapter extends BaseAdapter<General, GeneralAdapter.GeneralViewHolder> {


    public GeneralAdapter(Context context, List<General> list) {
        super(context, list);
    }

    @Override
    protected GeneralViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_general, parent, false);
        return new GeneralViewHolder(view);
    }

    @Override
    protected void onItemReset(GeneralViewHolder holder) {

    }

    @Override
    protected void onItemSelect(GeneralViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(GeneralViewHolder holder, int position) {
        holder.textView3.setText(getList().get(position).getString());
        holder.tvName.setText(getList().get(position).getString1());
        holder.tvBottom1.setText(getList().get(position).getString2());
        holder.tvBottom.setText(getList().get(position).getString3());
        Glide.with(getContext()).load(getList().get(position).getTheeopartal()).into(holder.ivPic);

    }

    class GeneralViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_name)
        BoldTextView tvName;
        @BindView(R.id.tv_bottom1)
        TextView tvBottom1;
        @BindView(R.id.tv_bottom)
        TextView tvBottom;
        @BindView(R.id.textView3)
        TextView textView3;

        public GeneralViewHolder(View itemView) {
            super(itemView);
        }
    }
}
