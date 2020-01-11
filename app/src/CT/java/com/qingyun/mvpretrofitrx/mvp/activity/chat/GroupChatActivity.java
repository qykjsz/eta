package com.qingyun.mvpretrofitrx.mvp.activity.chat;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.qingyun.mvpretrofitrx.mvp.adapter.ChatMessageAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.contract.ChatContact;
import com.qingyun.mvpretrofitrx.mvp.entity.ApplyGroup;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessage;
import com.qingyun.mvpretrofitrx.mvp.entity.Group;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.entity.NewChat;
import com.qingyun.mvpretrofitrx.mvp.presenter.ChatPresenter;
import com.qingyun.mvpretrofitrx.mvp.service.SingleSocket;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.KeyboardChangeListener;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.qingyun.mvpretrofitrx.mvp.websocket.JWebSocketClient;
import com.qingyun.mvpretrofitrx.mvp.websocket.JWebSocketClientService;
import com.qingyun.mvpretrofitrx.mvp.websocket.Util;
import com.senon.mvpretrofitrx.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;


public class GroupChatActivity extends BaseActivity<ChatContact.View, ChatContact.Presenter> implements ChatContact.View {
    @BindView(R.id.rcy)
    RecyclerView rcy;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.ly_1)
    ConstraintLayout ly1;
    private Context mContext;
    private JWebSocketClient client;
    private Group group;

    private JWebSocketClientService.JWebSocketClientBinder binder;
    private JWebSocketClientService jWebSClientService;
    private List<ChatMessage> chatMessageList = new ArrayList<>();//消息列表
    private ChatMessageReceiver chatMessageReceiver;
    ChatMessageAdapter chatMessageAdapter;
    ConstraintLayout.LayoutParams lp;
    private int oldHeight;


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("MainActivity", "服务与活动成功绑定");
            binder = (JWebSocketClientService.JWebSocketClientBinder) iBinder;
            jWebSClientService = binder.getService();
            client = jWebSClientService.client;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("MainActivity", "服务与活动成功断开");
        }
    };
    private int mPage = 0;


    @Override
    protected void refresh() {
        super.refresh();
        isLoadMore = true;
        mPage++;
        getPresenter().getGroupChatLog(ApplicationUtil.getCurrentWallet().getAddress(), group.getCode(), mPage);
    }

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etContent.getText().toString())) {
            ToastUtil.showShortToast(R.string.msg_not_null);
            return;
        }

        if (SingleSocket.getInstance().getSocket().connected()) {

            getPresenter().sendMessageToGroup(ApplicationUtil.getCurrentWallet().getAddress(), group.getCode(), etContent.getText().toString());
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setText(etContent.getText().toString());
            chatMessage.setFromwho(ApplicationUtil.getCurrentWallet().getAddress());
            chatMessage.setTowho(group.getCode());
            chatMessage.setFromname(ApplicationUtil.getChatPersonalInfo().getName());
            chatMessage.setToname(group.getName());
            chatMessage.setFromphoto(ApplicationUtil.getChatPersonalInfo().getPhoto());
            chatMessage.setTime(System.currentTimeMillis() / 1000 + "");
            chatMessage.setState(2);
            chatMessageAdapter.addItem(chatMessage);
            rcy.scrollToPosition(chatMessageAdapter.getItemCount() - 1);
            etContent.setText("");
            refreashView(this.chatMessageList, rcy);

        } else {
            Util.showToast(mContext, "连接已断开，请稍等或重启App哟");
            SingleSocket.getInstance().getSocket().connect();
        }

