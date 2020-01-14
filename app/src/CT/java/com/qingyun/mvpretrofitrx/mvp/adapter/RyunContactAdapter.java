package com.qingyun.mvpretrofitrx.mvp.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.utils.GlideUtils;
import com.qingyun.mvpretrofitrx.mvp.view.lettercontact.bean.City;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import cc.solart.turbo.BaseTurboAdapter;
import cc.solart.turbo.BaseViewHolder;
import cc.solart.turbo.OnItemClickListener;

public class RyunContactAdapter extends BaseTurboAdapter<GroupMember, BaseViewHolder> {

    private OnMyItemClickListener onItemClickListener;

    public RyunContactAdapter(Context context) {
        super(context);
    }

    public RyunContactAdapter(Context context, List<GroupMember> data) {
        super(context, data);
    }

    @Override
    protected int getDefItemViewType(int position) {
        GroupMember city = getItem(position);
        return city.getType();
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0)
            return new CityHolder(inflateItemView(R.layout.item_city, parent));
        else
            return new PinnedHolder(inflateItemView(R.layout.item_pinned_header, parent));
    }


    @Override
    protected void convert(BaseViewHolder holder, final GroupMember item) {


        if (holder instanceof CityHolder) {
            ((CityHolder) holder).city_name.setText(item.getName());
            Glide.with(mContext).load(item.getPhoto()).apply(GlideUtils.getChatAvaterOptions()).into(((CityHolder) holder).imageView29);
            ((CityHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(item);
                }
            });
        }else {
            ((PinnedHolder) holder).city_tip.setText(item.getPinyin());
        }
    }

    public int getLetterPosition(String letter){
        for (int i = 0 ; i < getData().size(); i++){
            if(getData().get(i).getType() ==1 && getData().get(i).getPinyin().equals(letter)){
                return i;
            }
        }
        return -1;
    }

    public void notifyDataSetChanged(List<GroupMember> groupMemberList) {
        mData = groupMemberList;
        notifyDataSetChanged();
    }

    class CityHolder extends BaseViewHolder {

        TextView city_name;
        ImageView imageView29;

        public CityHolder(View view) {
            super(view);
            city_name = findViewById(R.id.city_name);
            imageView29 = findViewById(R.id.imageView29);

        }
    }



    public void setItemClickListener(OnMyItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnMyItemClickListener {
        void onItemClick(GroupMember groupMember);
    }
    class PinnedHolder extends BaseViewHolder {

        TextView city_tip;

        public PinnedHolder(View view) {
            super(view);
            city_tip = findViewById(R.id.city_tip);
        }
    }
}
