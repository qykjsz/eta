package com.qingyun.mvpretrofitrx.mvp.weight;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import java.util.ArrayList;

import cc.shinichi.library.ImagePreview;

//import cc.shinichi.library.ImagePreview;

public class MJavascriptInterface {
    private Context context;
    private String [] imageUrls;


    public MJavascriptInterface(Context context,String[] imageUrls) {  
        this.context = context;  
        this.imageUrls = imageUrls;  
    }

    public MJavascriptInterface(Context context) {
        this.context = context;
    }

    @android.webkit.JavascriptInterface
    public void openImage(String img) {
        int index=0;
        ArrayList<String> imgs = new ArrayList<>();
        for (int i=0;i<imageUrls.length;i++){
            imgs.add(imageUrls[i]);
            if (imageUrls[i].equals(img))
                index = i;
        }

        // 一行代码即可实现大部分需求，如需定制，可参考下面自定义的代码：
        ImagePreview.getInstance().setContext(context).setImageList(imgs)
                .setIndex(index)
                .start();
        Log.e("img>>>>>>>",img);
    }  
}