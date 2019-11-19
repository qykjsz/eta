package com.qingyun.mvpretrofitrx.mvp.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;


/**
 * @author: android                              --------->作者
 * @description: --------->描述
 * @projectName: ruiyilianmengapp    ---------> 项目名称
 * @date: 2018-07-23    --------->日期
 * @time: 10:11               --------->时间
 */
public class JsOperation {
    private Context context;
    private View view;

    public JsOperation(Context context, View view) {
        this.context = context;
        this.view = view;
    }


    @JavascriptInterface//这句标识必须要写上否则会出问题
    public void test(String param) {
        Log.i("dsfgdsf", param);


        // Toast.makeText(AboutUsActivity.this,param,Toast.LENGTH_SHORT).show();


    }
}
