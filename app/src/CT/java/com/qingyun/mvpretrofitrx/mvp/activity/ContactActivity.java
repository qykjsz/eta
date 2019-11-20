package com.qingyun.mvpretrofitrx.mvp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qingyun.mvpretrofitrx.mvp.adapter.ContactAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseActivity;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.contract.ContactsContact;
import com.qingyun.mvpretrofitrx.mvp.entity.AssetResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.Contact;
import com.qingyun.mvpretrofitrx.mvp.presenter.ContactsPresenter;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.qingyun.mvpretrofitrx.mvp.utils.IntentUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.SystemUtil;
import com.senon.mvpretrofitrx.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class ContactActivity extends BaseActivity<ContactsContact.View, ContactsContact.Presenter> implements ContactsContact.View {

    ContactAdapter contactAdapter;
    @BindView(R.id.rcy)
    RecyclerView rcy;
    private List<Contact> list;

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
        return getResources().getString(R.string.contact);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_contact;
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
        list = new ArrayList<>();
        contactAdapter = new ContactAdapter(getContext(), list);
        contactAdapter.setItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List list, int position) {
//                EventBus.getDefault().post((Contact)list.get(position));
                Bundle bundle = new Bundle();
                bundle.putString(IntentUtils.TRANSFER_ADDRESS,((Contact)list.get(position)).getAddress());
                startActivity(TransferImmediateActivity.class,bundle);
            }
        });
        rcy.setLayoutManager(new LinearLayoutManager(getContext()));
        rcy.addItemDecoration(DividerHelper.getMyDivider(getContext()));
        rcy.setAdapter(contactAdapter);
        refreashView(list, rcy);


    }

    @Override
    protected void onResume() {
        super.onResume();
        SystemUtil.getMyUUID(getActivity(), new SystemUtil.RequestPermissionListener() {
            @Override
            public void requestSuccess(String uuid) {
                getPresenter().getContactList(uuid);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_add_new_contact)
    public void onViewClicked()
    {
        startActivity(AddNewContactActivity.class);
    }

    @Override
    public void addContactsSuccess() {

    }

    @Override
    public void getContactListSuccess(List<Contact> contactList) {
        list = contactList;
        contactAdapter.notifyDataSetChanged(list);
        refreashView(list, rcy);
    }

    @Override
    public void getWalletInfoSuccess(AssetResponse assetResponse) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }

}
