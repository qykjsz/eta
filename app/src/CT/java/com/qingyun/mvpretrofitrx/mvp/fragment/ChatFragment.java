package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.animation.Animator;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.activity.chat.AddFriendsActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.chat.AddressBookActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.chat.CreateGroupChatActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.chat.FriendsMessageActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.chat.GroupMessageActivity;
import com.qingyun.mvpretrofitrx.mvp.adapter.ChatAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.contract.ChatContact;
import com.qingyun.mvpretrofitrx.mvp.entity.ApplyGroup;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessage;
import com.qingyun.mvpretrofitrx.mvp.entity.Group;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.entity.NewChat;
import com.qingyun.mvpretrofitrx.mvp.presenter.ChatPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.TimeUtils;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.ObservableTransformer;
import per.goweii.anylayer.Alignment;
import per.goweii.anylayer.AnimHelper;
import per.goweii.anylayer.AnyLayer;
import per.goweii.anylayer.LayerManager;

public class ChatFragment extends BaseFragment<ChatContact.View, ChatContact.Presenter> implements ChatContact.View {
    @BindView(R.id.ly_1)
    ConstraintLayout ly1;
    @BindView(R.id.ly_2)
    ConstraintLayout ly2;
    @BindView(R.id.nest_scroll)
    NestedScrollView nestScroll;
    Unbinder unbinder;
    @BindView(R.id.rcy_chat)
    RecyclerView rcyChat;
    @BindView(R.id.btn_add)
    ImageView btnAdd;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.tv_name)
    BoldTextView tvName;
    @BindView(R.id.tv_new_message)
    TextView tvNewMessage;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.iv_pic_friends)
    ImageView ivPicFriends;
    @BindView(R.id.tv_name_friends)
    BoldTextView tvNameFriends;
    @BindView(R.id.tv_new_message_friends)
    TextView tvNewMessageFriends;
    @BindView(R.id.tv_time_friends)
    TextView tvTimeFriends;

    private ChatAdapter chatAdapter;
    private List<ChatMessage> list;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_chat;
    }

    @Override
    public ChatContact.Presenter createPresenter() {
        return new ChatPresenter(getActivity());
    }

    @Override
    public ChatContact.View createView() {
        return this;
    }

    @Override
    public void init() {

        list = new ArrayList<>();
        chatAdapter = new ChatAdapter(getContext(), list);
        rcyChat.setLayoutManager(new LinearLayoutManager(getContext()));
        rcyChat.setAdapter(chatAdapter);

    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void refreashData() {
        super.refreashData();
//        getPresenter().newChaList(ApplicationUtil.getCurrentWallet().getAddress());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

//    @OnClick({R.id.ly_friends_chat,R.id.btn_start, R.id.btn_person, R.id.btn_add, R.id.ly_group_chat})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btn_start:
//                getPresenter().registerChat(ApplicationUtil.getCurrentWallet().getAddress());
//                break;
//            case R.id.btn_person:
//                startActivity(AddressBookActivity.class);
//                break;
//            case R.id.btn_add:
//                AnyLayer.target(btnAdd)
//                        .contentView(R.layout.dialog_chat_add)
//
//                        .alignment(Alignment.Direction.VERTICAL, Alignment.Horizontal.ALIGN_RIGHT, Alignment.Vertical.BELOW, true)
//                        .backgroundColorInt(getResources().getColor(R.color.bg_dialog))
//                        .contentAnim(new LayerManager.IAnim() {
//                            @Override
//                            public Animator inAnim(View content) {
//                                return AnimHelper.createTopInAnim(content);
//                            }
//
//                            @Override
//                            public Animator outAnim(View content) {
//                                return AnimHelper.createTopOutAnim(content);
//                            }
//                        }).bindData(new LayerManager.IDataBinder() {
//                    @Override
//                    public void bind(AnyLayer anyLayer) {
//
//                    }
//                })
//                        .onClickToDismiss(R.id.textView133, new LayerManager.OnLayerClickListener() {
//                            @Override
//                            public void onClick(AnyLayer anyLayer, View v) {
//                                startActivity(CreateGroupChatActivity.class);
//                            }
//                        })
//                        .onClickToDismiss(R.id.btn_add_friends, new LayerManager.OnLayerClickListener() {
//                            @Override
//                            public void onClick(AnyLayer anyLayer, View v) {
//                                startActivity(AddFriendsActivity.class);
//                            }
//                        })
//
//                        .onClickToDismiss(R.id.textView134, new LayerManager.OnLayerClickListener() {
//                            @Override
//                            public void onClick(AnyLayer anyLayer, View v) {
//
//                            }
//                        })
//                        .show();
//                break;
//            case R.id.ly_group_chat:
//                startActivity(GroupMessageActivity.class);
//
//                break;
//            case R.id.ly_friends_chat:
//                startActivity(FriendsMessageActivity.class);
//
//                break;
//        }
//    }

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
        ly1.setVisibility(View.GONE);
        nestScroll.setVisibility(View.VISIBLE);


    }

    @Override
    public void sendMessageToGroupSuccess(String s) {

    }

    @Override
    public void exitGroupSuccess(String s) {

    }

    @Override
    public void getGroupMemberListSuccess(List<GroupMember> groupMemberList) {

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
    public void getGroupListSuccess(List<Group> groupList) {

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
        ly1.setVisibility(View.GONE);
        nestScroll.setVisibility(View.VISIBLE);
        list = newChat.getChats();
        chatAdapter.notifyDataSetChanged(list);
        refreashView(list, rcyChat);
        tvNewMessage.setText(newChat.getAddgroup().getName());
        if (!TextUtils.isEmpty(newChat.getAddgroup().getTime()))
            tvTime.setText(TimeUtils.getTime(Long.parseLong(newChat.getAddgroup().getTime())));
        tvNewMessageFriends.setText(newChat.getAddfriend().getName());
        if (!TextUtils.isEmpty(newChat.getAddfriend().getTime()))
            tvTimeFriends.setText(TimeUtils.getTime(Long.parseLong(newChat.getAddfriend().getTime())));
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

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);

    }
}
