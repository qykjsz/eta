package com.qingyun.mvpretrofitrx.mvp.fragment.ryunchat;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import com.develop.wallet.eth.Wallet;
import com.qingyun.mvpretrofitrx.mvp.activity.chat.AddFriendsActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.chat.CreateGroupChatActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.rongyun.ChooseContactActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.contract.ChatContact;
import com.qingyun.mvpretrofitrx.mvp.entity.ApplyGroup;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessage;
import com.qingyun.mvpretrofitrx.mvp.entity.Group;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.entity.NewChat;
import com.qingyun.mvpretrofitrx.mvp.entity.RyunToken;
import com.qingyun.mvpretrofitrx.mvp.presenter.ChatPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.ScanUtil;
import com.rance.library.ButtonData;
import com.rance.library.ButtonEventListener;
import com.rance.library.SectorMenuButton;
import com.senon.mvpretrofitrx.R;
import com.trello.rxlifecycle2.android.FragmentEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.fragment.IHistoryDataResultCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import per.goweii.anylayer.AnyLayer;
import per.goweii.anylayer.LayerManager;

public class RyunChatChatListFragment extends BaseFragment<ChatContact.View,ChatContact.Presenter> implements ChatContact.View {
    @BindView(R.id.top_sector_menu)
    SectorMenuButton topSectorMenu;
    MyRyunChatList mConversationListFragment;

    private FragmentTransaction transaction;
    private int index;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ryun_chat_chat_list;
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
    public void init() {
        index = 0;
//        EventBus.getDefault().register(this);
        initTopSectorMenuButton();
//        getPresenter().getChatToken(ApplicationUtil.getCurrentWallet().getAddress());
        mConversationListFragment=new MyRyunChatList();

        Uri uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")
//                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")
                .build();

        mConversationListFragment.setUri(uri);
        FragmentManager manager = getChildFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.conversationlist, mConversationListFragment);
        transaction.commit();

        Conversation.ConversationType []conversationType = new Conversation.ConversationType[1];
        conversationType[0] = Conversation.ConversationType.GROUP;
        mConversationListFragment.getConversationList(conversationType, new IHistoryDataResultCallback<List<Conversation>>() {
            @Override
            public void onResult(List<Conversation> conversations) {
                for (int i=0;i<conversations.size();i++){
                    getPresenter().getGroupInfo(ApplicationUtil.getChatPersonalInfo().getId()+"",conversations.get(i).getTargetId());
                }
            }

            @Override
            public void onError() {

            }
        },false);
//        //初始化聊天界面底部的自定义按钮  具体在下面会详细介绍
//        RongExtensionManager.getInstance().registerExtensionModule(RecognizeExtensionModule2.getInstence());
//        getPresenter().getNicknameByAdress(ApplicationUtil.getCurrentWallet().getAddress());
//
////群聊的用户数据提供者  同上
//        RongIM.setGroupUserInfoProvider(new RongIM.GroupUserInfoProvider() {
//            @Override
//            public GroupUserInfo getGroupUserInfo(String s, String s1) {
//                //设置数据
//                RongIM.getInstance().refreshGroupInfoCache(new Group(groupid, nickName,Uri.parse(avatar)));
//                return getGroupUserInfos(s, s1); //向自己后台获取
//            }
//        }, true);



    }


    private void initTopSectorMenuButton() {

        final List<ButtonData> buttonDatas = new ArrayList<>();
        int[] drawable = {R.mipmap.lt_gn_icon, R.mipmap.lt_cjql_icon,
                R.mipmap.lt_tjhy_icon, R.mipmap.lt_sys_icon};
        for (int i = 0; i < 4; i++) {
            ButtonData buttonData = ButtonData.buildIconButton(getContext(), drawable[i], 0);
            buttonDatas.add(buttonData);
        }
        topSectorMenu.setButtonDatas(buttonDatas);
        setListener(topSectorMenu);
    }

    private void setListener(final SectorMenuButton button) {
        button.setButtonEventListener(new ButtonEventListener() {
            @Override
            public void onButtonClicked(int index) {
                switch (index){
                    case 1:
                        startActivity(ChooseContactActivity.class);
                        break;
                    case 2:
                        startActivity(AddFriendsActivity.class);
                        break;
                    case 3:
                        ScanUtil.start(getActivity());
                        break;
                }

            }

            @Override
            public void onExpand() {

            }

            @Override
            public void onCollapse() {

            }
        });
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

        RongIM.getInstance().refreshGroupInfoCache(new io.rong.imlib.model.Group(group.getId()+"",group.getName(),Uri.parse(group.getPhoto())));

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
    public void onMyResume() {
        super.onMyResume();
//                RongIM.getInstance().clearEncryptedConversations();


    }

    @Override
    public void refreashData() {
        super.refreashData();

        if (index!=0){
            index=1;
            FragmentManager manager = getChildFragmentManager();
            transaction = manager.beginTransaction();
            transaction.remove(mConversationListFragment);
            transaction.commit();
            mConversationListFragment = new MyRyunChatList();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.conversationlist, mConversationListFragment);
            transaction.commit();
        }




    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);

    }



}
