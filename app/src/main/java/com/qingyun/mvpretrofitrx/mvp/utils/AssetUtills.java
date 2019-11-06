package com.qingyun.mvpretrofitrx.mvp.utils;

import android.content.Context;
import android.content.res.Resources;

public class AssetUtills {
    public static int getResId(String name,Context context){
        Resources resources  = context.getResources();
        int id = resources.getIdentifier(name,"drawable",AppUtils.getPackageName(context));
        return id;
    }
}
