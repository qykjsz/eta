package com.qingyun.mvpretrofitrx.mvp.activity.rongyun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.qingyun.mvpretrofitrx.mvp.activity.chat.GroupShareActivity;
import com.qingyun.mvpretrofitrx.mvp.adapter.RyunGroupMemberAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.contract.ChatContact;
import com.qingyun.mvpretrofitrx.mvp.entity.ApplyGroup;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessage;
import com.qingyun.mvpretrofitrx.mvp.entity.Group;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.entity.NewChat;
import com.qingyun.mvpretrofitrx.mvp.entity.RyunToken;
import com.qingyun.mvpretrofitrx.mvp.presenter.ChatPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.GlideUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.qingyun.mvpretrofitrx.mvp.weight.GridSpacingItemDecoration;
import com.senon.mvpretrofitrx.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class GroupChatInfoActivity extends BaseActivity<ChatContact.View, ChatContact.Presenter> implements ChatContact.View {
    @BindView(R.id.rcy_group_member)
    RecyclerView rcyGroupMember;
    @BindView(R.id.tv_clear_log_1)
    BoldTextView tvClearLog1;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_group_name)
    TextView tvGroupName;
    @BindView(R.id.tv_group_id)
    TextView tvGroupId;
    @BindView(R.id.tv_group_explain)
    TextView tvGroupExplain;
    @BindView(R.id.tv_clear_log)
    TextView tvClearLog;
    @BindView(R.id.cb_not_message)
    CheckBox cbNotMessage;
    @BindView(R.id.cb_top)
    CheckBox cbTop;
    @BindView(R.id.cb_show_nick_name)
    CheckBox cbShowNickName;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.btn_exit)
    BoldTextView btnExit;
    private String id;
    private RyunGroupMemberAdapter groupMemberAdapter;
    private List<GroupMember> list;
    private Group group;
    private MultipartBody.Part img_area;
    private Boolean qz = false;

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
        return R.layout.activity_group_info_ryun;
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
        list = new ArrayList<>();
        groupMemberAdapter = new RyunGroupMemberAdapter(getContext(), list, qz);
        groupMemberAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                GroupMember groupMember = (GroupMember) list.get(position);
                if (groupMember.getActionType() == 1 || groupMember.getActionType() == 2) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(IntentUtils.ACTION_TYPE, groupMember.getActionType());
                    bundle.putInt(IntentUtils.ID, Integer.parseInt(id));
                    startActivity(ChooseContactActivity.class, bundle);
                }
            }
        });
        rcyGroupMember.setLayoutManager(new GridLayoutManager(getContext(), 5));
        rcyGroupMember.addItemDecoration(new GridSpacingItemDecoration(5, getResources().getDimensionPixelSize(R.dimen.dp_20), false));
        rcyGroupMember.setAdapter(groupMemberAdapter);


    }


    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().getGroupInfo(ApplicationUtil.getChatPersonalInfo().getId() + "", id);

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
        groupMemberAdapter.notifyDataSetChanged(groupMemberList, qz);
        setTitle(getResources().getString(R.string.group_info) + "  " + "(" + groupMemberList.size() + ")");
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
        this.group = group;
