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

public class RyunGroupMemberAdapter extends BaseAdapter<GroupMember, RyunGroupMemberAdapter.RyunGroupMemberAdapterViewHolder> {

    private final Boolean qz;

    public RyunGroupMemberAdapter(Context context, List<GroupMember> list, Boolean qz) {
        super(context, list);
        this.qz = qz;
        list.add(new GroupMember(1));
        if (qz)
        list.add(new GroupMember(2));

    }

    @Override
    protected RyunGroupMemberAdapterViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_group_member_ryun, parent, false);
                break;
                case 1:
                    view = LayoutInflater.from(getContext()).inflate(R.layout.item_group_member_add, parent, false);
            break;
                    case 2:
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_group_member_delete, parent, false);
                break;
        }


        return new RyunGroupMemberAdapterViewHolder(view);
    }

    @Override
    protected void onItemReset(RyunGroupMemberAdapterViewHolder holder) {

    }

    public void notifyDataSetChanged(List<GroupMember> list,Boolean qz) {
//        list.addAll(list);
        list.add(new GroupMember(1));
        if (qz)
            list.add(new GroupMember(2));
        super.notifyDataSetChanged(list);
    }

    @Override
    protected void onItemSelect(RyunGroupMemberAdapterViewHolder holder) {

    }

    @Override
    public int getItemViewType(int position) {
        return getList().get(position).getActionType();
    }

    @Override
    protected void viewHolderBind(RyunGroupMemberAdapterViewHolder holder, int position) {
        if (getList().get(position).getActionType()==0){
            holder.tvName.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(getList().get(position).getPhoto()).apply(GlideUtils.getChatAvaterOptions()).into(holder.ivAvatar);
            holder.tvName.setText(getList().get(position).getName());

        }else if (getList().get(position).getActionType()==1){
//            holder.tvName.setVisibility(View.INVISIBLE);
            holder.tvName.setText("dddddd");

            Glide.with(getContext()).load(R.mipmap.lt_tjyqhy).apply(GlideUtils.getChatAvaterOptions()).into(holder.ivAvatar);


        }else if (getList().get(position).getActionType()==2){
//            holder.tvName.setVisibility(View.INVISIBLE);
            holder.tvName.setText("dddddd");

            Glide.with(getContext()).load(R.mipmap.lt_scqy).apply(GlideUtils.getChatAvaterOptions()).into(holder.ivAvatar);

        }
    }

    class RyunGroupMemberAdapterViewHolder extends BaseViewHolder {

        @BindView(R.id.iv_avatar)
        ImageView ivAvatar;
        @BindView(R.id.tv_name)
        TextView tvName;

        public RyunGroupMemberAdapterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
