package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.CoinType;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class BottomLevelAdapter extends BaseAdapter<CoinType, BottomLevelAdapter.BottomLevelViewHolder> {




    public BottomLevelAdapter(Context context, List<CoinType> list) {
        super(context, list);
    }

    @Override
    protected BottomLevelViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_bottom_level, parent, false);
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
        holder.tvName.setText(getList().get(position).getCoinType());
        holder.tvBottom.setText(getList().get(position).getName());
        holder.ivPic.setImageResource(getList().get(position).getResId());
        if (position!=0){
            holder.itemView.setBackground(getContext().getResources().getDrawable(R.mipmap.sy_zc_dk));
        }else {
//            holder.itemView.setEnabled(true);
            holder.itemView.setBackgroundColor(getContext().getResources().getColor(R.color.transparent));

        }

    }

    class BottomLevelViewHolder extends BaseViewHolder {

        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_name)
        BoldTextView tvName;
        @BindView(R.id.tv_bottom)
        TextView tvBottom;
        public BottomLevelViewHolder(View itemView) {
            super(itemView);
        }
    }
}
