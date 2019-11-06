package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.Contact;
import com.senon.mvpretrofitrx.R;

import java.util.List;

public class ContactAdapter  extends BaseAdapter<Contact, ContactAdapter.ContactViewHolder> {

    public ContactAdapter(Context context, List<Contact> list) {
        super(context, list);
    }

    @Override
    protected ContactViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_contact,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    protected void onItemReset(ContactViewHolder holder) {

    }

    @Override
    protected void onItemSelect(ContactViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(ContactViewHolder holder, int position) {

    }

    class  ContactViewHolder extends BaseViewHolder{


        public ContactViewHolder(View itemView) {
            super(itemView);
        }
    }
}
