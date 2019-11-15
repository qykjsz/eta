package com.qingyun.mvpretrofitrx.mvp.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by xl on 2016/11/11.
 */
public abstract class FatherAdapter<T> extends BaseAdapter {

    private List<T> datas;
    protected LayoutInflater mInflater;
    protected Context mContext;
    /**
     * 等待对话框
     */
//    protected OnWaitDialogOperationListener mOnWaitDialogOperationListener;


    protected int mode;

    protected int currentSelect = -1;


    public boolean[] getStates() {
        return states;
    }

    protected boolean[] states;

    public FatherAdapter(Context ctx) {
        mInflater = LayoutInflater.from(ctx);
        mContext = ctx;
//        if (null != mContext && mContext instanceof OnWaitDialogOperationListener) {//上下文传一般都是传的Act,继承基类,基类实现了该接口
//            setOnWaitDialogOperationListener((OnWaitDialogOperationListener) mContext);
//        }

    }

    public FatherAdapter(Context ctx, int mode) {
        this(ctx);
        this.mode = mode;
    }

    @Override
    public int getCount() {
        return null == datas ? 0 : datas.size();
    }

    @Override
    public boolean isEmpty() {
        return null == datas ? true : datas.isEmpty();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setDatas(List<T> datas) {//自己没有内存所以不用清
        this.datas = datas;
        if (null != datas) {
            states = new boolean[datas.size()];
        }
        notifyDataSetChanged();
    }




    public void removeItem(int position) {
        if (datas.get(position) != null) {
            datas.remove(position);
            notifyDataSetChanged();
        }
    }

    public void addItem(T t) {
        if (null != datas) {
            datas.add(t);
            notifyDataSetChanged();
        }
    }

    public void modifyItem(int position, T t) {
        datas.set(position, t);
        notifyDataSetChanged();
    }

    public void removeItem(T t) {
        if (datas.contains(t)) {
            datas.remove(t);
            notifyDataSetChanged();
        }
    }

    public void removeAll() {
        if (datas != null || !datas.isEmpty()) {
            datas.clear();
        }
        notifyDataSetChanged();
    }

    public void addData(List<T> list) {
        if (datas != null) {
            datas.addAll(list);
        } else {
            datas = list;
        }
        notifyDataSetChanged();
    }


    public void clear() {
        if(datas!=null){
            datas.clear();
        }

        notifyDataSetChanged();
    }

    public List<T> getData() {
        return datas;
    }


    protected View getConvertView(int layoutRes, ViewGroup parent) {
        return mInflater.inflate(layoutRes, parent, false);
    }

    /**
     * 改变选中后
     * @param position
     * @return
     * @description 选中状态和适配器绑定
     */
    public boolean changeSelect(int position) {
        if (position == currentSelect) {
            return false;
        }
        if (currentSelect != -1 && currentSelect < getCount()) {
            states[currentSelect] = false;
        }
        currentSelect = position;
        states[currentSelect] = true;
        notifyDataSetChanged();
        return true;
    }
}
