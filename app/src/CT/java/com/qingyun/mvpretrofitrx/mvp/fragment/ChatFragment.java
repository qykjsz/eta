package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.animation.Animator;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.develop.wallet.eth.Wallet;
import com.develop.wallet.eth.WalletManager;
import com.qingyun.mvpretrofitrx.mvp.activity.chat.AddFriendsActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.chat.AddressBookActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.chat.CreateGroupChatActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.chat.FriendsMessageActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.chat.GroupChatActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.chat.GroupMessageActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.chat.MyChatActivity;
import com.qingyun.mvpretrofitrx.mvp.adapter.ChatAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.contract.ChatContact;
import com.qingyun.mvpretrofitrx.mvp.entity.ApplyGroup;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessage;
import com.qingyun.mvpretrofitrx.mvp.entity.Group;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.entity.NewChat;
import com.qingyun.mvpretrofitrx.mvp.presenter.ChatPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ScanUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.TimeUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.qingyun.mvpretrofitrx.mvp.weight.dialog.ProgressDialogUtils;
import com.senon.mvpretrofitrx.R;
import com.trello.rxlifecycle2.android.FragmentEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.ObservableTransformer;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
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
    @BindView(R.id.btn_person)
    ImageView btnPerson;


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
        EventBus.getDefault().register(this);
        rcyChat.setNestedScrollingEnabled(false);
        list = new ArrayList<>();
        chatAdapter = new ChatAdapter(getContext(), list);
        chatAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                ChatMessage chatMessage = (ChatMessage) list.get(position);
                if (chatMessage.getType() == 1) {
                    GroupMember groupMember = new GroupMember();
                    groupMember.setAddress(chatMessage.getFromwho());
                    groupMember.setName(chatMessage.getFromwhoname());
                    groupMember.setPhoto(chatMessage.getPhone());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(IntentUtils.GROUP_MEMBER, groupMember);
                    startActivity(MyChatActivity.class, bundle);
                } else {
                    Group group = new Group();
                    group.setCode(chatMessage.getQcode());
                    group.setName(chatMessage.getQname());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(IntentUtils.GROUP, group);
//                startActivity(GroupInfoActivity.class,bundle);
                    startActivity(GroupChatActivity.class, bundle);
                }
            }
        });
        rcyChat.setLayoutManager(new LinearLayoutManager(getContext()));
        rcyChat.setAdapter(chatAdapter);
        getPresenter().getNicknameByAdress(ApplicationUtil.getCurrentWallet().getAddress());

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
        getPresenter().newChaList(ApplicationUtil.getCurrentWallet().getAddress());

    }

    @Override
    protected void refresh() {
        super.refresh();
        getPresenter().newChaList(ApplicationUtil.getCurrentWallet().getAddress());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ly_friends_chat, R.id.btn_start, R.id.btn_person, R.id.btn_add, R.id.ly_group_chat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:


                DialogUtils.showPayDialog(getContext(), new DialogUtils.SureListener() {
                    @Override
                    public void onSure(Object o) {
                        ProgressDialogUtils.getInstances().showDialog();
                        WalletManager.decrypt(o.toString(), ApplicationUtil.getCurrentWallet(), new WalletManager.CheckPasswordListener() {
                            @Override
                            public void onSuccess() {
                                getPresenter().registerChat(ApplicationUtil.getCurrentWallet().getAddress());
                                ProgressDialogUtils.getInstances().cancel();
                            }

                            @Override
                            public void onFailure(Exception e) {
                                ToastUtil.showShortToast(R.string.pass_err);
                                ProgressDialogUtils.getInstances().cancel();

                            }
                        });

                    }
                });


                break;
            case R.id.btn_person:
//                startActivity(AddressBookActivity.class);
                RongIM.getInstance().startConversationList(getActivity());

                break;
            case R.id.btn_add:
                AnyLayer.target(btnAdd)
                        .contentView(R.layout.dialog_chat_add)

                        .alignment(Alignment.Direction.VERTICAL, Alignment.Horizontal.ALIGN_RIGHT, Alignment.Vertical.BELOW, true)
                        .backgroundColorInt(getResources().getColor(R.color.bg_dialog))
                        .contentAnim(new LayerManager.IAnim() {
                            @Override
                            public Animator inAnim(View content) {
                                return AnimHelper.createTopInAnim(content);
                            }

                            @Override
                            public Animator outAnim(View content) {
                                return AnimHelper.createTopOutAnim(content);
                            }
                        }).bindData(new LayerManager.IDataBinder() {
                    @Override
                    public void bind(AnyLayer anyLayer) {

                    }
                })
                        .onClickToDismiss(R.id.textView133, new LayerManager.OnLayerClickListener() {
                            @Override
                            public void onClick(AnyLayer anyLayer, View v) {
                                startActivity(CreateGroupChatActivity.class);
                            }
                        })
                        .onClickToDismiss(R.id.btn_add_friends, new LayerManager.OnLayerClickListener() {
                            @Override
                            public void onClick(AnyLayer anyLayer, View v) {
                                startActivity(AddFriendsActivity.class);
                            }
                        })

                        .onClickToDismiss(R.id.textView134, new LayerManager.OnLayerClickListener() {
                            @Override
                            public void onClick(AnyLayer anyLayer, View v) {


                                ScanUtil.start(getActivity());
//                                RxPermissions rxPermissions=new RxPermissions(getActivity());
//                                rxPermissions.request(Manifest.permission.CAMERA).subscribe(new Consumer<Boolean>() {
//                                    @Override
//                                    public void accept(Boolean aBoolean) throws Exception {
//                                        if (aBoolean){
//                                            Intent intent = new Intent(getActivity(),ScanActivity.class);
//                                            startActivity(intent);
//                                        }else{
//                                            //只要有一个权限被拒绝，就会执行
//                                            ToastUtil.showShortToast(R.string.camera_permission_required);
//                                        }
//                                    }
//                                });

                            }
                        })
                        .show();
                break;
            case R.id.ly_group_chat:
                startActivity(GroupMessageActivity.class);

                break;
            case R.id.ly_friends_chat:
                startActivity(FriendsMessageActivity.class);

                break;
        }
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

        showChatUi(true);


    }


    private void showChatUi(boolean isChat){
        if (isChat){
            ly1.setVisibility(View.GONE);
            btnAdd.setVisibility(View.VISIBLE);
            btnPerson.setVisibility(View.VISIBLE);
            getRefreash().setVisibility(View.VISIBLE);
        }else {
            ly1.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.GONE);
            btnPerson.setVisibility(View.GONE);
            getRefreash().setVisibility(View.GONE);
        }
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

        ApplicationUtil.setChatPersonalInfo(groupMember);
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
        getRefreash().setEnableLoadMore(false);
        getPresenter().getChatToken(ApplicationUtil.getCurrentWallet().getAddress());
        getRefreash().setVisibility(View.VISIBLE);
        showChatUi(true);
        list = newChat.getChats();
        chatAdapter.notifyDataSetChanged(list);
        refreashView(list, rcyChat);
        tvNewMessage.setText("");
        tvTime.setText("");
        tvNewMessageFriends.setText("");
        tvTimeFriends.setText("");

        if (!TextUtils.isEmpty(newChat.getAddgroup().getName()))

            tvNewMessage.setText(newChat.getAddgroup().getName() + " " + getResources().getString(R.string.apply_into_group));

        if (!TextUtils.isEmpty(newChat.getAddgroup().getTime()))
            tvTime.setText(TimeUtils.getTime(Long.parseLong(newChat.getAddgroup().getTime())));
        if (!TextUtils.isEmpty(newChat.getAddfriend().getName()))
            tvNewMessageFriends.setText(newChat.getAddfriend().getName() + " " + getResources().getString(R.string.apply_to_be_your_friends));
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
    public void upDataAvatarSuccess(String s) {

    }

    @Override
    public void getChatTokenSuccess(String token) {
        RongIM.init(getContext());
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }
            @Override
            public void onSuccess(String userid) {
                Log.d("TAG", "--onSuccess" + userid);

            }
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.d("TAG", "--onSuccess" + errorCode);
            }
        });
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetStickyEvent(ChatMessage message) {
        refreashData();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWalletChange(Wallet wallet) {
        showChatUi(false);
    }

}
