package com.qingyun.mvpretrofitrx.mvp.utils;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
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
        RoundedCorners roundedCorners= new RoundedCorners(15);
//通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(300, 300);

//        RequestOptions options = new RequestOptions()
//                .centerCrop()
//                .transform(new CircleCrop())
//                .placeholder(R.mipmap.avater_default)
//                .error(R.mipmap.avater_default)
//                .priority(Priority.HIGH);
        return options;
    }
}
