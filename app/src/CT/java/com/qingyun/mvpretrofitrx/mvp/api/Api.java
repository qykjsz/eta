package com.qingyun.mvpretrofitrx.mvp.api;

import android.text.TextUtils;
import android.util.Log;

import com.qingyun.mvpretrofitrx.mvp.base.BaseApi;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;

import io.reactivex.Observable;


public class Api {

    //    private String baseUrl = "https://application.us.zone/api/";
    private static String baseUrl = "https://et2.etac.io/api/";
    private volatile static ApiService apiService;

    public static ApiService getApiService() {

        if (apiService == null) {
            synchronized (Api.class) {
                if (apiService == null) {
                    new Api();
                }
            }
        }
        return apiService;
    }

    private Api() {
//        if (ApplicationUtil.isApkInDebug(ApplicationUtil.getContext())) {
//            baseUrl = "https://et2.etac.io/api/";
//            Log.e("---------------", "debug");
//        } else {
//            Log.e("---------------", "release");

            baseUrl = "https://etoken.etac.io/api/";

//        }
        BaseApi baseApi = new BaseApi();
        apiService = baseApi.getRetrofit(baseUrl).create(ApiService.class);
    }

    private Api(String baseUrl) {
        this.baseUrl = baseUrl;
        BaseApi baseApi = new BaseApi();
        apiService = baseApi.getRetrofit(baseUrl).create(ApiService.class);
    }

    public static void UpdataToken() {
        synchronized (Api.class) {
            new Api();
        }
    }

    public static void UpdataBaseUrl(String baseUrl) {
        if (TextUtils.isEmpty(baseUrl)){
            synchronized (Api.class) {
                new Api();
            }
        }else {
            synchronized (Api.class) {
                new Api(baseUrl);
            }
        }
    }
    public static String returnEtUrl(){
        return baseUrl;
    }

}
