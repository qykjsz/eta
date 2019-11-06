//package com.qingyun.mvpretrofitrx.mvp.weight;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.ms.banner.holder.BannerViewHolder;
//import com.senon.mvpretrofitrx.R;
//
//public class CustomViewHolder2 implements BannerViewHolder<String> {
//
//
//    private ImageView imageView;
//
//    @Override
//    public View createView(Context context) {
//        View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
//        imageView = (ImageView) view.findViewById(R.id.iv);
//
//        return view;
//    }
//
//    @Override
//    public void onBind(Context context, int i, String s) {
//        Glide.with(context).load(s).into(imageView);
//    }
//
//}
