package com.qingyun.mvpretrofitrx.mvp.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class SelectTextView extends android.support.v7.widget.AppCompatTextView {
    private Boolean selected = false;
    private int selectResId;
    private int unSelectResId;



    public void initTextView(Boolean selected,int selectResId,int unSelectResId){
        this.selected = selected;
        this.selectResId = selectResId;
        this.unSelectResId = unSelectResId;
    }
    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public int getSelectResId() {
        return selectResId;
    }

    public void setSelectResId(int selectResId) {
        this.selectResId = selectResId;
    }

    public int getUnSelectResId() {
        return unSelectResId;
    }

    public void setUnSelectResId(int unSelectResId) {
        this.unSelectResId = unSelectResId;
    }

    public SelectTextView(Context context) {
        super(context);
    }



    public SelectTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectTextView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



}
