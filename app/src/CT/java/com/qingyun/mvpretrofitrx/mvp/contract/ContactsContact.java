package com.qingyun.mvpretrofitrx.mvp.contract;

import com.qingyun.mvpretrofitrx.mvp.base.BasePresenter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseView;
import com.qingyun.mvpretrofitrx.mvp.entity.Contact;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;

import java.util.List;


public interface ContactsContact {

    interface View extends BaseView {
        void addContactsSuccess();
        void getContactListSuccess(List<Contact> contactList);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void addContacts(String imei,String name,String remarks,String wallettype,String address);
        public abstract void getContactList(String contacts);

    }
}
