package com.qingyun.mvpretrofitrx.mvp.activity.chat;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.qingyun.mvpretrofitrx.mvp.adapter.ChatMessageAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessage;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatAcrivity extends BaseActivity {
    @BindView(R.id.rcy)
    RecyclerView rcy;
    @BindView(R.id.refreash)
    SmartRefreshLayout refreash;
    @BindView(R.id.et_content)
    EditText etContent;
    ChatMessageAdapter chatMessageAdapter;
    private List<ChatMessage> list;

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
        return getResources().getString(R.string.chat);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chat;
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

        list = new ArrayList<>();
        list.add(new ChatMessage());
        list.add(new ChatMessage());
        list.add(new ChatMessage());
        list.add(new ChatMessage());
        list.add(new ChatMessage());
        list.add(new ChatMessage());
        list.add(new ChatMessage());
        list.add(new ChatMessage());
        list.add(new ChatMessage());
        list.add(new ChatMessage());


        chatMessageAdapter = new ChatMessageAdapter(getContext(),list, true);
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.setAdapter(chatMessageAdapter);

//        //底部布局弹出,聊天列表上滑
//        rcy.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                if (bottom < oldBottom) {
//                    rcy.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (chatMessageAdapter.getItemCount() > 0) {
//                                rcy.smoothScrollToPosition(chatMessageAdapter.getItemCount() - 1);
//                            }
//                        }
//                    });
//                }
//            }
//        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
