package com.qingyun.mvpretrofitrx.mvp.activity.rongyun;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.senon.mvpretrofitrx.R;

public class SetBeizhuActivity extends BaseActivity {
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
        return getResources().getString(R.string.set_beizhu);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_beizhu;
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

        getTvTitleRight().setText(getResources().getString(R.string.complete));
        getTvTitleRight().setTextColor(getResources().getColor(R.color.color_FFFFFF));
        getTvTitleRight().setBackground(getResources().getDrawable(R.drawable.bg_mainblue_round_3));

    }
}
