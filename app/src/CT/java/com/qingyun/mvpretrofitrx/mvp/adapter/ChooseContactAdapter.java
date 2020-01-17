package com.qingyun.mvpretrofitrx.mvp.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qingyun.mvpretrofitrx.mvp.entity.GroupMember;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.GlideUtils;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import cc.solart.turbo.BaseTurboAdapter;
import cc.solart.turbo.BaseViewHolder;

public class ChooseContactAdapter extends BaseTurboAdapter<GroupMember, BaseViewHolder> {

    private  RecyclerView recyclerView;
    private RyunContactAdapter.OnMyItemClickListener onItemClickListener;
    private SelectListener selectListener;

    public ChooseContactAdapter(Context context) {
        super(context);
    }
    private List<GroupMember> selectList = new ArrayList<>();
    private int actionType;
    public ChooseContactAdapter(Context context, List<GroupMember> data) {
        super(context, data);
    }

    public ChooseContactAdapter(Context context, List<GroupMember> data, int actionType, RecyclerView recyclerView) {
        super(context, data);
        this.actionType = actionType;
        this.recyclerView = recyclerView;
    }
    @Override
    protected int getDefItemViewType(int position) {
        GroupMember city = getItem(position);
        return city.getType();
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0)
        {
            if (actionType==0){
                return new CityHolder(inflateItemView(R.layout.item_city, parent));

            }else {
                return new CityHolder(inflateItemView(R.layout.item_city_single_choose, parent));
            }
        }else return new PinnedHolder(inflateItemView(R.layout.item_pinned_header, parent));

    }


    @Override
    protected void convert(BaseViewHolder holder, final GroupMember item) {


        if (holder instanceof CityHolder) {
            Glide.with(mContext).load(item.getPhoto()).apply(GlideUtils.getChatAvaterOptions()).into(((CityHolder) holder).imageView29);
            ((CityHolder) holder).city_name.setText(item.getName());
            if (actionType==0){
                ((CityHolder) holder).cb_select.setVisibility(View.VISIBLE);
                ((CityHolder) holder).iv_select.setVisibility(View.GONE);

                ((CityHolder) holder).cb_select.setChecked(item.isCheck());
                ((CityHolder) holder).cb_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                        item.setCheck(isChecked);
                        if (isChecked){
                                selectList.add(item);
                            }else {
                                selectList.remove(item);
                            }
                        selectListener.selectList(selectList,actionType);
                    }
                });
            }else {
                ((CityHolder) holder).cb_select.setVisibility(View.GONE);
                ((CityHolder) holder).iv_select.setVisibility(View.VISIBLE);
                if (!item.isCheck()){
                    Glide.with(mContext).load(R.mipmap.lt_xzlxr_wxz).into(((CityHolder) holder).iv_select);

                }else {
                    Glide.with(mContext).load(R.mipmap.lt_xzcy_icon).into(((CityHolder) holder).iv_select);

                }

                ((CityHolder) holder).iv_select.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectList.clear();
                        for (int i=0;i<mData.size();i++){
                            if (mData.get(i).getId()==item.getId()){
                                mData.get(i).setCheck(true);
                            }else {
                                mData.get(i).setCheck(false);
                            }
                        }
                        selectList.add(item);
                        selectListener.selectList(selectList,actionType);
                        if (recyclerView.isComputingLayout()) {
                            recyclerView.post(new Runnable() {
                                @Override
                                public void run() {
                                   notifyDataSetChanged();
                                }
                            });
                        } else {
                            notifyDataSetChanged();

                        }

                    }
                });
            }

//            ((CityHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onItemClickListener.onItemClick(item);
//                }
//            });
        }else {
            ((PinnedHolder) holder).city_tip.setText(item.getPinyin());
        }
    }

    public int getLetterPosition(String letter){
        for (int i = 0 ; i < getData().size(); i++){
            if(getData().get(i).getType()==1 && getData().get(i).getPinyin().equals(letter)){
                return i;
            }
        }
        return -1;
    }

    public void notifyDataSetChanged(List<GroupMember> groupMemberList) {
        mData = groupMemberList;
        notifyDataSetChanged();
    }

    public List<GroupMember> getSelectList() {
         return selectList;
    }

    public String getSelectListStr() {
        String ids = ApplicationUtil.getChatPersonalInfo().getId()+"";
        for (int i=0;i<selectList.size();i++){

                ids = ids+","+selectList.get(i).getId();

        }
        return ids;
    }

    class CityHolder extends BaseViewHolder {

        TextView city_name;
        CheckBox cb_select;
        ImageView imageView29;
        ImageView iv_select;

        public CityHolder(View view) {
            super(view);
            city_name = findViewById(R.id.city_name);
            cb_select = findViewById(R.id.cb_select);
            cb_select.setVisibility(View.VISIBLE);
            imageView29 = findViewById(R.id.imageView29);
            iv_select = findViewById(R.id.iv_select);

        }
    }


    public interface SelectListener{
        void selectList(List<GroupMember> groupMemberList,int actionType);
    }

    public void setSelectListener(SelectListener selectListener){
        this.selectListener = selectListener;
    }
    public void setItemClickListener(RyunContactAdapter.OnMyItemClickListener onItemClickListener){
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
