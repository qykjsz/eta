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
import com.qingyun.mvpretrofitrx.mvp.entity.ApplyGroup;
import com.qingyun.mvpretrofitrx.mvp.utils.GlideUtils;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class GroupMessageAdapter extends BaseAdapter<ApplyGroup, GroupMessageAdapter.GroupMessageViewHolder> {


    private OnItemClickListener refuseListener;
    private OnItemClickListener agreeListener;

    public GroupMessageAdapter(Context context, List<ApplyGroup> list) {
        super(context, list);
    }

    @Override
    protected GroupMessageViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_group_massage, parent, false);
        return new GroupMessageViewHolder(view);
    }

    @Override
    protected void onItemReset(GroupMessageViewHolder holder) {

    }

    @Override
    protected void onItemSelect(GroupMessageViewHolder holder) {

    }


    public void setRefuseListener(OnItemClickListener refuseListener) {
        this.refuseListener = refuseListener;
    }

    public void setAgreeListener(OnItemClickListener agreeListener) {
        this.agreeListener = agreeListener;
    }


    @Override
    protected void viewHolderBind(GroupMessageViewHolder holder, final int position) {


        if (getList().get(position).getState() == 1) {
            holder.btnAgree.setVisibility(View.VISIBLE);
            holder.btnRefuse.setVisibility(View.VISIBLE);
            holder.btnAgree.setEnabled(true);
            holder.btnRefuse.setEnabled(true);

            holder.tvStatus.setVisibility(View.GONE);
            holder.tvNewMessage.setVisibility(View.GONE);

        } else {
            holder.btnAgree.setVisibility(View.INVISIBLE);
            holder.btnAgree.setEnabled(false);
            holder.btnRefuse.setVisibility(View.INVISIBLE);
            holder.btnRefuse.setEnabled(false);

            holder.tvStatus.setVisibility(View.VISIBLE);
            holder.tvNewMessage.setVisibility(View.VISIBLE);
            holder.tvNewMessage.setText(getContext().getResources().getString(R.string.deal_name_)+getList().get(position).getOperation());
            if (getList().get(position).getState() == 2) {
                holder.tvStatus.setText(R.string.agreed);
                holder.tvStatus.setTextColor(getContext().getResources().getColor(R.color.main_blue));
            } else if (getList().get(position).getState() == 3) {
                holder.tvStatus.setText(R.string.refused);
                holder.tvStatus.setTextColor(getContext().getResources().getColor(R.color.color_B7BCDC));
            }
        }


        holder.btnRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refuseListener.onItemClick(getList(), position);
            }
        });

        holder.btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agreeListener.onItemClick(getList(), position);
            }
        });

        holder.tvName.setText(getList().get(position).getName());
        holder.tvQname.setText(getList().get(position).getQname());
        Glide.with(getContext()).load(getList().get(position).getId()).apply(GlideUtils.getAvaterOptions()).into(holder.ivPic);
        holder.tvPic.setText(getList().get(position).getQname().substring(0, 1));
    }

    class GroupMessageViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_pic)
        BoldTextView tvPic;
        @BindView(R.id.tv_name)
        BoldTextView tvName;
        @BindView(R.id.btn_agree)
        TextView btnAgree;
        @BindView(R.id.btn_refuse)
        TextView btnRefuse;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_qname)
        TextView tvQname;

        @BindView(R.id.iv_pic)
        ImageView ivPic;

        @BindView(R.id.tv_new_message)
        TextView tvNewMessage;
        public GroupMessageViewHolder(View itemView) {
            super(itemView);
        }
    }
}
