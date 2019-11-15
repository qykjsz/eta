package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingyun.mvpretrofitrx.mvp.entity.Content;
import com.qingyun.mvpretrofitrx.mvp.entity.Item;
import com.senon.mvpretrofitrx.R;

import java.util.List;

import butterknife.BindView;
import drawthink.expandablerecyclerview.adapter.BaseRecyclerViewAdapter;
import drawthink.expandablerecyclerview.bean.RecyclerViewData;
import drawthink.expandablerecyclerview.holder.BaseViewHolder;

/**
 * Created by jhj_Plus on 2016/9/2.
 */
public class SupportAdapter extends BaseRecyclerViewAdapter<Item, Content, SupportAdapter.SupportViewHolder> {

    private Context ctx;
    private List datas;
    private LayoutInflater mInflater;

    public SupportAdapter(Context ctx, List<RecyclerViewData> datas) {
        super(ctx, datas);
        mInflater = LayoutInflater.from(ctx);
        this.ctx = ctx;
        this.datas = datas;
    }

    @Override
    public View getGroupView(ViewGroup parent) {
        return mInflater.inflate(R.layout.item_suppot_item, parent, false);
    }

    @Override
    public View getChildView(ViewGroup parent) {
        return mInflater.inflate(R.layout.item_item, parent, false);

    }

    @Override
    public SupportViewHolder createRealViewHolder(Context ctx, View view, int viewType) {
        return new SupportViewHolder(ctx, view, viewType);
    }

    @Override
    public void onBindGroupHolder(SupportViewHolder holder, int groupPos, int position, Item groupData) {
        holder.tvParent.setText(groupData.getName());

    }

    @Override
    public void onBindChildpHolder(SupportViewHolder holder, int groupPos, int childPos, int position, Content childData) {
        holder.tvChild.setText(childData.getName());

    }

    public void notifyDataSetChanged(List<RecyclerViewData> list) {
        this.datas = list;
        notifyDataSetChanged();
    }

    class SupportViewHolder extends BaseViewHolder {
        TextView tvChild;
        TextView tvParent;
        public SupportViewHolder(Context ctx, View itemView, int viewType) {
            super(ctx, itemView, viewType);
            tvChild = itemView.findViewById(R.id.tv_child);
            tvParent = itemView.findViewById(R.id.tv_parent);

        }

        @Override
        public int getChildViewResId() {
            return R.id.content;
        }

        @Override
        public int getGroupViewResId() {
            return R.id.content;

        }
    }
}
