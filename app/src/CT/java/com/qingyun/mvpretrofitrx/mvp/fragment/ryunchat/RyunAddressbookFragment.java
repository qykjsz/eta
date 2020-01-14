package com.qingyun.mvpretrofitrx.mvp.fragment.ryunchat;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.request.target.ThumbnailImageViewTarget;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qingyun.mvpretrofitrx.mvp.activity.chat.FriendsMessageActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.rongyun.ContactDetailActivity;
import com.qingyun.mvpretrofitrx.mvp.adapter.RyunContactAdapter;
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
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.view.lettercontact.LetterComparator;
import com.qingyun.mvpretrofitrx.mvp.view.lettercontact.PinnedHeaderDecoration;
import com.qingyun.mvpretrofitrx.mvp.view.lettercontact.WaveSideBarView;
import com.qingyun.mvpretrofitrx.mvp.view.lettercontact.adapter.CityAdapter;
import com.qingyun.mvpretrofitrx.mvp.view.lettercontact.bean.City;
import com.rance.library.ButtonEventListener;
import com.rance.library.SectorMenuButton;
import com.senon.mvpretrofitrx.R;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.ObservableTransformer;

public class RyunAddressbookFragment extends BaseFragment<ChatContact.View,ChatContact.Presenter> implements  ChatContact.View{
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.side_view)
    WaveSideBarView mSideBarView;
    Unbinder unbinder;
    RyunContactAdapter adapter;
    private List<GroupMember> list;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_ryun_addressbook;
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final PinnedHeaderDecoration decoration = new PinnedHeaderDecoration();
        decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator() {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition) {
                return true;
            }
        });
        mRecyclerView.addItemDecoration(decoration);


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
        mRecyclerView.setAdapter(adapter);
        mSideBarView.setOnTouchLetterChangeListener(new WaveSideBarView.OnTouchLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                int pos = adapter.getLetterPosition(letter);
                if (pos != -1) {
                    mRecyclerView.scrollToPosition(pos);
                    LinearLayoutManager mLayoutManager =
                            (LinearLayoutManager) mRecyclerView.getLayoutManager();
                    mLayoutManager.scrollToPositionWithOffset(pos, 0);
                }
            }
        });

    }

    @Override
    public void refreashData() {
        super.refreashData();
        if (ApplicationUtil.getChatPersonalInfo()==null)
            return;
        getPresenter().getFriendsList(ApplicationUtil.getChatPersonalInfo().getId()+"","");
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

    @OnClick({R.id.btn_new_friends, R.id.btn_group})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_new_friends:
                startActivity(FriendsMessageActivity.class);
                break;
            case R.id.btn_group:
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
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);

    }
}
