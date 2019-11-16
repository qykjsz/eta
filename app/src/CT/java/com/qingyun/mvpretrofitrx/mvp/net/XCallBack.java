package com.qingyun.mvpretrofitrx.mvp.net;


import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.ZLog;

import org.xutils.common.Callback.CommonCallback;
import org.xutils.common.util.LogUtil;
import org.xutils.ex.HttpException;


/**
 * 自定义x3回调的类
 * @author Sam
 * @date:2016-2-29上午10:19:03
 */
public abstract class XCallBack implements CommonCallback<String> {
    private static final String RESULT_SUCCESS = "success";
    private static final String RESULT_ERRMSG = "errMsg";
//    private static boolean isDebug = true;
//    private static boolean isDebug = BuildConfig.APIS;

    private static String TAG = "XCallBack";
    private static Toast toast;
    @Override
    public void onSuccess(String result) {
        ZLog.showPost("post=="+result);
        JSONObject object = JSON.parseObject(result);
        int code = object.getIntValue("code");
        String msg = object.getString("msg");
        if(code == 200){
            onAfterSuccessOk(object);
        }else{
            ToastUtil.showLongToast(msg);
            onAfterSuccessErr(object,msg);
        }

    }

    @Override
    public void onCancelled(CancelledException cex) {
        LogUtil.e(cex.toString()+"取消");
    }



    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        if (ex instanceof HttpException) { // 网络错误
            HttpException httpEx = (HttpException) ex;
            int responseCode = httpEx.getCode();
            String responseMsg = httpEx.getMessage();
            String errorResult = httpEx.getResult();
            ZLog.showPost("post=="+ex.toString()+"网络错误"+responseMsg);
            ToastUtil.showLongToast(responseCode);
        } else { // 其他错误
            ZLog.showPost("post=="+ex.toString()+"失败"+isOnCallback);
        }

    }

    @Override
    public void onFinished() {
        onAfterFinished();
    }

    /**
     * 原接口finished之后调用
     *
     * @author Sam
     * @date:2016-3-16下午2:33:44
     * @description
     */
    public abstract void onAfterFinished();

    /**
     * 原接口onSuccess中处理success
     *
     * @param object
     * @author Sam
     * @date:2016-3-16下午2:34:14
     * @description
     */
    public abstract void onAfterSuccessOk(JSONObject object);

    public abstract void onAfterSuccessErr(JSONObject object,String msg );
}
