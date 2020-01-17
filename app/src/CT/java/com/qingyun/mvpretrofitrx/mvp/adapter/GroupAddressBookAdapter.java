package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.Group;
import com.qingyun.mvpretrofitrx.mvp.utils.GlideUtils;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class GroupAddressBookAdapter extends BaseAdapter<Group, GroupAddressBookAdapter.GroupAddressBookViewHolder> {



    public GroupAddressBookAdapter(Context context, List<Group> list) {
        super(context, list);
    }

    @Override
    protected GroupAddressBookViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_group_addressbook, parent, false);
        return new GroupAddressBookViewHolder(view);
    }

    @Override
    protected void onItemReset(GroupAddressBookViewHolder holder) {

    }

    @Override
    protected void onItemSelect(GroupAddressBookViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(GroupAddressBookViewHolder holder, int position) {

        Glide.with(getContext()).load(getList().get(position).getPhoto()).apply(GlideUtils.getChatAvaterOptions()).into(holder.ivAvatar);
        holder.tvName.setText(getList().get(position).getName());
        holder.tvNum.setText("("+getList().get(position).getNumber()+")");

    }

    class GroupAddressBookViewHolder extends BaseViewHolder {

        @BindView(R.id.iv_avatar)
        ImageView ivAvatar;
        @BindView(R.id.tv_name)
        BoldTextView tvName;
        @BindView(R.id.tv_num)
        TextView tvNum;

        public GroupAddressBookViewHolder(View itemView) {
            super(itemView);
        }
    }
}
