package com.qingyun.mvpretrofitrx.mvp.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.qingyun.mvpretrofitrx.mvp.activity.chat.GroupInfoActivity;
import com.qingyun.mvpretrofitrx.mvp.adapter.FriendsListAdapter;
import com.qingyun.mvpretrofitrx.mvp.adapter.GroupListAdapter;
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
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.senon.mvpretrofitrx.R;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;

public class GroupListFragment extends BaseFragment<ChatContact.View,ChatContact.Presenter> implements ChatContact.View {
    GroupListAdapter groupListAdapter;
    @BindView(R.id.rcy)
    RecyclerView rcy;
    private List<Group> list;
    private List<Group>  searchList;

    public GroupListFragment() {
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
    public boolean haveHeader() {
        return false;
    }

    @Override
    protected String getTitleText() {
        return "";
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_simple_list;
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
        list = new ArrayList<>();
        searchList = new ArrayList<>();
        groupListAdapter = new GroupListAdapter(getContext(), list);
        groupListAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(IntentUtils.GROUP,(Group)list.get(position));
                startActivity(GroupInfoActivity.class,bundle);
            }
        });
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.setAdapter(groupListAdapter);
        refreashView(list,rcy);
        refresh();

    }

    @Override
    protected void refresh() {
        super.refresh();
     getPresenter().getGroupList(ApplicationUtil.getCurrentWallet().getAddress());
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
        list = groupList;
        groupListAdapter.notifyDataSetChanged(list);
        refreashView(list,rcy);
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

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);

    }

    public void search(String toString) {
        if (TextUtils.isEmpty(toString)){
            groupListAdapter.notifyDataSetChanged(list);
            refreashView(list,rcy);
        }else {
            searchList.clear();
            for (int i = 0;i<list.size();i++){
                if (list.get(i).getName().contains(toString)){
                    searchList.add(list.get(i));
                }
            }
            groupListAdapter.notifyDataSetChanged(searchList);
            refreashView(searchList,rcy);


        }

    }
}
