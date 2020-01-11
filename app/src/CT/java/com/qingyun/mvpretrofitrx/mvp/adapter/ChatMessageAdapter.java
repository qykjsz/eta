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
import com.qingyun.mvpretrofitrx.mvp.entity.ChatMessage;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.GlideUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.TimeUtils;
import com.qingyun.mvpretrofitrx.mvp.weight.BoldTextView;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;

public class ChatMessageAdapter extends BaseAdapter<ChatMessage, ChatMessageAdapter.ChatMessageViewHolder> {
    private final boolean isGroup;



    public ChatMessageAdapter(Context context, List<ChatMessage> list, boolean isGroup) {
        super(context, list);
        this.isGroup = isGroup;
    }

    @Override
    protected ChatMessageViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 1) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_chat_message_other, parent, false);

        } else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_chat_message_me, parent, false);

        }
        return new ChatMessageViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (getList().get(position).getFromwho().equals(ApplicationUtil.getCurrentWallet().getAddress())) {
            return 0;
        } else if (getList().get(position).getTowho().equals(ApplicationUtil.getCurrentWallet().getAddress())) {
            return 1;
        } else {
            return 1;
        }

    }

    @Override
    protected void onItemReset(ChatMessageViewHolder holder) {

    }

    @Override
    protected void onItemSelect(ChatMessageViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(ChatMessageViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            Glide.with(getContext()).load(getList().get(position).getFromphoto()).apply(GlideUtils.getAvaterOptions()).into(holder.ivAvatar);
            holder.tvName.setText(getList().get(position).getFromname());

        } else {
//            if (isGroup) {
//                holder.ivAvatar.setVisibility(View.GONE);
//                holder.tvAvatar.setVisibility(View.VISIBLE);
//                holder.tvAvatar.setText(getList().get(position).getToname().substring(0,1));
//
//            } else {
//                holder.tvAvatar.setVisibility(View.GONE);
//                holder.ivAvatar.setVisibility(View.VISIBLE);
//                Glide.with(getContext()).load(getList().get(position).getTophoto()).apply(GlideUtils.getAvaterOptions()).into(holder.ivAvatar);
//            }
            Glide.with(getContext()).load(getList().get(position).getFromphoto()).apply(GlideUtils.getAvaterOptions()).into(holder.ivAvatar);

                holder.tvName.setText(getList().get(position).getFromname());



        }


        holder.tvMessage.setText(getList().get(position).getText());
        holder.tvTime.setText(TimeUtils.getTime(Long.parseLong(getList().get(position).getTime())));

    }

    class ChatMessageViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_avatar)
        ImageView ivAvatar;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_message)
        TextView tvMessage;
        @BindView(R.id.tv_avatar)
        BoldTextView tvAvatar;

        public ChatMessageViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void addItem(ChatMessage chatMessage){
        if (list.size()==0){
            list.add(chatMessage);
            notifyDataSetChanged();
        }else {
            list.add(chatMessage);
            notifyItemInserted(list.size());
        }

//        compatibilityDataSizeChanged(1);

    }

    /**
     * compatible getLoadMoreViewCount and getEmptyViewCount may change
     *
     * @param size Need compatible data size
     */
    private void compatibilityDataSizeChanged(int size) {
        final int dataSize = list == null ? 0 : list.size();
        if (dataSize == size) {
            notifyDataSetChanged();
        }
    }

}
