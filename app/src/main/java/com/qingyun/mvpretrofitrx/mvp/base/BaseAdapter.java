package com.qingyun.mvpretrofitrx.mvp.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseAdapter<T extends Object, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {


    //单选还是多选
    private Boolean isSingleChoose = true;
    private Context context;
    private int selectPosition = -1;
    private OnItemClickListener onItemClickListener;
    private List<T> list;


    public int getSelectPosition() {
        return selectPosition;
    }

    private List<T> selectItems = new ArrayList<>();

    private SparseBooleanArray mCheckStates = new SparseBooleanArray();

    public BaseAdapter(Context context, List<T> list) {
        this.context = context;
        if (list==null) list = new ArrayList<>();
        this.list = list;
        for (int i = 0; i < list.size(); i++) {
            mCheckStates.put(i, false);
        }
    }

    public BaseAdapter(Context context, List<T> list, Boolean isSingleChoose) {
        this.context = context;
        this.list = list;
        this.isSingleChoose = isSingleChoose;
        for (int i = 0; i < list.size(); i++) {
            mCheckStates.put(i, false);
        }
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    public void setmCheckStates(SparseBooleanArray mCheckStates) {
        this.mCheckStates = mCheckStates;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public T getSelectItem() {
        if (selectPosition == -1) return null;
        return getList().get(selectPosition);
    }
    public void setSelectItems(List<T> selectItems) {
        this.selectItems = selectItems;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {


        return getViewHolder(parent, viewType);
    }

    public View getView(int resId, ViewGroup parent){
        return LayoutInflater.from(context).inflate(resId,parent,false);
    }
    protected abstract VH getViewHolder(ViewGroup parent, int viewType);


    public void setDefaultSelectItem(int defaultSelectPosition) {
        mCheckStates.put(defaultSelectPosition, true);
        selectPosition = defaultSelectPosition;
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        //单选
        if (isSingleChoose) {
            if (position == selectPosition) {
                onItemSelect(holder);
            } else {
                onItemReset(holder);

            }
        } else {
            //多选
            if (mCheckStates.get(position)) {
                onItemSelect(holder);

            } else {
                onItemReset(holder);
            }
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPosition = position;
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(list, position);
                }
                if (!mCheckStates.get(position)){
                    selectItems.add(getList().get(position));
                }else {
                    selectItems.remove(getList().get(position));
                }
                mCheckStates.put(position, !mCheckStates.get(position));
                notifyDataSetChanged();
            }
        });
        viewHolderBind(holder, position);


    }

    protected abstract void onItemReset(VH holder);

    protected abstract void onItemSelect(VH holder);

    protected abstract void viewHolderBind(VH holder, int position);

    @Override
    public int getItemCount() {
        return list.size();
    }




    public interface OnItemClickListener<T> {
        void onItemClick(List<T> list, int position);
    }

    public void setItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }


    public void notifyDataSetChanged(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

}
