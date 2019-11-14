package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.Content;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class ContentAdapter extends BaseAdapter<Content, ContentAdapter.ContentViewHolder> {



    public ContentAdapter(Context context, List<Content> list) {
        super(context, list);
    }

    @Override
    protected ContentViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_content, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    protected void onItemReset(ContentViewHolder holder) {

    }

    @Override
    protected void onItemSelect(ContentViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(ContentViewHolder holder, int position) {
        holder.tvSecond.setText(getList().get(position).getName());
    }

    class ContentViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_second)
        TextView tvSecond;
        public ContentViewHolder(View itemView) {
            super(itemView);
        }
    }
}
