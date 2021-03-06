package com.qingyun.mvpretrofitrx.mvp.api;

import android.util.Log;

import com.qingyun.mvpretrofitrx.mvp.base.BaseApi;
import com.qingyun.mvpretrofitrx.mvp.utils.ApplicationUtil;

import io.reactivex.Observable;


public class Api {

//    private String baseUrl = "https://application.us.zone/api/";
    private static String baseUrl = "http://13.250.27.45:8081/api/";
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
        if (ApplicationUtil.isApkInDebug(ApplicationUtil.getContext()))
        {
            baseUrl = "http://13.250.27.45:8081/api/";
            Log.e("---------------","debug");
        }else {
            Log.e("---------------","release");

            baseUrl = "https://application.us.zone/api/";

        }
        BaseApi baseApi = new BaseApi();
        apiService = baseApi.getRetrofit(baseUrl).create(ApiService.class);
    }

    public static void UpdataToken() {
        synchronized (Api.class) {
                new Api();
        }
    }


}
