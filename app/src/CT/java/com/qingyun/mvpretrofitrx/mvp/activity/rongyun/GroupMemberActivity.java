package com.qingyun.mvpretrofitrx.mvp.activity.rongyun;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.qingyun.mvpretrofitrx.mvp.adapter.RyunGroupMemberAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.contract.ChatContact;
import com.qingyun.mvpretrofitrx.mvp.entity.ApplyGroup;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatCode;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessage;
import com.qingyun.mvpretrofitrx.mvp.entity.Group;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.entity.NewChat;
import com.qingyun.mvpretrofitrx.mvp.entity.RyunToken;
import com.qingyun.mvpretrofitrx.mvp.presenter.ChatPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.weight.GridSpacingItemDecoration;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.ObservableTransformer;

import static com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils.IS_GROUP_OWNER;

public class GroupMemberActivity extends BaseActivity<ChatContact.View,ChatContact.Presenter> implements ChatContact.View {


    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rcy)
    RecyclerView rcy;
    private String id;
    RyunGroupMemberAdapter groupMemberAdapter;
    private List<GroupMember> list;
    private boolean qz;


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
        return R.layout.activity_group_member_ryun;
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
        id = getIntent().getStringExtra(IntentUtils.ID);
        qz = getIntent().getBooleanExtra(IS_GROUP_OWNER,false);
        list = new ArrayList<>();
        groupMemberAdapter = new RyunGroupMemberAdapter(getContext(),list,qz);
        groupMemberAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                GroupMember groupMember = (GroupMember) list.get(position);
                if (groupMember.getActionType()==1||groupMember.getActionType()==2){
                    Bundle bundle = new Bundle();
                    bundle.putInt(IntentUtils.ACTION_TYPE,groupMember.getActionType());
                    bundle.putInt(IntentUtils.ID, Integer.parseInt(id));
                    startActivity(ChooseContactActivity.class,bundle);
                }
            }
        });
        rcy.setLayoutManager(new GridLayoutManager(getContext(),5));
        rcy.addItemDecoration(new GridSpacingItemDecoration(5, getResources().getDimensionPixelSize(R.dimen.dp_20), false));
        rcy.setAdapter(groupMemberAdapter);

    }


    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().getGroupMemberList(ApplicationUtil.getChatPersonalInfo().getId()+"",id);
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
        groupMemberAdapter.notifyDataSetChanged(groupMemberList,qz);
        setTitle(getResources().getString(R.string.group_member)+"  "+"("+groupMemberList.size()+")");
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
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
