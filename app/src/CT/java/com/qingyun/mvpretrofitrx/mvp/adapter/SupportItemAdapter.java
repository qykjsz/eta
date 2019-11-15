package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.base.BaseAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.BaseViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.Content;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.qingyun.mvpretrofitrx.mvp.utils.DividerHelper;
import com.senon.mvpretrofitrx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SupportItemAdapter extends BaseAdapter<Item, SupportItemAdapter.SupportItemViewHolder> {


    private List<ContentAdapter> contentAdapters;
    public SupportItemAdapter(Context context, List<Item> list) {
        super(context, list);
        contentAdapters = new ArrayList<>();

    }

    @Override
    protected SupportItemViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_suppot_item, parent, false);

        SupportItemViewHolder supportItemViewHolder = new SupportItemViewHolder(view);
        for (int i=0;i<getList().size();i++){
            ContentAdapter contentAdapter = new ContentAdapter(getContext(),getList().get(i).getContent());
            supportItemViewHolder.rcySecondItem.setLayoutManager(new LinearLayoutManager(getContext()));
            supportItemViewHolder.rcySecondItem.addItemDecoration(DividerHelper.getMyDivider(getContext()));
            contentAdapters.add(i,contentAdapter);
        }

        return supportItemViewHolder;
    }

    @Override
    protected void onItemReset(SupportItemViewHolder holder) {

    }

    @Override
    protected void onItemSelect(SupportItemViewHolder holder) {

    }

    @Override
    protected void viewHolderBind(SupportItemViewHolder holder, final int position) {

        holder.tvItem.setText(getList().get(position).getName());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                contentAdapters.get(position).notifyDataSetChanged(getList().get(position).getContent());
//                notifyDataSetChanged();
//            }
//        });

//        contentAdapters.get(position).notifyDataSetChanged(getList().get(position).getContent());
    }

    class SupportItemViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_item)
        TextView tvItem;
        @BindView(R.id.rcy_second_item)
        RecyclerView rcySecondItem;
        public SupportItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
