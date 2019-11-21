package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.contract.ContactsContact;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Contact;
import com.qingyun.mvpretrofitrx.mvp.entity.ImportScanResult;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.qingyun.mvpretrofitrx.mvp.presenter.ContactsPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.DialogUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.SystemUtil;
import com.senon.mvpretrofitrx.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.shinichi.library.tool.utility.ui.PhoneUtil;
import io.reactivex.ObservableTransformer;

public class AddNewContactActivity extends BaseActivity<ContactsContact.View,ContactsContact.Presenter> implements ContactsContact.View {
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.tv_bottom)
    TextView tvBottom;
    @BindView(R.id.et_address)
    EditText etAddress;

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
        return getResources().getString(R.string.add_new_contact);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_new_contact;
    }

    @Override
    public ContactsContact.Presenter createPresenter() {
        return new ContactsPresenter(this);
    }

    @Override
    public ContactsContact.View createView() {
        return this;
    }


    @Override
    public void init() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_save, R.id.btn_scan,R.id.tv_bottom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                SystemUtil.getMyUUID(getActivity(), new SystemUtil.RequestPermissionListener() {
                    @Override
                    public void requestSuccess(String uuid) {
                        getPresenter().addContacts(uuid,etName.getText().toString(),etRemark.getText().toString(),tvBottom.getText().toString(),etAddress.getText().toString());

                    }
                });
                break;
            case R.id.tv_bottom:
//                getPresenter().getWalletInfo(ApplicationUtil.getCurrentWallet().getAddress());
                startActivity(ChooseBottomLsvelActivity.class);
                break;
            case R.id.btn_scan:
                startActivity(ImportScanActivity.class);
                break;
        }
    }

    @Override
    public void addContactsSuccess() {
        finish();
    }

    @Override
    public void getContactListSuccess(List<Contact> contactList) {

    }

    @Override
    public void getWalletInfoSuccess(AssetResponse assetResponse) {
        DialogUtils.showCoinChooseDialog(getActivity(), assetResponse.getGlod(), new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
                tvBottom.setText(((Wallet)list.get(position)).getName());
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreashWallet(ImportScanResult importScanResult) {
        etAddress.setText(importScanResult.getAddress());
    }


    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
