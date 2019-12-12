package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.Contact;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class ContactAdapter extends BaseAdapter<Contact, ContactAdapter.ContactViewHolder> {


    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();


    private OnItemClickListener onDeleteListener;
    private OnItemClickListener onEditListener;
    private OnItemClickListener onmyItemClickListener;

    public ContactAdapter(Context context, List<Contact> list) {
        super(context, list);
        viewBinderHelper.setOpenOnlyOne(true);

    }

    public ViewBinderHelper getViewBinderHelper() {
        return viewBinderHelper;
    }

    @Override
    protected ContactViewHolder getViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }


    @Override
    protected void onItemReset(ContactViewHolder holder) {

    }

    @Override
    protected void onItemSelect(ContactViewHolder holder) {

    }


    public void saveStates(Bundle outState) {
        viewBinderHelper.saveStates(outState);
    }

    public void restoreStates(Bundle inState) {
        viewBinderHelper.restoreStates(inState);
    }


    public void setDeleteListener(OnItemClickListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }

    public void setEditListener(OnItemClickListener onEditListener) {
        this.onEditListener = onEditListener;
    }

    public void setItemClickListener(OnItemClickListener onItemClickListener) {
        this.onmyItemClickListener = onItemClickListener;
    }




    public void notifyMyItemRemoved(int position){
        getList().remove(position);
        notifyItemRemoved(position);
    }


    @Override
    protected void viewHolderBind(ContactViewHolder holder, final int position) {
        holder.tvCoinType.setText(getList().get(position).getWallettype());
        holder.tvName.setText(getList().get(position).getName());
        viewBinderHelper.bind(holder.lySw, getList().get(position).getAddress());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteListener != null) {
                    onDeleteListener.onItemClick(getList(), position);

                }
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteListener != null)
                    onEditListener.onItemClick(getList(), position);
            }
        });

        holder.lyFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onmyItemClickListener.onItemClick(getList(),position);
            }
        });
    }

    class ContactViewHolder extends BaseViewHolder {


        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_coin_type)
        TextView tvCoinType;
        @BindView(R.id.ly_sw)
        SwipeRevealLayout lySw;
        @BindView(R.id.btn_edit)
        ImageView btnEdit;
        @BindView(R.id.btn_delete)
        ImageView btnDelete;
        @BindView(R.id.ly_front)
        RelativeLayout lyFront;


        public ContactViewHolder(View itemView) {
            super(itemView);
        }


    }

    @Override
    public void notifyDataSetChanged(List<Contact> list) {
        viewBinderHelper.unbind();
        super.notifyDataSetChanged(list);

    }
}
