package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.Chat;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessage;
import com.senon.mvpretrofitrx.R;

import java.util.List;

public class ChatAdapter extends BaseAdapter<ChatMessage, ChatAdapter.ChatViewHolder> {

    public ChatAdapter(Context context, List<ChatMessage> list) {
        super(context, list);
    }

    @Override
    protected ChatViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_chat,parent,false);
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

    }

    class ChatViewHolder extends BaseViewHolder{

        public ChatViewHolder(View itemView) {
            super(itemView);
        }
    }
}
