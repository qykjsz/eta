package com.qingyun.mvpretrofitrx.mvp.activity.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
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
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class GroupInfoActivity extends BaseActivity<ChatContact.View, ChatContact.Presenter> implements ChatContact.View {
    @BindView(R.id.tv_group_explain)
    TextView tvGroupExplain;
    @BindView(R.id.btn_group_num)
    TextView btnGroupNum;
    @BindView(R.id.cb_verti)
    CheckBox cbVerti;
    @BindView(R.id.tv_group_name)
    TextView tvGroupName;
    @BindView(R.id.tv_qcode)
    TextView tvQcode;
    @BindView(R.id.tv_group_num)
    TextView tvGroupNum;
    @BindView(R.id.tv_group_avatar)
    TextView tvGroupAvatar;
    private Group group;

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
        return getResources().getString(R.string.group_info);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_group_info;
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

        TextView tvGroupNum;
        group = (Group) getIntent().getSerializableExtra(IntentUtils.GROUP);
        getPresenter().getGroupInfo(ApplicationUtil.getCurrentWallet().getAddress(), group.getCode());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_exit_group, R.id.btn_group_name, R.id.btn_share, R.id.btn_group_num, R.id.btn_groip_transfer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_group_name:
                break;
            case R.id.btn_share:

                Bundle bundle = new Bundle();
                bundle.putSerializable(IntentUtils.GROUP, group);
                startActivity(GroupShareActivity.class, bundle);
                break;
            case R.id.btn_group_num:
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable(IntentUtils.GROUP, group);
                startActivity(GroupMemberActivity.class, bundle1);
                break;
            case R.id.btn_groip_transfer:
                if (group.getOwner()!=1){
                    ToastUtil.showShortToast(R.string.not_permissions);
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable(IntentUtils.GROUP, group);
                startActivity(GroupTransferActivity.class,bundle2);
                break;
            case R.id.btn_exit_group:
                DialogUtils.showConfirmDialog(getActivity(), 0, R.string.sure_to_exit_group, R.string.cancel, R.string.confirm, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPresenter().exitGroup(ApplicationUtil.getCurrentWallet().getAddress(), group.getCode());
                    }
                });
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
        ToastUtil.showShortToast(s);
        finish();

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
        ToastUtil.showShortToast(s);
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
    public void getGroupInfoSuccess(final Group group) {
        this.group = group;
        tvGroupExplain.setText(group.getIntroduce());
        tvGroupName.setText(group.getName());
        tvQcode.setText(group.getCode());
        tvGroupNum.setText(group.getNumber());
        tvGroupAvatar.setText(group.getName().substring(0,1));
        if (group.getVerification() == 1) {
            cbVerti.setChecked(true);
        }

        if (group.getOwner()!=1){
            cbVerti.setEnabled(false);
                }

        cbVerti.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!isChecked) {
                    getPresenter().setIfGroupReview(ApplicationUtil.getCurrentWallet().getAddress(), group.getCode(), "2");
                } else {
                    getPresenter().setIfGroupReview(ApplicationUtil.getCurrentWallet().getAddress(), group.getCode(), "1");

                }
            }
        });
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
