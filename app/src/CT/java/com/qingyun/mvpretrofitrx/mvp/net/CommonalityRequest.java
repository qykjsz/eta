package com.qingyun.mvpretrofitrx.mvp.net;

import android.text.TextUtils;
import android.widget.TextView;


import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 * Date 2019/4/10.
 * Created by Sam
 * ClassExplain：共用的请求
 */
public class CommonalityRequest {

    public returnInfo returnInfo;

    //类型返回的接口
    public interface returnInfo {
        void getInfo(String msg);
    }

    //类型返回的接口
    public interface returnVideoInfo {
        void getVideoInfo(String videoUrl, String name, String reply_num, int isLike);
    }
}
