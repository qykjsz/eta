package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.icu.text.TimeZoneFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessage;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.senon.mvpretrofitrx.R;

import java.util.List;

public class ChatMessageAdapter extends BaseAdapter<ChatMessage, ChatMessageAdapter.ChatMessageViewHolder> {


    public ChatMessageAdapter(Context context, List<ChatMessage> list) {
        super(context, list);
    }

    @Override
    protected ChatMessageViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType==1){
             view = LayoutInflater.from(getContext()).inflate(R.layout.item_chat_message_other,parent,false);

        }else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_chat_message_me,parent,false);

        }
        return new ChatMessageViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
//        if (getList().get(position).getFrom().equals(ApplicationUtil.getCurrentWallet().getAddress())){
//            return 0;
//        }else if (getList().get(position).getTo().equals(ApplicationUtil.getCurrentWallet().getAddress())){
//            return 1;
//        }else {
//            return 2;
//        }
        return 1;
    }

    @Override
    protected void onItemReset(ChatMessageViewHolder holder) {

    }

    @Override
    protected void onItemSelect(ChatMessageViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(ChatMessageViewHolder holder, int position) {

    }

    class ChatMessageViewHolder extends BaseViewHolder{

        public ChatMessageViewHolder(View itemView) {
            super(itemView);
        }
    }
}