//        if (client != null && client.isOpen()) {
//            jWebSClientService.sendMsg(etContent.getText().toString());
//            getPresenter().sendMessage(ApplicationUtil.getCurrentWallet().getAddress(),groupMember.getAddress(),etContent.getText().toString());
//            chatMessageList.add(new ChatMessage());
//            chatMessageAdapter.notifyDataSetChanged(chatMessageList);
//        } else {
//            Util.showToast(mContext, "连接已断开，请稍等或重启App哟");
//        }
        etContent.setText("");
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
    protected void setHeaderData() {
        super.setHeaderData();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }

    @Override
    public void getGroupChatLogSuccess(List<ChatMessage> chatMessageList) {
        if (chatMessageList == null) chatMessageList = new ArrayList<>();

        if (isLoadMore) {
            this.chatMessageList.addAll(0, chatMessageList);
        } else {
            this.chatMessageList = chatMessageList;
        }
        chatMessageAdapter.notifyDataSetChanged(this.chatMessageList);
        refreashView(this.chatMessageList, rcy);
        if (!isLoadMore) {
            rcy.scrollToPosition(this.chatMessageList.size() - 1);
        }

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
    public void getChatTokenSuccess(String token) {

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


    private class ChatMessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            chatMessageList.add(new ChatMessage());
            chatMessageAdapter.notifyDataSetChanged(chatMessageList);
        }
    }


    @Override
    protected String getTitleRightText() {
        return null;
    }

    @Override
    public boolean haveHeader() {
        return true;
    }

    @Override
    protected String getTitleLeftText() {

        return null;
    }

    @Override
    protected String getTitleText() {
        return getResources().getString(R.string.chat);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chat;
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
        getRefreash().setEnableLoadMore(false);


        lp = (ConstraintLayout.LayoutParams) ly1.getLayoutParams();
        ly1.post(new Runnable() {
            @Override
            public void run() {
                oldHeight = ly1.getMeasuredHeight();
            }
        });
        new KeyboardChangeListener(getActivity()).setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                Log.e(">>>>>>>>>>>", isShow + "" + keyboardHeight);
                lp.height = oldHeight + keyboardHeight;
                ly1.setLayoutParams(lp);
                if (isShow) {
                    rcy.scrollToPosition(chatMessageAdapter.getItemCount() - 1);
                } else {

                }
            }
        });


        EventBus.getDefault().register(this);
        group = (Group) getIntent().getSerializableExtra(IntentUtils.GROUP);
        getPresenter().setMessageReadGroup(ApplicationUtil.getCurrentWallet().getAddress(),group.getCode());
        getPresenter().getGroupChatLog(ApplicationUtil.getCurrentWallet().getAddress(), group.getCode(), mPage);
        setTitle(group.getName());
        setIvTitleRight(R.mipmap.icon11, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(IntentUtils.GROUP, group);
                startActivity(GroupInfoActivity.class, bundle);
            }
        });
        mContext = GroupChatActivity.this;
        chatMessageAdapter = new ChatMessageAdapter(mContext, chatMessageList, true);
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.setAdapter(chatMessageAdapter);
        if (!SingleSocket.getInstance().getSocket().connected()) {
            SingleSocket.getInstance().getSocket().connect();
        }
//        //启动服务
//        startJWebSClientService();
//        //绑定服务
//        bindService();
//        //注册广播
//        doRegisterReceiver();
//        //检测通知是否开启
//        checkNotification(mContext);
        initView();