//        @BindView(R.id.rcy_group_member)
//        RecyclerView rcyGroupMember;
//        @BindView(R.id.tv_clear_log_1)
//        BoldTextView tvClearLog1;
//        @BindView(R.id.iv_avatar)
//        ImageView ivAvatar;
//        @BindView(R.id.tv_group_name)
//        TextView tvGroupName;
//        @BindView(R.id.tv_group_id)
//        TextView tvGroupId;
//        @BindView(R.id.tv_group_explain)
//        TextView tvGroupExplain;
//        @BindView(R.id.tv_clear_log)
//        TextView tvClearLog;
//        @BindView(R.id.cb_not_message)
//        CheckBox cbNotMessage;
//        @BindView(R.id.cb_top)
//        CheckBox cbTop;
//        @BindView(R.id.cb_show_nick_name)
//        CheckBox cbShowNickName;
//        @BindView(R.id.tv_nickname)
//        TextView tvNickname;
//        private String id;

        qz = group.getQzid() == ApplicationUtil.getChatPersonalInfo().getId();
        if (!qz) {

            btnExit.setText(R.string.delete_add_exit);

        }
        tvGroupId.setText(group.getId() + "");
        tvGroupName.setText(group.getName());
        Glide.with(getContext()).load(group.getPhoto()).apply(GlideUtils.getChatAvaterOptions()).into(ivAvatar);
        tvGroupExplain.setText(group.getIntroduce());
        getPresenter().getGroupMemberList(ApplicationUtil.getChatPersonalInfo().getId() + "", id);

    }

    @Override
    public void upDataAvatarSuccess(String s) {
        ToastUtil.showShortToast(s);
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
        ToastUtil.showShortToast(s);
    }

    @Override
    public void addBlacklistSuccess(String s) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ly_group_manager, R.id.btn_see_more_menber, R.id.ly_group_name, R.id.ly_share, R.id.ly_froup_explain, R.id.ly_nick_name, R.id.ly_clear_log, R.id.btn_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ly_group_manager:
                ToastUtil.showShortToast(R.string.not_open);
                break;
            case R.id.iv_avatar:
                if (group.getQzid() == ApplicationUtil.getChatPersonalInfo().getId()) {
                    DialogUtils.showPictureChooseDialogAvatar(getActivity(), false);
                }
                else {
                    ToastUtil.showShortToast(R.string.not_permissions);
                }
                break;
            case R.id.btn_see_more_menber:
                Bundle bundle = new Bundle();
                bundle.putString(IntentUtils.ID, id);
                bundle.putBoolean(IntentUtils.IS_GROUP_OWNER, qz);
                startActivity(GroupMemberActivity.class, bundle);
                break;
            case R.id.ly_group_name:
                if (group.getQzid() == ApplicationUtil.getChatPersonalInfo().getId()) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt(IntentUtils.TYPE, 2);
                    bundle2.putString(IntentUtils.ID, id);
                    startActivity(GroupInfoSettingActivity.class, bundle2);

                } else {
                    ToastUtil.showShortToast(R.string.not_permissions);
                }

                break;
            case R.id.ly_share:
                if (group == null)
                    return;
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable(IntentUtils.GROUP, group);
                startActivity(GroupShareActivity.class, bundle1);
                break;
            case R.id.ly_froup_explain:
                Bundle bundle3 = new Bundle();
                bundle3.putSerializable(IntentUtils.GROUP, group);
                startActivity(GroupExplainActivity.class, bundle3);
                break;
            case R.id.ly_nick_name:
                Bundle bundle4 = new Bundle();
                bundle4.putInt(IntentUtils.TYPE, 1);
                startActivity(GroupInfoSettingActivity.class, bundle4);
                break;
            case R.id.ly_clear_log:
                getPresenter().addGroupAddressBook(ApplicationUtil.getChatPersonalInfo().getId() + "", id);
                break;
            case R.id.btn_exit:
                DialogUtils.showConfirmDialog(getActivity(), 0, R.string.exit_group, R.string.cancel, R.string.confirm, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (group.getQzid() == ApplicationUtil.getChatPersonalInfo().getId()) {
                            getPresenter().deleteGroupAddressBook(ApplicationUtil.getChatPersonalInfo().getId() + "", id);

                        } else {
                            getPresenter().exitGroup(ApplicationUtil.getChatPersonalInfo().getId() + "", id);
                        }


                    }
                });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*结果回调*/
        if (requestCode == DialogUtils.CODE_CHOOSE_PICTURE_CAMEAR || requestCode == DialogUtils.CODE_CHOOSE_PICTURE_PHONE) {
            if (data != null) {
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                String picturePath = selectList.get(0).getCompressPath();
                RequestBody imageBody_head = RequestBody.create(MediaType.parse("image/jpeg"), new File(picturePath));
                img_area = MultipartBody.Part.createFormData("photo", new File(picturePath).getName(), imageBody_head);
                Glide.with(getActivity()).load(new File(picturePath)).apply(GlideUtils.getAvaterOptions()).into(ivAvatar);
                getPresenter().upDataGroupAvatar(ApplicationUtil.getChatPersonalInfo().getId() + "", id, img_area);
            }
        }

    }
}
