package com.qingyun.mvpretrofitrx.mvp.activity.rongyun;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;

import io.rong.callkit.RongCallKit;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.model.Conversation;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN;

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
    public boolean haveHeader() {
        return true;
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
    protected void setHeaderData() {
        super.setHeaderData();
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |SOFT_INPUT_STATE_HIDDEN );

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
        final String targetId = getIntent().getData().getQueryParameter("targetId");  //传递的融云的id
        String targetIds = getIntent().getData().getQueryParameter("targetIds");  //传递的融云的id
        String title = getIntent().getData().getQueryParameter("title"); //传递的融云名称/用户昵称
        setTitle(title);
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
        setIvTitleRight(R.mipmap.lt_zlsz_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (getIntent().getData().getLastPathSegment().equals(Conversation.ConversationType.GROUP.getName())){
                    bundle.putString(IntentUtils.ID,targetId);
                    startActivity(GroupChatInfoActivity.class,bundle);
                }

            }
        });
        /**
         * 发起单人通话。
         *
         * @param context   上下文
         * @param targetId  会话 id
         * @param mediaType 会话媒体类型
         */
//        RongCallKit.startSingleCall(FriendChatActivity.this,targetId,RongCallKit.CallMediaType.CALL_MEDIA_TYPE_AUDIO);
//        RongCallKit.setGroupMemberProvider(new RongCallKit.GroupMembersProvider() {
//            @Override
//
//            public ArrayList<String> getMemberList(String groupId, final RongCallKit.OnGroupMembersResult result) {
//                //返回群组成员userId的集合
//                return null;
//            }
//        });
    }
}
