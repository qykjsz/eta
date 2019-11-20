package com.qingyun.mvpretrofitrx.mvp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.qingyun.mvpretrofitrx.mvp.activity.WebActivity;
import com.qingyun.mvpretrofitrx.mvp.api.Api;
import com.qingyun.mvpretrofitrx.mvp.base.FatherAdapter;
import com.qingyun.mvpretrofitrx.mvp.base.SuperViewHolder;
import com.qingyun.mvpretrofitrx.mvp.entity.DApp;
import com.qingyun.mvpretrofitrx.mvp.net.HttpParamsUtils;
import com.qingyun.mvpretrofitrx.mvp.net.XCallBack;
import com.senon.mvpretrofitrx.R;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Method;

/**
 * Date 2019/11/18.
 * Created by Sam
 * ClassExplain：
 */
public class FindGridViewAdapter extends FatherAdapter<DApp>{

    public FindGridViewAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_dapp, parent, false);
            new ViewHolder(convertView);
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        final DApp item = getItem(position);
        setUi(viewHolder, item);
        return convertView;
    }

    private void setUi(final ViewHolder viewHolder, final DApp item) {
        viewHolder.tv_name.setText(item.name);
        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.mipmap.app_icon);
        requestOptions.circleCropTransform();
        requestOptions.transforms( new RoundedCorners(20));
        Glide.with(mContext).load(item.img).apply(requestOptions).into(viewHolder.iv_image);
    }

    public class ViewHolder extends SuperViewHolder {
        TextView tv_name;//名称
        ImageView iv_image;//图片

        public ViewHolder(View view) {
            super(view);
            iv_image = view.findViewById(R.id.iv_image);
            tv_name = view.findViewById(R.id.tv_name);
        }
    }
}