//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    //1.创建监听指定服务器地址以及指定服务器监听的端口号
//                    Socket socket = new Socket("47.244.50.67", 2569);//192.168.1.101为我这个本机的IP地址，端口号为9080.
//                    //2.拿到客户端的socket对象的输出流发送给服务器数据
//                    ////                    OutputStream os = socket.getOutputStream();
//                    //                    //写入要发送给服务器的数据
//                    ////                    String s1 = new String("这里是你要发送到服务端的数据".getBytes(),"UTF-8");
//                    ////                    os.write(s1.getBytes());
//                    ////                    os.flush();
//                    ////                    socket.shutdownOutput();
//                    //                    //拿到socket的输入流，这里存储的是服务器返回的数据
//                   while (true){
//                       InputStream is = socket.getInputStream();
//                       //解析服务器返回的数据
//                       int lenght = 0;
//                       byte[] buff = new byte[1024];
//                       final StringBuffer sb = new StringBuffer();
//                       while((lenght = is.read(buff)) != -1){
//                           sb.append(new String(buff,0,lenght,"UTF-8"));
//                       }
//                       runOnUiThread(new Runnable() {
//                           @Override
//                           public void run() {
//                               //这里更新UI
//                               ToastUtil.showShortToast(sb.toString());
//                            Log.e("-------------->",sb.toString());
//                           }
//                       });
//                   }
//
//                } catch (UnknownHostException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }finally {
//                    //3、关闭IO资源（注：实际开发中需要放到finally中）
////                    is.close();
////                    os.close();
////                    socket.close();
//                }
//            }
//        }.start();


    }


    /**
     * 绑定服务
     */
    private void bindService() {
        Intent bindIntent = new Intent(mContext, JWebSocketClientService.class);
        bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
    }

    /**
     * 启动服务（websocket客户端服务）
     */
    private void startJWebSClientService() {
        Intent intent = new Intent(mContext, JWebSocketClientService.class);
        startService(intent);
    }

    /**
     * 动态注册广播
     */
    private void doRegisterReceiver() {
        chatMessageReceiver = new ChatMessageReceiver();
        IntentFilter filter = new IntentFilter("com.xch.servicecallback.content");
        registerReceiver(chatMessageReceiver, filter);
    }

    private void initView() {
        //监听输入框的变化
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (et_content.getText().toString().length() > 0) {
//                    btn_send.setVisibility(View.VISIBLE);
//                } else {
//                    btn_send.setVisibility(View.GONE);
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_send:
//                String content = et_content.getText().toString();
//                if (content.length() <= 0) {
//                    Util.showToast(mContext, "消息不能为空哟");
//                    return;
//                }
//
//                if (client != null && client.isOpen()) {
//                    jWebSClientService.sendMsg(content);
//
//                    //暂时将发送的消息加入消息列表，实际以发送成功为准（也就是服务器返回你发的消息时）
////                    ChatMessage chatMessage = new ChatMessage();
////                    chatMessage.setContent(content);
////                    chatMessage.setIsMeSend(1);
////                    chatMessage.setIsRead(1);
////                    chatMessage.setTime(System.currentTimeMillis() + "");
////                    chatMessageList.add(chatMessage);
////                    initChatMsgListView();
////                    et_content.setText("");
//                } else {
//                    Util.showToast(mContext, "连接已断开，请稍等或重启App哟");
//                }
//                break;
//            default:
//                break;
//        }
//    }


    /**
     * 检测是否开启通知
     *
     * @param context
     */
    private void checkNotification(final Context context) {
        if (!isNotificationEnabled(context)) {
            new AlertDialog.Builder(context).setTitle("温馨提示")
                    .setMessage("你还未开启系统通知，将影响消息的接收，要去开启吗？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setNotification(context);
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        }
    }

    /**
     * 如果没有开启通知，跳转至设置界面
     *
     * @param context
     */
    private void setNotification(Context context) {
        Intent localIntent = new Intent();
        //直接跳转到应用通知设置的代码：
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            localIntent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            localIntent.putExtra("app_package", context.getPackageName());
            localIntent.putExtra("app_uid", context.getApplicationInfo().uid);
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            localIntent.addCategory(Intent.CATEGORY_DEFAULT);
            localIntent.setData(Uri.parse("package:" + context.getPackageName()));
        } else {
            //4.4以下没有从app跳转到应用通知设置页面的Action，可考虑跳转到应用详情页面,
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= 9) {
                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
            } else if (Build.VERSION.SDK_INT <= 8) {
                localIntent.setAction(Intent.ACTION_VIEW);
                localIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
                localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
            }
        }
        context.startActivity(localIntent);
    }

    /**
     * 获取通知权限,监测是否开启了系统通知
     *
     * @param context
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private boolean isNotificationEnabled(Context context) {

        String CHECK_OP_NO_THROW = "checkOpNoThrow";
        String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class appOpsClass = null;
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetStickyEvent(ChatMessage message) {
        if (message.getState() == 2) {
            if (group.getCode().equals(message.getTowho())) {
                chatMessageAdapter.addItem(message);
                rcy.scrollToPosition(chatMessageAdapter.getItemCount() - 1);
            }
        }

    }

}
