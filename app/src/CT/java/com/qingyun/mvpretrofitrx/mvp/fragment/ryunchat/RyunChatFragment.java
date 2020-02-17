package com.qingyun.mvpretrofitrx.mvp.fragment.ryunchat;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.develop.wallet.eth.Wallet;
import com.qingyun.mvpretrofitrx.mvp.adapter.MainViewPagerAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.contract.ChatContact;
import com.qingyun.mvpretrofitrx.mvp.entity.ApplyGroup;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessage;
import com.qingyun.mvpretrofitrx.mvp.entity.Group;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.entity.NewChat;
import com.qingyun.mvpretrofitrx.mvp.entity.RyunToken;
import com.qingyun.mvpretrofitrx.mvp.entity.SystemNotice;
import com.qingyun.mvpretrofitrx.mvp.presenter.ChatPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.IndicatorUtils;
import com.senon.mvpretrofitrx.R;
import com.trello.rxlifecycle2.android.FragmentEvent;

import net.lucode.hackware.magicindicator.MagicIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.ObservableTransformer;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;

public class RyunChatFragment extends BaseFragment<ChatContact.View, ChatContact.Presenter> implements ChatContact.View {
    @BindView(R.id.magic_indicator3)
    MagicIndicator magicIndicator3;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    @BindView(R.id.tv_switch_account)
    TextView tvSwitchAccount;
    private List<BaseFragment> fragments;
    private List<String> titles;
    private int index;
    private RyunToken token;
    private MainViewPagerAdapter mainViewPagerAdapter;
    boolean accountChange;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_information;
    }

    @Override
    public ChatContact.Presenter createPresenter() {
        return new ChatPresenter(getContext());
    }

    @Override
    public ChatContact.View createView() {
        return this;
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void refreashData() {
        super.refreashData();

        getPresenter().getChatToken(ApplicationUtil.getCurrentWallet().getAddress());
        if (accountChange) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            for (Fragment fragment : fragments) {
                ft.remove(fragment);
            }
            ft.commitNow();
            fragments.clear();
            mainViewPagerAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void init() {
//        getPresenter().getChatToken(ApplicationUtil.getCurrentWallet().getAddress());
//        getPresenter().registerChat(ApplicationUtil.getCurrentWallet().getAddress());
        accountChange = false;
        EventBus.getDefault().register(this);
        titles = new ArrayList<>();
        titles.add(getResources().getString(R.string.chat));
        titles.add(getResources().getString(R.string.address_book));
        titles.add(getResources().getString(R.string.mine));
        fragments = new ArrayList<>();
//        RyunChatChatListFragment ru = new RyunChatChatListFragment();
//        RyunAddressbookFragment ryunChatChatListFragment = new RyunAddressbookFragment();
//        RyunPersonalFragment ryunChatChatListFragment = new RyunPersonalFragment();

        fragments.add(new RyunChatChatListFragment());
        fragments.add(new RyunAddressbookFragment());
        fragments.add(new RyunPersonalFragment());
        index = 0;
        mainViewPagerAdapter = new MainViewPagerAdapter(getContext(), fragments, getChildFragmentManager());

        viewPager.setAdapter(mainViewPagerAdapter);
        viewPager.setOffscreenPageLimit(3);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                index = i;
                fragments.get(i).refreashData();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        IndicatorUtils.initMagicIndicator3MMM(viewPager, titles, getActivity(), magicIndicator3, 0, 10);
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
    public void onMyResume() {
        super.onMyResume();
        fragments.get(index).onMyResume();
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
    public void getNicknameByAdressSuccess(final GroupMember groupMember) {
//        final UserInfo userInfo = new UserInfo(groupMember.getId()+"",groupMember.getName(), Uri.parse(groupMember.getPhoto()));
//        RongIM.getInstance().setCurrentUserInfo(userInfo);
//        RongIM.getInstance().setMessageAttachedUserInfo(true);
//        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
//
//            @Override
//            public UserInfo getUserInfo(String userId) {
//
//                return userInfo;//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
//            }
//
//        }, true);

        ApplicationUtil.setChatPersonalInfo(groupMember);

        getPresenter().getGroupList(ApplicationUtil.getChatPersonalInfo().getId() + "");


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
//        for (int i=0;i<groupList.size();i++){
//            final io.rong.imlib.model.Group group = new io.rong.imlib.model.Group(groupList.get(i).getId()+"",groupList.get(i).getName(),Uri.parse(groupList.get(i).getPhoto()));
//            RongIM.setGroupInfoProvider(new RongIM.GroupInfoProvider() {
//                @Override
//                public io.rong.imlib.model.Group getGroupInfo(String s) {
//                    return group;
//                }
//            },true);
//        }
//        RongIM.setGroupUserInfoProvider(new RongIM.GroupUserInfoProvider() {
//            @Override
//            public GroupUserInfo getGroupUserInfo(String s, String s1) {
//                return null;
//            }
//        },true);
//        final List<io.rong.imlib.model.Group > group = new ArrayList<>();
//        for (int i=0;i<groupList.size();i++){
//            RongIM.getInstance().refreshGroupInfoCache(new io.rong.imlib.model.Group(groupList.get(i).getId()+"",groupList.get(i).getName(),Uri.parse(groupList.get(i).get)));
////            group.add(new io.rong.imlib.model.Group(groupList.get(i).getId()+"",groupList.get(i).getName(),Uri.parse(groupList.get(i).getPhoto())));
//        }
//

        RongIM.connect(token.getToken(), new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }

            @Override
            public void onSuccess(String userid) {
                Log.d("TAG", "--onSuccess" + userid);
                tvSwitchAccount.setVisibility(View.GONE);

                if (accountChange) {
                    accountChange = false;
                    fragments.add(new RyunChatChatListFragment());
                    fragments.add(new RyunAddressbookFragment());
                    fragments.add(new RyunPersonalFragment());
                    index = 0;
                    mainViewPagerAdapter = new MainViewPagerAdapter(getContext(), fragments, getChildFragmentManager());
                    viewPager.setAdapter(mainViewPagerAdapter);
                    viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int i, float v, int i1) {

                        }

                        @Override
                        public void onPageSelected(int i) {
                            index = i;
                            fragments.get(i).refreashData();
                        }

                        @Override
                        public void onPageScrollStateChanged(int i) {

                        }
                    });
                    IndicatorUtils.initMagicIndicator3MMM(viewPager, titles, getActivity(), magicIndicator3, 0, 10);

                }

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.d("TAG", "--onSuccess" + errorCode);
            }
        });

