package com.qingyun.mvpretrofitrx.mvp.activity.chat;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qingyun.mvpretrofitrx.mvp.adapter.GroupMessageAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.contract.ChatContact;
import com.qingyun.mvpretrofitrx.mvp.entity.ApplyGroup;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessage;
import com.qingyun.mvpretrofitrx.mvp.entity.Group;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMessage;
import com.qingyun.mvpretrofitrx.mvp.entity.NewChat;
import com.qingyun.mvpretrofitrx.mvp.entity.RyunToken;
import com.qingyun.mvpretrofitrx.mvp.presenter.ChatPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.ObservableTransformer;

public class GroupMessageActivity extends BaseActivity<ChatContact.View,ChatContact.Presenter> implements ChatContact.View {
    @BindView(R.id.rcy)
    RecyclerView rcy;
    private GroupMessageAdapter groupMessageAdapter;
    private List<ApplyGroup> list;

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
        return getResources().getString(R.string.group_message);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_simple_list;
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
    public void init() {
        list = new ArrayList<>();
        groupMessageAdapter = new GroupMessageAdapter(getContext(),list);
        groupMessageAdapter.setAgreeListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                getPresenter().dealApplyIntoGroupApply(ApplicationUtil.getCurrentWallet().getAddress(),((ApplyGroup)list.get(position)).getQcode(),((ApplyGroup)list.get(position)).getId(),2);

            }
        });
        groupMessageAdapter.setRefuseListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                getPresenter().dealApplyIntoGroupApply(ApplicationUtil.getCurrentWallet().getAddress(),((ApplyGroup)list.get(position)).getQcode(),((ApplyGroup)list.get(position)).getId(),3);
            }
        });
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.setAdapter(groupMessageAdapter);
        refreashView(list,rcy);
        getPresenter().addGroupList(ApplicationUtil.getCurrentWallet().getAddress(),page);
    }

    @Override
    protected void refresh() {
        super.refresh();
        getPresenter().addGroupList(ApplicationUtil.getCurrentWallet().getAddress(),page);

    }

    @Override
    protected void loadMore() {
        super.loadMore();
        getPresenter().addGroupList(ApplicationUtil.getCurrentWallet().getAddress(),page);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
        ToastUtil.showShortToast(s);
        page=0;
        refresh();
//        getPresenter().addGroupList(ApplicationUtil.getCurrentWallet().getAddress(),page);


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
        if (isLoadMore){
            list.addAll(applyGroupList);
        }else {
            list = applyGroupList;
        }
        groupMessageAdapter.notifyDataSetChanged(list);
        refreashView(list,rcy);

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
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
