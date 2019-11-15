package com.qingyun.mvpretrofitrx.mvp.net;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.xutils.http.RequestParams;

/**
 * 请求参数工具类
 *
 * @author Sam
 * @date:2014-11-21上午9:42:12
 * 此类可提供超时时间，数组和对象的传递，可查看OS零售项目
 */
public class HttpParamsUtils {

    /**dd
     * xUtils3的RequestParams
     *
     * @author xl
     * @date:2014-2-17上午10:32:47
     * @description 所有的参数都走该方法
     * @param url
     * @return
     */
    public static RequestParams getX3Params(String url) {
        RequestParams params = new RequestParams(url);
        //设置超时时间
        params.setConnectTimeout(25000);// 全局改为25秒超时
        //添加请求头
        return params;
    }

    /**
     * x3RequestParams json格式
     *
     * @author Sam
     * @date:2014-2-18下午5:05:58
     * @description
     * @param url
     * @param object
     * @return
     */
    public static RequestParams getX3Params(String url, JSONObject object) {
        RequestParams params = getX3Params(url);
        params.setBodyContent(object.toJSONString());
        params.addHeader("Content-Type", "application/json");
        return params;
    }

    /*
     * 传json组
     */
    public static RequestParams getX3Params(String url, JSONArray jsonArray) {
        RequestParams params = getX3Params(url);
        params.addHeader("Content-Type", "application/json");
        params.setBodyContent(jsonArray.toJSONString());
        return params;
    }

    public static RequestParams getX3Params(String url,
                                            String jsonString) {
        RequestParams params = getX3Params(url);
        params.addHeader("Content-Type", "application/json");
        params.setBodyContent(jsonString);
        return params;
    }

    /**
     * 带Accrpt
     *
     * @param url
     * @param jsonString
     * @return
     */
    public static RequestParams getX3ParamsAccept(String url,
                                                  String jsonString) {
        RequestParams params = getX3Params(url);
        params.addHeader("Content-Type", "application/json");
        params.addHeader("Accept", "application/json");
        params.setBodyContent(jsonString);
        return params;
    }


    /**
     * @author Sam
     * @date:2014-10-25下午1:43:25
     * @description
     * @param content
     * @return
     */
    public static RequestParams getYouDaoTranslateParams(String content) {
        RequestParams x3Params = getX3Params("http://fanyi.youdao.com/openapi.do");
        x3Params.addQueryStringParameter("keyfrom", "zhubaoyi");
        x3Params.addQueryStringParameter("key", "871285639");
        x3Params.addQueryStringParameter("type", "data");
        x3Params.addQueryStringParameter("doctype", "json");
        x3Params.addQueryStringParameter("version", "1.1");
        x3Params.addQueryStringParameter("q", content);
        return x3Params;
    }



}
