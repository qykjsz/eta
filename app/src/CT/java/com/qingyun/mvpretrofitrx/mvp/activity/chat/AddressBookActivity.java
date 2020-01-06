package com.qingyun.mvpretrofitrx.mvp.activity.chat;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qingyun.mvpretrofitrx.mvp.adapter.MainViewPagerAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.contract.ChatContact;
import com.qingyun.mvpretrofitrx.mvp.entity.ApplyGroup;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessage;
import com.qingyun.mvpretrofitrx.mvp.entity.Group;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.entity.NewChat;
import com.qingyun.mvpretrofitrx.mvp.fragment.FriendsChatFragment;
import com.qingyun.mvpretrofitrx.mvp.fragment.GroupListFragment;
import com.qingyun.mvpretrofitrx.mvp.presenter.ChatPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.GlideUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.IndicatorUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class AddressBookActivity extends BaseActivity<ChatContact.View, ChatContact.Presenter> implements ChatContact.View {
    @BindView(R.id.magic_indicator3)
    MagicIndicator magicIndicator3;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.iv_edit_name)
    ImageView ivEditName;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.iv_name_confirm)
    ImageView ivNameConfirm;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.iv_avater)
    ImageView ivAvater;
    private List<BaseFragment> fragment;
    private MainViewPagerAdapter mainViewPagerAdapter;
    private List<String> titles;
    private GroupMember personal;

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
        return getResources().getString(R.string.address_book);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chat_address_book;
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

        getPresenter().getNicknameByAdress(ApplicationUtil.getCurrentWallet().getAddress());
        titles = new ArrayList<>();
        titles.add(getResources().getString(R.string.friends));
        titles.add(getResources().getString(R.string.groups));
        fragment = new ArrayList<>();
        fragment.add(new FriendsChatFragment());
        fragment.add(new GroupListFragment());
        mainViewPagerAdapter = new MainViewPagerAdapter(getContext(), fragment, getSupportFragmentManager());
        vp.setAdapter(mainViewPagerAdapter);
        IndicatorUtils.initMagicIndicator3(vp, titles, getActivity(), magicIndicator3, 0, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_code, R.id.iv_edit_name, R.id.iv_name_confirm, R.id.iv_avater})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_code:
                Bundle bundle = new Bundle();
                bundle.putSerializable(IntentUtils.GROUP_MEMBER,personal);
                startActivity(MyQrcodeActivity.class,bundle);
                break;
            case R.id.iv_edit_name:
                ivEditName.setVisibility(View.GONE);
                etName.setVisibility(View.VISIBLE);
                ivNameConfirm.setVisibility(View.VISIBLE);
                etName.setText(tvName.getText().toString());

                break;
            case R.id.iv_name_confirm:

                getPresenter().changeNickname(ApplicationUtil.getCurrentWallet().getAddress(), etName.getText().toString());

                break;
            case R.id.iv_avater:
                DialogUtils.showPictureChooseDialog(getActivity(), true);
                break;
        }
    }

    @Override
    public void applyToFriendsSuccess(String s) {

    }

    @Override
    public void changeNicknameSuccess(String s) {
        ToastUtil.showShortToast(s);
        ivEditName.setVisibility(View.VISIBLE);
        tvName.setText(etName.getText().toString());
        ivNameConfirm.setVisibility(View.GONE);
        etName.setVisibility(View.GONE);
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
        personal = groupMember;
        tvName.setText(groupMember.getName());
        Glide.with(getContext()).load(groupMember.getPhoto()).apply(GlideUtils.getAvaterOptions()).into(ivAvater);
        tvAccount.setText(groupMember.getAddress());
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
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
