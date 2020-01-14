package com.qingyun.mvpretrofitrx.mvp.fragment.ryunchat;

import android.content.Context;
import android.util.Log;

import com.qingyun.mvpretrofitrx.mvp.adapter.MyChatListAdapter;

import java.util.List;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.fragment.IHistoryDataResultCallback;
import io.rong.imkit.widget.adapter.ConversationListAdapter;
import io.rong.imkit.widget.adapter.SubConversationListAdapter;
import io.rong.imlib.model.Conversation;

public class MyRyunChatList extends ConversationListFragment {


    private MyChatListAdapter mAdapter;

    public void setAdapter(MyChatListAdapter adapter) {
        mAdapter = adapter;
    }

    //重写此方法，设置自己的adapter
    @Override
    public ConversationListAdapter onResolveAdapter(Context context) {
        if (mAdapter == null) {
            mAdapter = new MyChatListAdapter(context);
        }
        return mAdapter;
    }


    @Override
    public void getConversationList(Conversation.ConversationType[] conversationTypes, IHistoryDataResultCallback<List<Conversation>> callback, boolean isLoadMore) {
        Log.e("rongyun--->>",conversationTypes.toString());
        super.getConversationList(conversationTypes, callback, isLoadMore);



    }


}
