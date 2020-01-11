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
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessage;
import com.qingyun.mvpretrofitrx.mvp.utils.GlideUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.TimeUtils;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class ChatAdapter extends BaseAdapter<ChatMessage, ChatAdapter.ChatViewHolder> {



    public ChatAdapter(Context context, List<ChatMessage> list) {
        super(context, list);
    }

    @Override
    protected ChatViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_chat, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    protected void onItemReset(ChatViewHolder holder) {

    }

    @Override
    protected void onItemSelect(ChatViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(ChatViewHolder holder, int position) {
        if (getList().get(position).getType() == 1) {
            holder.tvName.setText(getList().get(position).getFromwhoname());
            holder.tvPic.setVisibility(View.INVISIBLE);
            holder.ivPic.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(getList().get(position).getPhone()).apply(GlideUtils.getAvaterOptions()).into(holder.ivPic);
        } else {
            holder.tvPic.setVisibility(View.VISIBLE);
            holder.ivPic.setVisibility(View.INVISIBLE);
            holder.tvName.setText(getList().get(position).getQname());
            holder.tvPic.setText(getList().get(position).getQname().substring(0,1));
        }
        holder.tvNewMessage.setText(getList().get(position).getText());
        holder.tvTime.setText(TimeUtils.getTime(Long.parseLong(getList().get(position).getTime())));
    }

    class ChatViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_name)
        BoldTextView tvName;
        @BindView(R.id.tv_new_message)
        TextView tvNewMessage;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_pic)
        BoldTextView tvPic;
        public ChatViewHolder(View itemView) {
            super(itemView);
        }
    }
}
