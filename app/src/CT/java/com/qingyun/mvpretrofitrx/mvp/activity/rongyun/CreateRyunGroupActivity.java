package com.qingyun.mvpretrofitrx.mvp.activity.rongyun;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
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
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.GlideUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;
import io.rong.imkit.RongIM;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CreateRyunGroupActivity extends BaseActivity<ChatContact.View,ChatContact.Presenter> implements ChatContact.View {
    @BindView(R.id.iv_group_avatar)
    ImageView ivGroupAvatar;
    @BindView(R.id.et_group_name)
    EditText etGroupName;
    @BindView(R.id.et_group_explain)
    EditText etGroupExplain;
    @BindView(R.id.tv_text_num)
    TextView tvTextNum;
    private MultipartBody.Part img_area;
    private String ids;

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
        return getResources().getString(R.string.create_chat_group);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_ryun_group;
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
        ids = getIntent().getStringExtra(IntentUtils.IDS);

        etGroupExplain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)){
                    tvTextNum.setText("0");
                }else {
                    tvTextNum.setText(s.length()+"");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public File decodeToFile(int resId){
        InputStream is = getResources().openRawResource(resId);
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        String defaultPath = getApplicationContext().getFilesDir().getAbsolutePath() + "/defaultAvatar";
        File file = new File(defaultPath);
        String defaultImgPath = defaultPath + "/messageImg.jpg";

        if (!file.exists()) {
            file.mkdirs();
        } else {
            return  file;
        }
        file = new File(defaultImgPath);
        try {
            file.createNewFile();
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 20, fOut);
            is.close();
            fOut.flush();
            fOut.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * drawable转为file
     * @param mContext
     * @param drawableId  drawable的ID
     * @param fileName   转换后的文件名
     * @return
     */
    public File drawableToFile(Context mContext, int drawableId, File fileName){
//        InputStream is = view.getContext().getResources().openRawResource(R.drawable.logo);
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), drawableId);
//        Bitmap bitmap = BitmapFactory.decodeStream(is);

        String defaultPath = mContext.getFilesDir()
                .getAbsolutePath() + "/defaultGoodInfo";
        File file = new File(defaultPath);
        if (!file.exists()) {
            file.mkdirs();
        } else {
            return null;
        }
        String defaultImgPath = defaultPath + "/"+fileName;
        file = new File(defaultImgPath);
        try {
            file.createNewFile();

            FileOutputStream fOut = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.PNG, 20, fOut);
//            is.close();
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_group_avatar, R.id.btn_create_group})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_group_avatar:
                DialogUtils.showPictureChooseDialogAvatar(getActivity(),false);
                break;
            case R.id.btn_create_group:
                if (img_area==null){
                    File file =  decodeToFile(R.mipmap.lt_szqtx);
                    RequestBody imageBody_head = RequestBody.create(MediaType.parse("image/jpeg"), file);
                    img_area = MultipartBody.Part.createFormData("photo", file.getName(), imageBody_head);
                }
              getPresenter().createGroup(ApplicationUtil.getChatPersonalInfo().getId()+"",etGroupName.getText().toString(),etGroupExplain.getText().toString(),ids,img_area);
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
                Glide.with(getActivity()).load(new File(picturePath)).apply(GlideUtils.getChatAvaterOptions()).into(ivGroupAvatar);

            }
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
        ToastUtil.showShortToast(R.string.create_chat_group_success);
        getPresenter().getGroupInfo(ApplicationUtil.getChatPersonalInfo().getId() + "", s);
//        Bundle bundle = new Bundle();
//        bundle.putString(IntentUtils.ID,s);
//        startActivity(GroupChatInfoActivity.class,bundle);
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
        getPresenter().addGroupAddressBook(ApplicationUtil.getChatPersonalInfo().getId()+"",group.getId()+"");
        RongIM.getInstance().refreshGroupInfoCache(new io.rong.imlib.model.Group(group.getId()+"",group.getName(), Uri.parse(group.getPhoto())));
        finish();
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
