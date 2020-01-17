package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.senon.mvpretrofitrx.R;

import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.adapter.ConversationListAdapter;

public class MyChatListAdapter extends ConversationListAdapter {
    public MyChatListAdapter(Context context) {
        super(context);
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    LayoutInflater mInflater;
    Context mContext;

    @Override
    public long getItemId(int position) {
        return position;
    }

//    //绑定视图并返回
//    @Override
//    protected View newView(Context context, int position, ViewGroup group) {
//        return newView(context, position, group);
////        return mInflater.inflate(R.layout.item_chat_message_me,group,false);
//    }
    // 设置视图的元素
    @Override
    protected void bindView(View v, int position, UIConversation data) {
        super.bindView(v,position,data);
    }


}
