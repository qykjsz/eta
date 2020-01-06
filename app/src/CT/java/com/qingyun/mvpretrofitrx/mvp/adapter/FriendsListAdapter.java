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
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class FriendsListAdapter extends BaseAdapter<GroupMember, FriendsListAdapter.FriendsListViewHolder> {



    private int position;

    public FriendsListAdapter(Context context, List<GroupMember> list) {
        super(context, list);
    }

    public FriendsListAdapter(Context context, List<GroupMember> list, int position) {
        super(context, list);
        this.position = position;

    }

    @Override
    protected FriendsListViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_friends_list, parent, false);


        return new FriendsListViewHolder(view);
    }

    @Override
    protected void onItemReset(FriendsListViewHolder holder) {

    }

    @Override
    protected void onItemSelect(FriendsListViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(FriendsListViewHolder holder, int position) {
        holder.tvName.setText(getList().get(position).getName());
        holder.tvAvatar.setText(getList().get(position).getName().substring(0,1));
        Glide.with(getContext()).load(getList().get(position).getPhoto()).apply(GlideUtils.getAvaterOptions()).into(holder.ivPic);

    }

    class FriendsListViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_name)
        BoldTextView tvName;
        @BindView(R.id.tv_new_message)
        TextView tvNewMessage;
        @BindView(R.id.tv_avatar)
        BoldTextView tvAvatar;

        public FriendsListViewHolder(View itemView) {
            super(itemView);
        }
    }
}
