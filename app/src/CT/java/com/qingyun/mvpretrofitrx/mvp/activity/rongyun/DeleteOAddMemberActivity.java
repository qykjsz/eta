package com.qingyun.mvpretrofitrx.mvp.activity.rongyun;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.qingyun.mvpretrofitrx.mvp.adapter.RyunContactAdapter;
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
import com.qingyun.mvpretrofitrx.mvp.view.lettercontact.PinnedHeaderDecoration;
import com.qingyun.mvpretrofitrx.mvp.view.lettercontact.WaveSideBarView;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.ObservableTransformer;

public class DeleteOAddMemberActivity extends BaseActivity<ChatContact.View,ChatContact.Presenter> implements ChatContact.View {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rcy_contact)
    RecyclerView rcyContact;
    @BindView(R.id.side_view)
    WaveSideBarView sideView;
    RyunContactAdapter adapter;
    private List<GroupMember> list;
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
        return getResources().getString(R.string.choose_contact);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_choose_contact;
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
        getPresenter().getFriendsList(ApplicationUtil.getChatPersonalInfo().getId()+"","");

        getTvTitleRight().setText(getResources().getString(R.string.complete));
        getTvTitleRight().setTextColor(getResources().getColor(R.color.color_FFFFFF));
        getTvTitleRight().setBackground(getResources().getDrawable(R.drawable.bg_mainblue_round_3));
        setTitleRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CreateRyunGroupActivity.class);
            }
        });


        rcyContact.setLayoutManager(new LinearLayoutManager(getContext()));

        final PinnedHeaderDecoration decoration = new PinnedHeaderDecoration();
        decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator() {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition) {
                return true;
            }
        });
        rcyContact.addItemDecoration(decoration);


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Type listType = new TypeToken<ArrayList<City>>() {
//                }.getType();
//                Gson gson = new Gson();
//                final List<City> list = gson.fromJson(City.DATA, listType);
//                Collections.sort(list, new LetterComparator());
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        adapter = new CityAdapter(getActivity(), list);
//                        mRecyclerView.setAdapter(adapter);
//                    }
//                });
//            }
//        }).start();
        list = new ArrayList<>();
        adapter = new RyunContactAdapter(getActivity(), list);
        adapter.setItemClickListener(new RyunContactAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(GroupMember groupMember) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(IntentUtils.GROUP_MEMBER,groupMember);
                startActivity(ContactDetailActivity.class,bundle);
            }
        });
        rcyContact.setAdapter(adapter);
        sideView.setOnTouchLetterChangeListener(new WaveSideBarView.OnTouchLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                int pos = adapter.getLetterPosition(letter);
                if (pos != -1) {
                    rcyContact.scrollToPosition(pos);
                    LinearLayoutManager mLayoutManager =
                            (LinearLayoutManager) rcyContact.getLayoutManager();
                    mLayoutManager.scrollToPositionWithOffset(pos, 0);
                }
            }
        });



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
    public boolean haveHeader() {
        return true;
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
        list = groupMemberList;
//        Collections.sort(list, new LetterComparator());
        adapter.notifyDataSetChanged(groupMemberList);
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