/**
 * 设置接收消息的监听器。
 *
 * 所有接收到的消息、通知、状态都经由此处设置的监听器处理。包括私聊消息、群组消息、聊天室消息以及各种状态。
 */
        RongIM.getInstance().setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
            @Override
            public boolean onReceived(Message message, int left) {
                Log.e("push>>message", message.toString());
                try {
                    if (message.getConversationType().getValue() == Conversation.ConversationType.SYSTEM.getValue()) {
                        String ss = message.getContent().toString();
                        Log.e("------------------>", ss);
//                        JSONObject jsonObject= message.getContent().getJSONUserInfo();
//                        if (jsonObject.has("extra")){
//                            String str = jsonObject.getString("extra");
//                            SystemNotice systemNotice = new Gson().fromJson(str,SystemNotice.class);
//                            EventBus.getDefault().post(systemNotice);
//
//                        }
                        if (ss.contains("{\"type\":1}")) {
                            EventBus.getDefault().post(new SystemNotice("1"));

                        }
                    } else if (message.getConversationType().getValue() == Conversation.ConversationType.GROUP.getValue()) {
                        getPresenter().getGroupInfo(ApplicationUtil.getChatPersonalInfo().getId() + "", message.getTargetId());
                    }
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
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
        RongIM.getInstance().refreshGroupInfoCache(new io.rong.imlib.model.Group(group.getId() + "", group.getName(), Uri.parse(group.getPhoto())));
    }

    @Override
    public void upDataAvatarSuccess(String s) {

    }

    @Override
    public void getChatTokenSuccess(RyunToken token) {
        this.token = token;
        getPresenter().getNicknameByAdress(ApplicationUtil.getCurrentWallet().getAddress());
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
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWalletChange(Wallet wallet) {
//        RongIM.getInstance().clearEncryptedConversations();
//        RongIM.getInstance().disconnect();
////        refreashData();
        accountChange = true;
        tvSwitchAccount.setVisibility(View.VISIBLE);
    }

}
