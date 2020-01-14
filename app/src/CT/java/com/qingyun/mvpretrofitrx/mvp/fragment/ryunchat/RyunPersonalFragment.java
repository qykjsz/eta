package com.qingyun.mvpretrofitrx.mvp.fragment.ryunchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.qingyun.mvpretrofitrx.mvp.activity.chat.MyQrcodeActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.contract.ChatContact;
import com.qingyun.mvpretrofitrx.mvp.entity.ApplyGroup;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessage;
import com.qingyun.mvpretrofitrx.mvp.entity.Group;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.entity.ImageSelectPath;
import com.qingyun.mvpretrofitrx.mvp.entity.NewChat;
import com.qingyun.mvpretrofitrx.mvp.entity.RyunToken;
import com.qingyun.mvpretrofitrx.mvp.presenter.ChatPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.GlideUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;
import com.trello.rxlifecycle2.android.FragmentEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.ObservableTransformer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RyunPersonalFragment extends BaseFragment<ChatContact.View, ChatContact.Presenter> implements ChatContact.View {
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    Unbinder unbinder;
    private GroupMember personal;
    private MultipartBody.Part img_area;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ryun_personal;
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
        EventBus.getDefault().register(this);
        getPresenter().getNicknameByAdress(ApplicationUtil.getCurrentWallet().getAddress());
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
        ToastUtil.showShortToast(s);
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
        Glide.with(getContext()).load(groupMember.getPhoto()).apply(GlideUtils.getAvaterOptions()).into(ivAvatar);
        tvAccount.setText(groupMember.getAddress());
        ApplicationUtil.setChatPersonalInfo(groupMember);
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
        ToastUtil.showShortToast(s);
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

    @OnClick({R.id.ly_code, R.id.iv_avatar,R.id.tv_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ly_code:
                Bundle bundle = new Bundle();
                bundle.putSerializable(IntentUtils.GROUP_MEMBER, personal);
                startActivity(MyQrcodeActivity.class, bundle);
                break;
            case R.id.iv_avatar:
                DialogUtils.showPictureChooseDialogAvatar(getActivity(), true);
                break;
            case R.id.tv_name:
                DialogUtils.shoEditDialog(personal.getName(),getContext(), new DialogUtils.SureListener() {
                    @Override
                    public void onSure(Object o) {
                        tvName.setText(o.toString());
                        getPresenter().changeNickname(ApplicationUtil.getChatPersonalInfo().getId()+"",o.toString());
                    }
                });
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetStickyEvent(ImageSelectPath imageSelectPath) {
        String picturePath = imageSelectPath.getPath();
        RequestBody imageBody_head = RequestBody.create(MediaType.parse("image/jpeg"), new File(picturePath));
        img_area = MultipartBody.Part.createFormData("photo", new File(picturePath).getName(), imageBody_head);
        Glide.with(getActivity()).load(new File(picturePath)).apply(GlideUtils.getAvaterOptions()).into(ivAvatar);
        getPresenter().upDataAvatar(ApplicationUtil.getChatPersonalInfo().getId()+"",img_area);
    }


}
