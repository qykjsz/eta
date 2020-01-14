package com.qingyun.mvpretrofitrx.mvp.activity.rongyun;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.senon.mvpretrofitrx.R;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.model.Conversation;

public class FriendChatActivity extends BaseActivity {
    private String mTargetId="FriendChatActivity";

    @Override
    protected String getTitleRightText() {
        return null;
    }

    @Override
    protected String getTitleLeftText() {
        return null;
    }

    @Override
    protected String getTitleText() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chat_friends;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
//        String targetId = "67";
//        Conversation.ConversationType conversationType = Conversation.ConversationType.PRIVATE;
        Uri dd = getIntent().getData();
        String targetId = getIntent().getData().getQueryParameter("targetId");  //传递的融云的id
        String targetIds = getIntent().getData().getQueryParameter("targetIds");  //传递的融云的id
        String title = getIntent().getData().getQueryParameter("title"); //传递的融云名称/用户昵称
//        Conversation.ConversationType conversationType = Conversation.ConversationType.valueOf( getIntent().getData().getLastPathSegment());
        ConversationFragment mConversationFragment=new ConversationFragment();
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("subconversationlist").appendPath(getIntent().getData().getLastPathSegment())
                .appendQueryParameter("targetId", targetId).build();
        mConversationFragment.setUri(uri);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fly_friends_chat,mConversationFragment );
        transaction.commit(); // 提交创建Fragment请求
        
    }
}
