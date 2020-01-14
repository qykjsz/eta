package com.qingyun.mvpretrofitrx.mvp.utils;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.senon.mvpretrofitrx.R;

public class GlideUtils {

    public static RequestOptions getAvaterOptions(){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .transform(new CircleCrop())
                .placeholder(R.mipmap.avater_default)
                .error(R.mipmap.avater_default)
                .priority(Priority.HIGH);
        return options;
    }



    public static RequestOptions getChatAvaterOptions(){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .transform(new CircleCrop())
                .placeholder(R.mipmap.avater_default)
                .error(R.mipmap.avater_default)
                .priority(Priority.HIGH);
        return options;
    }
}
