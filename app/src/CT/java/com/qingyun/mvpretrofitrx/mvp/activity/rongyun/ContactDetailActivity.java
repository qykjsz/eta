package com.qingyun.mvpretrofitrx.mvp.activity.rongyun;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.utils.GlideUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class ContactDetailActivity extends BaseActivity {
    @BindView(R.id.iv_name)
    ImageView ivName;
    @BindView(R.id.tvname)
    BoldTextView tvname;
    @BindView(R.id.tv_beizu)
    TextView tvBeizu;
    @BindView(R.id.tv_id)
    TextView tvId;
    private GroupMember groupMember;

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
        return "";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_contact_detail;
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
        groupMember = (GroupMember) getIntent().getSerializableExtra(IntentUtils.GROUP_MEMBER);
        setIvTitleRight(R.mipmap.lt_zlsz_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(IntentUtils.GROUP_MEMBER,groupMember);
                Intent intent = new Intent(getContext(),FriendsSettingActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,0);
//                startActivity(FriendsSettingActivity.class,bundle);
            }
        });
        Glide.with(getActivity()).load(groupMember.getPhoto()).apply(GlideUtils.getChatAvaterOptions()).into(ivName);
        tvname.setText(groupMember.getName());
        tvId.setText(getResources().getString(R.string.id_)+groupMember.getId()+"");
        tvBeizu.setText(getResources().getString(R.string.beizhu_)+groupMember.getRemarks());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0&&resultCode==1){
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_send_message)
    public void onViewClicked() {
        /**
         * 启动会话界面。
         */
        RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.PRIVATE , groupMember.getId()+"", groupMember.getName());
    }
}
