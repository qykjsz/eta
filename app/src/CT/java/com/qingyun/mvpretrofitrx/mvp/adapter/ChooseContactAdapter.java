package com.qingyun.mvpretrofitrx.mvp.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import cc.solart.turbo.BaseTurboAdapter;
import cc.solart.turbo.BaseViewHolder;

public class ChooseContactAdapter extends BaseTurboAdapter<GroupMember, BaseViewHolder> {

    private OnMyItemClickListener onItemClickListener;

    public ChooseContactAdapter(Context context) {
        super(context);
    }

    public ChooseContactAdapter(Context context, List<GroupMember> data) {
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
        CheckBox cb_select;

        public CityHolder(View view) {
            super(view);
            city_name = findViewById(R.id.city_name);
            cb_select = findViewById(R.id.cb_select);
            cb_select.setVisibility(View.VISIBLE);
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
