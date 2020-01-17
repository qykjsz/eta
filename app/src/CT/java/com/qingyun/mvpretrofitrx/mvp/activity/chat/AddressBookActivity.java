package com.qingyun.mvpretrofitrx.mvp.activity.chat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.qingyun.mvpretrofitrx.mvp.adapter.MainViewPagerAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.contract.ChatContact;
import com.qingyun.mvpretrofitrx.mvp.entity.ApplyGroup;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessage;
import com.qingyun.mvpretrofitrx.mvp.entity.Group;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.entity.NewChat;
import com.qingyun.mvpretrofitrx.mvp.entity.RyunToken;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
    @BindView(R.id.et_search)
    EditText etSearch;
    private List<BaseFragment> fragment;
    private MainViewPagerAdapter mainViewPagerAdapter;
    private List<String> titles;
    private GroupMember personal;
    private FriendsChatFragment friendsChatFragment;
    private GroupListFragment groupListFragment;
    private MultipartBody.Part img_area;

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
        friendsChatFragment = new FriendsChatFragment();
        groupListFragment = new GroupListFragment();
        getPresenter().getNicknameByAdress(ApplicationUtil.getCurrentWallet().getAddress());
        titles = new ArrayList<>();
        titles.add(getResources().getString(R.string.friends));
        titles.add(getResources().getString(R.string.groups));
        fragment = new ArrayList<>();
        fragment.add(friendsChatFragment);
        fragment.add(groupListFragment);
        mainViewPagerAdapter = new MainViewPagerAdapter(getContext(), fragment, getSupportFragmentManager());
        vp.setAdapter(mainViewPagerAdapter);
        IndicatorUtils.initMagicIndicator3(vp, titles, getActivity(), magicIndicator3, 0, 0);





        addSearch();
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void addSearch() {
        RxTextView.textChanges(etSearch)//当EditText发生改变
                //每500毫秒发射一次
                //仅在过了一段指定的时间还没发射数据时才发射一个数据
                //如果原始Observable在这个新生成的Observable终止之前发射了另一个数据， debounce 会抑制(suppress)这个数据项。
                .debounce(300, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())//内容监听操作需要在主线程操作
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(CharSequence charSequence) throws Exception {
                        return charSequence.length() >= 0;
                    }
                })
                .observeOn(Schedulers.io())
                .switchMap(new Function<CharSequence, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(CharSequence charSequence) throws Exception {
                        Observable<String> just = Observable.just(charSequence.toString());
                        return just;

                    }
                })

//
                .retry()//凡是请求出错就重试（例如超时、数据解析异常等），直到正确为止。（如果不retry的话就会调用onError。onError会导致整个订阅链条死掉，无法触发下一次了）
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                        friendsChatFragment.search(s.toString());
                        groupListFragment.search(s.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
                bundle.putSerializable(IntentUtils.GROUP_MEMBER, personal);
                startActivity(MyQrcodeActivity.class, bundle);
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
                DialogUtils.showPictureChooseDialogAvatar(getActivity(), true);
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
                img_area = MultipartBody.Part.createFormData("userphone", new File(picturePath).getName(), imageBody_head);
                Glide.with(getActivity()).load(new File(picturePath)).apply(GlideUtils.getAvaterOptions()).into(ivAvater);
                getPresenter().upDataAvatar(ApplicationUtil.getCurrentWallet().getAddress(),img_area);

            }
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
        getPresenter().getNicknameByAdress(ApplicationUtil.getCurrentWallet().getAddress());

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
        getPresenter().getNicknameByAdress(ApplicationUtil.getCurrentWallet().getAddress());
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
