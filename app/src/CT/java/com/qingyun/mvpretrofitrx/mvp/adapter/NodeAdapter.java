package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.Node;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class NodeAdapter extends BaseAdapter<Node, NodeAdapter.NodeViewHolder> {



    public NodeAdapter(Context context, List<Node> list) {
        super(context, list);
    }

    @Override
    protected NodeViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_node, parent, false);
        return new NodeViewHolder(view);
    }

    @Override
    protected void onItemReset(NodeViewHolder holder) {


    }


    @Override
    protected void onItemSelect(NodeViewHolder holder) {



    }

    @Override
    protected void viewHolderBind(NodeViewHolder holder, int position) {
        holder.tvName.setText(getList().get(position).getName());
        holder.tvNode.setText(getList().get(position).getUrl());
        holder.tvSpeed.setText(getList().get(position).getSpeed());
        if (position==0){
            holder.ivChoose.setVisibility(View.VISIBLE);
            holder.tvName.setTextColor(getContext().getResources().getColor(R.color.main_blue));
        }else {
            holder.ivChoose.setVisibility(View.GONE);
            holder.tvName.setTextColor(getContext().getResources().getColor(R.color.color_main_text));

        }

    }

    class NodeViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_node)
        TextView tvNode;
        @BindView(R.id.tv_speed)
        TextView tvSpeed;
        @BindView(R.id.iv_speed)
        TextView ivSpeed;
        @BindView(R.id.iv_choose)
        ImageView ivChoose;
        public NodeViewHolder(View itemView) {
            super(itemView);
        }
    }
}
