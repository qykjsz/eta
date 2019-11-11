package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetModle;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class FlashAdapter extends BaseAdapter<AssetModle, FlashAdapter.SelectheappViewHolder> {


    public FlashAdapter(Context context, List<AssetModle> list) {
        super(context, list);
    }

    @Override
    protected SelectheappViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_select_the_app, parent, false);
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
        holder.imageView.setImageResource(getList().get(position).getResId());
        holder.textView.setText(getList().get(position).getStrId());
    }

    class SelectheappViewHolder extends BaseViewHolder {
        @BindView(R.id.image_view)
        ImageView imageView;
        @BindView(R.id.text_view)
        TextView textView;

        public SelectheappViewHolder(View itemView) {
            super(itemView);
        }
    }
}
