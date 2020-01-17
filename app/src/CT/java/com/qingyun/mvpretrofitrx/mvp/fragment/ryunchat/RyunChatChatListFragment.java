package com.qingyun.mvpretrofitrx.mvp.fragment.ryunchat;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.qingyun.mvpretrofitrx.mvp.activity.chat.AddFriendsActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.chat.CreateGroupChatActivity;
import com.qingyun.mvpretrofitrx.mvp.activity.rongyun.ChooseContactActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseFragment;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.ScanUtil;
import com.rance.library.ButtonData;
import com.rance.library.ButtonEventListener;
import com.rance.library.SectorMenuButton;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;
import per.goweii.anylayer.AnyLayer;
import per.goweii.anylayer.LayerManager;

public class RyunChatChatListFragment extends BaseFragment {
    @BindView(R.id.top_sector_menu)
    SectorMenuButton topSectorMenu;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_ryun_chat_chat_list;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
        initTopSectorMenuButton();
//        getPresenter().getChatToken(ApplicationUtil.getCurrentWallet().getAddress());
        MyRyunChatList mConversationListFragment=new MyRyunChatList();
        Uri uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")
//                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")
                .build();
        mConversationListFragment.setUri(uri);
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.conversationlist, mConversationListFragment);
        transaction.commit();

//        //初始化聊天界面底部的自定义按钮  具体在下面会详细介绍
//        RongExtensionManager.getInstance().registerExtensionModule(RecognizeExtensionModule2.getInstence());
//        getPresenter().getNicknameByAdress(ApplicationUtil.getCurrentWallet().getAddress());
//
////群聊的用户数据提供者  同上
//        RongIM.setGroupUserInfoProvider(new RongIM.GroupUserInfoProvider() {
//            @Override
//            public GroupUserInfo getGroupUserInfo(String s, String s1) {
//                //设置数据
//                RongIM.getInstance().refreshGroupInfoCache(new Group(groupid, nickName,Uri.parse(avatar)));
//                return getGroupUserInfos(s, s1); //向自己后台获取
//            }
//        }, true);



    }


    private void initTopSectorMenuButton() {
        final List<ButtonData> buttonDatas = new ArrayList<>();
        int[] drawable = {R.mipmap.lt_gn_icon, R.mipmap.lt_cjql_icon,
                R.mipmap.lt_tjhy_icon, R.mipmap.lt_sys_icon};
        for (int i = 0; i < 4; i++) {
            ButtonData buttonData = ButtonData.buildIconButton(getContext(), drawable[i], 0);
            buttonDatas.add(buttonData);
        }
        topSectorMenu.setButtonDatas(buttonDatas);
        setListener(topSectorMenu);
    }


    private void setListener(final SectorMenuButton button) {
        button.setButtonEventListener(new ButtonEventListener() {
            @Override
            public void onButtonClicked(int index) {
                switch (index){
                    case 1:
                        startActivity(ChooseContactActivity.class);

                        break;
                    case 2:
                        startActivity(AddFriendsActivity.class);
                        break;
                    case 3:
                        ScanUtil.start(getActivity());

                        break;
                }

            }

            @Override
            public void onExpand() {

            }

            @Override
            public void onCollapse() {

            }
        });
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
}
