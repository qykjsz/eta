package com.qingyun.mvpretrofitrx.mvp.utils;

import android.content.Context;

import com.senon.mvpretrofitrx.R;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

public class DividerHelper {

    public static HorizontalDividerItemDecoration getMyDivider(Context context){

       return new HorizontalDividerItemDecoration.Builder(context)
                .color(context.getResources().getColor(R.color.color_line))
                .marginResId(R.dimen.dp_padding_x_min,R.dimen.dp_padding_x_min)
                .sizeResId(R.dimen.divider)
                .build();
    }

    public static HorizontalDividerItemDecoration getNotMarginDivider(Context context){

        return new HorizontalDividerItemDecoration.Builder(context)
                .color(context.getResources().getColor(R.color.color_line))
                .sizeResId(R.dimen.divider)
                .build();
    }

    public static HorizontalDividerItemDecoration getMy10PaddingDivider(Context context){

        return new HorizontalDividerItemDecoration.Builder(context)
                .color(context.getResources().getColor(R.color.trans))
                .sizeResId(R.dimen.dp_5)
                .build();
    }


    public static HorizontalDividerItemDecoration getRankFragmentDivider(Context context){

        return new HorizontalDividerItemDecoration.Builder(context)
                .color(context.getResources().getColor(R.color.color_line))
                .marginResId(R.dimen.dp_50,R.dimen.dp_padding_x_min)
                .sizeResId(R.dimen.divider)
                .build();
    }
}
