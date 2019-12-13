package com.qingyun.mvpretrofitrx.mvp.progress;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import com.google.gson.JsonSyntaxException;
import com.qingyun.mvpretrofitrx.mvp.base.BaseResponse;
import com.qingyun.mvpretrofitrx.mvp.entity.NormalResponse;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;
import com.qingyun.mvpretrofitrx.mvp.utils.ExceptionHandle;
import com.qingyun.mvpretrofitrx.mvp.utils.SpUtils;
import com.qingyun.mvpretrofitrx.mvp.utils.ToastUtil;
import com.senon.mvpretrofitrx.R;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * 观察者
 */
public class ProgressObserver<T> implements Observer<BaseResponse<T>>, ProgressCancelListener {
    private static final String TAG = "ProgressObserver____ ";
    private ObserverResponseListener listener;
    private ProgressDialogHandler mProgressDialogHandler;
    private Context context;
    private Disposable d;

    public ProgressObserver(Context context, ObserverResponseListener listener, boolean isDialog, boolean cancelable) {
        this.listener = listener;
        this.context = context;
        if (isDialog) {
            mProgressDialogHandler = new ProgressDialogHandler(context, this, cancelable);
        }
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
        Log.e(TAG, "onSubscribe: ");
        showProgressDialog();
    }

    @Override
    public void onNext(BaseResponse<T> tBaseResponse) {
        dismissProgressDialog();
        if (tBaseResponse.getCode() == 200||tBaseResponse.getCode()==1) {
            listener.onNext(tBaseResponse.getData());//可定制接口，通过code回调处理不同的业务
        } else {
            if (!TextUtils.isEmpty(tBaseResponse.getMsg())) {
                ToastUtil.showShortToast(tBaseResponse.getMsg());
                listener.onError(tBaseResponse.getMsg());

            }
        }


    }

//    @Override
//    public void onNext(Object o) {
//        dismissProgressDialog();
//        listener.onNext(o);//可定制接口，通过code回调处理不同的业务
//    }

//    @Override
//    public void onNext(BaseResponse<T> t) {
//        int  code =t.getCode();
//        if (code==1){
//            listener.onNext(t.getData());//可定制接口，通过code回调处理不同的业务
//        }else if (code == 2){
//            ToastUtil.showShortToast(t.getMsg());
//            ApplicationUtil.exitApp();
//            Intent intent = new Intent(ApplicationUtil.getContext(),LoginActivity.class);
//            ApplicationUtil.getContext().startActivity(intent);
//        }
//        else {
//            listener.onError(t.getMsg());
//            ToastUtil.showShortToast(t.getMsg());
//        }
//    }

    @Override

    public void onError(Throwable e) {
        dismissProgressDialog();
//        if (e instanceof JsonSyntaxException){
//            ((JsonSyntaxException)e).
//            String s = body.string();
//            Gson gson = new Gson();
//            BaseResponse bad = gson.fromJson(s, BaseResponse.class);
//            ToastUtil.showShortToast(bad.getMsg());
//        }

        if (e instanceof com.jakewharton.retrofit2.adapter.rxjava2.HttpException) {
            ResponseBody body = ((com.jakewharton.retrofit2.adapter.rxjava2.HttpException) e).response().errorBody();
            //token失效
            if (((com.jakewharton.retrofit2.adapter.rxjava2.HttpException) e).code() == 401) {

                ApplicationUtil.exitApp();
                return;
            }
            try {
                String s = body.string();
                Gson gson = new Gson();
                BaseResponse bad = gson.fromJson(s, BaseResponse.class);
                ToastUtil.showShortToast(bad.getMsg());


            } catch (IOException IOe) {
                IOe.printStackTrace();
            }catch (Exception e1){
                ToastUtil.showShortToast("服务器错误");
            }
        }

        Log.e(TAG, "onError: ", e);
        //自定义异常处理
        if (e instanceof ExceptionHandle.ResponeThrowable) {
            listener.onError(e.getMessage());
        } else {
            listener.onError(e.getMessage());
        }

        if (e instanceof UnknownHostException) {
            ToastUtil.showLongToast("请打开网络");
        } else if (e instanceof SocketTimeoutException) {
            ToastUtil.showLongToast("请求超时");
        } else if (e instanceof ConnectException) {
            ToastUtil.showLongToast("连接失败");
        } else if (e instanceof HttpException) {
            ToastUtil.showLongToast("请求超时");
        }
        else {
            ToastUtil.showLongToast("请求失败");
        }
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
        Log.e(TAG, "onComplete: ");
    }

    @Override
    public void onCancelProgress() {
        Log.e(TAG, "requestCancel: ");
        //如果处于订阅状态，则取消订阅
        if (!d.isDisposed()) {
            d.dispose();
        }
    }
}
