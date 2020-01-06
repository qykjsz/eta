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
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.utils.GlideUtils;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class GroupMemberAdapter extends BaseAdapter<GroupMember, GroupMemberAdapter.GroupMemberViewHolder> {


    public GroupMemberAdapter(Context context, List<GroupMember> list) {
        super(context, list);
    }

    @Override
    protected GroupMemberViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_group_member, parent, false);
        return new GroupMemberViewHolder(view);
    }

    @Override
    protected void onItemReset(GroupMemberViewHolder holder) {

    }

    @Override
    protected void onItemSelect(GroupMemberViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(GroupMemberViewHolder holder, int position) {
        holder.tvName.setText(getList().get(position).getName());
        Glide.with(getContext()).load(getList().get(position).getPhoto()).apply(GlideUtils.getAvaterOptions()).into(holder.ivAvatar);
        if (getList().get(position).getShenfen()==1){
            holder.tvOwner.setVisibility(View.VISIBLE);
        }else {
            holder.tvOwner.setVisibility(View.GONE);

        }

    }

    class GroupMemberViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_avatar)
        ImageView ivAvatar;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_owner)
        TextView tvOwner;
        public GroupMemberViewHolder(View itemView) {
            super(itemView);
        }
    }
}
