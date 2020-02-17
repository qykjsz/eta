package com.qingyun.mvpretrofitrx.mvp.activity.rongyun;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.contract.ChatContact;
import com.qingyun.mvpretrofitrx.mvp.entity.ApplyGroup;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessage;
import com.qingyun.mvpretrofitrx.mvp.entity.Group;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.entity.NewChat;
import com.qingyun.mvpretrofitrx.mvp.entity.RyunToken;
import com.qingyun.mvpretrofitrx.mvp.presenter.ChatPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.StatusBarUtil;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.model.Conversation;

public class FriendChatActivity extends BaseActivity<ChatContact.View, ChatContact.Presenter> implements ChatContact.View {
    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    @BindView(R.id.iv_title_right1)
    ImageView ivTitleRight1;
    private String mTargetId = "FriendChatActivity";
    private String targetId;

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
        return false;
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
    public ChatContact.Presenter createPresenter() {
        return new ChatPresenter(this);
    }

    @Override
    public ChatContact.View createView() {
        return this;
    }


    @Override
    protected boolean setSoftInput() {
        return false;
    }

    @Override
    protected void setHeaderData() {
        super.setHeaderData();
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |SOFT_INPUT_STATE_HIDDEN );

    }


    @Override
    public boolean setHideBar() {
        return false;
    }

    @Override
    public void init() {
//        String targetId = "67";
//        Conversation.ConversationType conversationType = Conversation.ConversationType.PRIVATE;
        StatusBarUtil.setStatusBarDarkTheme(getActivity(), true);
        Uri dd = getIntent().getData();
        targetId = getIntent().getData().getQueryParameter("targetId");  //传递的融云的id
        String targetIds = getIntent().getData().getQueryParameter("targetIds");  //传递的融云的id
        String title = getIntent().getData().getQueryParameter("title"); //传递的融云名称/用户昵称
        setTitle(title);
//        Conversation.ConversationType conversationType = Conversation.ConversationType.valueOf( getIntent().getData().getLastPathSegment());
        ConversationFragment mConversationFragment = new ConversationFragment();
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("subconversationlist").appendPath(getIntent().getData().getLastPathSegment())
                .appendQueryParameter("targetId", targetId).build();
        mConversationFragment.setUri(uri);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fly_friends_chat, mConversationFragment);
        transaction.commit(); // 提交创建Fragment请求
        if (getIntent().getData().getLastPathSegment().equals(Conversation.ConversationType.GROUP.getName())) {
            setIvTitleRight(R.mipmap.lt_zlsz_icon, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    if (getIntent().getData().getLastPathSegment().equals(Conversation.ConversationType.GROUP.getName())) {
                        getPresenter().checkIsInGroup(ApplicationUtil.getChatPersonalInfo().getId()+"",targetId);
//                        startActivity(GroupChatInfoActivity.class,bundle);
                    }

                }
            });

        }
        getIvTitleRight().setVisibility(View.GONE);
        if (getIntent().getData().getLastPathSegment().equals(Conversation.ConversationType.GROUP.getName())) {
            getPresenter().getGroupMemberList(ApplicationUtil.getChatPersonalInfo().getId() + "", targetId);
        }
    }

    @Override
    public void setIvTitleRight(int resid, View.OnClickListener onClickListener) {
        ivTitleRight1.setVisibility(View.VISIBLE);
//        ivTitleRight.setImageResource(resid);
        Glide.with(getContext()).load(resid).into(ivTitleRight1);
        ivTitleRight1.setOnClickListener(onClickListener);
    }

    @Override
    public void setTitle(String s) {
        tvTitle1.setText(s);
    }

    @Override
    public ImageView getIvTitleRight() {
        return ivTitleRight1;
    }

    @Override
    public void applyToFriendsSuccess(String s) {

    }

    @Override
    public void changeNicknameSuccess(String s) {

    }

    @Override
    public void setMessageReadGroupSuccess(String s) {

    }

    @Override
    public void getGroupChatLogSuccess(List<ChatMessage> chatMessageList) {

    }

    @Override
    public void registerChatSuccess(String s) {

    }

    @Override
    public void sendMessageToGroupSuccess(String s) {

    }

    @Override
    public void exitGroupSuccess(String s) {

    }

    @Override
    public void getGroupMemberListSuccess(List<GroupMember> groupMemberList) {
        for (int i = 0; i < groupMemberList.size(); i++) {
            int finalI = i;
            if (groupMemberList.get(i).getId() == ApplicationUtil.getChatPersonalInfo().getId()) {
                getIvTitleRight().setVisibility(View.VISIBLE);

            }

        }
    }

    @Override
    public void transferGroupSuccess(String s) {

    }

    @Override
    public void getNicknameByAdressSuccess(GroupMember groupMember) {

    }

    @Override
    public void dealApplyIntoGroupApplySuccess(String s) {

    }

    @Override
    public void applyIntoGroupSuccess(String s) {

    }

    @Override
    public void setIfGroupReviewSuccess(String s) {

    }


    @Override
    public void getGroupListSuccess(final List<Group> groupList) {

    }

    @Override
    public void createGroupSuccess(String s) {

    }

    @Override
    public void addGroupListSuccess(List<ApplyGroup> applyGroupList) {

    }

    @Override
    public void addFriendsListSuccess(List<ApplyGroup> applyGroupList) {

    }

    @Override
    public void newChaListSuccess(NewChat newChat) {

    }

    @Override
    public void seeChatMessageLogSuccess(List<ChatMessage> chatMessageList) {

    }

    @Override
    public void viewMessageSuccess(String s) {

    }

    @Override
    public void sendMessageSuccess(String s) {

    }

    @Override
    public void getFriendsListSuccess(List<GroupMember> groupMemberList) {

    }

    @Override
    public void applyToFriendsPassSuccess(String s) {

    }

    @Override
    public void applyToFriendsRefuseSuccess(String s) {

    }

    @Override
    public void getGroupInfoSuccess(Group group) {
        if (group==null){
            Bundle bundle = new Bundle();
            bundle.putString(IntentUtils.ID, targetId);
            Intent intent = new Intent(getContext(), GroupChatInfoActivity.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, 0);
        }

    }

    @Override
    public void upDataAvatarSuccess(String s) {

    }

    @Override
    public void getChatTokenSuccess(RyunToken ryunToken) {

    }

    @Override
    public void deleteFriendsSuccess(String s) {

    }

    @Override
    public void setRemarkSuccess(String s) {

    }

    @Override
    public void addGroupMemberSuccess(String s) {

    }

    @Override
    public void removeGroupMenberSuccess(String s) {

    }

    @Override
    public void upDataGroupNameSuccess(String s) {

    }

    @Override
    public void upDataGroupExplainSuccess(String s) {

    }

    @Override
    public void addGroupAddressBookSuccess(String s) {

    }

    @Override
    public void addBlacklistSuccess(String s) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            finish();
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick(R.id.ly_back1)
    public void onViewClicked() {
        finish();
    }
}
