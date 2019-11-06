package com.lzj.gallery.library.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzj.gallery.library.R;
import com.lzj.gallery.library.transformer.CornerTransform;

import java.util.List;

/**
 * Created by Administrator on 2018/11/28.
 * banner的适配器
 */

public class BannerFragmentPagerAdapter extends PagerAdapter {
    private List<Fragment> mList;
    private Context mContext;
    private int defaultImg=R.mipmap.ic_banner_error;//默认图片
    private int mRoundCorners=-1;

    public void setDefaultImg(int defaultImg) {
        this.defaultImg = defaultImg;
    }

    public void setmRoundCorners(int mRoundCorners) {
        this.mRoundCorners = mRoundCorners;
    }

    public BannerFragmentPagerAdapter(List<Fragment> list, Context context){
        this.mList = list;
        this.mContext = context;
    }
    @Override
    public int getCount() {
        return 500000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.banner_img_layout,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.img);

        int index=position % mList.size();
//        LoadImage(mList.get(index),imageView);

        container.addView(view);
        return view;
    }

    /**
     * 加载图片
     */
    public  void LoadImage(String url, ImageView imageview) {
        if(mRoundCorners==-1){
            Glide.with(mContext)
                    .load(url)
                    .centerCrop()
                    .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
                    .placeholder(defaultImg)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageview);
        }
        else {
            Glide.with(mContext)
                    .load(url)
                    .centerCrop()
                    .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
                    .placeholder(defaultImg)
                    .transform(new CornerTransform(mContext, mRoundCorners))
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageview);
        }
    }


}