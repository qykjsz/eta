package com.qingyun.mvpretrofitrx.mvp.presenter;

import android.content.Context;

import com.qingyun.mvpretrofitrx.mvp.contract.CoinContact;
import com.qingyun.mvpretrofitrx.mvp.contract.ContactsContact;
import com.qingyun.mvpretrofitrx.mvp.entity.Contact;
import com.qingyun.mvpretrofitrx.mvp.entity.Wallet;
import com.qingyun.mvpretrofitrx.mvp.model.CoinModel;
import com.qingyun.mvpretrofitrx.mvp.model.ContactsModel;
import com.qingyun.mvpretrofitrx.mvp.progress.ObserverResponseListener;

import java.util.List;

public class ContactsPresenter extends ContactsContact.Presenter {
    private ContactsModel model;
    private Context context;


    public ContactsPresenter(Context context) {
        this.model = new ContactsModel();
        this.context = context;
    }


    @Override
    public void addContacts(String imei, String name, String remarks, String wallettype, String address) {
        model.addContacts(context,imei,  name,  remarks,  wallettype,  address,getView().bindLifecycle(), new ObserverResponseListener<Object>() {

            @Override
            public void onNext(Object o) {
                if(getView() != null){
                    getView().addContactsSuccess();
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    @Override
    public void getContactList(String contacts) {
        model.getContactList(context,contacts,getView().bindLifecycle(), new ObserverResponseListener<List<Contact>>() {

            @Override
            public void onNext(List<Contact> contactList) {
                if(getView() != null){
                    getView().getContactListSuccess(contactList);
                }
            }

            @Override
            public void onError(String e) {

            }
        });

    }
}
